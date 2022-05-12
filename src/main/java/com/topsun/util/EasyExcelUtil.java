package com.topsun.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.topsun.listener.ExcelListener;

public class EasyExcelUtil {
	
	
	private static Sheet initSheet;

	   static {
	      initSheet = new Sheet(1, 0);
	      initSheet.setSheetName("sheet");
	      //设置自适应宽度
	      initSheet.setAutoWidth(Boolean.TRUE);
	   }


	public static List<Object> readExcel(MultipartFile excel,BaseRowModel rowModel) throws IOException{
		
		
		return readExcel(excel, rowModel, 1, 1);
	}
	
	public static List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel, int sheetNo, int headLineNum) throws IOException {
		
		ExcelListener excelListener = new ExcelListener();
		ExcelReader reader = getReader(excel, excelListener);
		if (reader == null) {
            return null;
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel.getClass()));
        return excelListener.getDatas();
	}
	
	public static ExcelReader getReader(MultipartFile excel, ExcelListener excelListener) throws IOException{
        String fileName = excel.getOriginalFilename();
        if (fileName == null || !(fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx") || fileName.toLowerCase().endsWith(".xlsm"))){
            throw new IOException("文件格式错误!!!");
        }

        InputStream is = new BufferedInputStream(excel.getInputStream());

        return new ExcelReader(is, null, excelListener, false);
    }
	
	public static void writeExcel(HttpServletResponse response,BaseRowModel rowModel,String fileName,List<List<String>> data) throws IOException  {
		
				ServletOutputStream out = null;
			    out = response.getOutputStream();
	            response.setContentType("multipart/form-data");
	            response.setCharacterEncoding("utf-8");
	            //设置返回头为附件形式，并提供下载文件名
	            response.setHeader("Content-disposition","attachment;filename="+URLEncoder.encode(fileName,"utf-8")+".xls");
	            StyleExcelHandler handler = new StyleExcelHandler();
	            ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null, out, ExcelTypeEnum.XLS, true,handler);
	            //提供excel表头模板
	            Sheet sheet = new Sheet(1,0,rowModel.getClass());
	            sheet.setSheetName("sheet1");
	            //写入数据，数据格式为List<List<String>>
//	            List<List<String>> data = new ArrayList<List<String>>();
	            sheet.setStartRow(0);
	            writer.write0(data, sheet);
	            writer.finish();
	            out.flush();
	      
		
	}
	
	
	public static void exportExcel(HttpServletResponse response,BaseRowModel rowModel,String fileName,List<? extends BaseRowModel> list)throws Exception{
		
			ServletOutputStream out = null;
	
		    out = response.getOutputStream();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            //设置返回头为附件形式，并提供下载文件名
            response.setHeader("Content-disposition","attachment;filename="+URLEncoder.encode(fileName,"utf-8")+".xls");
            StyleExcelHandler handler = new StyleExcelHandler();
            ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null, out, ExcelTypeEnum.XLS, true, handler);
            //提供excel表头模板
            Sheet sheet = new Sheet(1,0,rowModel.getClass());
            sheet.setSheetName(fileName);
            //写入数据，数据格式为List<List<String>>
//            List<List<String>> data = new ArrayList<List<String>>();
            sheet.setStartRow(0);
            sheet.setAutoWidth(true); 
            writer.write(list, sheet);
            writer.finish();
            out.flush();
        }
	
	
	public static void exportMoreSheetExcel(HttpServletResponse response,BaseRowModel rowModel,Map<Integer,List<? extends BaseRowModel>> list)throws Exception{
		
		ServletOutputStream out = null;

	    out = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        //设置返回头为附件形式，并提供下载文件名
        response.setHeader("Content-disposition","attachment;filename="+URLEncoder.encode("待整改车辆统计","utf-8")+".xls");
        StyleExcelHandler handler = new StyleExcelHandler();
        ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null, out, ExcelTypeEnum.XLS, true, handler);
        String sheetName = "";
        int count = 1;
        for (Integer key : list.keySet()) {
			switch (key) {
				case 1:sheetName = "未调试";break;
				case 2:sheetName = "流量卡未换";break;
				case 3:sheetName = "电路未整改";break;
				case 4:sheetName = "GPS不定位";break;
				case 5:sheetName = "车辆掉线";break;
			}
			Sheet sheet = new Sheet(count,0,rowModel.getClass());
			 //提供excel表头模板
	        sheet.setSheetName(sheetName);
	        //写入数据，数据格式为List<List<String>>
//	        List<List<String>> data = new ArrayList<List<String>>();
	        sheet.setStartRow(0);
	        sheet.setAutoWidth(true); 
	        writer.write(list.get(key), sheet);
	        count++;
		}
        writer.finish();
        out.flush();
    }
	
	public static void exportOperationExcel(HttpServletResponse response,BaseRowModel rowModel,String fileName,List<? extends BaseRowModel> list)throws Exception{
		
		ServletOutputStream out = null;

	    out = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        //设置返回头为附件形式，并提供下载文件名
        response.setHeader("Content-disposition","attachment;filename="+URLEncoder.encode("运维统计","utf-8")+".xls");
        StyleExcelHandler handler = new StyleExcelHandler();
        ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null, out, ExcelTypeEnum.XLS, true, handler);
        //提供excel表头模板
        Sheet sheet = new Sheet(1,0,rowModel.getClass());
        sheet.setSheetName(fileName);
        sheet.setStartRow(0);
        sheet.setAutoWidth(true); 
        writer.write(list, sheet);
        writer.finish();
        out.flush();
    }

	
	
	public void download(String path, HttpServletResponse response) {
		try {

			String filename = URLEncoder.encode(path, "UTF-8");
			// 以流的形式下载文件。
			InputStream fis = this.getClass().getClassLoader().getResourceAsStream("resources/"+path);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			OutputStream toClient = response.getOutputStream();
			response.setContentType("application/force-download;charset=UTF-8");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void exportExcelByDynamic (HttpServletResponse response,String fileName, List<List<String>> data, List<String> head, Sheet sheet) throws Exception{
	      sheet = (sheet != null) ? sheet : initSheet;

	      if(head != null){
	         List<List<String>> list = new ArrayList<>();
	         head.forEach(h -> list.add(Collections.singletonList(h)));
	         sheet.setHead(list);
	      }

	      sheet.setSheetName(fileName);
	      ServletOutputStream out = response.getOutputStream();
          response.setContentType("multipart/form-data");
          response.setCharacterEncoding("utf-8");
          //设置返回头为附件形式，并提供下载文件名
          response.setHeader("Content-disposition","attachment;filename="+URLEncoder.encode(fileName,"utf-8")+".xls");
          StyleExcelHandler handler = new StyleExcelHandler();
          ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null, out, ExcelTypeEnum.XLS, true, handler);
         
          writer.write0(data, sheet);
          writer.finish();
          out.flush();

	   }


}
