package vn.edu.epu.quanlyhoso.dto.request;

import jakarta.validation.constraints.NotNull;
import vn.edu.epu.quanlyhoso.enums.ProjectStatus;

public class UpdateProjectStatusRequest {

    @NotNull
    private ProjectStatus status;

    private String reason;

    public UpdateProjectStatusRequest() {
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
