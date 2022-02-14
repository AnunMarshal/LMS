package com.indium.LeaveManagementSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerActionDto {
    private int leaveDetailId;
    private String status;

}
