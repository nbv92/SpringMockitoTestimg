package ru.skypro.SpringMockitoTestimg.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import ru.skypro.SpringMockitoTestimg.model.Employee;
import ru.skypro.SpringMockitoTestimg.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/sum")
    public Integer getSalarySum (@PathVariable("id") Integer id){
        return departmentService.getSalarySum(id);
    }

    @GetMapping("{id}/salary/min")
    public Integer getEmployeeWithMinSalary (@PathVariable("id") Integer id){
        return departmentService.getEmployeeWithMinSalary(id).getSalary();
    }

    @GetMapping("{id}/salary/max")
    public Integer getEmployeeWithSMaxSalary (@PathVariable("id") Integer id){
        return departmentService.getEmployeeWithMaxSalary(id).getSalary();
    }

    @GetMapping("{id}/employees")
    public List<Employee> getEmployees (@PathVariable("id") Integer id){
        return departmentService.getEmployees(id);
    }

    @GetMapping("employees")
    public Map<Integer, List<Employee>> getGroupedByDepartmentEmployees (){
        return departmentService.getGroupedByDepartmentEmployees();
    }
}
