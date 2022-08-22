package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.JogosultsagDetailDto;
import com.projekt.klinikaStudyBase.service.JogosultsagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogosultsag")
@CrossOrigin(origins = "http://localhost:4200")
public class JogosultsagEndpoint {

    @Autowired
    private JogosultsagService jogosultsagService;

    @Transactional
    @ApiOperation(value = "Uj jogosultsag mentese.", notes = "Uj jogosultsag mentese.")
    @PostMapping
    public ResponseEntity<JogosultsagDetailDto> addNewJogosultsag(@RequestBody final JogosultsagDetailDto newJogosultsagDto) {
        return ResponseEntity.ok(jogosultsagService.addNewJogosultsag(newJogosultsagDto));
    }

    @ApiOperation(value = "Jogosultsagok lekerese.", notes = "Jogosultsagok lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<JogosultsagDetailDto>> getAll() {
        return ResponseEntity.ok().body(jogosultsagService.findAll());
    }

    @ApiOperation(value = "Jogosultsag lekerese azonosito alapjan.", notes = "Jogosultsag lekerese azonosito alapjan.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<JogosultsagDetailDto> get(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(jogosultsagService.find(id));
    }
}
