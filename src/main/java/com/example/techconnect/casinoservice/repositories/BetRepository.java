package com.example.techconnect.casinoservice.repositories;

import com.example.techconnect.casinoservice.models.bets.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllBetsByPlayerIdAndPlayerGameId(long playerId, long gameId);

    List<Bet> findAllBetsByPlayerGameId(long gameId);
}
