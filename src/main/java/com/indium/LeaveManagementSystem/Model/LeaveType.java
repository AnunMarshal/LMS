package com.indium.LeaveManagementSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LeaveType {
    @Id
    private int id;
    private String type;
    private Date createdAt;
    private Date UpdatedAt;
    private String createdBy;
    private String updatedBy;
    private String Status;


    public void setCreatedAt(Long createdAt) {
        Date d = new Date(createdAt);
        this.createdAt = d;
    }
    public void setUpdatedAt(Long updatedAt) {
        Date d = new Date(updatedAt);
        this.UpdatedAt = d;
    }

}