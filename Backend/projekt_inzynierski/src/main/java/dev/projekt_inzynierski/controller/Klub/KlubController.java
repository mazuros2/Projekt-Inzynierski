package dev.projekt_inzynierski.controller.Klub;

import dev.projekt_inzynierski.models.Klub;
import dev.projekt_inzynierski.service.Klub.KlubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KlubController {
    @Autowired
    private final KlubService klubService;

    public KlubController(KlubService klubService) {
        this.klubService = klubService;
    }
    @GetMapping("/getKluby")
    public List<Klub> getKluby(){
        List<Klub> kluby = klubService.getKluby();
        return kluby;
    }

}
