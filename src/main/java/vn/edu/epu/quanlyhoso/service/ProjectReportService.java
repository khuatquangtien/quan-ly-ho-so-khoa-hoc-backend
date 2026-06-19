package vn.edu.epu.quanlyhoso.service;

import java.util.List;
import vn.edu.epu.quanlyhoso.dto.request.SubmitProjectReportRequest;
import vn.edu.epu.quanlyhoso.dto.response.ProjectReportResponse;

public interface ProjectReportService {

    ProjectReportResponse submitReport(Integer projectId, SubmitProjectReportRequest request);

    List<ProjectReportResponse> getReportsByProject(Integer projectId);
}
