package com.springboot.Configration;

import com.springboot.domain.VehicleInfo;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class FileUtil {
    public static ResponseEntity<byte[]> exportPlanExcel(List<VehicleInfo> vehicleInfos){
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            //1. 创建一个 Excel 文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2. 创建文档摘要
            workbook.createInformationProperties();
            //3. 获取并配置文档信息
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            dsi.setCategory("计划");
            dsi.setCompany("安康车辆段");
            //4. 获取并设置文档摘要信息
            SummaryInformation si = workbook.getSummaryInformation();
            si.setSubject("检修车计划");
            si.setTitle("检修车计划");
            si.setAuthor("安康车辆段");
            //5. 创建样式
            //创建标题行的样式
            HSSFSheet sheet = workbook.createSheet("安康车辆段检修车计划");
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-mm-dd"));
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //设置列的宽度
            sheet.setColumnWidth(0,8*256);
            sheet.setColumnWidth(1,8*256);
            sheet.setColumnWidth(2,15*256);
            sheet.setColumnWidth(3,15*256);
            sheet.setColumnWidth(4,15*256);
            sheet.setColumnWidth(5,15*256);
            sheet.setColumnWidth(6,15*256);
            sheet.setColumnWidth(7,15*256);
            sheet.setColumnWidth(8,15*256);
            //6. 创建标题行
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("序号");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("台位");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("次数");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("车号");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell4.setCellValue("车型");
            cell4.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(5);
            cell5.setCellValue("轴型");
            cell5.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(6);
            cell6.setCellValue("上次厂修");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(7);
            cell7.setCellValue("下次厂修");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(8);
            cell8.setCellValue("检修日期");
            cell8.setCellStyle(headerStyle);
            //填充数据
            for(int i=0;i<vehicleInfos.size();i++){
                HSSFRow row = sheet.createRow(i+1);
                VehicleInfo vec = vehicleInfos.get(i);
                row.createCell(0).setCellValue(i+1);
                row.createCell(1).setCellValue(vec.getLocation());
                row.createCell(2).setCellValue(vec.getSequence());
                row.createCell(3).setCellValue(vec.getVehicleNumber());
                row.createCell(4).setCellValue(vec.getVehicleType());
                row.createCell(5).setCellValue(vec.getAxleType());
                row.createCell(6).setCellValue(vec.getPreOverhaul());
                row.createCell(7).setCellValue(vec.getNextOverhaul());
                row.createCell(8).setCellValue(vec.getRepairDate());
            }
            //设置返回格式
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("检修车计划.xls".getBytes("UTF-8"),"iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(),headers, HttpStatus.CREATED);
    }

    public static List<VehicleInfo> excel2VehicleInfo(MultipartFile file) {
        List<VehicleInfo> list = new ArrayList<>();
        VehicleInfo vec = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    vec = new VehicleInfo();
                    //8. 数据封装
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        if (cell  ==  null) continue;
                        String cellValue = "";
                        if(cell.getCellType() == CellType.STRING){
                            cellValue = cell.getStringCellValue();
                        }
                        if(cell.getCellType() == CellType.NUMERIC){
                            cellValue = String.valueOf(Math.round(cell.getNumericCellValue()));
                        }
                        switch (k) {
                            case 0:
                                vec.setLocation(cellValue);
                                break;
                            case 1:
                                vec.setSequence(cellValue);
                                break;
                            case 2:
                                vec.setVehicleNumber(cellValue);
                                break;
                            case 3:
                                vec.setVehicleType(cellValue);
                                break;
                            case 4:
                                vec.setAxleType(cellValue);
                                break;
                            case 5:
                                vec.setPreOverhaul(cellValue);
                                break;
                            case 6:
                                vec.setNextOverhaul(cellValue);
                                break;
                            case 7:
                                vec.setRepairDate(cellValue);
                                break;
                            default:
                                break;
                        }
                        }
                    list.add(vec);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
