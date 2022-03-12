package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.AddressDto;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {


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
    public HttpEntity<?> post(@Valid @RequestBody AddressDto addressDto) {
        ApiResponse responseApi = addressService.postAddress(addressDto);
        return ResponseEntity.status(201).body(responseApi);
    }


    @PutMapping("/{id}")
    public HttpEntity<?> put(@Valid @RequestBody AddressDto addressDto, @PathVariable Integer id){
        ApiResponse apiResponse = addressService.putAddress(addressDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:404).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
