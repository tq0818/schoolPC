package com.yuxin.wx.controller.tiku;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.tiku.ITikuTopicOptionService;
import com.yuxin.wx.api.tiku.ITikuTopicService;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.model.tiku.TikuTopicOption;
import com.yuxin.wx.utils.WebUtils;

@Controller
@RequestMapping("/tikuSync")
public class TikuSyncController {

    @Autowired
    ITikuTopicService tikuTopicService;
    @Autowired
    ITikuTopicOptionService tikuTopicOptionService;

    /**
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/importFromExcel")
    public Map<String, Object> importFromExcel(Model model, HttpServletRequest request, HttpServletResponse rsp, TikuSubject subject) {
        Map<String, Object> rlt = new HashMap<String, Object>();
        List<Map<String, Object>> failed = new ArrayList<Map<String, Object>>();
        String file = "D:/tikuImport.xls";
        FileInputStream fis = null;
        Workbook wb = null;
        try {
            fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            Cookie cookie = new Cookie("importtiku", bigInt.toString(16));
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("importtiku")) {
                    String[] importList = cookie.getValue().split("&&");
                    for (String s : importList) {
                        if (s.equals(file)) {
                            throw new Exception("改文件已被导入,请重新选择");
                        }
                    }
                    cookie.setValue(cookie.getValue() + "&&" + c.getValue());
                    break;
                }
            }
            rsp.addCookie(cookie);
            if (file.endsWith(".xls")) {
                wb = new HSSFWorkbook(fis);
            } else if (file.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(fis);
            } else {
                throw new Exception(file + "不是excel文件");
            }
            rlt.put("ec", "0");
            rlt.put("em", "文件导入成功");
        } catch (Exception e) {
            e.printStackTrace();
            rlt.put("ec", "-1");
            rlt.put("em", e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Sheet sheet = wb.getSheet("Sheet1");
        int lrow = sheet.getLastRowNum();
        int frow = 4;
        for (int r = frow; r < lrow; r++) {
            // 捕捉过程中出现的异常,导入出错不中断
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            try {
                TikuTopic topic = new TikuTopic();
                String topicType = topicTypeConvert(topic, getCellValue(row.getCell(1), false).toString(), true);
                topic.setTopicType(topicType);
                topic.setTopicName(topicNameConvert(topic, getCellValue(row.getCell(2), true).toString()));
                topic.setAnalyseWord(topicAnalysisConvert(topic, getCellValue(row.getCell(10), true).toString()));
                topic.setDifficulty(topicDifficultyConvert(topic, getCellValue(row.getCell(11), false).toString(), true));
                topic.setTikuSubjectId(206);
                topic.setTikuCategoryId(98);
                topic.setParentId(0);
                topic.setCompanyId(11749);
                topic.setPaperFlag(0);
                topic.setChildFlag(0);
                topic.setCreator(WebUtils.getCurrentUserId(request));
                topic.setCreateTime(new Date());
                topic.setStatus("PAPER_STATUS_PUBLISH");
                tikuTopicService.insert(topic);

                // 单选,多选,判断,不定项
                if (topicType.equals("TOPIC_TYPE_RADIO") || topicType.equals("TOPIC_TYPE_MULTIPLE") || topicType.equals("TOPIC_TYPE_UNDEFINED")) {
                    topic.setAnswer(topicAnswerConvert(topic, getCellValue(row.getCell(3), false).toString()));
                    Map<String, String> answerMap = new HashMap<String, String>();
                    answerMap.put("0", "A");
                    answerMap.put("1", "B");
                    answerMap.put("2", "C");
                    answerMap.put("3", "D");
                    answerMap.put("4", "E");
                    answerMap.put("5", "F");
                    int optionTotal = 0;
                    for (int o = 0; o < 6; o++) {
                        TikuTopicOption option = new TikuTopicOption();
                        option.setTopicId(topic.getId());
                        Cell optionCell = row.getCell(4 + o);
                        if (optionCell == null) {
                            continue;
                        } else {
                            optionTotal++;
                        }
                        String optionNo = answerMap.get(o + "");
                        if (topic.getAnswer().indexOf(optionNo) != -1) {
                            option.setCorrectFlag(1);
                        } else {
                            option.setCorrectFlag(0);
                        }
                        option.setOptionNo(optionNo);
                        option.setOptionName(getCellValue(optionCell, false).toString());
                        tikuTopicOptionService.insert(option);
                    }
                    if (optionTotal == 0) {
                        throw new Exception("选项为空");
                    }
                } else if (topicType.equals("TOPIC_TYPE_TRUE_FALSE")) {
                    topic.setAnswer(topicAnswerConvert(topic, getCellValue(row.getCell(3), false).toString()));
                    Map<String, String> answerMap = new HashMap<String, String>();
                    answerMap.put("0", "A");
                    answerMap.put("1", "B");
                    for (int o = 0; o < 2; o++) {
                        TikuTopicOption option = new TikuTopicOption();
                        option.setTopicId(topic.getId());
                        Cell optionCell = row.getCell(4 + o);
                        if (optionCell == null) {
                            continue;
                        }
                        String optionNo = answerMap.get(o + "");
                        if (topic.getAnswer().indexOf(optionNo) != -1) {
                            option.setCorrectFlag(1);
                        } else {
                            option.setCorrectFlag(0);
                        }
                        option.setOptionNo(optionNo.equals("A") ? "对" : "错");
                        tikuTopicOptionService.insert(option);
                    }
                } else if (topicType.equals("TOPIC_TYPE_FILLING")) {// 填空题
                    StringBuffer answerBuffer = new StringBuffer();
                    for (int o = 0; o < 6; o++) {
                        Cell optionCell = row.getCell(4 + o);
                        if (optionCell == null) {
                            continue;
                        }
                        Object cellValue = getCellValue(optionCell, false);
                        answerBuffer.append(cellValue.toString() + "&&");
                    }
                    String answer = answerBuffer.substring(0, answerBuffer.lastIndexOf("&&"));
                    topic.setAnswer(answer);
                    tikuTopicService.update(topic);
                } else if (topicType.equals("TOPIC_TYPE_ANSWER")) {// 简答题
                    topic.setAnswer(topicAnswerConvert(topic, getCellValue(row.getCell(3), true).toString()));
                    tikuTopicService.update(topic);
                }
            } catch (Exception e) {
                // 导入失败,返回导入失败的行数
                Map<String, Object> item = new HashMap<String, Object>();
                item.put(getCellValue(row.getCell(0), true).toString(), e.getMessage());
                failed.add(item);
            }
        }
        rlt.put("failed", failed);
        return rlt;
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportExcel.xls")
    public void exportExcel(Model model, HttpServletRequest request, HttpServletResponse response, TikuSubject subject) {
        String file = "D:/tikuExportTemplate.xls";
        InputStream is = null;
        Workbook wb = null;
        try {
            is = new FileInputStream(file);
            if (file.endsWith(".xls")) {
                wb = new HSSFWorkbook(is);
            } else if (file.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                throw new Exception(file + "是excel文件");
            }
            Sheet sheet = wb.getSheet("Sheet1");
            TikuTopic topicSearch = new TikuTopic();
            topicSearch.setPageSize(99999);
            topicSearch.setCompanyId(11749);
            topicSearch.setTikuSubjectId(206);
            List<TikuTopic> tikuTopicList = tikuTopicService.findTikuTopicByPage(topicSearch);
            for (int t = 0; t < tikuTopicList.size(); t++) {
                TikuTopic topic = tikuTopicList.get(t);
                String topicType = topic.getTopicType();
                if (topicType.equals("TOPIC_TYPE_CASE") || (topic.getParentId() != null && !topic.getParentId().equals(0))) {
                    continue;
                }
                // 开始创建
                Row row = sheet.createRow(sheet.getLastRowNum() + 1);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(t + 1);
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(topicTypeConvert(topic, topic.getTopicType(), false));
                Cell cell2 = row.createCell(2);
                String topicName = topic.getTopicName();
                topicName = topicName.replaceAll("<br/>", "\n");
                topicName = topicName.replaceAll("<p/>", "");
                topicName = topicName.replaceAll("<p>", "");
                topicName = topicName.replaceAll("</p>", "");
                cell2.setCellValue(topicName);
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(topic.getAnswer());
                Cell cell10 = row.createCell(10);
                String analyseWord = topic.getAnalyseWord();
                analyseWord = analyseWord.replaceAll("<br/>", "\n");
                analyseWord = analyseWord.replaceAll("<p/>", "");
                analyseWord = analyseWord.replaceAll("<p>", "");
                analyseWord = analyseWord.replaceAll("</p>", "");
                cell10.setCellValue(analyseWord);
                Cell cell11 = row.createCell(11);
                cell11.setCellValue(topicDifficultyConvert(topic, topic.getDifficulty(), false));
                if (topicType.equals("TOPIC_TYPE_RADIO") || topicType.equals("TOPIC_TYPE_MULTIPLE") || topicType.equals("TOPIC_TYPE_UNDEFINED")) {
                    TikuTopicOption tikuTopicSearch = new TikuTopicOption();
                    tikuTopicSearch.setTopicId(topic.getId());
                    List<TikuTopicOption> tikuTopicOptionList = tikuTopicOptionService.findTikuTopicOptionByPage(tikuTopicSearch);
                    if (tikuTopicOptionList != null && tikuTopicOptionList.size() > 0) {
                        for (int o = 0; o < tikuTopicOptionList.size() && o < 6; o++) {
                            Cell cell = row.createCell(4 + o);
                            cell.setCellValue(tikuTopicOptionList.get(o).getOptionName());
                        }
                    }
                } else if (topicType.equals("TOPIC_TYPE_TRUE_FALSE")) {
                    TikuTopicOption tikuTopicSearch = new TikuTopicOption();
                    tikuTopicSearch.setTopicId(topic.getId());
                    List<TikuTopicOption> tikuTopicOptionList = tikuTopicOptionService.findTikuTopicOptionByPage(tikuTopicSearch);
                    if (tikuTopicOptionList != null && tikuTopicOptionList.size() > 0) {
                        for (int o = 0; o < tikuTopicOptionList.size() && o < 6; o++) {
                            Cell cell = row.createCell(4 + o);
                            cell.setCellValue(tikuTopicOptionList.get(o).getOptionNo());
                        }
                    }
                } else if (topicType.equals("TOPIC_TYPE_FILLING")) {// 填空题
                    String answer = topic.getAnswer();
                    String[] answers = answer.split("&&");
                    for (int o = 0; o < answers.length && o < 6; o++) {
                        Cell cell = row.createCell(4 + o);
                        cell.setCellValue(answers[o]);
                    }
                    cell3.setCellValue("");
                } else if (topicType.equals("TOPIC_TYPE_ANSWER")) {// 简答题
                    String answer = topic.getAnswer();
                    answer = answer.replaceAll("<br/>", "\n");
                    answer = answer.replaceAll("<p/>", "");
                    answer = answer.replaceAll("<p>", "");
                    answer = answer.replaceAll("</p>", "");
                    cell3.setCellValue(answer);
                }
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String topicTypeConvert(TikuTopic topic, String ot, boolean key) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "TOPIC_TYPE_RADIO");
        map.put("2", "TOPIC_TYPE_MULTIPLE");
        map.put("3", "TOPIC_TYPE_TRUE_FALSE");
        map.put("4", "TOPIC_TYPE_UNDEFINED");
        map.put("5", "TOPIC_TYPE_FILLING");
        map.put("6", "TOPIC_TYPE_ANSWER");
        if (key) {
            String rlt = map.get(ot);
            if (rlt == null) {
                throw new Exception("试题类型不存在；");
            }
            return rlt;
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (ot.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
            return null;
        }
    }

    private static String topicNameConvert(TikuTopic topic, String ot) throws Exception {
        if (ot == null) {
            throw new Exception("未编辑试题内容；");
        }
        return ot;
    }

    private static String topicAnswerConvert(TikuTopic topic, String ot) throws Exception {
        String topicType = topic.getTopicType();
        if (!topicType.equals("TOPIC_TYPE_TRUE_FALSE") && (ot == null || ot.equals(""))) {
            throw new Exception("试题答案为空");
        }
        if (topicType.equals("TOPIC_TYPE_TRUE_FALSE")) {
            if (!ot.equals("A") && !ot.equals("B")) {
                throw new Exception("试题答案不符合规范");
            }
        }
        if (topicType.equals("TOPIC_TYPE_RADIO") || topicType.equals("TOPIC_TYPE_MULTIPLE") || topicType.equals("TOPIC_TYPE_UNDEFINED")) {
            for (int i = 0; i < ot.length(); i++) {
                char a = ot.charAt(i);
                if (a != 'A' && a != 'B' && a != 'C' && a != 'D' && a != 'E' && a != 'F') {
                    throw new Exception("试题答案不符合规范");
                }
            }
        }
        return ot;
    }

    private static String topicAnalysisConvert(TikuTopic topic, String ot) {
        return ot;
    }

    private static String topicDifficultyConvert(TikuTopic topic, String ot, boolean key) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "DIFFICULT_TYPE_EASY");
        map.put("2", "DIFFICULT_TYPE_MIDDLE");
        map.put("3", "DIFFICULT_TYPE_DIFFICULT");
        if (key) {
            String rlt = map.get(ot);
            if (rlt == null) {
                throw new Exception("试题难度配置不正确");
            }
            return rlt;
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (ot.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
            return null;
        }
    }

    private static Object getCellValue(Cell cell, boolean ptag) {

        Object obj = null;
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_NUMERIC: // 数字
            DecimalFormat df = new DecimalFormat("#");
            obj = df.format(cell.getNumericCellValue());
            break;
        case Cell.CELL_TYPE_STRING: // 字符串
            obj = cell.getStringCellValue();
            StringBuffer sb = new StringBuffer();
            String value = obj.toString();
            String[] split = value.split("\n");
            for (int i = 0; i < split.length; i++) {
                if (ptag) {
                    sb.append("<p>" + split[i] + "</p><br/>");
                } else {
                    sb.append(split[i] + "<br/>");
                }
            }
            obj = sb.substring(0, sb.lastIndexOf("<br/>"));
            break;
        case Cell.CELL_TYPE_BOOLEAN: // Boolean
            obj = cell.getBooleanCellValue();
            break;
        case Cell.CELL_TYPE_FORMULA: // 公式
            // obj = cell.getCellFormula();
            // 计算结果
            try {
                obj = String.valueOf(cell.getNumericCellValue());
            } catch (IllegalStateException e) {
                obj = String.valueOf(cell.getRichStringCellValue());
            }
            break;
        case Cell.CELL_TYPE_BLANK: // 空值
            obj = "";
            break;
        case Cell.CELL_TYPE_ERROR: // 故障
            obj = "";
            break;
        default:
            break;
        }
        return obj;
    }
}
