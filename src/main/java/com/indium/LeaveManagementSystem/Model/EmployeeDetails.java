package com.indium.LeaveManagementSystem.Model;

import com.indium.LeaveManagementSystem.DTO.RolesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empId;
    private String name;
    @OneToOne @JoinColumn(name="roles_id")
    private Roles roles;
    private String address;
    private String phone;
    private String email;
    private Date createdAt;
    private Date UpdatedAt;
    private String createdBy;
    private String updatedBy;
    private String Status;


    public void setCreatedAt(Long createdAt) {
        Date d = new Date(createdAt);
        this.createdAt = d;
    }

    public void setUpdatedAt(Long updatedAt) {
        Date d = new Date(updatedAt);
        this.UpdatedAt = d;
    }


    public void setRoles(RolesDto rolesDto) {
        Roles roles=new Roles();
        roles.setId(rolesDto.getId());
        this.roles = roles;
    }


}
