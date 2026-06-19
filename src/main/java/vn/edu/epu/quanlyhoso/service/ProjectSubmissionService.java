package vn.edu.epu.quanlyhoso.service;

import vn.edu.epu.quanlyhoso.dto.request.SubmitProjectSubmissionRequest;
import vn.edu.epu.quanlyhoso.dto.response.ProjectSubmissionResponse;

public interface ProjectSubmissionService {

    ProjectSubmissionResponse submitFinalSubmission(Integer projectId, SubmitProjectSubmissionRequest request);

    ProjectSubmissionResponse getSubmissionByProject(Integer projectId);
}
