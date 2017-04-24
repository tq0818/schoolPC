package com.yuxin.wx.controller.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

import com.yuxin.wx.model.course.Video;

public class Ex {

	private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_XLSX = "xlsx";

    /***
     * <pre>
     * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
     *   xls:HSSFWorkbook
     *   xlsx：XSSFWorkbook
     * @param filePath
     * @return
     * @throws IOException
     * </pre>
     */
    private static Workbook getWorkbook(String filePath) throws IOException {
        Workbook workbook = null;
        InputStream is = new FileInputStream(filePath);
        if (filePath.endsWith(EXTENSION_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (filePath.endsWith(EXTENSION_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    /**
     * 文件检查
     * @param filePath
     * @throws FileNotFoundException
     * @throws FileFormatException
     */
    private static void preReadCheck(String filePath) throws FileNotFoundException, FileFormatException {
        // 常规检查
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("传入的文件不存在：" + filePath);
        }

        if (!(filePath.endsWith(EXTENSION_XLS) || filePath.endsWith(EXTENSION_XLSX))) {
            throw new FileFormatException("传入的文件不是excel");
        }
    }

    /**
     * 读取excel文件内容
     * @param filePath
     * @throws FileNotFoundException
     * @throws FileFormatException
     */
    public static void readExcel(String filePath) throws FileNotFoundException, FileFormatException {
        // 检查
        preReadCheck(filePath);
        // 获取workbook对象
        Workbook workbook = null;

        try {
            workbook = getWorkbook(filePath);
            String sql = "";
            List<Video> video = new ArrayList<Video>();
            // 读文件 一个sheet一个sheet地读取
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                System.out.println("=======================" + sheet.getSheetName() + "=========================");

                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();

                // 读取首行 即,表头
                Row firstRow = sheet.getRow(firstRowIndex);
                String xk = "";
                for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
                    Cell cell = firstRow.getCell(i);
                    String cellValue = getCellValue(cell, true);
                    if(!cellValue.equals("")){
                        System.out.print(i + " " + cellValue + "\t");
                        if(i == 0){
                        	xk = cellValue;
                        }
                    }
                }
                System.out.println("");

                // 读取数据行
                for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                    Video vd = new Video();
                	Row currentRow = sheet.getRow(rowIndex);// 当前行
                    int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                    int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                    for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
                        Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                        String currentCellValue = getCellValue(currentCell, true);// 当前单元格的值
                        if(!currentCellValue.equals("")){
                            System.out.print(columnIndex + " " + currentCellValue + "\t");
                            if(columnIndex == 1){
                            	vd.setVideoName(currentCellValue);
                            }
                            if(columnIndex == 2){
                            	vd.setVideoCcId(currentCellValue);
                            }
                            
                        }
                    }

                    if(xk.equals("幼儿园保教知识")){
                        vd.setItemOneId(39300);
                        vd.setItemSecondId(43556);
                    }
                    if(xk.equals("幼儿园综合素质")){
                        vd.setItemOneId(39300);
                        vd.setItemSecondId(43557);
                    }
                    if(xk.equals("强化提升——幼儿园保教知识与能力")){
                        vd.setItemOneId(39300);
                        vd.setItemSecondId(39302);
                    }
                    if(xk.equals("小学教育教学知识与能力")){
                        vd.setItemOneId(42715);
                        vd.setItemSecondId(43558);
                    }
                    if(xk.equals("小学综合素质")){
                        vd.setItemOneId(42715);
                        vd.setItemSecondId(43560);
                    }
                    if(xk.equals("强化提升——小学教育知识与能力")){
                        vd.setItemOneId(42715);
                        vd.setItemSecondId(42717);
                    }
                    if(xk.equals("中学教育知识与能力")){
                        vd.setItemOneId(42721);
                        vd.setItemSecondId(43563);
                    }
                    if(xk.equals("中学综合素质")){
                        vd.setItemOneId(42721);
                        vd.setItemSecondId(43564);
                    }
                    if(xk.equals("强化提升——中学教育知识与能力")){
                        vd.setItemOneId(42721);
                        vd.setItemSecondId(42723);
                    }
                    if(xk.equals("综合素质强化提升（中小幼）")){
                        vd.setItemOneId(43554);
                    }
                    if(xk.equals("面试")){
                        vd.setItemOneId(43555);
                    }
                    vd.setVideoStatus("VIDEO_PROCESS_NOMAL");
                    vd.setCompanyId(13947);
                    vd.setSchoolId(14390);
                    vd.setStorageType("VIDEO_STORAGE_TYPE_CC");
                    video.add(vd);
                    System.out.println("");
                }
                System.out.println("======================================================");
            }
            for (Video v : video) {
				sql += "insert into video values(null,'"+v.getVideoName()+"','"+
						v.getVideoCcId()+"',null,null,'"+v.getVideoStatus()+"',"+
						v.getItemOneId()+","+v.getItemSecondId()+",null,null,"+
						v.getSchoolId()+","+v.getCompanyId()+",null,null,null,null,'"+
						v.getStorageType()+"',null,null,null);\r\n";
			}
            File file = new File("C:/Users/1/Desktop/kcdm.txt");
            FileWriter fw = new FileWriter(file);
            fw.write(sql);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook = null;
            }
        }
    }

    /**
     * 取单元格的值
     * @param cell 单元格对象
     * @param treatAsStr 为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
     * @return
     */
    private static String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }

        if (treatAsStr) {
            // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
            // 加上下面这句，临时把它当做文本来读取
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    public static void main(String[] args){
    	try {
			readExcel("C:/Users/1/Desktop/kcdm.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
