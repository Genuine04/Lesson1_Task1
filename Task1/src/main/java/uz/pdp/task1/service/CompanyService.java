package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.Company;
import uz.pdp.task1.payload.AddressDto;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.CompanyDto;
import uz.pdp.task1.repo.CompanyRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {


    @Autowired
    CompanyRepo companyRepo;


    //GET ALL
    public List<CompanyDto> getAllCompanyDtos(){
        return companyRepo.findAll().stream().map(this::companyToCompanyDto).collect(Collectors.toList());
    }


    //GET BY ID
    public CompanyDto getCompanyById(Integer id){
        Optional<Company> optionalCompany = companyRepo.findById(id);
        return optionalCompany.map(this::companyToCompanyDto).orElse(null);
    }


    //POST
    public ApiResponse postCompany(CompanyDto companyDto){
        companyRepo.save(companyDtoToCompany(companyDto));
        return new ApiResponse("Saved successfully", true);
    }


    //PUT
    public ApiResponse putCompany(CompanyDto companyDto, Integer id){
        Optional<Company> optionalCompany = companyRepo.findById(id);
        if (optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setCorpName(companyDto.getCorpName());
            company.setDirectorName(company.getDirectorName());
            company.setAddress(addressDtoToAddress(companyDto.getAddressDto()));
            Company save = companyRepo.save(company);
            return new ApiResponse("Updatedd successfully", true);
        }
        return new ApiResponse("Company not found", false);
    }


    //DELETE
    public ApiResponse deleteCompany(Integer id) {
        try {
            companyRepo.deleteById(id);
            return new ApiResponse("Deleted suucessfully", true);
        } catch (Exception e) {
            return new ApiResponse("Company not found", false);
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

}
