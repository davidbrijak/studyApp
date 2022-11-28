package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.FizetesiPeriodusDetailDto;
import com.projekt.klinikaStudyBase.service.FizetesiPeriodusService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fizetesiPeriodus")
@RequiredArgsConstructor
public class FizetesiPeriodusEndpoint {

    private final FizetesiPeriodusService fizetesiPeriodusService;

    @ApiOperation(value = "Fizetesi periodus lekerese azonosito alapjan.", notes = "Fizetesi periodus lekerese azonosito alapjan.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<FizetesiPeriodusDetailDto> get(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(fizetesiPeriodusService.find(id));
    }

    @Transactional
    @ApiOperation(value = "Uj periodus mentese.", notes = "Uj periodus mentese.")
    @PostMapping
    public ResponseEntity<FizetesiPeriodusDetailDto> add(@RequestBody @Valid final FizetesiPeriodusDetailDto newPeriodus) {
        return ResponseEntity.ok(fizetesiPeriodusService.add(newPeriodus));
    }

    @ApiOperation(value = "Fizetesi periodusok lekerese.", notes = "Fizetesi perioudusok lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<FizetesiPeriodusDetailDto>> getAll() {
        return ResponseEntity.ok().body(fizetesiPeriodusService.findAll());
    }

    @Transactional
    @ApiOperation(value = "Fizetesi perioudus torlese azonosito alapjan.", notes = "Fizetesi periodus torlese azonosito alapjan.")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final Long id) {
        fizetesiPeriodusService.delete(id);
    }
}
