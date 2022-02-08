package com.indium.LeaveManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeDto {
    private int id;
    private String type;
    private String Status;
    private Date createdAt;

}
