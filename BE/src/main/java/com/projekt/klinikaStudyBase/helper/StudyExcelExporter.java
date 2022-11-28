package com.projekt.klinikaStudyBase.helper;

import com.projekt.klinikaStudyBase.dto.MeresDetailDto;
import com.projekt.klinikaStudyBase.dto.StudyDetailDto;
import com.projekt.klinikaStudyBase.dto.VizsgalatiModDetailDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudyExcelExporter {
    int rowCount = 1;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    StudyDetailDto studyDetailDto;

    public StudyExcelExporter(StudyDetailDto studyDetailDto) {
        this.studyDetailDto = studyDetailDto;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Vizsgálati adatlap");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Adat", style);
        createCell(row, 1, "Érték", style);
    }

    private void writeDataLines() {

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        CellStyle boldStyle = workbook.createCellStyle();
        XSSFFont boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldFont.setFontHeight(12);
        boldStyle.setFont(boldFont);

        createRowWithData("Cím", studyDetailDto.getCim());
        createRowWithData("Study kód:", studyDetailDto.getStudyKod());
        //createRowWithData("Megbízó cég:", studyDetailDto.getMegbizoCeg());
        //createRowWithData("CRO", studyDetailDto.getEljaroCeg().getNev());
        //createRowWithData("Principal investigator:", studyDetailDto.getKutatasVezeto()); //TODO
        sheet.createRow(rowCount++); //emptyRow

        //Monitor:
        createRowWithData("Monitor:", "");
        createRowWithData("Név:", studyDetailDto.getMonitor().getNev());
        createRowWithData("Email:", studyDetailDto.getMonitor().getEmail());
        createRowWithData("Telefon:", studyDetailDto.getMonitor().getTelefonSzam());
        sheet.createRow(rowCount++); //emptyRow

        //Pénzügyi kontakt
        createRowWithData("Pénzügyi kontakt:", "");
        createRowWithData("Név:", studyDetailDto.getMonitor().getNev());
        createRowWithData("Email:", studyDetailDto.getMonitor().getEmail());
        createRowWithData("Telefon:", studyDetailDto.getPenzugyiKontakt().getTelefonSzam());
        createRowWithData("Postacím:", studyDetailDto.getPenzugyiKontakt().getPostaCim());
        sheet.createRow(rowCount++);

        //Szamlazasi cim
        createRowWithData("Számlázási cím:", "");
        /*createRowWithData("Cég:", studyDetailDto.getEljaroCeg().getNev());
        createRowWithData("Cím:", studyDetailDto.getEljaroCeg().getCim());
        createRowWithData("Adószám:", studyDetailDto.getEljaroCeg().getAdoszam());*/
        sheet.createRow(rowCount++);

        //Szerződéskötés dátuma
        createRowWithData("Szerződéskötés dátuma:", studyDetailDto.getSzerzodeskotesDatum() == null ?
                null : studyDetailDto.getSzerzodeskotesDatum().toString());
        sheet.createRow(rowCount++);

        //Fizetési periódus
        Row fizetesiPeriodusRow = createRowWithData("Fizetési periódus", studyDetailDto.getFizetesiPeriodusDetailDto().getMegnevezes());
        createCell(fizetesiPeriodusRow, 2, "Más:", style);
        sheet.createRow(rowCount++);

        //árlista
        Row arListaRow = sheet.createRow(rowCount++);
        createCell(arListaRow, 0, "Árlista", style);
        createCell(arListaRow, 1, "HUF", style);
        createCell(arListaRow, 2, "EUR", style);
        createCell(arListaRow, 3, "Megjegyzés", style);

        /*for (VizsgalatiModDetailDto vizsgalatiModDetailDto : studyDetailDto.getVizsgalatiModDetailDtoList()) {
            Row vizsgalatiModRekordRow = sheet.createRow(rowCount++);
            createCell(vizsgalatiModRekordRow, 0, vizsgalatiModDetailDto.getMegnevezes(), boldStyle);
            createCell(vizsgalatiModRekordRow, 1, vizsgalatiModDetailDto.getHuf(), boldStyle);
            createCell(vizsgalatiModRekordRow, 2, vizsgalatiModDetailDto.getEur(), boldStyle);
        }*/
        sheet.createRow(rowCount++);

        //Mérések
        List<MeresDetailDto> sortedMeresekList = sortMeresekByDate();
        for (MeresDetailDto meresDetailDto : sortedMeresekList) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");
            String meresYear = simpleDateFormat.format(meresDetailDto.getDatum());
            if (meresDetailDto.equals(sortedMeresekList.get(0))) {
                createMeresekHeader(meresYear);
            } else if (
                    !simpleDateFormat.format(sortedMeresekList.get(sortedMeresekList.indexOf(meresDetailDto) - 1).getDatum())
                            .equals(meresYear)
            ) {
                sheet.createRow(rowCount++);
                createMeresekHeader(meresYear);
            }

            Row vizsgalatiModRekordRow = sheet.createRow(rowCount++);
            createCell(vizsgalatiModRekordRow, 0, meresDetailDto.getAlanyNev(), style);
            createCell(vizsgalatiModRekordRow, 1, meresDetailDto.getAlanyNev(), style); //Beteg azonosito TODO
            createCell(vizsgalatiModRekordRow, 2, meresDetailDto.getTaj(), style);
            createCell(vizsgalatiModRekordRow, 3, "TODO", style);
            createCell(vizsgalatiModRekordRow, 4, meresDetailDto.getDatum().toString(), style);
            createCell(vizsgalatiModRekordRow, 5, meresDetailDto.getFeltoltesSzam(), style);
            //createCell(vizsgalatiModRekordRow, 6, new ArrayList<>(meresDetailDto.getVizsgalatok()).get(0), style);
            createCell(vizsgalatiModRekordRow, 7, meresDetailDto.getVizsgalatAra(), style);
            createCell(vizsgalatiModRekordRow, 8, meresDetailDto.getVizsgaloOrvosNev(), style);
            createCell(vizsgalatiModRekordRow, 9, meresDetailDto.getOperator1IdNev(), style);
            createCell(vizsgalatiModRekordRow, 10, meresDetailDto.getOperator2IdNev(), style);
            createCell(vizsgalatiModRekordRow, 11, meresDetailDto.isFizetve() ? "IGEN" : "NEM", style);
            createCell(vizsgalatiModRekordRow, 12, meresDetailDto.getMegjegyzes(), style);
        }
    }

    public Row createRowWithData(String key, String value) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        CellStyle boldStyle = workbook.createCellStyle();
        XSSFFont boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldFont.setFontHeight(12);
        boldStyle.setFont(boldFont);

        Row row = sheet.createRow(rowCount++);
        createCell(row, 0, key, boldStyle);
        createCell(row, 1, value, style);
        return row;
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double){
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    public List<MeresDetailDto> sortMeresekByDate() {
        Collections.sort(studyDetailDto.getMeresek(), new Comparator<MeresDetailDto>() {

            public int compare(MeresDetailDto o1, MeresDetailDto o2) {
                return o1.getDatum().compareTo(o2.getDatum());
            }
        });
        Collections.reverse(studyDetailDto.getMeresek());
        return studyDetailDto.getMeresek();
    }

    public void createMeresekHeader(String ev) {
        CellStyle boldStyle = workbook.createCellStyle();
        XSSFFont boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldFont.setFontHeight(12);
        boldStyle.setFont(boldFont);

        Row meresekRow = sheet.createRow(rowCount++);
        createCell(meresekRow, 0, ev + " évi mérések", boldStyle);
        createCell(meresekRow, 1, "Beteg azonosító", boldStyle);
        createCell(meresekRow, 2, "TAJ", boldStyle);
        createCell(meresekRow, 3, "DOB", boldStyle);
        createCell(meresekRow, 4, "Dátum", boldStyle);
        createCell(meresekRow, 5, "Feltöltési szám", boldStyle);
        createCell(meresekRow, 6, "Mérés", boldStyle);
        createCell(meresekRow, 7, "Vizsgálat ára", boldStyle);
        createCell(meresekRow, 8, "Radiológus", boldStyle);
        createCell(meresekRow, 9, "Operátor1", boldStyle);
        createCell(meresekRow, 10, "Operátor2", boldStyle);
        createCell(meresekRow, 11, "Fizetve", boldStyle);
        createCell(meresekRow, 12, "Megjegyzés", boldStyle);
    }

}
