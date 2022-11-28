package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.NewSzemelyDto;
import com.projekt.klinikaStudyBase.dto.PageRequest;
import com.projekt.klinikaStudyBase.dto.SzemelyDetailDto;
import com.projekt.klinikaStudyBase.dto.SzemelyFilter;
import com.projekt.klinikaStudyBase.service.SzemelyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/szemely")
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

    @ApiOperation(value = "Szemelyek lekerese filter alapjan.", notes = "Szemelyek lekerese filter alapjan.")
    @PostMapping("/search/filter")
    public ResponseEntity<List<SzemelyDetailDto>> search(@RequestBody final SzemelyFilter filter) {
        return ResponseEntity.ok(szemelyService.search(filter));
    }

    @ApiOperation(value = "Szemelye lekerese id alapjan.", notes = "Szemely lekérése azonosito alapjan.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<SzemelyDetailDto> get(final Long id) {
        return ResponseEntity.ok().body(szemelyService.findbyId(id));
    }

    @ApiOperation(value = "Szemelye lekerese id alapjan.", notes = "Szemely lekérése azonosito alapjan.")
    @PostMapping(path = "/page")
    public ResponseEntity<List<SzemelyDetailDto>> getPage(@RequestBody final PageRequest pageRequest) {
        return ResponseEntity.ok().body(szemelyService.searchPageAble(org.springframework.data.domain.PageRequest.of(pageRequest.getPageNo(), pageRequest.getPageSize())));
    }

    @ApiOperation(value = "Azt ellenorzi van e az adott szerepkor ala tartozo szemely.", notes = "Azt ellenorzi van e az adott szerepkor ala tartozo szemely.")
    @GetMapping(path = "/hasAnySzemelyBySzerepkor/{id}")
    public ResponseEntity<Boolean> hasAnySzemelyBySzerepkor(@PathVariable final Long id) {
        return ResponseEntity.ok().body(szemelyService.hasAnySzemelyBySzerepkor(id));
    }
}
