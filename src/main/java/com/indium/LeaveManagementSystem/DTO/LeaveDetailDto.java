package com.indium.LeaveManagementSystem.DTO;

import com.indium.LeaveManagementSystem.Model.EmployeeDetails;
import com.indium.LeaveManagementSystem.Model.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDetailDto {
    private int id;
    private  EmployeeDetailsDto employeeDetailsDto ;
    private EmployeeDetailsDto managerDto;
    private LeaveTypeDto leaveTypeDto;
    private String fromDate;
    private String toDate;
    private int noofDays;
    private String reason;
    private String status;
    private Date createdAt;


    public void setCreatedAt(Long createdAt) {
        Date d = new Date(createdAt);
        this.createdAt = d;
    }
}
