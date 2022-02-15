package com.indium.LeaveManagementSystem.Model;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import com.indium.LeaveManagementSystem.DTO.RolesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

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
     public void setEmployeeDetails(EmployeeDetailsDto employeeDetailsDto) {
        EmployeeDetails employeeDetails=new EmployeeDetails();
        employeeDetails.setEmpId(employeeDetailsDto.getEmpId());
        this.employeeDetails = employeeDetails;
    }
    public void setLeaveType(LeaveTypeDto leaveTypeDto) {
        LeaveType leaveType=new LeaveType();
        leaveType.setId(leaveTypeDto.getId());
        this.leaveType = leaveType;
    }



}