package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.NewSzervezetDto;
import com.projekt.klinikaStudyBase.dto.SzervezetDetailDto;
import com.projekt.klinikaStudyBase.service.SzervezetService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/szervezet")
@RequiredArgsConstructor
public class SzervezetEndpoint {

    private final SzervezetService szervezetService;

    @Transactional
    @ApiOperation(value = "Uj szervezet mentese.", notes = "Uj szervezet mentese.")
    @PostMapping
    public ResponseEntity<SzervezetDetailDto> add(@RequestBody final NewSzervezetDto newSzervezetDto) {
        return ResponseEntity.ok(szervezetService.add(newSzervezetDto));
    }

    @ApiOperation(value = "Szervezetek lekerese.", notes = "Rendszerben nyilvatartott szervezetek lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<SzervezetDetailDto>> getAll() {
        return ResponseEntity.ok().body(szervezetService.findAll());
    }

    @ApiOperation(value = "Szervezetek lekerese szervezet típúsok alapján.",
            notes = "Rendszerben nyilvatartott szervezetek lekerese szervezet típusok alapján.")
    @GetMapping(path = "/tipus/list/{id}")
    public ResponseEntity<List<SzervezetDetailDto>> getAllByTipus(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok().body(szervezetService.findAllByTipus(id));
    }

    @ApiOperation(value = "Szervezet torlese azonosito alapjan.", notes = "Szervezet torlese azonosito alapjan.")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final Long id) {
        szervezetService.delete(id);
    }
}
