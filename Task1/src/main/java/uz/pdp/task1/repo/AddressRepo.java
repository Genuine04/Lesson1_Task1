package uz.pdp.task1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
}
