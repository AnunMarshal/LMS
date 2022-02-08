package com.indium.LeaveManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveDetailResponse {
   // private int id;
    private int empId;
    private int ManagerId;
    private String LeaveType;
    private String FromDate;
    private String ToDate;
    private int NoofDays;
    private String reason;
    private String status;
    private Date createdAt;


    public void setCreatedAt(Long createdAt) {
        Date d = new Date(createdAt);
        this.createdAt = d;
    }
}
