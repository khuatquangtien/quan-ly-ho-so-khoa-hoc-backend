package vn.edu.epu.quanlyhoso.project.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.epu.quanlyhoso.common.exception.ResourceNotFoundException;
import vn.edu.epu.quanlyhoso.project.dto.request.SubmitProjectReportRequest;
import vn.edu.epu.quanlyhoso.project.dto.response.ProjectReportResponse;
import vn.edu.epu.quanlyhoso.project.entity.Project;
import vn.edu.epu.quanlyhoso.project.entity.ProjectReport;
import vn.edu.epu.quanlyhoso.project.enums.ProjectStatus;
import vn.edu.epu.quanlyhoso.project.exception.InvalidProjectStatusException;
import vn.edu.epu.quanlyhoso.project.mapper.ProjectReportMapper;
import vn.edu.epu.quanlyhoso.project.repository.ProjectReportRepository;
import vn.edu.epu.quanlyhoso.project.repository.ProjectRepository;

@Service
public class ProjectReportServiceImpl implements ProjectReportService {

    private final ProjectRepository projectRepository;
    private final ProjectReportRepository projectReportRepository;
    private final ProjectReportMapper projectReportMapper;

    public ProjectReportServiceImpl(
            ProjectRepository projectRepository,
            ProjectReportRepository projectReportRepository,
            ProjectReportMapper projectReportMapper) {
        this.projectRepository = projectRepository;
        this.projectReportRepository = projectReportRepository;
        this.projectReportMapper = projectReportMapper;
    }

    @Override
    @Transactional
    public ProjectReportResponse submitReport(Integer projectId, SubmitProjectReportRequest request) {
        // TODO: Verify the authenticated lecturer can submit reports for this project after JWT module is integrated.
        Project project = findProjectById(projectId);
        if (project.getStatus() != ProjectStatus.ONGOING) {
            throw new InvalidProjectStatusException("Progress reports can only be submitted for ONGOING projects");
        }

        ProjectReport report = new ProjectReport();
        report.setProject(project);
        report.setProgressPercentage(request.getProgressPercentage());
        report.setReportContent(request.getReportContent());

        return projectReportMapper.toResponse(projectReportRepository.save(report));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectReportResponse> getReportsByProject(Integer projectId) {
        // TODO: Apply lecturer/admin visibility rules after Security module is merged.
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found with id: " + projectId);
        }
        return projectReportRepository.findByProjectIdOrderBySubmittedAtDesc(projectId).stream()
                .map(projectReportMapper::toResponse)
                .toList();
    }

    private Project findProjectById(Integer projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
    }
}
