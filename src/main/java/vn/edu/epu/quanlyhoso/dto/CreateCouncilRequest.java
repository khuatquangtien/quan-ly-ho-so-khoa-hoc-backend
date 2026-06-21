package vn.edu.epu.quanlyhoso.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CreateCouncilRequest {

    @NotBlank(message = "Council name is required")
    private String name;

    @Valid
    @NotEmpty(message = "Council must have members")
    private List<CouncilMemberRequest> members;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<CouncilMemberRequest> getMembers() { return members; }
    public void setMembers(List<CouncilMemberRequest> members) { this.members = members; }
}
