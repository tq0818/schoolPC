package com.yuxin.wx.common;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.utils.ExcelUtil;

public class ViewFiles extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String excelName =(String) model.get("fileName");
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(excelName, "UTF-8"));
		OutputStream ouputStream = response.getOutputStream(); 
		try{
	        @SuppressWarnings("unchecked")
			List<ExcelSheetEntity> list=(List<ExcelSheetEntity>)model.get("list");  
	        ExcelSheetEntity entity=(ExcelSheetEntity)model.get("entity");
	        HSSFWorkbook wb=(HSSFWorkbook)model.get("workbook");
	        if(wb!=null){
	        	workbook=wb;
	        }else if(list==null){
	        	workbook=ExcelUtil.newWorkbook(entity);
	        }else{
	        	workbook=ExcelUtil.newWorkbook(list);
	        }
		}catch(Exception e){
			throw e;
		}finally{
	        workbook.write(ouputStream);     
	        ouputStream.flush();     
	        ouputStream.close();
		}
       
        
	}
	
}
