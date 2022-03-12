package uz.pdp.task1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Worker;

public interface WorkerRepo extends JpaRepository<Worker, Integer> {

    boolean existsWorkerByPhoneNumber(String phoneNumber);

}
