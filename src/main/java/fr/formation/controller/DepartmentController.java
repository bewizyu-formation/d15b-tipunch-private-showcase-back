package fr.formation.controller;

import fr.formation.model.Department;
import fr.formation.repository.DepartmentRepositoryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentRepositoryImpl departmentRepository;

    public DepartmentController(DepartmentRepositoryImpl departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping()
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{deptId}")
    public Department findOne(@PathVariable Integer deptId) {
        return departmentRepository.findOne(deptId);
    }

}
