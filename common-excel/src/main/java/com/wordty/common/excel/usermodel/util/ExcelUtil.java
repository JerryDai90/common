package com.wordty.common.excel.usermodel.util;

import java.io.FileInputStream;
import java.io.InputStream;

import com.wordty.common.assist.utils.ResourceCloseUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil
{
	/**
	 * 实例化 excel workbook.
	 *
	 * @param path  excel 所在全路径
	 * @return workbook
	 * @throws Exception exception
	 * @author jerry
	 * @date 2017 -06-16 19:12:23
	 */
	public static Workbook getWorkbook(String path) throws Exception{
		FileInputStream fis = null;
		Workbook book = null;
		try{
			fis = new FileInputStream(path);
			book = WorkbookFactory.create(fis);

		}catch (Exception e) {
			throw e;
		}finally{
			ResourceCloseUtil.close(fis);
		}
		return book;
	}
	
}
