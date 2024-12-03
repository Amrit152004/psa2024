package com.crm.cotroller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService=employeeService;
    }
 //create method to add employee
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee( @Valid @RequestBody EmployeeDto dto,
    BindingResult result ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto employeeDto = employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);

    }
    @DeleteMapping
    public String DeleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployee(id);
        return "Hello";

    }

    @PutMapping
    public ResponseEntity<EmployeeDto> UpdateEmployee(@RequestParam Long id, @RequestBody EmployeeDto dto){
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);

    }



    @GetMapping
    public List<EmployeeDto> getEmployees(
            @RequestParam (name = "pageSize", required = false,defaultValue = "2") int pageSize,
          @RequestParam(name = "pageNo", required = false, defaultValue = "0")int pageNo,
            @RequestParam(name = "sortBy", required = false, defaultValue ="name")String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue ="asc")String sortDir
            /* add more parameters if required */
    ){
        List<EmployeeDto> employee = employeeService.getEmployee(pageNo, pageSize, sortBy,sortDir);
       // List<Employee> employee1 = employee;
        return employee;



    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }





}
