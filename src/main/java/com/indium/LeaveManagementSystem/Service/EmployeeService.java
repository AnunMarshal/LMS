package com.indium.LeaveManagementSystem.Service;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.Repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class.getName());
    @Autowired
    private EmployeeDetailsRepository repository;


    public EmployeeDetailsDto createEmployeeDetails(EmployeeDetailsDto employeeDetailsRequest) {
        EmployeeDetailsDto response = new EmployeeDetailsDto();
        com.indium.LeaveManagementSystem.Model.EmployeeDetails employeeDetails=new com.indium.LeaveManagementSystem.Model.EmployeeDetails();

       if(employeeDetailsRequest != null) {

           employeeDetails.setName(employeeDetailsRequest.getName());
           //employeeDetails.setRoleId(employeeDetailsRequest.getRole());
           employeeDetails.setAddress(employeeDetailsRequest.getAddress());
           employeeDetails.setPhone(employeeDetailsRequest.getPhone());
           employeeDetails.setEmail(employeeDetailsRequest.getEmail());
           employeeDetails.setCreatedAt(System.currentTimeMillis());
           employeeDetails.setStatus("Active");

           repository.save(employeeDetails);

           response.setStatus("Successfully Added");

       }else{
           response.setStatus("Error while adding details");
       }
        return response;
    }

    public EmployeeDetailsDto getEmployeeDetailsByID(int id) throws IOException {

        EmployeeDetailsDto response= new EmployeeDetailsDto();
        Optional<com.indium.LeaveManagementSystem.Model.EmployeeDetails> result= repository.findById(id);
        logger.info("***-->"+result.get().toString());

        if(result.isPresent() && !result.get().getStatus().equals("Deleted")) {
            response.setEmpId(result.get().getEmpId());
            response.setName(result.get().getName());
            //response.setRole(result.get().getRoleId());
            response.setAddress(result.get().getAddress());
            response.setPhone(result.get().getPhone());
            response.setEmail(result.get().getEmail());

            response.setStatus(result.get().getStatus());

        }else{
            response.setStatus("Error while getting details");
        }
        return response;
    }

    public EmployeeDetailsDto updateEmployeeDetails(EmployeeDetailsDto employeeDetailsRequest){

        EmployeeDetailsDto response = new EmployeeDetailsDto();
        com.indium.LeaveManagementSystem.Model.EmployeeDetails employeeDetails=new com.indium.LeaveManagementSystem.Model.EmployeeDetails();
        Optional<com.indium.LeaveManagementSystem.Model.EmployeeDetails> result= repository.findById(employeeDetailsRequest.getEmpId());

        if(result.isPresent() && !result.get().getStatus().equals("Deleted")) {

            employeeDetails.setEmpId(employeeDetailsRequest.getEmpId());
            employeeDetails.setName(employeeDetailsRequest.getName());
            //employeeDetails.setRoleId(employeeDetailsRequest.getRole());
            employeeDetails.setAddress(employeeDetailsRequest.getAddress());
            employeeDetails.setPhone(employeeDetailsRequest.getPhone());
            employeeDetails.setEmail(employeeDetailsRequest.getEmail());

            employeeDetails.setStatus("Active");

            repository.save(employeeDetails);

            response.setStatus("Successfully Updated");

        }else{
            response.setStatus("Error while updating");
        }
        return response;
    }

    public EmployeeDetailsDto deleteEmployeeDetails(int id){

        EmployeeDetailsDto response = new EmployeeDetailsDto();
        com.indium.LeaveManagementSystem.Model.EmployeeDetails employeeDetails=new com.indium.LeaveManagementSystem.Model.EmployeeDetails();
        Optional<com.indium.LeaveManagementSystem.Model.EmployeeDetails> result= repository.findById(id);

        if(result.isPresent() && !result.get().getStatus().equals("Deleted")) {
            //repository.deleteById(id);

            employeeDetails.setEmpId(result.get().getEmpId());
            employeeDetails.setName(result.get().getName());
            //employeeDetails.setRoleId(result.get().getRoleId());
            employeeDetails.setAddress(result.get().getAddress());
            employeeDetails.setPhone(result.get().getPhone());
            employeeDetails.setEmail(result.get().getEmail());
            employeeDetails.setCreatedAt(System.currentTimeMillis());
            employeeDetails.setStatus("Deleted");

            repository.save(employeeDetails);

            response.setStatus("employee detail deleted successfully ");
        }else{
            response.setStatus("Error while deleting");
        }

        return response;
    }

}