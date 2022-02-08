package com.indium.LeaveManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveDetailRequest {
   // private int id;
    private int empId;
    private int ManagerId;
    private String LeaveType;
    private String FromDate;
    private String ToDate;
    private int NoofDays;
    private String reason;
}
