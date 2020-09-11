package com.example.techconnect.casinoservice.repositories;

import com.example.techconnect.casinoservice.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
