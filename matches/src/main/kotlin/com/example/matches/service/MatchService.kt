package com.example.matches.service

import com.example.matches.entity.Match
import com.example.matches.repository.MatchRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MatchService (
    private  val matchRepository: MatchRepository
) {
    fun deleteMatch(matchId: Long) : Boolean {
        val match = getMatch(matchId)
        return if(match == null)
            false
        else {
            matchRepository.deleteById(matchId)
            true
        }
    }

    fun getMatches(): List<Match> {
        return matchRepository.findAll()
    }

    fun getMatch(matchId: Long): Match? {
        return matchRepository.findByIdOrNull(matchId)
    }

    fun createMatch(match: Match): Match {
        return matchRepository.save(match)
    }
}