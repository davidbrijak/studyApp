package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.NewSzemelyDto;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;
import com.projekt.klinikaStudyBase.service.SzemelyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/szemely")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SzemelyEndpoint {

    private final SzemelyService szemelyService;

    @Transactional
    @ApiOperation(value = "Uj szemely mentese.", notes = "Uj szemely mentese hozzatartozo szerepkorrel.")
    @PostMapping
    public ResponseEntity<SzemelyDetailDto> addSzemelyWithSzerepkor(@RequestBody final NewSzemelyDto newSzemely) {
        return ResponseEntity.ok(szemelyService.addSzemelyWithSzerepkor(newSzemely));
    }

    @ApiOperation(value = "Szemelyek lekerese.", notes = "Rendszerben nyilvatartott szemelyek lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<SzemelyDetailDto>> getAll() {
        return ResponseEntity.ok().body(szemelyService.findAll());
    }

    @ApiOperation(value = "Szemelye lekerese id alapjan.", notes = "Szemely lekérése azonosito alapjan.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<SzemelyDetailDto> get(final Long id) {
        return ResponseEntity.ok().body(szemelyService.findbyId(id));
    }

}
