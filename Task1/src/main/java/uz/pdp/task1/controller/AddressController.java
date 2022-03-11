package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.service.AddressService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    ApiResponse responseApi = new ApiResponse();

    @Autowired
    AddressService addressService;


    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(addressService.getAllAddresses());
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }


    @PostMapping()
    public HttpEntity<?> post(@Valid @RequestBody Address address) {
        ApiResponse responseApi = addressService.postAddress(address);
        return ResponseEntity.status(201).body(responseApi);
    }


    @PutMapping("/{id}")
    public HttpEntity<?> put(@Valid @RequestBody Address address, @PathVariable Integer id){
        ApiResponse apiResponse = addressService.putAddress(address, id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:409).body(apiResponse);
    }
}
