package com.example.techconnect.casinoservice.repositories;

import com.example.techconnect.casinoservice.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findAllPlayersByGameId(long id);
}
