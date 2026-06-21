package vn.edu.epu.quanlyhoso.dto.request;

import lombok.Data;

@Data
public class ProfileRequest {
    private String fullName;
    private String academicRank;   
    private String academicDegree; 
    private String faculty;        
    private String researchDirection;
}
