package vn.edu.epu.quanlyhoso.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.epu.quanlyhoso.dto.request.SubmitProjectSubmissionRequest;
import vn.edu.epu.quanlyhoso.dto.response.ProjectSubmissionResponse;
import vn.edu.epu.quanlyhoso.entity.Project;
import vn.edu.epu.quanlyhoso.entity.ProjectSubmission;
import vn.edu.epu.quanlyhoso.enums.ProjectStatus;
import vn.edu.epu.quanlyhoso.exception.DuplicateSubmissionException;
import vn.edu.epu.quanlyhoso.exception.InvalidProjectStatusException;
import vn.edu.epu.quanlyhoso.exception.ResourceNotFoundException;
import vn.edu.epu.quanlyhoso.mapper.ProjectSubmissionMapper;
import vn.edu.epu.quanlyhoso.repository.ProjectRepository;
import vn.edu.epu.quanlyhoso.repository.ProjectSubmissionRepository;
import vn.edu.epu.quanlyhoso.search.ProjectSearchIndexService;
import vn.edu.epu.quanlyhoso.service.ProjectSubmissionService;

@Service
public class ProjectSubmissionServiceImpl implements ProjectSubmissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSubmissionServiceImpl.class);

    private final ProjectRepository projectRepository;
    private final ProjectSubmissionRepository projectSubmissionRepository;
    private final ProjectSubmissionMapper projectSubmissionMapper;
    private final ProjectSearchIndexService projectSearchIndexService;

    public ProjectSubmissionServiceImpl(
            ProjectRepository projectRepository,
            ProjectSubmissionRepository projectSubmissionRepository,
            ProjectSubmissionMapper projectSubmissionMapper,
            ProjectSearchIndexService projectSearchIndexService) {
        this.projectRepository = projectRepository;
        this.projectSubmissionRepository = projectSubmissionRepository;
        this.projectSubmissionMapper = projectSubmissionMapper;
        this.projectSearchIndexService = projectSearchIndexService;
    }

    @Override
    @Transactional
    public ProjectSubmissionResponse submitFinalSubmission(
            Integer projectId,
            SubmitProjectSubmissionRequest request) {
        // TODO: Verify the authenticated lecturer can submit final acceptance for this project after JWT module is integrated.
        Project project = findProjectById(projectId);
        if (project.getStatus() != ProjectStatus.ONGOING) {
            throw new InvalidProjectStatusException("Final submissions can only be submitted for ONGOING projects");
        }
        // A project has only one final acceptance submission; the table also enforces this with a unique project_id.
        if (projectSubmissionRepository.existsByProjectId(projectId)) {
            throw new DuplicateSubmissionException("Final submission already exists for project id: " + projectId);
        }

        ProjectSubmission submission = new ProjectSubmission();
        submission.setProject(project);
        submission.setSummaryReport(request.getSummaryReport());
        ProjectSubmission savedSubmission = projectSubmissionRepository.save(submission);

        // Final acceptance is the only lecturer flow that completes an ONGOING project.
        project.setStatus(ProjectStatus.COMPLETED);
        project.setSubmission(savedSubmission);
        projectRepository.save(project);
        syncProjectIndex(project);

        return projectSubmissionMapper.toResponse(savedSubmission);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectSubmissionResponse getSubmissionByProject(Integer projectId) {
        // TODO: Apply lecturer/admin visibility rules after Security module is merged.
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found with id: " + projectId);
        }
        ProjectSubmission submission = projectSubmissionRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Final submission not found for project id: " + projectId));
        return projectSubmissionMapper.toResponse(submission);
    }

    private Project findProjectById(Integer projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
    }

    private void syncProjectIndex(Project project) {
        try {
            projectSearchIndexService.indexProject(project);
        } catch (RuntimeException exception) {
            LOGGER.warn("Failed to synchronize project search index for project id: {}", project.getId(), exception);
        }
    }
}
