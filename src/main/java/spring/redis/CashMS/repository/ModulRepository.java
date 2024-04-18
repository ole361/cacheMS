package spring.redis.CashMS.repository;


import spring.redis.CashMS.model.Modul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulRepository extends JpaRepository<Modul, Long> {
}

