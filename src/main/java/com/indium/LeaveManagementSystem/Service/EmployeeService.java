package com.indium.LeaveManagementSystem.Service;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.DTO.EmployeeLeaveBalanceDTO;
import com.indium.LeaveManagementSystem.Model.EmployeeDetails;
import com.indium.LeaveManagementSystem.Model.EmployeeLeaveBalance;
import com.indium.LeaveManagementSystem.Repository.EmployeeDetailsRepository;
import com.indium.LeaveManagementSystem.Repository.EmployeeLeaveBalanceRepository;
import com.indium.LeaveManagementSystem.utils.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class.getName());
    @Autowired
    private EmployeeDetailsRepository repository;
    @Autowired
    private EmployeeLeaveBalanceRepository elbRepository;


    public EmployeeDetailsDto createEmployeeDetails(EmployeeDetailsDto employeeDetailsRequest) {
        EmployeeDetailsDto response = new EmployeeDetailsDto();
        com.indium.LeaveManagementSystem.Model.EmployeeDetails employeeDetails=new com.indium.LeaveManagementSystem.Model.EmployeeDetails();

       if(employeeDetailsRequest != null) {

           employeeDetails.setName(employeeDetailsRequest.getName());
           employeeDetails.setRoles(employeeDetailsRequest.getRoles());
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
        Optional<EmployeeDetails> result= repository.findById(id);

        if(result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {
            response.setEmpId(result.get().getEmpId());
            response.setName(result.get().getName());
            response.setRoles(result.get().getRoles());
            response.setAddress(result.get().getAddress());
            response.setPhone(result.get().getPhone());
            response.setEmail(result.get().getEmail());
            response.setStatus(result.get().getStatus());
            response.setCreatedAt(result.get().getCreatedAt().getTime());

        }else{
            response.setStatus("Error while getting details");
        }
        return response;
    }

    public List<EmployeeDetailsDto> getEmployeeDetails(){
        List<EmployeeDetailsDto> response =new ArrayList<EmployeeDetailsDto>();

        List<EmployeeDetails>  employeeDetailsList=repository.findAll().stream().filter(e->
                !e.getStatus().equals(MessageConstants.DELETED)).collect(Collectors.toList());

        log.info("************7 "+employeeDetailsList.toString());


        for(EmployeeDetails employeeDetails:employeeDetailsList){

            EmployeeDetailsDto employeeDetailsDto=new EmployeeDetailsDto();
            log.info("EmpId ******* "+employeeDetails.getEmpId());
            employeeDetailsDto.setEmpId(employeeDetails.getEmpId());
            employeeDetailsDto.setName(employeeDetails.getName());
            employeeDetailsDto.setRoles(employeeDetails.getRoles());
            employeeDetailsDto.setAddress(employeeDetails.getAddress());
            employeeDetailsDto.setPhone(employeeDetails.getPhone());
            employeeDetailsDto.setEmail(employeeDetails.getEmail());
            employeeDetailsDto.setStatus(employeeDetails.getStatus());

            log.info("employeeDetailsDto ********* "+employeeDetailsDto.toString());
            response.add(employeeDetailsDto);

        }
        return response;
    }


    public EmployeeDetailsDto updateEmployeeDetails(EmployeeDetailsDto employeeDetailsDto){

        EmployeeDetailsDto response = new EmployeeDetailsDto();
        EmployeeDetails employeeDetails=new EmployeeDetails();
        Optional<EmployeeDetails> result= repository.findById(employeeDetailsDto.getEmpId());

        if(result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {

            employeeDetails.setEmpId(employeeDetailsDto.getEmpId());
            employeeDetails.setName(employeeDetailsDto.getName());
            employeeDetails.setRoles(employeeDetailsDto.getRoles());
            employeeDetails.setAddress(employeeDetailsDto.getAddress());
            employeeDetails.setPhone(employeeDetailsDto.getPhone());
            employeeDetails.setEmail(employeeDetailsDto.getEmail());
            employeeDetails.setUpdatedAt(System.currentTimeMillis());
            employeeDetails.setCreatedAt(result.get().getCreatedAt().getTime());
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
        EmployeeDetails employeeDetails=new EmployeeDetails();
        Optional<EmployeeDetails> result= repository.findById(id);

        if(result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {
            //repository.deleteById(id);

            employeeDetails.setEmpId(result.get().getEmpId());
            employeeDetails.setName(result.get().getName());
            employeeDetails.setRoles(result.get().getRoles());
            employeeDetails.setAddress(result.get().getAddress());
            employeeDetails.setPhone(result.get().getPhone());
            employeeDetails.setEmail(result.get().getEmail());
            employeeDetails.setCreatedAt(result.get().getCreatedAt().getTime());
            employeeDetails.setUpdatedAt(System.currentTimeMillis());
            employeeDetails.setStatus("Deleted");

            repository.save(employeeDetails);

            response.setStatus("employee detail deleted successfully ");
        }else{
            response.setStatus("Error while deleting");
        }

        return response;
    }
    public EmployeeLeaveBalanceDTO createEmployeeLeaveBalance(EmployeeLeaveBalance employeeLeaveBalance) {
        EmployeeLeaveBalanceDTO response = new EmployeeLeaveBalanceDTO();
        EmployeeLeaveBalance balance = new EmployeeLeaveBalance();
        balance.setEmpId(employeeLeaveBalance.getEmpId());
        balance.setLeaveTypeId(employeeLeaveBalance.getLeaveTypeId());
        balance.setLeaveAvailability(employeeLeaveBalance.getLeaveAvailability());
        balance.setCreatedAt(System.currentTimeMillis());
        elbRepository.save(balance);
        return  response;
    }
   public List<EmployeeLeaveBalanceDTO> getLeaveBalance(){
       List<EmployeeLeaveBalanceDTO> dto=new ArrayList<EmployeeLeaveBalanceDTO>();
       List<EmployeeLeaveBalance> elb=elbRepository.findAll().stream().filter(e-> !e.getStatus().equals("delete")).collect(Collectors.toList());

       for(EmployeeLeaveBalance balance:elb){
           EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO=new EmployeeLeaveBalanceDTO();
           employeeLeaveBalanceDTO.setEmpId(balance.getEmpId());
           employeeLeaveBalanceDTO.setLeaveTypeId(balance.getLeaveTypeId());
           employeeLeaveBalanceDTO.setLeaveAvailability(balance.getLeaveAvailability());
           employeeLeaveBalanceDTO.setCreatedAt(balance.getCreatedAt().getTime());
           dto.add(employeeLeaveBalanceDTO);


       }
       return dto;
   }
    /*public List<EmployeeLeaveBalance> getLeaveBalance() {
        List<EmployeeLeaveBalance> elb = new ArrayList<>();
        elbRepository.findAll().forEach(elb::add);
        return elb;
    }*/
    public EmployeeLeaveBalance getLeaveBalanceById(int id) {  //get based on id
        return elbRepository.findById(id).orElse(null);
    }
    public EmployeeLeaveBalance updateLeaveBalance(EmployeeLeaveBalance leaveBalance){
        elbRepository.save(leaveBalance);
        return leaveBalance;
    }
    public void deleteLeaveBalance(int id) {
        elbRepository.deleteById(id);
    }


}

