package com.example.matches

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/teams")
class TeamController(private val teamRepo: TeamRepository) {

    @GetMapping("/test")
    fun getTest(): String {
        return "teams"
    }

    @GetMapping("/all")
    fun getTeams(): List<Team> {
        return teamRepo.findAll()
    }

    @GetMapping("/{id}")
    fun getTeamById(@PathVariable id: Long): Optional<Team> {
        return teamRepo.findById(id)
    }

    @PostMapping("/save")
    fun saveTeam(@RequestBody team: Team): String {
        teamRepo.save(team)
        return "Successfully saved a team"
    }

    @DeleteMapping("/{id}")
    fun deleteTeam(@PathVariable id: Long) {
        teamRepo.deleteById(id)
    }
}