package com.ssm.util;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public final class ExportExcelUtil {

	/**
	 * 导出excel
	 * @param response
	 * @param fileName 文件标题
	 * @param title 内容标题
	 * @param smailTitleList 小标题列表
	 * @param colWidth 列宽
	 * @param list 数据列表
	 */
	public static void export(HttpServletResponse response, String fileName, String title, 
			String[] smailTitleList, int colWidth, List<String[]> list){
		try{
			// 取得输出流
			OutputStream os = response.getOutputStream();
			// 创建工作簿
	        WritableWorkbook wbook = Workbook.createWorkbook(os);
	        // 创建第一个sheet
			WritableSheet wsheet = wbook.createSheet(fileName, 0);
			// 文件名为中文
			fileName = new String(fileName.getBytes(), "iso-8859-1");
			// 清空输出流   
			response.reset();
			// 设定输出文件头
	        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
	        // 定义输出类型 
	        response.setContentType("application/msexcel");
	        // 开始行数,如果有标题的话，标题会占一行
	        int row = 0;
	        // 如果title不为空，说明有标题
	        if(StringUtils.isNotBlank(title)){
	        	//有标题，行数加1
	        	row += 1;
	        	// 标题合并单元格，合并的列数为小标题数量减1
	        	wsheet.mergeCells(0, 0,smailTitleList.length - 1, 0);
	        	// 设置字体样式
	    		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
	    		WritableCellFormat wcfFC = new WritableCellFormat(wfont); 
	    		// 设置字体居中
	    		wcfFC.setAlignment(Alignment.CENTRE);
	    		//设置背景颜色
	    		wcfFC.setBackground(Colour.AQUA);
	    		// 设置边框
	    		wcfFC.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
	    		// 设置带样式的标题
	        	wsheet.addCell(new Label(0, 0, title, wcfFC));
	        }
	        /**设置数据小标题**/
	        //小标题字体样式
	        WritableFont wfont1 = new WritableFont(WritableFont.ARIAL,10, WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat wcfFC1 = new WritableCellFormat(wfont1); 
			//设置字体居中
			wcfFC1.setAlignment(Alignment.CENTRE);
			//设置小标题背景颜色
			wcfFC1.setBackground(Colour.YELLOW);
			// 设置边框
			wcfFC1.setBorder(Border.ALL, BorderLineStyle.THIN);
			//循环输出小标题
	        for(int i = 0; i < smailTitleList.length; i ++){
	        	String stitle = smailTitleList[i];
	        	wsheet.addCell(new Label(i, row, stitle, wcfFC1));
	        	//设置小标题宽度, 如果等于0，就为默认
	        	if(colWidth != 0){
		        	wsheet.setColumnView(i , colWidth);
	        	}
	        }
	        //设置完小标题，行数加1
	        row += 1;
	        /**开始输出数据**/
	        WritableCellFormat cFormat = new WritableCellFormat();
            //水平居中对齐
	        cFormat.setAlignment(Alignment.CENTRE);
            //竖直方向居中对齐
	        cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	        // 设置边框
	        cFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
	        //循环输出内容
	        for(int i = 0; i < list.size(); i ++){
	        	String[] objs = list.get(i);
	        	for(int j = 0; j < objs.length; j ++){
	        		wsheet.addCell(new Label(j, i + row, objs[j], cFormat));
	        	}
	        }
	        // 写入文件
	        wbook.write();
	        // 关闭文件
			wbook.close();
			// 关闭流
			os.close(); 
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

