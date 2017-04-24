package com.yuxin.wx.controller.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.Connection;

@Controller
@RequestMapping("/database")
public class DatabaseController {

	@RequestMapping(value = "/index")
	public String index(Model model) throws Exception {
		return "system/compare";
	}

	@ResponseBody
	@RequestMapping(value = "/compare")
	public Map<String, Object> compare(Model model, HttpServletRequest request) throws Exception {

		String url1 = request.getParameter("url1");
		String url2 = request.getParameter("url2");

		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> modifys = new HashMap<String, Object>();
		Map<String, Object> deletes = new HashMap<String, Object>();
		Map<String, Object> adds = new HashMap<String, Object>();
		res.put("modifys", modifys);
		res.put("deletes", deletes);
		res.put("adds", adds);

		// 创建连接
		Class.forName("com.mysql.jdbc.Driver");
		Connection m_conn = (Connection) DriverManager.getConnection(url1);

		// 获取表的信息
		DatabaseMetaData m_DBMetaData = m_conn.getMetaData();
		ResultSet m_tableRet = m_DBMetaData.getTables(null, "%", "%", new String[] { "TABLE" });
		Map<String, Map<String, Col>> m_tableMap = new HashMap<String, Map<String, Col>>();

		while (m_tableRet.next()) {
			String tableName = m_tableRet.getString("TABLE_NAME");
			Map<String, Col> cols = new HashMap<String, Col>();
			ResultSet colRet = m_DBMetaData.getColumns(null, "%", tableName, "%");
			String columnName, columnType;
			int datasize, digits, nullable;
			while (colRet.next()) {
				columnName = colRet.getString("COLUMN_NAME");
				columnType = colRet.getString("TYPE_NAME");
				datasize = colRet.getInt("COLUMN_SIZE");
				digits = colRet.getInt("DECIMAL_DIGITS");
				nullable = colRet.getInt("NULLABLE");
				Col col = new Col(columnName, columnType, datasize, digits, nullable);
				cols.put(columnName, col);
			}
			m_tableMap.put(tableName, cols);
			colRet.close();
		}
		m_tableRet.close();
		m_conn.close();
		System.out.println(m_tableMap);

		// 加载第二个数据库数据
		Connection c_conn = (Connection) DriverManager.getConnection(url2);
		// 获取表的信息
		DatabaseMetaData c_DBMetaData = c_conn.getMetaData();
		ResultSet c_tableRet = c_DBMetaData.getTables(null, "%", "%", new String[] { "TABLE" });
		// 遍历表结构，找到不同表结构的表
		while (c_tableRet.next()) {
			String tableName = c_tableRet.getString("TABLE_NAME");
			Map<String, Col> cols = new HashMap<String, Col>();
			// 对比基库
			Map<String, Col> m_cols = m_tableMap.get(tableName);
			if (m_cols == null) {// 若不存在则为新增加的表
				adds.put(tableName, cols);
			} else {// 基库中存在该表,从m_tableMap中移除，方便后续查找删除的表
				m_tableMap.remove(tableName);
			}
			Map<String, Object> modify = new HashMap<String, Object>();
			Map<String, Object> col_modifys = new HashMap<String, Object>();
			Map<String, Object> col_adds = new HashMap<String, Object>();
			Map<String, Object> col_deletes = new HashMap<String, Object>();
			ResultSet colRet = c_DBMetaData.getColumns(null, "%", tableName, "%");
			String columnName, columnType;
			int datasize, digits, nullable;
			while (colRet.next()) {
				columnName = colRet.getString("COLUMN_NAME");
				columnType = colRet.getString("TYPE_NAME");
				datasize = colRet.getInt("COLUMN_SIZE");
				digits = colRet.getInt("DECIMAL_DIGITS");
				nullable = colRet.getInt("NULLABLE");
				Col col = new Col(columnName, columnType, datasize, digits, nullable);
				cols.put(columnName, col);
				// 字段比较
				if (m_cols != null) {
					Col m_col = m_cols.get(col.getColumnName());
					if (m_col == null) {// 新增列
						col_adds.put(col.getColumnName(), col);
					} else {// 移除字段,方便后续挑选删除的字段
						m_cols.remove(col.getColumnName());
						if (!col.compare(m_col)) {
							System.out.println(tableName + "." + columnName + "不一致");
							col_modifys.put(col.getColumnName(), col);
						}
					}
				}
			}
			// 剩余的就是已经被删除的
			if (m_cols != null) {
				col_deletes.putAll(m_cols);
				if (col_modifys.size() > 0 || col_deletes.size() > 0 || col_adds.size() > 0) {
					modify.put("col_modifys", col_modifys);
					modify.put("col_deletes", col_deletes);
					modify.put("col_adds", col_adds);
					modifys.put(tableName, modify);
				}
			}
			colRet.close();
		}
		// 剩余的是删除的表
		deletes.putAll(m_tableMap);
		c_tableRet.close();
		c_conn.close();

		return res;
	}

	public static void main2(String[] args) throws Exception {
		FileReader reader = new FileReader("C:/yunduo_test.sql");
		BufferedReader br = new BufferedReader(reader);
		FileWriter writer = new FileWriter("E:/structors.sql");
		BufferedWriter bw = new BufferedWriter(writer);

		String str = null;
		while ((str = br.readLine()) != null) {
			if (!str.startsWith("INSERT")) {
				System.out.println(str);
				bw.newLine();
				bw.write(str);
			}
		}

		br.close();
		reader.close();

		bw.close();
		writer.close();
	}
}

class Col {
	String columnName, columnType;
	int datasize, digits, nullable;

	Col(String columnName, String columnType, int datasize, int digits, int nullable) {
		this.columnName = columnName;
		this.columnType = columnType;
		this.datasize = datasize;
		this.digits = digits;
		this.nullable = nullable;
	}

	boolean compare(Col _c) {
		if (!columnName.equals(_c.getColumnName()) || !columnType.equals(_c.getColumnType()) || datasize != _c.getDatasize() || digits != _c.getDigits()
		        || nullable != _c.getNullable()) {
			return false;
		}
		return true;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public int getDatasize() {
		return datasize;
	}

	public void setDatasize(int datasize) {
		this.datasize = datasize;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public int getNullable() {
		return nullable;
	}

	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

	@Override
	public String toString() {
		return "columnName:" + columnName + " columnType:" + columnType + " datasize:" + datasize + " digits:" + digits + " nullable:" + nullable;
	}
}
