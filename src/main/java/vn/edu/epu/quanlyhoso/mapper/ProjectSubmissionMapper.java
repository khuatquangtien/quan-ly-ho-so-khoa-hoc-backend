package vn.edu.epu.quanlyhoso.mapper;

import org.springframework.stereotype.Component;
import vn.edu.epu.quanlyhoso.dto.response.ProjectSubmissionResponse;
import vn.edu.epu.quanlyhoso.entity.ProjectSubmission;

@Component
public class ProjectSubmissionMapper {

    public ProjectSubmissionResponse toResponse(ProjectSubmission submission) {
        ProjectSubmissionResponse response = new ProjectSubmissionResponse();
        response.setId(submission.getId());
        response.setProjectId(submission.getProject().getId());
        response.setSummaryReport(submission.getSummaryReport());
        response.setSubmittedAt(submission.getSubmittedAt());
        return response;
    }
}
