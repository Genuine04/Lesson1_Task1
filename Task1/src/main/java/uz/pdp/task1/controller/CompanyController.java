package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.CompanyDto;
import uz.pdp.task1.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {


    @Autowired
    CompanyService companyService;


    @GetMapping
    public HttpEntity<?> getAll(){
        List<CompanyDto> allCompanyDtos = companyService.getAllCompanyDtos();
        return ResponseEntity.ok(allCompanyDtos);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        CompanyDto companyById = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyById);
    }

    @PostMapping
    public HttpEntity<?> post(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.postCompany(companyDto);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> put(@Valid @RequestBody CompanyDto companyDto, @PathVariable Integer id){
        ApiResponse apiResponse = companyService.putCompany(companyDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:404).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:404).body(apiResponse);
    }


}
