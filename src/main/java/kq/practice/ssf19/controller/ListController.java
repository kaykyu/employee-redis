package kq.practice.ssf19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kq.practice.ssf19.repository.EmployeeRepo;

@Controller
@RequestMapping(path="/employees")
public class ListController {

    @Autowired
    EmployeeRepo repo;
    
    @GetMapping(path="/list")
    public String listEmployees(Model model) {
        
        model.addAttribute("employees", repo.getAll());
        return "employeelist";
    }
}
