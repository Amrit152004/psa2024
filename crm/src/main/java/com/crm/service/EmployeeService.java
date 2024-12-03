package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private Long id;
    private EmployeeDto dto;
    @Autowired
    private ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
      //  Employee employee2=new Employee(employee);
        Employee employee1 = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(employee1);
        return employeeDto;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
      
        Optional<Employee> emp = employeeRepository.findById(id);
        Employee employee = emp.get();
        employee.setName(dto.getName());
      employee.setEmailId(dto.getEmailId());
      employee.setMobile(dto.getMobile());
    employeeRepository.save(employee);
    return  mapToDto(employee);

    }

EmployeeDto mapToDto(Employee employee){
//        EmployeeDto dto=new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());
    //EmployeeDto do=modelMapper.map(employee, EmployeeDto.class);
   return  modelMapper.map(employee, EmployeeDto.class);
       // return do;
}

    Employee mapToEntity(EmployeeDto dto){
//        Employee e=new Employee();
//       e.setId(dto.getId());
//        e.setName(dto.getName());
//        e.setEmailId(dto.getEmailId());
//        e.setMobile(dto.getMobile());

     return  modelMapper.map(dto,Employee.class);
       // return e;
    }


    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
    Sort sort=   sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

       Pageable page= PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> content = all.getContent();
        List<EmployeeDto> collect = content.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return collect;
    }
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () ->new ResourceNotFound("Not Found")
        );
        EmployeeDto employeeDto = mapToDto(employee);
        return employeeDto;

    }
}



























