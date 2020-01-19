package com.wayfather.springbooteasypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.hutool.core.util.RandomUtil;
import com.wayfather.springbooteasypoi.common.DownloadWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

/**
 * @author IBM
 * @className ExportController
 * @description TODO
 * @date 2019/12/9 11:45
 **/
@Controller
@RequestMapping("export")
public class ExportController {
    @Autowired
    ResourceLoader resourceLoader;
    @RequestMapping("easypoi")
    public void exportEasyPOI(HttpServletRequest request, HttpServletResponse response) {
        Resource resource = resourceLoader.getResource("classpath:xlstemplate/businessTemplate.xlsx");
//        TemplateExportParams params = new TemplateExportParams(
//                "src/main/resources/xlstemplate/businessTemplate.xlsx");
        System.out.println(((ClassPathResource) resource).getPath());
        TemplateExportParams params = new TemplateExportParams(
                ((ClassPathResource) resource).getPath());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("businessNumber", "1910221439386584asdf");
        map.put("checkNumber", "JS20191128001");
        map.put("sellerUnit", "XXXX科技有限公司");
        map.put("buyerUnit", "XXXX集团有限责任公司");


        List<Map<String, String>> listMap = new ArrayList<>();
        int totalCount=0;
        BigDecimal allMoney=new BigDecimal(0);

        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("category", "环保" + i);
            lm.put("sku", "AT000" + i);
            lm.put("productName", "testname"+i);
            lm.put("unit", "条");
            int count=RandomUtil.randomInt(1000,9999);
            totalCount+=count;
            lm.put("count", count+"");
            int price=RandomUtil.randomInt(1000,9999);
            lm.put("price", price+"");
            lm.put("totalMoney", count*price + "");
            allMoney.add(new BigDecimal(count*price));
            lm.put("receiveDate",LocalDate.now().plusDays(RandomUtil.randomInt(1,5))+"");

            listMap.add(lm);
        }
        map.put("maplist", listMap);
        map.put("totalCount", totalCount+"");
        map.put("allMoney", allMoney);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);

        if(workbook!=null){
            try {
                ByteArrayOutputStream outPut = new ByteArrayOutputStream();
                workbook.write(outPut);
                byte[] bytes = outPut.toByteArray();
                String fileName = "业务结算单test.xlsx";
                response.setCharacterEncoding("UTF-8");
                DownloadWriter.writeToResponse(response, bytes, "application/vnd.ms-excel", fileName);
            } catch (IOException e) {
                throw new RuntimeException("下载业务结算单出错：" + e.getMessage());
            }
        }else{
            System.out.println("没找到");
        }


        return;
    }
}
