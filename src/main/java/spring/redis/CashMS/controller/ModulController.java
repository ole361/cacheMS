package spring.redis.CashMS.controller;

import org.slf4j.LoggerFactory;
import spring.redis.CashMS.model.Modul;
import spring.redis.CashMS.service.ModulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/moduls")
public class ModulController {

    private static final Logger log = LoggerFactory.getLogger(ModulService.class);

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
        log.info("Request for id: " + id);
        return modulService.getModulById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/trigger")
    public String triggerModul() {
        modulService.generateAndSaveModuls(10000);
        return "Moduls will be generated";
    }
}

