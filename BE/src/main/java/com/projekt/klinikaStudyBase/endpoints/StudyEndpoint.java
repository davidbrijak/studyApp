package com.projekt.klinikaStudyBase.endpoints;

import com.projekt.klinikaStudyBase.dto.NewStudyDto;
import com.projekt.klinikaStudyBase.dto.StudyDetailDto;
import com.projekt.klinikaStudyBase.dto.StudyListDto;
import com.projekt.klinikaStudyBase.helper.StudyExcelExporter;
import com.projekt.klinikaStudyBase.service.StudyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/study")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class StudyEndpoint {

    private final StudyService studyService;

    @Transactional
    @ApiOperation(value = "Uj study mentese.", notes = "Uj study mentese.")
    @PostMapping
    public void add(@RequestBody final NewStudyDto newStudyDto) {
        studyService.add(newStudyDto);
    }

    @ApiOperation(value = "Study lekerese azonosito alapjan.", notes = "Study lekerese azonosito alapjan.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<StudyDetailDto> get(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(studyService.findById(id));
    }

    @ApiOperation(value = "Study-k litazasa.", notes = "Studyk listazasa.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<StudyListDto>> getAll() {
        return ResponseEntity.ok().body(studyService.findAll());
    }

    @GetMapping("/export/{id}")
    public void exportToExcel(@PathVariable(name = "id") final Long id,
                              HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        StudyDetailDto studyDetailDto = studyService.findById(id);

        StudyExcelExporter excelExporter = new StudyExcelExporter(studyDetailDto);

        excelExporter.export(response);
    }
}
