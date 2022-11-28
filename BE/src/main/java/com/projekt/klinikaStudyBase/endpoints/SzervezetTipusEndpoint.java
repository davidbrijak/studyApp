package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.SzervezetTipusDetailDto;
import com.projekt.klinikaStudyBase.service.SzervezetTipusService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/szervezetTipus")
@RequiredArgsConstructor
public class SzervezetTipusEndpoint {

    private final SzervezetTipusService szervezetTipusService;

    @Transactional
    @ApiOperation(value = "Uj szervezet tipus mentese.", notes = "Uj szervezet tipus mentese.")
    @PostMapping
    public ResponseEntity<SzervezetTipusDetailDto> add(@RequestBody final SzervezetTipusDetailDto newSzervezetTipus) {
        return ResponseEntity.ok(szervezetTipusService.add(newSzervezetTipus));
    }

    @ApiOperation(value = "Szervezet tipusok lekerese.", notes = "Rendszerben nyilvatartott szervezet tipusok lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<SzervezetTipusDetailDto>> getAll() {
        return ResponseEntity.ok().body(szervezetTipusService.findAll());
    }

    @Transactional
    @ApiOperation(value = "Szervezet tipus torlese azonosito alapjan.", notes = "Szervezet tipus torlese azonosito alapjan.")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final Long id) {
        szervezetTipusService.delete(id);
    }

    @ApiOperation(value = "Szervezet tipus lekerese.", notes = "Szervezet tipus lekerese.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<SzervezetTipusDetailDto> get(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(szervezetTipusService.find(id));
    }
}
