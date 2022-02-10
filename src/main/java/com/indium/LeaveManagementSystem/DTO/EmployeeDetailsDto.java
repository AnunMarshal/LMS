package com.indium.LeaveManagementSystem.DTO;

import com.indium.LeaveManagementSystem.Model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsDto {

    private int empId;
    private String name;
    private RolesDto rolesDto;
    private String address;
    private String phone;
    private String email;
    private String status;
    private Date createdAt;


    public void setCreatedAt(Long createdAt) {
        Date d = new Date(createdAt);
        this.createdAt = d;
    }

}
