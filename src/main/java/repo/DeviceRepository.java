package repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>{

}
