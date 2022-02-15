package com.indium.LeaveManagementSystem.Service;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.DTO.LeaveDetailDto;
import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import com.indium.LeaveManagementSystem.DTO.RolesDto;
import com.indium.LeaveManagementSystem.Model.EmployeeDetails;
import com.indium.LeaveManagementSystem.Model.LeaveDetail;
import com.indium.LeaveManagementSystem.Model.LeaveType;
import com.indium.LeaveManagementSystem.Repository.EmployeeDetailsRepository;
import com.indium.LeaveManagementSystem.Repository.LeaveDetailRepository;
import com.indium.LeaveManagementSystem.Repository.LeaveTypeRepository;
import com.indium.LeaveManagementSystem.utils.MessageConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LeaveService {

    private static final Logger log = LoggerFactory.getLogger(LeaveService.class.getName());
    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private LeaveDetailRepository eldrepository;

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    public ResponseEntity<String> createLeaveType(LeaveTypeDto leaveTypeRequest) {

        LeaveType leaveType = new LeaveType();

        if (leaveTypeRequest != null) {

            leaveType.setId(leaveTypeRequest.getId());
            leaveType.setType(leaveTypeRequest.getType());
            leaveType.setCreatedAt(System.currentTimeMillis());
            leaveType.setStatus("Active");

            leaveTypeRepository.save(leaveType);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVETYPE_ADDED);

            return new ResponseEntity<String>(MessageConstants.LEAVETYPE_ADDED, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_ADD, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<LeaveTypeDto> getLeaveTypeByID(int id) {

        LeaveTypeDto response = new LeaveTypeDto();
        Optional<LeaveType> result = leaveTypeRepository.findById(id);

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {
            response.setId(result.get().getId());
            response.setType(result.get().getType());
            response.setStatus(result.get().getStatus());
            response.setCreatedAt(result.get().getCreatedAt().getTime());

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVETYPE_BY_ID);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
        } else {
            return new ResponseEntity(MessageConstants.BAD_REQUEST_GET, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<List<LeaveTypeDto>> getLeaveTypes() {
        List<LeaveTypeDto> response = new ArrayList<LeaveTypeDto>();

        List<LeaveType> leaveTypeList = leaveTypeRepository.findAll().stream().filter(e ->
                !e.getStatus().equals("Deleted")).collect(Collectors.toList());

        log.info("************7 " + leaveTypeList.toString());


        for (LeaveType leaveType : leaveTypeList) {
            LeaveTypeDto leaveTypeDto = new LeaveTypeDto();

            log.info("EmpId ******* " + leaveTypeDto.getId());

            leaveTypeDto.setId(leaveType.getId());
            leaveTypeDto.setType(leaveType.getType());
            leaveTypeDto.setStatus(leaveType.getStatus());
            leaveTypeDto.setCreatedAt(leaveType.getCreatedAt().getTime());

            log.info("leaveTypeDto ********* " + leaveTypeDto.toString());

            response.add(leaveTypeDto);
        }
        return ResponseEntity.ok(response);
    }


    public ResponseEntity<String> updateLeaveType(LeaveTypeDto leaveTypeDto) {
        LeaveType leaveType = new LeaveType();
        Optional<LeaveType> result = leaveTypeRepository.findById(leaveTypeDto.getId());

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {

            leaveType.setId(leaveTypeDto.getId());
            leaveType.setType(leaveTypeDto.getType());
            leaveType.setUpdatedAt(System.currentTimeMillis());
            leaveType.setCreatedAt(result.get().getCreatedAt().getTime());
            leaveType.setStatus("Active");

            leaveTypeRepository.save(leaveType);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVETYPE_UPDATED);

            return new ResponseEntity<String>(MessageConstants.LEAVETYPE_UPDATED, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_UPDATE, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteLeaveType(int id) {
        LeaveTypeDto response = new LeaveTypeDto();
        LeaveType leaveType = new LeaveType();
        Optional<LeaveType> result = leaveTypeRepository.findById(id);

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {

            leaveType.setId(result.get().getId());
            leaveType.setType(result.get().getType());
            leaveType.setCreatedAt(result.get().getCreatedAt().getTime());
            leaveType.setUpdatedAt(System.currentTimeMillis());
            leaveType.setStatus("Deleted");

            leaveTypeRepository.save(leaveType);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVETYPE_DELETED);

            return new ResponseEntity<String>(MessageConstants.LEAVETYPE_DELETED, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_DELETE, HttpStatus.BAD_REQUEST);
        }
    }
    //======================================================================================


    public ResponseEntity<String> createLeaveDetail(LeaveDetailDto leaveDetailRequest) {

        /*Optional<EmployeeDetails> employeeDetail= employeeDetailsRepository
               .findById(leaveDetailDto.getEmployeeDetails().getEmpId());

         Optional<LeaveType> leaveType= leaveTypeRepository
               .findById(leaveDetailDto.getLeaveType().getId());

        if (leaveDetailDto != null  && employeeDetail.get().getStatus().equals(MessageConstants.ACTIVE)
              && leaveType.get().getStatus().equals(MessageConstants.ACTIVE) ) {
   */

        LeaveDetail leaveDetail = new LeaveDetail();
        if (leaveDetailRequest != null) {

            leaveDetail.setId(leaveDetailRequest.getId());
            leaveDetail.setEmployeeDetails(leaveDetailRequest.getEmployeeDetailsDto());
            leaveDetail.setManager(leaveDetailRequest.getManagerDto());
            leaveDetail.setLeaveType(leaveDetailRequest.getLeaveTypeDto());
            leaveDetail.setFromDate(leaveDetailRequest.getFromDate());
            leaveDetail.setToDate(leaveDetailRequest.getToDate());
            leaveDetail.setNoofDays(leaveDetailRequest.getNoofDays());
            leaveDetail.setReason(leaveDetailRequest.getReason());
            leaveDetail.setCreatedAt(System.currentTimeMillis());
            leaveDetail.setStatus("Pending");

            log.info("No of Days----------> " + leaveDetailRequest.getNoofDays());

            eldrepository.save(leaveDetail);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVEDETAIL_ADDED);

            return new ResponseEntity<String>(MessageConstants.LEAVEDETAIL_ADDED, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_ADD, HttpStatus.BAD_REQUEST);
        }
    }



    public ResponseEntity getLeaveDetailByID(int id) throws IOException {
        LeaveDetailDto response = new LeaveDetailDto();

        Optional<LeaveDetail> result = eldrepository.findById(id);
        log.info("**********Role" + result.get().getEmployeeDetails().getRoles().getId());
        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {
            RolesDto rolesDto = new RolesDto();

            rolesDto.setId(result.get().getEmployeeDetails().getRoles().getId());
            rolesDto.setRoleName(result.get().getEmployeeDetails().getRoles().getRoleName());
            rolesDto.setStatus(result.get().getEmployeeDetails().getRoles().getStatus());

            EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();

            employeeDetailsDto.setEmpId(result.get().getEmployeeDetails().getEmpId());
            employeeDetailsDto.setName(result.get().getEmployeeDetails().getName());
            employeeDetailsDto.setRolesDto(rolesDto);
            employeeDetailsDto.setAddress(result.get().getEmployeeDetails().getAddress());
            employeeDetailsDto.setPhone(result.get().getEmployeeDetails().getPhone());
            employeeDetailsDto.setEmail(result.get().getEmployeeDetails().getEmail());
            employeeDetailsDto.setStatus(result.get().getEmployeeDetails().getStatus());
            //  employeeDetailsDto.setCreatedAt(System.currentTimeMillis());

            EmployeeDetailsDto manager = new EmployeeDetailsDto();
            RolesDto rolesDto1 = new RolesDto();
            rolesDto1.setId(result.get().getManager().getEmpId());

            manager.setEmpId(result.get().getManager().getEmpId());
            manager.setName(result.get().getManager().getName());
            manager.setRolesDto(rolesDto1);
            manager.setAddress(result.get().getManager().getAddress());
            manager.setPhone(result.get().getManager().getPhone());
            manager.setEmail(result.get().getManager().getEmail());
            manager.setStatus(result.get().getManager().getStatus());

            LeaveTypeDto leaveTypeDto = new LeaveTypeDto();

            leaveTypeDto.setId(result.get().getLeaveType().getId());
            leaveTypeDto.setType(result.get().getLeaveType().getType());
            leaveTypeDto.setStatus(result.get().getLeaveType().getStatus());
            leaveTypeDto.setCreatedAt(System.currentTimeMillis());


            response.setId(result.get().getId());
            response.setEmployeeDetailsDto(employeeDetailsDto);
            response.setManagerDto(manager);
            response.setLeaveTypeDto(leaveTypeDto);
            response.setFromDate(result.get().getFromDate());
            response.setToDate(result.get().getToDate());
            response.setNoofDays(result.get().getNoofDays());
            response.setReason(result.get().getReason());
            response.setCreatedAt(System.currentTimeMillis());
            response.setStatus(result.get().getStatus());

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVEDETAIL_ID);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
        } else {
            return new ResponseEntity(MessageConstants.BAD_REQUEST_GET, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<LeaveDetailDto>> getLeaveDetail() {

        List<LeaveDetailDto> response = new ArrayList<>();
        log.info("********202");
        List<LeaveDetail> leaveDetailList = eldrepository.findAll().stream().filter(e ->
                e.getStatus().equals(MessageConstants.PENDING)).collect(Collectors.toList());

        // List<LeaveDetail>  leaveDetailList=eldrepository.findAll();

        log.info("************getall" + leaveDetailList.toString());

        for (LeaveDetail leaveDetail : leaveDetailList) {
            LeaveDetailDto leaveDetailDto = new LeaveDetailDto();

            RolesDto rolesDto = new RolesDto();
            rolesDto.setId(leaveDetail.getManager().getRoles().getId());
            rolesDto.setRoleName(leaveDetail.getManager().getRoles().getRoleName());
            rolesDto.setStatus(leaveDetail.getManager().getRoles().getStatus());


            EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();

            employeeDetailsDto.setEmpId(leaveDetail.getEmployeeDetails().getEmpId());
            employeeDetailsDto.setName(leaveDetail.getEmployeeDetails().getName());
            employeeDetailsDto.setRolesDto(rolesDto);
            employeeDetailsDto.setAddress(leaveDetail.getEmployeeDetails().getAddress());
            employeeDetailsDto.setPhone(leaveDetail.getEmployeeDetails().getPhone());
            employeeDetailsDto.setEmail(leaveDetail.getEmployeeDetails().getEmail());
            employeeDetailsDto.setStatus(leaveDetail.getEmployeeDetails().getStatus());
            employeeDetailsDto.setCreatedAt(System.currentTimeMillis());


            EmployeeDetailsDto manager = new EmployeeDetailsDto();
            RolesDto rolesDto2 = new RolesDto();
            rolesDto2.setId(leaveDetail.getManager().getRoles().getId());
            rolesDto2.setRoleName(leaveDetail.getManager().getRoles().getRoleName());
            rolesDto2.setStatus(leaveDetail.getManager().getRoles().getStatus());

            manager.setEmpId(leaveDetail.getManager().getEmpId());
            manager.setName(leaveDetail.getManager().getName());
            manager.setRolesDto(rolesDto2);
            manager.setAddress(leaveDetail.getManager().getAddress());
            manager.setPhone(leaveDetail.getManager().getPhone());
            manager.setEmail(leaveDetail.getManager().getEmail());
            manager.setStatus(leaveDetail.getManager().getStatus());


            LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
            leaveTypeDto.setId(leaveDetail.getLeaveType().getId());
            leaveTypeDto.setType(leaveDetail.getLeaveType().getType());
            leaveTypeDto.setStatus(leaveDetail.getLeaveType().getStatus());
            leaveTypeDto.setCreatedAt(leaveDetail.getCreatedAt().getTime());

            LeaveDetailDto leaveDetailDto1 = new LeaveDetailDto();
            log.info("EmpId ******* " + leaveDetail.getId());


            log.info("EmpId ******* " + leaveDetailDto1.getId());

            leaveDetailDto1.setId(leaveDetail.getId());
            leaveDetailDto1.setEmployeeDetailsDto(employeeDetailsDto);
            leaveDetailDto1.setManagerDto(manager);
            leaveDetailDto1.setLeaveTypeDto(leaveTypeDto);
            leaveDetailDto1.setFromDate(leaveDetail.getFromDate());
            leaveDetailDto1.setToDate(leaveDetail.getToDate());
            leaveDetailDto1.setNoofDays(leaveDetail.getNoofDays());
            leaveDetailDto1.setReason(leaveDetail.getReason());
            leaveDetailDto1.setStatus(leaveDetail.getStatus());
            leaveDetailDto1.setCreatedAt(leaveDetail.getCreatedAt().getTime());

            log.info("leaveDetailDto ********* " + leaveDetailDto.toString());

            response.add(leaveDetailDto1);

        }
        return ResponseEntity.ok(response);
    }


    public ResponseEntity<String> updateLeaveDetail(LeaveDetailDto leaveDetailDto) {
        LeaveDetailDto response = new LeaveDetailDto();
        LeaveDetail LeaveDetail = new LeaveDetail();
        Optional<LeaveDetail> result = eldrepository.findById(leaveDetailDto.getId());

        EmployeeDetails manager = new EmployeeDetails();
        manager.setEmpId(leaveDetailDto.getManagerDto().getEmpId());

        if (result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {

            LeaveDetail.setId(leaveDetailDto.getId());
            LeaveDetail.setEmployeeDetails(leaveDetailDto.getEmployeeDetailsDto());
            LeaveDetail.setManager(leaveDetailDto.getManagerDto());
            LeaveDetail.setLeaveType(leaveDetailDto.getLeaveTypeDto());
            LeaveDetail.setFromDate(leaveDetailDto.getFromDate());
            LeaveDetail.setToDate(leaveDetailDto.getToDate());
            LeaveDetail.setNoofDays(leaveDetailDto.getNoofDays());
            LeaveDetail.setReason(leaveDetailDto.getReason());
            LeaveDetail.setCreatedAt(result.get().getCreatedAt().getTime());
            LeaveDetail.setUpdatedAt(System.currentTimeMillis());
            LeaveDetail.setStatus(leaveDetailDto.getStatus());
            eldrepository.save(LeaveDetail);

            response.setStatus("Successfully Added");

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVEDETAIL_UPDATED);

            return new ResponseEntity<String>(MessageConstants.LEAVEDETAIL_UPDATED, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_UPDATE, HttpStatus.BAD_REQUEST);
            //response.setStatus("Error while updating");
        }
        //return response;
    }

    public ResponseEntity<String> deleteLeaveDetail(int id) {
        LeaveDetailDto response = new LeaveDetailDto();
        LeaveDetail leaveDetail = new LeaveDetail();
        Optional<LeaveDetail> result = eldrepository.findById(id);

        RolesDto rolesDto = new RolesDto();
        rolesDto.setId(result.get().getId());


        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setEmpId(result.get().getEmployeeDetails().getEmpId());


        EmployeeDetailsDto manager = new EmployeeDetailsDto();
        manager.setEmpId(result.get().getManager().getEmpId());

        LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
        leaveTypeDto.setId(result.get().getLeaveType().getId());


        if (result.isPresent() && !result.get().getStatus().equals(MessageConstants.DELETED)) {
            leaveDetail.setId(result.get().getId());
            leaveDetail.setEmployeeDetails(employeeDetailsDto);
            leaveDetail.setManager(manager);
            leaveDetail.setLeaveType(leaveTypeDto);
            leaveDetail.setFromDate(result.get().getFromDate());
            leaveDetail.setToDate(result.get().getToDate());
            leaveDetail.setNoofDays(result.get().getNoofDays());
            leaveDetail.setReason(result.get().getReason());
            leaveDetail.setCreatedAt(result.get().getCreatedAt().getTime());
            leaveDetail.setUpdatedAt(System.currentTimeMillis());
            leaveDetail.setStatus(MessageConstants.DELETED);
            eldrepository.save(leaveDetail);
            response.setStatus("Successfully added");

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVEDETAIL_DELETED);

            return new ResponseEntity<String>(MessageConstants.LEAVEDETAIL_DELETED, headers, HttpStatus.OK);
            //response.setStatus("Leave Detail deleted successfully");

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_DELETE, HttpStatus.BAD_REQUEST);
            //response.setStatus("Error while deleting");
        }
        //return response;
    }
}