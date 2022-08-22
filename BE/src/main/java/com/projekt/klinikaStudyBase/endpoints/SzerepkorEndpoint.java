package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.SzerepkorDetailDto;
import com.projekt.klinikaStudyBase.service.SzerepkorService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/szerepkor")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SzerepkorEndpoint {

    private final SzerepkorService service;

    @Transactional
    @ApiOperation(value = "Uj szerepkor mentese.", notes = "Uj szerepkor mentese.")
    @PostMapping
    public ResponseEntity<SzerepkorDetailDto> add(@RequestBody final SzerepkorDetailDto newSzerepkor) {
        return ResponseEntity.ok(service.add(newSzerepkor));
    }

    @ApiOperation(value = "Szerepekörök lekerese.", notes = "Szerepekörök lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<SzerepkorDetailDto>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Transactional
    @ApiOperation(value = "Szerepkor torlese azonosito alapjan.", notes = "Szerepkor torlese azonosito alapjan.")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") final Long userId) {
        service.delete(userId);
    }


    @ApiOperation(value = "Szerepkor lekerese azonosito alapjan.", notes = "Szerepkor lekerese azonosito alapjan.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<SzerepkorDetailDto> get(@PathVariable(name = "id") final Long userId) {
        return ResponseEntity.ok().body(service.get(userId));
    }
}
