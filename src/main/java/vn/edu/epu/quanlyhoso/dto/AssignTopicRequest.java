package vn.edu.epu.quanlyhoso.dto;

import javax.validation.constraints.NotNull;

public class AssignTopicRequest {

    @NotNull(message = "Project id is required")
    private Long projectId;

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
}
