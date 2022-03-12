package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.Company;
import uz.pdp.task1.entity.Department;
import uz.pdp.task1.payload.AddressDto;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.CompanyDto;
import uz.pdp.task1.payload.DepartmentDto;
import uz.pdp.task1.repo.DepartmentRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {


    @Autowired
    DepartmentRepo departmentRepo;


    //GET ALL
    public List<DepartmentDto> getAllDepartments(){
        return departmentRepo.findAll().stream().map(this::departmentToDepartmentDto).collect(Collectors.toList());
    }


    //GET BY ID
    public DepartmentDto getDepartmentDtoById(Integer id){
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        return optionalDepartment.map(this::departmentToDepartmentDto).orElse(null);
    }


    //POST
    public ApiResponse postDepartment(DepartmentDto departmentDto){
        departmentRepo.save(departmentDtoToDepartment(departmentDto));
        return new ApiResponse("Saved successfully", true);
    }


    //PUT
    public ApiResponse putDepartment(DepartmentDto departmentDto, Integer id){
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department not found", false);
        Department department = optionalDepartment.get();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setCompany(companyDtoToCompany(departmentDto.getCompanyDto()));
        Department save = departmentRepo.save(department);
        return new ApiResponse("Updated successfully", true);
    }


    //DELETE
    public ApiResponse deleteDepartment(Integer id){
        try {
            departmentRepo.deleteById(id);
            return new ApiResponse("Deleted successfully", true);
        }catch (Exception e){
            return new ApiResponse("Department not found", false);
        }
    }


    //ADDRESS
    public Address addressDtoToAddress(AddressDto addressDto) {
        return new Address(addressDto.getId(), addressDto.getStreet(), addressDto.getHomeNumber());
    }


    //ADDRESS DTO
    public AddressDto addressToAddressDto(Address address) {
        return new AddressDto(address.getId(), address.getStreet(), address.getHomeNumber());
    }


    //COMPANY
    public Company companyDtoToCompany(CompanyDto companyDto){
        return new Company(companyDto.getId(), companyDto.getCorpName(), companyDto.getDirectorName(), addressDtoToAddress(companyDto.getAddressDto()));
    }


    //COMPANY DTO
    public CompanyDto companyToCompanyDto(Company company){
        return new CompanyDto(company.getId(), company.getCorpName(), company.getDirectorName(), addressToAddressDto(company.getAddress()));
    }


    //DEPARTMENT
    public Department departmentDtoToDepartment(DepartmentDto departmentDto){
        return new Department(departmentDto.getId(), departmentDto.getName(), companyDtoToCompany(departmentDto.getCompanyDto()));
    }


    //DEPARTMENT DTO
    public DepartmentDto departmentToDepartmentDto(Department department){
        return new DepartmentDto(department.getId(), department.getName(), companyToCompanyDto(department.getCompany()));
    }

}
