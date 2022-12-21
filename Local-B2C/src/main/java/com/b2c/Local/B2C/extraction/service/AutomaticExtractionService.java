package com.b2c.Local.B2C.extraction.service;

import com.b2c.Local.B2C.products.electronic.dao.ACRepository;
import com.b2c.Local.B2C.products.electronic.model.AC;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class AutomaticExtractionService {

    ACRepository acRepository;

    @Autowired
    public AutomaticExtractionService(ACRepository acRepository) {
        this.acRepository = acRepository;
    }

    private static final String SHEET = "excelHandler";

    static String[] headers = {"acId", "model", "brand", "colour", "warranty", "digitalDisplay", "weightInKg", "discountPercentage",
    "powerInStar", "availability", "builtInStabilizer", "capacityInTon","mode", "timer", "wiFi", "airConditionerType", "active", "price"};

    public static ByteArrayInputStream excelHandler(List<AC> list){
        try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row row = sheet.createRow(0);

            for (int col = 0; col < headers.length; col++) {
                Cell cell = row.createCell(col);
                cell.setCellValue(headers[col]);
            }

            int rowIdx = 1;
            for (AC ac : list) {
                Row row1 = sheet.createRow(rowIdx++);

                row1.createCell(0).setCellValue(ac.getBrand());
                row1.createCell(1).setCellValue(ac.getColour());
            }

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to excel file :" + e.getMessage());
        }
    }

    public ByteArrayInputStream load() throws IOException {
        List<AC> list = acRepository.findAll();
        ByteArrayInputStream stream = AutomaticExtractionService.excelHandler(list);
        return stream;
    }
}
