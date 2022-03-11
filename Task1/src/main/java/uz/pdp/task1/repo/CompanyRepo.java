package uz.pdp.task1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
}
