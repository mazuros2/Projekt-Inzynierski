package repository;

import models.Klub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlubRepository extends JpaRepository<Klub,Long> {
}
