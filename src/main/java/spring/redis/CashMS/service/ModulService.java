package spring.redis.CashMS.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import spring.redis.CashMS.model.Modul;
import spring.redis.CashMS.repository.ModulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        log.info("database is called");
        return modulRepository.findById(id);
    }
}

