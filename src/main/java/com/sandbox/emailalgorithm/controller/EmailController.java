package com.sandbox.emailalgorithm.controller;

import com.sandbox.emailalgorithm.entity.MessageDTO;
import com.sandbox.emailalgorithm.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
public class EmailController {

    private final EmailService service;

    @PostMapping
    public ResponseEntity<String> send(@RequestBody MessageDTO dto){
        return ResponseEntity.ok(service.sendHtmlEmail(dto.to(), "Algorithm Bubble Sort Project", dto.description()));
    }
}
