package com.sb_bank.cards.repository;

import com.sb_bank.cards.entity.Cards;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards, Long> {

    Optional<Cards> findByMobile(String mobile);

    @Transactional
    @Modifying
    void deleteByMobile(String mobile);
}
