package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.repo.AddressRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;


    public List<Address> getAllAddresses(){
        return addressRepo.findAll();
    }


    public Address getAddressById(Integer id){
        Optional<Address> optionalAddress = addressRepo.findById(id);
        return optionalAddress.orElse(null);
    }


    public ApiResponse postAddress(Address address){
        Address save = addressRepo.save(address);
        return new ApiResponse("Saved successfully", true);
    }


    public ApiResponse putAddress(Address address, Integer id){
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()){
            Address address1 = optionalAddress.get();
            address1.setHomeNumber(address.getHomeNumber());
            address1.setStreet(address.getStreet());
            return new ApiResponse("Updated successfully", true);
        }
        return new ApiResponse("Address not found", false);
    }


    public ApiResponse deleteAddress(Integer id){
        try{
            addressRepo.deleteById(id);
            return new ApiResponse("Deleted successfully", true);
        }catch (Exception e){
            return new ApiResponse("Address not found", false);
        }
    }

}
