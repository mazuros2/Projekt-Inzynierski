package dev.projekt_inzynierski.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demoContr")
public class DemoContr {
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello z endpointu");
    }
}
