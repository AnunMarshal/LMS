package com.indium.LeaveManagementSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeLeaveDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int empId;
    private int ManagerId;
    private String LeaveType;
    private String FromDate;
    private String ToDate;
    private int NoofDays;
    private String reason;
    private Date createdAt;
    private Date UpdatedAt;
    private String Status;
    private String createdBy;
    private String updatedBy;

    public void setCreatedAt(Long createdAt) {
        Date d = new Date(createdAt);
        this.createdAt = d;
    }

    public void setUpdatedAt(Long updatedAt) {
        Date d = new Date(updatedAt);
        this.UpdatedAt = d;
    }
}