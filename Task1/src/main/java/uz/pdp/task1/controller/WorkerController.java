package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.WorkerDto;
import uz.pdp.task1.service.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {


    @Autowired
    WorkerService workerService;


    @GetMapping
    public HttpEntity<?> getAll(){
        List<WorkerDto> allWorkers = workerService.getAllWorkers();
        return ResponseEntity.ok(allWorkers);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        WorkerDto workerDtoById = workerService.getWorkerDtoById(id);
        return ResponseEntity.ok(workerDtoById);
    }


    @PostMapping
    public HttpEntity<?> post(@Valid @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.postWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body(apiResponse);
    }


    @PutMapping("/{id}")
    public HttpEntity<?> put(@Valid @RequestBody WorkerDto workerDto, @PathVariable Integer id){
        ApiResponse apiResponse = workerService.putWorker(workerDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:404).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:404).body(apiResponse);
    }

}
