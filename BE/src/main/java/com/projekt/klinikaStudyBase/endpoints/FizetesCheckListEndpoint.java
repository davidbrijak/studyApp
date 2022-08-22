package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.FizetesCheckListDto;
import com.projekt.klinikaStudyBase.service.KifizetesCheckService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/kifizetesekCheck")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FizetesCheckListEndpoint {

    private final KifizetesCheckService service;

    @ApiOperation(value = "Kifizetes lekerese.", notes = "Kifizetesek lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<FizetesCheckListDto>> getAll() {
        return ResponseEntity.ok().body(service.getCheckListBySzemely());
    }

    @ApiOperation(value = "Kifizetes lekerese.", notes = "Rendszerben nyilvatartott kifizetes lekerese szemely alapjan.")
    @GetMapping(path = "/list/{id}")
    public ResponseEntity<List<FizetesCheckListDto>> getAllBySzemely(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok().body(service.getCheckListBySzemelyDetail(id));
    }

    @Transactional
    @ApiOperation(value = "Kifizetes lekerese2.", notes = "Rendszerben nyilvatartott kifizetes lekerese szemely alapjan2.")
    @PostMapping(path = "/change")
    public void change(@RequestBody final List<FizetesCheckListDto> fizetesCheckListDtos) {
        service.handleChanged(fizetesCheckListDtos);
    }
}
