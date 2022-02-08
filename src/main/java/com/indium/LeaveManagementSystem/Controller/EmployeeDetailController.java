package com.indium.LeaveManagementSystem.Controller;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsRequest;
import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsResponse;
import com.indium.LeaveManagementSystem.Service.LMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class EmployeeDetailController {
    private static final Logger log= LoggerFactory.getLogger(EmployeeDetailController.class.getName());
    @Autowired
    private LMSService service;

    @PostMapping("/addEmployeeDetails")
    public EmployeeDetailsResponse addEmplyeeDetails(@RequestBody EmployeeDetailsRequest employeeDetails){
        log.info("In method addEmplyeeDetails");
        return service.createEmployeeDetails(employeeDetails);
    }

    @GetMapping("/employeeDetails/{id}")
    public EmployeeDetailsResponse getEmployeeDetailsByID(@PathVariable int id) throws IOException {

            return service.getEmployeeDetailsByID(id);

    }
}
