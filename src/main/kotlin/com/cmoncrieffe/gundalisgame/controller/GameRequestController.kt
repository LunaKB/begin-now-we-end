package com.cmoncrieffe.gundalisgame.controller

import com.cmoncrieffe.gundalisgame.game.service.GameService
import com.cmoncrieffe.gundalisgame.game.service.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/gundalis-game/game")
class GameRequestController(
    @Autowired
    private val gameService: GameService
) {
    private var session: Session = gameService.newGame()

    @GetMapping("/play")
    fun play() : ResponseEntity<Any> {
        if (session.isFinished())
            session = gameService.newGame()

        return ResponseEntity.ok(session.next())
    }
}