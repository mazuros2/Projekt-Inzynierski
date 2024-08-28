package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.models.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigaRepository extends JpaRepository<Liga,Long> {

    @Query("SELECT l.nazwa_Ligi FROM Liga l")
    List<String> findAllLigaNames();

}
