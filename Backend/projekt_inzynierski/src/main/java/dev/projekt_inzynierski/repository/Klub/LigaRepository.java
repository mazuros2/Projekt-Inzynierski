package dev.projekt_inzynierski.repository.Klub;

import dev.projekt_inzynierski.DTO.LigaDTO;
import dev.projekt_inzynierski.models.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public interface LigaRepository extends JpaRepository<Liga,Long> {
    @Query("SELECT new dev.projekt_inzynierski.DTO.LigaDTO( l.id,l.nazwa_Ligi,l.poziom_Ligi)FROM Liga l")
    List<LigaDTO> findAllLigaNames();
}
