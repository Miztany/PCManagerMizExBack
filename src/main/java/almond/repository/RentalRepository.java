package almond.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import almond.entity.Device;
import almond.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer>  {

	@Query("select r from Rental r where r.device.failure = false and r.device.deleteFlag = false and r.deleteFlag = false")
	public List<Rental> findUsable();
	
	public List<Rental> findByRentalId(Integer rentalId);

	public List<Rental> findByDevice(Device device);

}
