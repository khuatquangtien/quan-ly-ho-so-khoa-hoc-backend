package vn.edu.epu.quanlyhoso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "council_members")
@IdClass(CouncilMemberId.class)
public class CouncilMember {

    @Id
    @Column(name = "council_id")
    private Long councilId;

    @Id
    @Column(name = "lecturer_id")
    private Long lecturerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false, length = 30)
    private CouncilRole position;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "council_id", insertable = false, updatable = false)
    private Council council;

    public Long getCouncilId() { return councilId; }
    public void setCouncilId(Long councilId) { this.councilId = councilId; }
    public Long getLecturerId() { return lecturerId; }
    public void setLecturerId(Long lecturerId) { this.lecturerId = lecturerId; }
    public CouncilRole getPosition() { return position; }
    public void setPosition(CouncilRole position) { this.position = position; }
    public Council getCouncil() { return council; }
    public void setCouncil(Council council) { this.council = council; }
}
