package spring.redis.CashMS.controller;

import spring.redis.CashMS.model.Modul;
import spring.redis.CashMS.service.ModulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moduls")
public class ModulController {

    @Autowired
    private ModulService modulService;

    @PostMapping
    public Modul addModul(@RequestBody Modul modul) {
        return modulService.saveModul(modul);
    }

    @GetMapping
    public List<Modul> getAllModuls() {
        return modulService.getAllModuls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modul> getModulById(@PathVariable Long id) {
        return modulService.getModulById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

