package tback.kicketingback.performance.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tback.kicketingback.performance.domain.Performance;
import tback.kicketingback.performance.domain.type.Genre;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

	Optional<Performance> findById(UUID uuid);

	List<Performance> findByNameContaining(String name);

	List<Performance> findByNameContainingAndGenreIn(String name, List<String> genres);

}
