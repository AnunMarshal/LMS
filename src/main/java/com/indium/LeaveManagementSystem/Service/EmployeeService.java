package com.indium.LeaveManagementSystem.Service;



import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.DTO.ManagerActionDto;
import com.indium.LeaveManagementSystem.DTO.RolesDto;
import com.indium.LeaveManagementSystem.DTO.EmployeeLeaveBalanceDTO;
import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import com.indium.LeaveManagementSystem.Model.EmployeeDetails;
import com.indium.LeaveManagementSystem.Model.EmployeeLeaveBalance;
import com.indium.LeaveManagementSystem.Model.LeaveDetail;
import com.indium.LeaveManagementSystem.Model.LeaveType;
import com.indium.LeaveManagementSystem.Repository.EmployeeDetailsRepository;
import com.indium.LeaveManagementSystem.Repository.EmployeeLeaveBalanceRepository;
import com.indium.LeaveManagementSystem.Repository.LeaveDetailRepository;
import com.indium.LeaveManagementSystem.utils.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class.getName());
    @Autowired
    private EmployeeDetailsRepository repository;
    @Autowired
    private EmployeeLeaveBalanceRepository elbRepository;
    @Autowired
    private LeaveDetailRepository leaveDetailRepository;


    public ResponseEntity<String> createEmployeeDetails(EmployeeDetailsDto employeeDetailsRequest) {

        EmployeeDetailsDto response = new EmployeeDetailsDto();
        EmployeeDetails employeeDetails = new EmployeeDetails();


        if (employeeDetailsRequest != null) {

            employeeDetails.setName(employeeDetailsRequest.getName());
            employeeDetails.setRoles(employeeDetailsRequest.getRolesDto());
            employeeDetails.setAddress(employeeDetailsRequest.getAddress());
            employeeDetails.setPhone(employeeDetailsRequest.getPhone());
            employeeDetails.setEmail(employeeDetailsRequest.getEmail());
            employeeDetails.setCreatedAt(System.currentTimeMillis());
            employeeDetails.setStatus("Active");

            repository.save(employeeDetails);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.EMPLOYEE_ADDED);

            return new ResponseEntity<String>(MessageConstants.EMPLOYEE_ADDED, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_ADD, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<EmployeeDetailsDto> getEmployeeDetailsByID(int id) throws IOException {

        EmployeeDetailsDto response = new EmployeeDetailsDto();
        Optional<EmployeeDetails> result = repository.findById(id);
        RolesDto rolesDto = new RolesDto();
        rolesDto.setId(result.get().getRoles().getId());
        rolesDto.setRoleName(result.get().getRoles().getRoleName());
        rolesDto.setStatus(result.get().getRoles().getStatus());

        if (result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {
            response.setEmpId(result.get().getEmpId());
            response.setName(result.get().getName());
            response.setRolesDto(rolesDto);
            response.setAddress(result.get().getAddress());
            response.setPhone(result.get().getPhone());
            response.setEmail(result.get().getEmail());
            response.setStatus(result.get().getStatus());
            response.setCreatedAt(result.get().getCreatedAt().getTime());

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.EMPLOYEE_BY_ID);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);

        } else {
            return new ResponseEntity(MessageConstants.BAD_REQUEST_GET, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<EmployeeDetailsDto>> getEmployeeDetails() {
        List<EmployeeDetailsDto> response = new ArrayList<>();

        List<EmployeeDetails> employeeDetailsList = repository.findAll().stream().filter(e ->
                !e.getStatus().equals(MessageConstants.DELETED)).collect(Collectors.toList());

        log.info("************7 " + employeeDetailsList.toString());

        for (EmployeeDetails employeeDetails : employeeDetailsList) {

            RolesDto rolesDto = new RolesDto();
            rolesDto.setId(employeeDetails.getRoles().getId());
            rolesDto.setRoleName(employeeDetails.getRoles().getRoleName());
            rolesDto.setStatus(employeeDetails.getRoles().getStatus());

            EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
            log.info("EmpId ******* " + employeeDetails.getEmpId());
            employeeDetailsDto.setEmpId(employeeDetails.getEmpId());
            employeeDetailsDto.setName(employeeDetails.getName());
            employeeDetailsDto.setRolesDto(rolesDto);
            employeeDetailsDto.setAddress(employeeDetails.getAddress());
            employeeDetailsDto.setPhone(employeeDetails.getPhone());
            employeeDetailsDto.setEmail(employeeDetails.getEmail());
            employeeDetailsDto.setStatus(employeeDetails.getStatus());
            employeeDetailsDto.setCreatedAt(employeeDetails.getCreatedAt().getTime());

            log.info("employeeDetailsDto ********* " + employeeDetailsDto.toString());
            response.add(employeeDetailsDto);

        }
        return ResponseEntity.ok(response);
    }


    public ResponseEntity<String> updateEmployeeDetails(EmployeeDetailsDto employeeDetailsDto) {

        EmployeeDetailsDto response = new EmployeeDetailsDto();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        Optional<EmployeeDetails> result = repository.findById(employeeDetailsDto.getEmpId());

        if (result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {

            employeeDetails.setEmpId(employeeDetailsDto.getEmpId());
            employeeDetails.setName(employeeDetailsDto.getName());
            employeeDetails.setRoles(employeeDetailsDto.getRolesDto());
            employeeDetails.setAddress(employeeDetailsDto.getAddress());
            employeeDetails.setPhone(employeeDetailsDto.getPhone());
            employeeDetails.setEmail(employeeDetailsDto.getEmail());
            employeeDetails.setUpdatedAt(System.currentTimeMillis());
            employeeDetails.setCreatedAt(result.get().getCreatedAt().getTime());
            employeeDetails.setStatus("Active");

            repository.save(employeeDetails);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.EMPLOYEE_UPDATED);

            return new ResponseEntity<String>(MessageConstants.EMPLOYEE_UPDATED, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_UPDATE, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteEmployeeDetails(int id) {

        EmployeeDetailsDto response = new EmployeeDetailsDto();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        Optional<EmployeeDetails> result = repository.findById(id);

        RolesDto rolesDto = new RolesDto();
        rolesDto.setId(result.get().getRoles().getId());
        rolesDto.setRoleName(result.get().getRoles().getRoleName());
        rolesDto.setStatus(result.get().getRoles().getStatus());

        if (result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {

            employeeDetails.setEmpId(result.get().getEmpId());
            employeeDetails.setName(result.get().getName());
            employeeDetails.setRoles(rolesDto);
            employeeDetails.setAddress(result.get().getAddress());
            employeeDetails.setPhone(result.get().getPhone());
            employeeDetails.setEmail(result.get().getEmail());
            employeeDetails.setCreatedAt(result.get().getCreatedAt().getTime());
            employeeDetails.setUpdatedAt(System.currentTimeMillis());
            employeeDetails.setStatus("Deleted");

            repository.save(employeeDetails);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.EMPLOYEE_DELETED);

            return new ResponseEntity<String>(MessageConstants.EMPLOYEE_DELETED, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_DELETE, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> createEmployeeLeaveBalance(EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO) {
        EmployeeLeaveBalanceDTO response = new EmployeeLeaveBalanceDTO();
        EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO1 = new EmployeeLeaveBalanceDTO();

        log.info("------------->" + employeeLeaveBalanceDTO.toString());
        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setEmpId(employeeLeaveBalanceDTO.getEmployeeDetailsDto().getEmpId());

        LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
        leaveTypeDto.setId(employeeLeaveBalanceDTO.getLeaveTypeDto().getId());

        EmployeeLeaveBalance employeeLeaveBalance = new EmployeeLeaveBalance();
        employeeLeaveBalance.setEmployeeDetails(employeeDetailsDto);
        employeeLeaveBalance.setLeaveType(leaveTypeDto);
        employeeLeaveBalance.setLeaveAvailability(employeeLeaveBalanceDTO.getLeaveAvailability());
        employeeLeaveBalance.setCreatedAt(System.currentTimeMillis());
        employeeLeaveBalance.setStatus("Active");

        elbRepository.save(employeeLeaveBalance);

        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", MessageConstants.EMPLOYEE_BY_ID);
        return new ResponseEntity<String>(MessageConstants.EMPLOYEE_ADDED, headers, HttpStatus.OK);
    }

    public ResponseEntity<List<EmployeeLeaveBalanceDTO>> getLeaveBalance() {

        List<EmployeeLeaveBalanceDTO> response = new ArrayList<EmployeeLeaveBalanceDTO>();
        List<EmployeeLeaveBalance> elb = elbRepository.findAll().
                stream().filter(e -> !e.getStatus().equals("Deleted")).collect(Collectors.toList());

        for (EmployeeLeaveBalance balance : elb) {
            EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
            employeeDetailsDto.setEmpId(balance.getEmployeeDetails().getEmpId());
            employeeDetailsDto.setName(balance.getEmployeeDetails().getName());
            employeeDetailsDto.setAddress(balance.getEmployeeDetails().getAddress());
            employeeDetailsDto.setEmail(balance.getEmployeeDetails().getEmail());
            employeeDetailsDto.setStatus(balance.getEmployeeDetails().getStatus());
            employeeDetailsDto.setPhone(balance.getEmployeeDetails().getPhone());
            employeeDetailsDto.setCreatedAt(balance.getEmployeeDetails().getCreatedAt().getTime());

            RolesDto rolesDto = new RolesDto();
            rolesDto.setId(balance.getEmployeeDetails().getRoles().getId());
            rolesDto.setRoleName(balance.getEmployeeDetails().getRoles().getRoleName());
            rolesDto.setStatus(balance.getEmployeeDetails().getRoles().getStatus());

            employeeDetailsDto.setRolesDto(rolesDto);

            LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
            leaveTypeDto.setId(balance.getLeaveType().getId());
            leaveTypeDto.setType(balance.getLeaveType().getType());
            leaveTypeDto.setStatus(balance.getLeaveType().getStatus());
            //leaveTypeDto.setCreatedAt(balance.getLeaveType().getCreatedAt().getTime());

            EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO = new EmployeeLeaveBalanceDTO();
            employeeLeaveBalanceDTO.setEmployeeDetailsDto(employeeDetailsDto);
            employeeLeaveBalanceDTO.setLeaveTypeDto(leaveTypeDto);
            employeeLeaveBalanceDTO.setLeaveAvailability(balance.getLeaveAvailability());
            employeeLeaveBalanceDTO.setCreatedAt(balance.getCreatedAt().getTime());
            employeeLeaveBalanceDTO.setStatus(balance.getStatus());

            response.add(employeeLeaveBalanceDTO);
        }
        return ResponseEntity.ok(response);
    }

    /*public Optional <EmployeeLeaveBalance> getLeaveBalanceById(int id) {
       return elbRepository.findById(id);
    }*/

    public ResponseEntity<List<EmployeeLeaveBalanceDTO>> getLeaveBalanceByEmp(int id) {

        List<EmployeeLeaveBalanceDTO> response=new ArrayList<>();

        Optional<EmployeeDetails> result = repository.findById(id);
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmpId(result.get().getEmpId());

        //log.info("****************"+employeeDetails.toString());

        List<EmployeeLeaveBalance> employeeLeaveBalanceList=elbRepository.findByEmployeeDetails(employeeDetails);

        for(EmployeeLeaveBalance employeeLeaveBalance:employeeLeaveBalanceList){
            EmployeeDetailsDto employeeDetailsDto=new EmployeeDetailsDto();
            employeeDetailsDto.setEmpId(result.get().getEmpId());

            RolesDto rolesDto=new RolesDto();
            rolesDto.setId(result.get().getRoles().getId());
            rolesDto.setRoleName(result.get().getRoles().getRoleName());
            rolesDto.setStatus(result.get().getRoles().getStatus());

            employeeDetailsDto.setRolesDto(rolesDto);
            employeeDetailsDto.setPhone(result.get().getPhone());
            employeeDetailsDto.setCreatedAt(result.get().getCreatedAt().getTime());
            employeeDetailsDto.setName(result.get().getName());
            employeeDetailsDto.setStatus(result.get().getStatus());
            employeeDetailsDto.setEmail(result.get().getEmail());

            LeaveTypeDto leaveTypeDto=new LeaveTypeDto();
            leaveTypeDto.setId(employeeLeaveBalance.getLeaveType().getId());
            leaveTypeDto.setType(employeeLeaveBalance.getLeaveType().getType());
            leaveTypeDto.setStatus(employeeLeaveBalance.getStatus());
            leaveTypeDto.setCreatedAt(employeeLeaveBalance.getCreatedAt().getTime());

            EmployeeLeaveBalanceDTO employeeLeaveBalanceDTO=new EmployeeLeaveBalanceDTO();

            employeeLeaveBalanceDTO.setEmployeeDetailsDto(employeeDetailsDto);
            employeeLeaveBalanceDTO.setLeaveAvailability(employeeLeaveBalance.getLeaveAvailability());
            employeeLeaveBalanceDTO.setLeaveTypeDto(leaveTypeDto);
            employeeLeaveBalanceDTO.setCreatedAt(employeeLeaveBalance.getCreatedAt().getTime());
            employeeLeaveBalanceDTO.setStatus(employeeLeaveBalance.getStatus());

            response.add(employeeLeaveBalanceDTO);

        }
        return ResponseEntity.ok(response);
        //return elbRepository.findByEmployeeDetails(employeeDetails);
    }

    public ResponseEntity<String> updateLeaveBalance(EmployeeLeaveBalanceDTO leaveBalanceDTO) {

        EmployeeLeaveBalanceDTO response = new EmployeeLeaveBalanceDTO();
        EmployeeLeaveBalance employeeLeaveBalance = new EmployeeLeaveBalance();
        Optional<EmployeeLeaveBalance> result = elbRepository.findById(leaveBalanceDTO.getId());

        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setEmpId(leaveBalanceDTO.getEmployeeDetailsDto().getEmpId());

        LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
        leaveTypeDto.setId(leaveBalanceDTO.getLeaveTypeDto().getId());

        if (result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {

            employeeLeaveBalance.setId(leaveBalanceDTO.getId());
            employeeLeaveBalance.setEmployeeDetails(employeeDetailsDto);
            employeeLeaveBalance.setLeaveAvailability(leaveBalanceDTO.getLeaveAvailability());
            employeeLeaveBalance.setLeaveType(leaveTypeDto);
            employeeLeaveBalance.setStatus(result.get().getStatus());
            employeeLeaveBalance.setCreatedAt(result.get().getCreatedAt().getTime());
            employeeLeaveBalance.setUpdatedAt(System.currentTimeMillis());

            elbRepository.save(employeeLeaveBalance);

            //response.setStatus("Successfully Updated");
            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.EMPLOYEE_UPDATED);

            return new ResponseEntity<String>(MessageConstants.EMPLOYEE_UPDATED, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_UPDATE, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteLeaveBalance(int id) {
        EmployeeLeaveBalanceDTO response = new EmployeeLeaveBalanceDTO();
        EmployeeLeaveBalance employeeLeaveBalance = new EmployeeLeaveBalance();
        Optional<EmployeeLeaveBalance> result = elbRepository.findById(id);

        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setEmpId(result.get().getEmployeeDetails().getEmpId());

        LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
        leaveTypeDto.setId(result.get().getLeaveType().getId());

        if (result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {

            employeeLeaveBalance.setId(result.get().getId());
            employeeLeaveBalance.setEmployeeDetails(employeeDetailsDto);
            employeeLeaveBalance.setLeaveAvailability(result.get().getLeaveAvailability());
            employeeLeaveBalance.setLeaveType(leaveTypeDto);
            employeeLeaveBalance.setStatus(MessageConstants.DELETED);
            employeeLeaveBalance.setCreatedAt(result.get().getCreatedAt().getTime());
            employeeLeaveBalance.setUpdatedAt(System.currentTimeMillis());

            elbRepository.save(employeeLeaveBalance);

            //response.setStatus("Successfully Deleted");
            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.EMPLOYEE_DELETED);

            return new ResponseEntity<String>(MessageConstants.EMPLOYEE_DELETED, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_DELETE, HttpStatus.BAD_REQUEST);
        }


    }

    public ResponseEntity<String> managerAction(ManagerActionDto managerActionDto) {

        String status=managerActionDto.getStatus();
        Optional<LeaveDetail> result1 = leaveDetailRepository.findById(managerActionDto.getLeaveDetailId());

        if (result1.isPresent() && result1.get().getStatus().equals(MessageConstants.PENDING)) {

            LeaveType leaveType = result1.get().getLeaveType();
            int noOfDays = result1.get().getNoofDays();
            EmployeeDetails empId = result1.get().getEmployeeDetails();

            if (status.equals(MessageConstants.APPROVED)) {

                HttpHeaders headers = new HttpHeaders();
                headers.add("desc", MessageConstants.LEAVE_APPROVED);

                LeaveDetail leaveDetail = new LeaveDetail();
                leaveDetail.setId(result1.get().getId());
                leaveDetail.setManager(result1.get().getManager());
                leaveDetail.setEmployeeDetails(result1.get().getEmployeeDetails());
                leaveDetail.setLeaveType(result1.get().getLeaveType());
                leaveDetail.setFromDate(result1.get().getFromDate());
                leaveDetail.setToDate(result1.get().getToDate());
                leaveDetail.setReason(result1.get().getReason());
                leaveDetail.setStatus(MessageConstants.APPROVED);
                leaveDetail.setNoofDays(result1.get().getNoofDays());
                //leaveDetail.setCreatedAt();
                //leaveDetailDto.setStatus("Approved");


                leaveDetailRepository.save(leaveDetail);

                List<EmployeeLeaveBalance> employeeLeaveBalanceList = elbRepository.findAll().
                        stream().filter(e -> e.getEmployeeDetails().equals(empId)).filter(e -> e.getLeaveType().equals(leaveType))
                        .collect(Collectors.toList());

                log.info("-------------> " + employeeLeaveBalanceList.toString());

                if (employeeLeaveBalanceList != null) {
                    for (EmployeeLeaveBalance employeeLeaveBalance1 : employeeLeaveBalanceList) {
                        EmployeeLeaveBalance employeeLeaveBalance = new EmployeeLeaveBalance();

                        //if(employeeLeaveBalance1.getLeaveAvailability()>=noOfDays) {

                        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
                        employeeDetailsDto.setEmpId(employeeLeaveBalance1.getEmployeeDetails().getEmpId());

                        LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
                        leaveTypeDto.setId(employeeLeaveBalance1.getLeaveType().getId());

                        employeeLeaveBalance.setId(employeeLeaveBalance1.getId());
                        employeeLeaveBalance.setEmployeeDetails(employeeDetailsDto);
                        employeeLeaveBalance.setLeaveType(leaveTypeDto);
                        employeeLeaveBalance.setLeaveAvailability(employeeLeaveBalance1.getLeaveAvailability() - noOfDays);
                        employeeLeaveBalance.setStatus(employeeLeaveBalance1.getStatus());
                        employeeLeaveBalance.setCreatedAt(employeeLeaveBalance1.getCreatedAt().getTime());
                        employeeLeaveBalance.setUpdatedAt(System.currentTimeMillis());


                        elbRepository.save(employeeLeaveBalance);
                        //}else{
                        //   return new ResponseEntity<String>(MessageConstants.No_LEAVE_BALANCE, HttpStatus.BAD_REQUEST);
                        //}

                    }
                }
                return new ResponseEntity<String>(MessageConstants.LEAVE_APPROVED, headers, HttpStatus.OK);

            } else {
                //response.setStatus("Leave Request Rejected");
                LeaveDetail leaveDetail = new LeaveDetail();
                leaveDetail.setId(result1.get().getId());
                leaveDetail.setManager(result1.get().getManager());
                leaveDetail.setEmployeeDetails(result1.get().getEmployeeDetails());
                leaveDetail.setLeaveType(result1.get().getLeaveType());
                leaveDetail.setFromDate(result1.get().getFromDate());
                leaveDetail.setToDate(result1.get().getToDate());
                leaveDetail.setReason(result1.get().getReason());
                leaveDetail.setStatus(MessageConstants.REJECTED);
                leaveDetail.setNoofDays(result1.get().getNoofDays());
                leaveDetailRepository.save(leaveDetail);
                return new ResponseEntity<String>(MessageConstants.LEAVE_REJECTED, HttpStatus.OK);
            }


            //return response;
        }
        return new ResponseEntity<String>(MessageConstants.INVALID_REQUEST, HttpStatus.BAD_REQUEST);


    }
}

