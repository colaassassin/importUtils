//package com.example.demo.utils;
//
//import org.apache.poi.hssf.usermodel.*;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ExcelUtils {
//    public Map<String,Object> exportMessageExcelFile(String title, String[] headers, List dossierList, OutputStream out, String pattern)
//    {
//        boolean flag = true;
//        Map<String,Object> map = new HashMap<String,Object>();
//        StringBuffer messageFile = new StringBuffer();
//        // 声明一个工作薄
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(title);
//        // 设置表格默认列宽度为15个字节
//        sheet.setDefaultColumnWidth((short) 15);
//        // 生成一个样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        //产生表格标题行
//        HSSFRow row = sheet.createRow(0);
//        for (short i = 0; i < headers.length; i++) {
//            HSSFCell cell = row.createCell(i);
//            cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text);
//        }
//        for(int i = 0; i< dossierList.size();i++)
//        {
//            Object[] obj =  (Object[]) dossierList.get(i);
//            row = sheet.createRow(i+1);
//            for (int j = 0; j < obj.length; j++) {
//                HSSFCell cell = row.createCell(j);
//                cell.setCellStyle(style);
//                if(j==0)
//                {
//                    cell.setCellValue(i+1);//序号
//                }
//                if(j==1)
//                {
//                    //Logger.debug("obj[5]"+obj[5]);
//                    cell.setCellValue(obj[5]==null?"":obj[5].toString());//办理人
//                }
//                if(j==2)
//                {
//                    //Logger.debug("obj[3]"+obj[3]);
//                    cell.setCellValue(obj[3]==null?"":obj[3].toString());//办理时间
//                }
//                if(j==3)
//                {
//                    // Logger.debug("obj[2]"+obj[2]);
//                    cell.setCellValue(obj[2]==null?obj[6]==null?"":obj[6].toString():obj[2].toString());//办理意见
//                    if(null!=obj[6]&&!"".equals(obj[6]))
//                    {
//                        messageFile.append(obj[6].toString()+",");
//                    }
//                    break;
//                }
//            }
//        }
//        map.put("messageFile", messageFile.toString().endsWith(",")?messageFile.toString().substring(0, messageFile.toString().length()-1):messageFile.toString());
//        try {
//            workbook.write(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//            flag = false;
//        }finally {
//            //清理资源
//            try {
//                if(out != null)
//                {
//                    out.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        map.put("flag", flag);
//        return map;
//    }
//
//
//
//}
