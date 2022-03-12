package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.Company;
import uz.pdp.task1.entity.Department;
import uz.pdp.task1.entity.Worker;
import uz.pdp.task1.payload.*;
import uz.pdp.task1.repo.WorkerRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerService {


    @Autowired
    WorkerRepo workerRepo;


    //GET ALL
    public List<WorkerDto> getAllWorkers(){
        return workerRepo.findAll().stream().map(this::workerToWorkerDto).collect(Collectors.toList());
    }


    //GET BY ID
    public WorkerDto getWorkerDtoById(Integer id){
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        return optionalWorker.map(this::workerToWorkerDto).orElse(null);
    }


    //POST
    public ApiResponse postWorker(WorkerDto workerDto){
        boolean b = workerRepo.existsWorkerByPhoneNumber(workerDto.getPhoneNumber());
        if (!b)
            return new ApiResponse("This phone number is existed", false);
        Worker save = workerRepo.save(workerDtoToWorker(workerDto));
        return new ApiResponse("Saved successfully", true);
    }


    //PUT
    public ApiResponse putWorker(WorkerDto workerDto, Integer id){
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Worker not found", false);
        Worker worker = optionalWorker.get();
        worker.setId(workerDto.getId());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(addressDtoToAddress(workerDto.getAddressDto()));
        worker.setDepartment(departmentDtoToDepartment(workerDto.getDepartmentDto()));
        Worker save = workerRepo.save(worker);
        return new ApiResponse("Updated successfully", true);
    }


    //DELETE
    public ApiResponse deleteWorker(Integer id){
        try {
            workerRepo.deleteById(id);
            return new ApiResponse("Deleted successfully", true);
        }catch (Exception e){
            return new ApiResponse("Worker not found", false);
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


    //WORKER
    public Worker workerDtoToWorker(WorkerDto workerDto){
        return new Worker(workerDto.getId(), workerDto.getName(), workerDto.getPhoneNumber(),
                addressDtoToAddress(workerDto.getAddressDto()), departmentDtoToDepartment(workerDto.getDepartmentDto()));
    }


    //WORKER DTO
    public WorkerDto workerToWorkerDto(Worker worker){
        return new WorkerDto(worker.getId(), worker.getName(), worker.getPhoneNumber(),
                addressToAddressDto(worker.getAddress()), departmentToDepartmentDto(worker.getDepartment()));
    }
}
