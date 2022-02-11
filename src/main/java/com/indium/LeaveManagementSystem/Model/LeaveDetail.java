package com.indium.LeaveManagementSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LeaveDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @JoinColumn(name = "employeeId")
    private EmployeeDetails employeeDetails;
    @OneToOne
    @JoinColumn(name = "managerId")
    private EmployeeDetails manager;
    @OneToOne
    @JoinColumn(name = "leaveTypeId")
    private LeaveType leaveType;
    private String fromDate;
    private String toDate;
    private int noofDays;
    private String reason;
    private Date createdAt;
    private Date updatedAt;
    private String Status;
    private String createdBy;
    private String updatedBy;

    public void setCreatedAt(Long createdAt) {
        Date d = new Date(createdAt);
        this.createdAt = d;
    }

    public void setUpdatedAt(Long updatedAt) {
        Date d = new Date(updatedAt);
        this.updatedAt = d;
    }
}