package ru.skypro.SpringMockitoTestimg.controller;

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

    @GetMapping("{id}/salary/sum")
    public Employee getEmployeeWithMaxSalary (@PathVariable("id") Integer id){
        return departmentService.getEmployeeWithMaxSalary(id);
    }

    @GetMapping("{id}/salary/min")
    public Employee getEmployeeWithMinSalary (@RequestParam("id") Integer id){
        return departmentService.getEmployeeWithMinSalary(id);
    }

    @GetMapping(value = "all", params = "departmentId")
    public List<Employee> getEmployeeAllByDepartment (@RequestParam("departmentId") Integer departmentId){
        return departmentService.getEmployeeAllByDepartment(departmentId);
    }

    @GetMapping("all")
    public Map<Integer, List<Employee>> getAllEmployees (){
        return departmentService.getAllEmployees();
    }
}
