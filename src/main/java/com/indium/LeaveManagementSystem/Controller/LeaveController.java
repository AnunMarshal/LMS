package com.indium.LeaveManagementSystem.Controller;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.DTO.EmployeeLeaveDetailRequest;
import com.indium.LeaveManagementSystem.DTO.EmployeeLeaveDetailResponse;
import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import com.indium.LeaveManagementSystem.Service.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class LeaveController {

    private static final Logger log= LoggerFactory.getLogger(LeaveController.class.getName());
    @Autowired
    LeaveService leaveTypeService;

    @PostMapping("/addLeaveType")
    public LeaveTypeDto addLeaveType(@RequestBody LeaveTypeDto leaveType){
        return leaveTypeService.createLeaveType(leaveType);
    }

    @GetMapping("/leaveType/{id}")
    public LeaveTypeDto getLeaveTypeByID(@PathVariable int id) throws IOException {
        return leaveTypeService.getLeaveTypeByID(id);
    }

    @PostMapping("/updateLeaveType")
    public LeaveTypeDto updateLeaveType(@RequestBody LeaveTypeDto leaveType){
        return leaveTypeService.updateLeaveType(leaveType);
    }

    @GetMapping("/deleteLeaveType/{id}")
    public LeaveTypeDto deleteLeaveType(@PathVariable int id){
        return leaveTypeService.deleteLeaveType(id);
    }



    @PostMapping("/addEmployeeLeaveDetail")
    public EmployeeLeaveDetailResponse addEmployeeLeaveDetail(@RequestBody EmployeeLeaveDetailRequest employeeLeaveDetailRequest) {
        log.info("Add the EmployeeLeavedetail");
        return leaveTypeService.createEmployeeLeaveDetail(employeeLeaveDetailRequest);
    }
    @GetMapping("/employeeLeaveDetail/{id}")
    public EmployeeLeaveDetailResponse getEmployeeLeaveDetailByID(@PathVariable int id) throws IOException {
        log.info("");

        return leaveTypeService.getEmployeeLeaveDetailByID(id);
    }

}