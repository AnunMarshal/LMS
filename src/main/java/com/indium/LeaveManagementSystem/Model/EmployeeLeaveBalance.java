package com.indium.LeaveManagementSystem.Model;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeLeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne @JoinColumn(name= "employee_id")
    private EmployeeDetails employeeDetails;
    private int leaveAvailability;
    private Date createdAt;
    private Date UpdatedAt;
    private String createdBy;
    private String updatedBy;
    private String Status;
    @OneToOne @JoinColumn(name="leaveType_id")
    private LeaveType leaveType;

    public void setCreatedAt(Long createdAt) {
        Date date = new Date(createdAt);

        this.createdAt = date;
    }
    public void setUpdatedAt(Long updatedAt) {
        Date date = new Date(updatedAt);
        this.UpdatedAt = date;
    }


    public void setEmployeeDetails(EmployeeDetailsDto employeeDetailsDto) {
        EmployeeDetails employeeDetails=new EmployeeDetails();
        employeeDetails.setEmpId(employeeDetailsDto.getEmpId());
        this.employeeDetails = employeeDetails;
    }

    public void setLeaveType(LeaveTypeDto leaveTypeDto) {
        LeaveType leaveType=new LeaveType();
        leaveType.setId(leaveTypeDto.getId());
        this.leaveType = leaveType;
    }
}
