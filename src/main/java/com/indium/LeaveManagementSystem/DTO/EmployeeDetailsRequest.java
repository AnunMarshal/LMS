package com.indium.LeaveManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsRequest {

    private String name;
    private String role;
    private String address;
    private String phone;
    private String email;
    //private String status;



}
