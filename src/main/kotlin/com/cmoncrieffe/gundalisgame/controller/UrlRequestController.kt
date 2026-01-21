package com.cmoncrieffe.gundalisgame.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/gundalis-game/api")
class UrlRequestController {
    @GetMapping("/")
    fun getLive() : ResponseEntity<Boolean> {
        return ResponseEntity.ok(true)
    }
}