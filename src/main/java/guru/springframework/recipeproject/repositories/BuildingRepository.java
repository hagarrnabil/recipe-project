package guru.springframework.recipeproject.repositories;

import guru.springframework.recipeproject.models.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
//    Page<Building> findByProjectcCode(Long projectCode, Pageable pageable);
//    Optional<Building> findByIdAndProjectcCode(Long id, Long projectCode);
}