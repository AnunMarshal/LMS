package com.indium.LeaveManagementSystem.Controller;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsRequest;
import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsResponse;
import com.indium.LeaveManagementSystem.Model.EmployeeDetails;
import com.indium.LeaveManagementSystem.Service.LMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;


@RestController
public class LMSController {
    private static final Logger LOGGER= LoggerFactory.getLogger(LMSController.class.getName());
    @Autowired
    private LMSService service;

    @PostMapping("/addEmployeeDetails")
    public EmployeeDetailsResponse addEmplyeeDetails(@RequestBody EmployeeDetailsRequest employeeDetails){
        LOGGER.info("In method addEmplyeeDetails");
        return service.createEmployeeDetails(employeeDetails);
    }

    @GetMapping("/employeeDetails/{id}")
    public EmployeeDetailsResponse getEmployeeDetailsByID(@PathVariable int id) throws IOException {
        LOGGER.info("Calling get session by id.");

            return service.getEmployeeDetailsByID(id);

    }
}
