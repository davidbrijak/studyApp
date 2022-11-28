package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.VizsgalatiModDetailDto;
import com.projekt.klinikaStudyBase.service.VizsgalatiModService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/vizsgalatiMod")
@RequiredArgsConstructor
public class VizsgalatiModEndpoint {

    private final VizsgalatiModService service;

    @ApiOperation(value = "Vizsgalati mod lekerese azonosito alapjan.", notes = "Vizsgalati mod lekerese azonosito alapjan.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<VizsgalatiModDetailDto> getById(@PathVariable(name = "id") final Long vizsgalatiModId) {
        return ResponseEntity.ok(service.findById(vizsgalatiModId));
    }

    @Transactional
    @ApiOperation(value = "Uj vizsgalati mod mentese.", notes = "Uj vizsgalati mod mentese.")
    @PostMapping
    public ResponseEntity<VizsgalatiModDetailDto> add(@RequestBody final VizsgalatiModDetailDto newViszgalatiMod) {
        return ResponseEntity.ok(service.add(newViszgalatiMod));
    }

    @ApiOperation(value = "Vizsgalati modok lekerese.", notes = "Vizsgalati modok lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<VizsgalatiModDetailDto>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Transactional
    @ApiOperation(value = "Vizsgalati mod torlese azonosito alapjan.", notes = "Vizsgalati mod torlese azonosito alapjan.")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final Long id) {
        service.deleteById(id);
    }
}
