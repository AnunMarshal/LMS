package com.indium.LeaveManagementSystem.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
public class EmployeeLeaveBalanceDTO {
    private  int empId;
    private int leaveTypeId;
    private int leaveAvailability;
    private Date createdAt;
    private String Status;

    public void setCreatedAt(Long createdAt) {
        Date date = new Date(createdAt);
        this.createdAt = date;
    }


}
