package com.example.matches.controller

import com.example.matches.entity.Match
import com.example.matches.service.MatchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/matches")
class MatchesController(private val matchService : MatchService){

    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("Test")
    }

    @GetMapping("/all")
    fun getMatches(): ResponseEntity<List<Match>> {
        return try {
            val matches = matchService.getMatches()
            ResponseEntity.ok(matches)
        }
        catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{id}")
    fun getMatchById(@PathVariable id: Long): ResponseEntity<Match> {
        return try {
            val match = matchService.getMatch(id)
            if(match != null)
                ResponseEntity.ok(match)
            else
                ResponseEntity.notFound().build()
        }
        catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/match")
    fun saveMatch(@RequestBody match: Match): ResponseEntity<Match> {
        return try {
            val createdMatch = matchService.createMatch(match)
            ResponseEntity.ok(createdMatch)
        }
        catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMatch(@PathVariable id: Long) : ResponseEntity<Boolean> {
        return try {
            val result = matchService.deleteMatch(id)
            if(result)
                ResponseEntity.ok(true)
            else
                ResponseEntity.notFound().build()
        }
        catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}