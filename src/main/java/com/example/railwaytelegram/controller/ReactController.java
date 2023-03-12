package com.example.railwaytelegram.controller;

import com.example.railwaytelegram.dto.InfoDto;
import com.example.railwaytelegram.dto.OperationDto;
import com.example.railwaytelegram.dto.OperationHistoryDto;
import com.example.railwaytelegram.serivce.ReactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://pav22sher.github.io")
@RestController
@RequestMapping("/api")
public class ReactController {
    @Autowired
    private ReactService reactService;

    @GetMapping("/info")
    public InfoDto getInfo(@RequestParam Long userId) {
        return reactService.getInfo(userId);
    }

    @PostMapping("/operation")
    public void saveOperation(@RequestBody OperationDto dto) {
        reactService.saveOperation(dto);
    }

    @GetMapping("/operation-history")
    public List<OperationHistoryDto> getOperationHistory(@RequestParam Long userId) {
        return reactService.getOperationHistory(userId);
    }
}
