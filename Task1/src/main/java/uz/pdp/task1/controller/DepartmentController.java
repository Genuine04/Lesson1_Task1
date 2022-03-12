package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.DepartmentDto;
import uz.pdp.task1.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;


    @GetMapping
    public HttpEntity<?> getAll(){
        List<DepartmentDto> allDepartments = departmentService.getAllDepartments();
        return ResponseEntity.ok(allDepartments);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        DepartmentDto departmentDtoById = departmentService.getDepartmentDtoById(id);
        return ResponseEntity.ok(departmentDtoById);
    }


    @PostMapping
    public HttpEntity<?> post(@Valid @RequestBody DepartmentDto departmentDto, @PathVariable Integer id){
        ApiResponse apiResponse = departmentService.postDepartment(departmentDto);
        return ResponseEntity.status(201).body(apiResponse);
    }


    @PutMapping("/{id}")
    public HttpEntity<?> put(@Valid @RequestBody DepartmentDto departmentDto, @PathVariable Integer id){
        ApiResponse apiResponse = departmentService.putDepartment(departmentDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:404).body(apiResponse);
    }

}
