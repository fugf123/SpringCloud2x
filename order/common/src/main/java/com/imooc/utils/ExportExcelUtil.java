package com.imooc.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import java.util.List;

public class ExportExcelUtil {

    //sheet标题
    private String title;
    //导出表的列名
    private String[] rowName;
    //导出每列宽度
    private Integer[] widths;
    //导出的数据
    private List<Object[]> dataList = new ArrayList<Object[]>();

    //构造方法，传入要导出的数据
    public ExportExcelUtil(String title, String[] rowName, Integer[] widths,List<Object[]> dataList) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
        this.widths= widths;
    }
    /*
     * 导出数据
     * */
    public XSSFWorkbook export() {
        XSSFWorkbook workbook = new XSSFWorkbook();   // 创建工作簿对象
        try {
            Sheet sheet = workbook.createSheet(title);
            CellStyle cellStyle =getColumnTopStyle(workbook);
            cellStyle.setFillForegroundColor(HSSFColor.LIME.index);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // 定义所需列数
            int columnNum = rowName.length;
            Row rowRowName = sheet.createRow(0);
            rowRowName.setHeight((short) (500)); //设置高度
            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                //设置列宽
                int width = widths[n] == null ? 5000 : widths[n] * 100;
                sheet.setColumnWidth(n, width);
                Cell cellRowName = rowRowName.createCell(n);                //创建列头对应个数的单元格
                cellRowName.setCellValue(rowName[n]);                      //设置列头单元格的值
                cellRowName.setCellStyle(cellStyle);                        //设置列头单元格样式
            }
            //将查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);//遍历每个对象
                Row row = sheet.createRow(i + 1);//创建所需的行数
                row.setHeight((short) (500)); //设置高度
                for (int j = 0; j < obj.length; j++) {
                    Cell cell = null;   //设置单元格的数据类型
                    cell = row.createCell(j);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());                       //设置单元格的值
                    }
                    cell.setCellStyle(getColumnTopStyle(workbook));                 //设置单元格样式
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
    /*
     * 单元格样式
     */
    public CellStyle getColumnTopStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
}
