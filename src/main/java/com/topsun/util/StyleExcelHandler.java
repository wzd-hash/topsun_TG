package com.topsun.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.excel.event.WriteHandler;

public class StyleExcelHandler implements WriteHandler{

	 private CellStyle cellStyle;
	    @Override
	    public void sheet(int i, Sheet sheet) {
	        Workbook workbook = sheet.getWorkbook();
	        // 创建样式
	        cellStyle = workbook.createCellStyle();
	        
	        
			sheet.createFreezePane(0, 1);//冻结第一行窗格
	    }
	 
	    @Override
	    public void row(int i, Row row) {
	    	
	    }
	 
	    @Override
	    public void cell(int i, Cell cell) {
	    	Sheet sheet = cell.getSheet();
	    	
	        if (cell.getRowIndex() >0) {
	            createStyle();
	            cell.setCellStyle(this.cellStyle);
	        }
	        int columWidth =  sheet.getColumnWidth(i);
			 
			 if (columWidth>6000) {
				 sheet.setColumnWidth(i, columWidth+2000);
			 }else {
				 sheet.setColumnWidth(i, 6000);
			 }
	        }
	 
	    private void createStyle() {
	          // 填充色：和EasyExcelUtil里的createTableStyle效果一样
//	        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//	        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
//	        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	 
	        // 下边框
	        cellStyle.setBorderBottom(BorderStyle.THIN);
	        // 左边框
	        cellStyle.setBorderLeft(BorderStyle.THIN);
	        // 右边框
	        cellStyle.setBorderRight(BorderStyle.THIN);
	        // 上边框
	        cellStyle.setBorderTop(BorderStyle.THIN);
	        // 设置自动换行
	        cellStyle.setWrapText(true);
	        // 水平对齐方式
	        cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        cellStyle.setFillBackgroundColor((short)22);
	        // 垂直对齐方式
	        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	    }

	
}
