package com.indium.LeaveManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {

    @Id
    private int id;
    private String roleName;
    private String Status;


}
