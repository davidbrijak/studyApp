package com.projekt.klinikaStudyBase.endpoints;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.projekt.klinikaStudyBase.dto.MeresDetailDto;
import com.projekt.klinikaStudyBase.dto.MeresFilterDto;
import com.projekt.klinikaStudyBase.dto.NewMeresDto;
import com.projekt.klinikaStudyBase.service.MeresService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/meres")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MeresEndpoint {

    private final MeresService service;

    @Transactional
    @ApiOperation(value = "Uj meres mentese.", notes = "Uj meres mentese.")
    @PostMapping
    public ResponseEntity<NewMeresDto> add(@RequestBody final NewMeresDto newMeresDto) {
        return ResponseEntity.ok(service.add(newMeresDto));
    }


    @Transactional
    @ApiOperation(value = "Meresek lekerese szuro alapjan.", notes = "Meresek lekerese szuro alapjan.")
    @PostMapping(path = "/list/filter")
    public ResponseEntity<List<MeresDetailDto>> getByFilter(@RequestBody final MeresFilterDto meresFilterDto) {
        return ResponseEntity.ok(service.findByFilter(meresFilterDto));
    }

    @ApiOperation(value = "Meresek lekerese.", notes = "Rendszerben nyilvatartott meresek lekerese.")
    @GetMapping(path = "/list")
    public ResponseEntity<List<MeresDetailDto>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation(value = "Meres lekerese.", notes = "Rendszerben nyilvatartott meres lekerese.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<NewMeresDto> get(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(value = "Meresek lekerese study alapjan.", notes = "Rendszerben nyilvatartott meresek lekerese study alapjan.")
    @GetMapping(path = "/list/{id}")
    public ResponseEntity<List<MeresDetailDto>> getAllByStudy(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok().body(service.findStudy(id));
    }

    /*@ApiOperation(value = "Meres exportálása CSV fájlba.", notes = "Rendszerben nyilvatartott mérés exportálása CSV fájlba.")
    @GetMapping(path = "/export-meres/{id}")
    public void exportCSV(@PathVariable(name = "id") final Long id, HttpServletResponse response) throws Exception {

        MeresDetailDto meresDetailDto = service.findById(id);

        //set file name and content type
        String filename = meresDetailDto.getFeltoltesSzam() + "meres.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<MeresDetailDto> writer = new StatefulBeanToCsvBuilder<MeresDetailDto>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all meres to csv file
        writer.write(meresDetailDto);
    }*/
}
