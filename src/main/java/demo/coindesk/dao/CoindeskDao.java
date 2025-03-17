package demo.coindesk.dao;

import demo.coindesk.entity.CoindeskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoindeskDao extends JpaRepository<CoindeskEntity, Long> {
    Optional<CoindeskEntity> findByCodeAndCharName(String code, String charName);
}
