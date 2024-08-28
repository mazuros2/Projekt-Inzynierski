package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.models.Trofeum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrofeumRepository extends JpaRepository<Trofeum,Long> {

    @Query("SELECT t FROM Trofeum t WHERE t.klub.id = :idKlub")
    List<Trofeum> findByKlubId(@Param("idKlub") long idKlub);

}
