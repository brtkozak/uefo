package com.example.matches.data;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchRepository : JpaRepository<Match, Long>