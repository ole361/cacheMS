package spring.redis.CashMS.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import spring.redis.CashMS.model.Modul;
import spring.redis.CashMS.repository.ModulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModulService {
    private static final Logger log = LoggerFactory.getLogger(ModulService.class);
    @Autowired
    private ModulRepository modulRepository;

    public Modul saveModul(Modul modul) {
        return modulRepository.save(modul);
    }


    public List<Modul> getAllModuls() {
        return modulRepository.findAll();
    }

    @Cacheable(value = "moduls", key = "#id")
    public Optional<Modul> getModulById(Long id) {
        log.info("Cache Miss for id: " + id);
        return modulRepository.findById(id);
    }


    public void generateAndSaveModuls(int numberOfModuls) {
        List<Modul> moduls = new ArrayList<>();

        for (int i = 0; i < numberOfModuls; i++) {
            String largeJson = generateLargeJson();
            moduls.add(new Modul(largeJson));

            // Batch save to reduce database calls
            if (i % 1000 == 0 && i > 0) {
                modulRepository.saveAll(moduls);
                moduls.clear();
            }
        }

        if (!moduls.isEmpty()) {
            modulRepository.saveAll(moduls);
        }
    }

    private String generateLargeJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < 100; i++) { // Adjust the number of fields to ensure the JSON size is around 1KB or more
            sb.append("\"key").append(i).append("\": \"value").append(i).append("\"");
            if (i < 99) sb.append(", ");
        }

        sb.append("}");
        return sb.toString();
    }
}

