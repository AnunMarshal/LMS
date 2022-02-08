package com.indium.LeaveManagementSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class EmployeeLeaveBalance {
    @Id

    private int empId;
    private int leaveTypeId;
    private int leaveAvailability;
    private Date createdAt;
    private Date UpdatedAt;
    private String createdBy;
    private String updatedBy;
    private String Status;

    public void setCreatedAt(Long createdAt) {
        Date date = new Date(createdAt);

        this.createdAt = date;
    }
    public void setUpdatedAt(Long updatedAt) {
        Date date = new Date(updatedAt);
        this.UpdatedAt = date;
    }

}
