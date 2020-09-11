package com.example.techconnect.casinoservice.repositories;

import com.example.techconnect.casinoservice.models.bets.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
}
