package vn.edu.epu.quanlyhoso.dto.request;

import jakarta.validation.constraints.NotBlank;

public class SubmitProjectSubmissionRequest {

    @NotBlank
    private String summaryReport;

    public SubmitProjectSubmissionRequest() {
    }

    public String getSummaryReport() {
        return summaryReport;
    }

    public void setSummaryReport(String summaryReport) {
        this.summaryReport = summaryReport;
    }
}
