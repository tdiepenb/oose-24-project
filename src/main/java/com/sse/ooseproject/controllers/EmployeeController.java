package com.sse.ooseproject.controllers;

import com.sse.ooseproject.EmployeeRepository;
import com.sse.ooseproject.StudentRepository;
import com.sse.ooseproject.models.Employee;
import com.sse.ooseproject.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public String employees(Model model, @RequestParam String sort_by, @RequestParam Boolean sort_asc) {
        Sort.Direction sortDirection;
        if(sort_asc){
            sortDirection = Sort.Direction.ASC;
        }else{
            sortDirection = Sort.Direction.DESC;
        }
        List<Employee> employees = employeeRepository.findAll(Sort.by(sortDirection, sort_by));
        model.addAttribute("sort_by", sort_by);
        model.addAttribute("sort_asc", sort_asc);
        model.addAttribute("employees", employees);

        // Returning the name of a view (found in resources/templates) as a string lets this endpoint return that view.
        return "employees";
    }
}
