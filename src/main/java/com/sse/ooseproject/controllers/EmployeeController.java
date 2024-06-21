package com.sse.ooseproject.controllers;

import com.sse.ooseproject.EmployeeRepository;
import com.sse.ooseproject.models.Employee;
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

    /**
     * Handles GET requests for retrieving a list of employees.
     *
     * This method fetches all employees from the repository, sorts them according to the specified
     * sort parameters, and adds the sorted list along with sorting parameters to the model.
     * The method then returns the name of the view to be rendered.
     *
     * @param model The Model object that will hold the data to be displayed on the view.
     * @param sort_by The field by which the employees should be sorted.
     * @param sort_asc A boolean indicating whether the sorting should be in ascending order.
     * @return The name of the view to be rendered, in this case, "employees".
     */
    @GetMapping("/employees")
    public String employees(Model model, @RequestParam String sort_by, @RequestParam Boolean sort_asc) {
        Sort.Direction sortDirection;
        if (sort_asc) {
            sortDirection = Sort.Direction.ASC;
        } else {
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
