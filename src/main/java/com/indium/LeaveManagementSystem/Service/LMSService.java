package com.indium.LeaveManagementSystem.Service;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsRequest;
import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsResponse;
import com.indium.LeaveManagementSystem.Model.EmployeeDetails;
import com.indium.LeaveManagementSystem.Repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

@Service
public class LMSService {
    private static final Logger logger = LoggerFactory.getLogger(LMSService.class.getName());
    @Autowired
    private EmployeeDetailsRepository repository;


    public EmployeeDetailsResponse createEmployeeDetails(EmployeeDetailsRequest employeeDetailsRequest) {
        EmployeeDetailsResponse response = new EmployeeDetailsResponse();
        EmployeeDetails employeeDetails=new EmployeeDetails();

       if(employeeDetailsRequest != null) {
           employeeDetails.setName(employeeDetailsRequest.getName());
           employeeDetails.setRoleId(employeeDetailsRequest.getRole());
           employeeDetails.setAddress(employeeDetailsRequest.getAddress());
           employeeDetails.setPhone(employeeDetailsRequest.getPhone());
           employeeDetails.setEmail(employeeDetailsRequest.getEmail());
           employeeDetails.setCreatedAt(System.currentTimeMillis());
           repository.save(employeeDetails);
           logger.info("Employee Details " + employeeDetails);
           logger.info("Employee Details " + employeeDetails.getEmpId());
           response.setStatus("Successfully Added");

       }else{
           response.setStatus("Data is Null");
       }
        return response;
    }

    public EmployeeDetailsResponse getEmployeeDetailsByID(int id) throws IOException {
        EmployeeDetailsResponse response= new EmployeeDetailsResponse();

       Optional<EmployeeDetails> result= repository.findById(id);
       logger.info("***-->"+result.get().toString());

        if(result.get() != null) {
            response.setName(result.get().getName());
            response.setRole(result.get().getRoleId());
            response.setAddress(result.get().getAddress());
            response.setPhone(result.get().getPhone());
            response.setEmail(result.get().getEmail());
            response.setCreatedAt(System.currentTimeMillis());

        }else{
            response.setStatus("Data is Null");
        }
        return response;
    }

}
