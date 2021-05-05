package com.example.matches

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/matches")
class MatchesController(private val matchRepo: MatchRepository){

    @GetMapping("/test")
    fun getTest(): String {
        return "matches"
    }

    @GetMapping("/all")
    fun getMatches(): List<Match> {
        return matchRepo.findAll()
    }

    @GetMapping("/{id}")
    fun getMatchById(@PathVariable id: Long): Optional<Match> {
        return  matchRepo.findById(id)
    }

    @PostMapping("/save")
    fun saveMatch(@RequestBody match: Match): String {
        matchRepo.save(match)
        return "Successfully saved a match"
    }

    @DeleteMapping("/{id}")
    fun deleteMatch(@PathVariable id: Long) {
        matchRepo.deleteById(id)
    }
}