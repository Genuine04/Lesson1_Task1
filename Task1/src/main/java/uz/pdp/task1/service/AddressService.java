package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.task1.entity.Address;
import uz.pdp.task1.payload.AddressDto;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.repo.AddressRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;


    //GET ALL
    public List<AddressDto> getAllAddresses() {
        return addressRepo.findAll().stream().map(this::addressToAddressDto).collect(Collectors.toList());
    }


    //GET BY ID
    public AddressDto getAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        return optionalAddress.map(this::addressToAddressDto).orElse(null);
    }


    //POST
    public ApiResponse postAddress(AddressDto addressDto) {
        addressRepo.save(addressDtoToAddress(addressDto));
        return new ApiResponse("Saved successfully", true);
    }


    //PUT
    public ApiResponse putAddress(AddressDto addressDto, Integer id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setHomeNumber(addressDto.getHomeNumber());
            address.setStreet(addressDto.getStreet());
            Address save = addressRepo.save(address);
            return new ApiResponse("Updated successfully", true);
        }
        return new ApiResponse("Address not found", false);
    }


    //DELETE
    public ApiResponse deleteAddress(Integer id) {
        try {
            addressRepo.deleteById(id);
            return new ApiResponse("Deleted successfully", true);
        } catch (Exception e) {
            return new ApiResponse("Address not found", false);
        }
    }


    //ADDRESS DTO
    public AddressDto addressToAddressDto(Address address) {
        return new AddressDto(address.getId(), address.getStreet(), address.getHomeNumber());
    }


    //ADDRESS
    public Address addressDtoToAddress(AddressDto addressDto) {
        return new Address(addressDto.getId(), addressDto.getStreet(), addressDto.getHomeNumber());
    }

}
