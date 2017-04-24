package com.yuxin.wx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @ClassName: ImportExcl
 * @Description: excle读取（支持2003和2007）
 * @author zhang.zx
 * @date 2015年9月30日 下午3:06:22
 * @modifier
 * @modify-date 2015年9月30日 下午3:06:22
 * @version 1.0
 */
public class ImportExcl {

	private static Log log=LogFactory.getLog("log");
	/** 总行数 */
	private static int totalRows = 0;

	/** 总列数 */
	private static int totalCells = 0;

	/** 错误信息 */
	private static String errorInfo;

	/** 构造方法 */
	public ImportExcl() {
	}

	/**
	 *得到总行数
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * 得到总列数
	 */
	public static int getTotalCells() {
		return totalCells;
	}

	/**
	 * 得到错误信息
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * 
	 * Class Name: ImportExcl.java
	 * @Description: 验证excle文件
	 * @author zhang.zx
	 * @date 2015年9月30日 下午3:03:43
	 * @modifier
	 * @modify-date 2015年9月30日 下午3:03:43
	 * @version 1.0
	 * @param filePath 文件完整路径
	 * @return
	 */
	private static boolean validateExcel(String filePath) {
		/** 检查文件名是否为空或者是否是Excel格式的文件 */
		if (filePath == null|| !(isExcel2003(filePath)||isExcel2007(filePath))) {
			errorInfo = "文件名不是excel格式";
			return false;
		}
		/** 检查文件是否存在 */
		File file = new File(filePath);
		if (file == null || !file.exists()) {
			errorInfo = "文件不存在";
			return false;
		}
		return true;
	}

	/**
	 * 
	 * Class Name: ImportExcl.java
	 * @Description: 根据文件名读取excel文件
	 * @author zhang.zx
	 * @date 2015年9月30日 下午3:05:00
	 * @modifier
	 * @modify-date 2015年9月30日 下午3:05:00
	 * @version 1.0
	 * @param filePath
	 * @return
	 */
	public static List<List<String>> read(String filePath) {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		InputStream is = null;
		try {
			/** 验证文件是否合法 */
			if (!validateExcel(filePath)) {
				log.info(errorInfo);
				return null;
			}
			/** 判断文件的类型，是2003还是2007 */
			boolean isExcel2003 = true;
			if (isExcel2007(filePath)) {
				isExcel2003 = false;
			}
			/** 调用本类提供的根据流读取的方法 */
			File file = new File(filePath);
			is = new FileInputStream(file);
			dataLst = read(is, isExcel2003);
			is.close();
		} catch (Exception ex) {
			log.error("文件读取失败",ex);
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					log.info("读取文件为空",e);
					e.printStackTrace();
				}
			}
		}
		/** 返回最后读取的结果 */
		return dataLst;
	}

	/**
	 * 
	 * Class Name: ImportExcl.java
	 * @Description: 根据流读取Excel文件
	 * @author zhang.zx
	 * @date 2015年9月30日 下午3:09:05
	 * @modifier
	 * @modify-date 2015年9月30日 下午3:09:05
	 * @version 1.0
	 * @param inputStream
	 * @param isExcel2003
	 * @return
	 */
	public static List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
		List<List<String>> dataLst = null;
		try {
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			dataLst = read(wb);
		} catch (IOException e) {
			log.error("创建Workbook失败",e);
			e.printStackTrace();
		}
		return dataLst;
	}

	/**
	 * 
	 * Class Name: ImportExcl.java
	 * @Description: 读取数据
	 * @author zhang.zx
	 * @date 2015年9月30日 下午3:09:54
	 * @modifier
	 * @modify-date 2015年9月30日 下午3:09:54
	 * @version 1.0
	 * @param wb
	 * @return
	 */
	private static List<List<String>> read(Workbook wb) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<List<String>> dataLst = new ArrayList<List<String>>();
		/** 得到第一个shell */
		Sheet sheet = wb.getSheetAt(0);
		/** 得到Excel的行数 */
		totalRows = sheet.getPhysicalNumberOfRows();
		/** 得到Excel的列数 */
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		/** 循环Excel的行 */
		for (int r = 0; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null || "".equals(row)|| !row.cellIterator().hasNext()) {
				continue;
			}
			List<String> rowLst = new ArrayList<String>();
			/** 循环Excel的列 */
			for (int c = 0; c < getTotalCells(); c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if (null != cell) {
					// 以下是判断数据的类型
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						if(HSSFDateUtil.isCellDateFormatted(cell)){
							cellValue=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
							break;
						}
						cellValue =String.format("%.0f", cell.getNumericCellValue());
						//cell.getNumericCellValue()+"";
						break;

					case HSSFCell.CELL_TYPE_STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;

					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						cellValue = cell.getBooleanCellValue() + "";
						break;

					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						cellValue = cell.getCellFormula() + "";
						break;

					case HSSFCell.CELL_TYPE_BLANK: // 空值
						cellValue = "";
						break;

					case HSSFCell.CELL_TYPE_ERROR: // 故障
						cellValue = "非法字符";
						break;

					default:
						cellValue = "未知类型";
						break;
					}
				}
				rowLst.add(cellValue);
			}
			/** 保存第r行的第c列 */
			dataLst.add(rowLst);
		}
		return dataLst;
	}

	/**
	 * 
	 * Class Name: ImportExcl.java
	 * @Description: 是否是2003的excel，返回true是2003
	 * @author zhang.zx
	 * @date 2015年9月30日 下午3:12:33
	 * @modifier
	 * @modify-date 2015年9月30日 下午3:12:33
	 * @version 1.0
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2003(String filePath) {

		return filePath.matches("^.+\\.(?i)(xls)$");

	}

	/**
	 * 
	 * Class Name: ImportExcl.java
	 * @Description: 是否是2007的excel，返回true是2007
	 * @author zhang.zx
	 * @date 2015年9月30日 下午3:12:11
	 * @modifier
	 * @modify-date 2015年9月30日 下午3:12:11
	 * @version 1.0
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

	
	/**
	 * 
	 * Class Name: ImportExcl.java
	 * @Description: 测试
	 * @author zhang.zx
	 * @date 2015年9月30日 下午3:01:40
	 * @modifier
	 * @modify-date 2015年9月30日 下午3:01:40
	 * @version 1.0
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println();
		ImportExcl poi = new ImportExcl();
		List<List<String>> list = poi.read("F:/user_example.xls");
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				//System.out.print("第" + (i) + "行");
				List<String> cellList = list.get(i);
				//System.out.println(cellList.get(0));
				for (int j = 0; j < cellList.size(); j++) {
					//System.out.print("    第" + (j + 1) + "列值：");
					System.out.print("    " + cellList.get(j));
					//System.out.println(cellList.get(0));
				}
				System.out.println();
			}
		}
	
	}

}