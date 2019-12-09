package com.wayfather.springbooteasypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.wayfather.springbooteasypoi.common.DownloadWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author IBM
 * @className ExportController
 * @description TODO
 * @date 2019/12/9 11:45
 **/
@Controller
@RequestMapping("export")
public class ExportController {
    @RequestMapping("easypoi")
    public void exportEasyPOI(HttpServletRequest request, HttpServletResponse response){
        TemplateExportParams params = new TemplateExportParams(
                "src/main/resources/xlstemplate/businessTemplate.xlsx");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("businessNumber", "1910221439386584");
//        map.put("money", 2000000.00);
//        map.put("upperMoney", "贰佰万");
//        map.put("company", "执笔潜行科技有限公司");
//        map.put("bureau", "财政局");
//        map.put("person", "JueYue");
//        map.put("phone", "1879740****");
//        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
//        for (int i = 0; i < 4; i++) {
//            Map<String, String> lm = new HashMap<String, String>();
//            lm.put("id", i + 1 + "");
//            lm.put("zijin", i * 10000 + "");
//            lm.put("bianma", "A001");
//            lm.put("mingcheng", "设计");
//            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
//            lm.put("quancheng", "开源项目");
//            lm.put("sqje", i * 10000 + "");
//            lm.put("hdje", i * 10000 + "");
//
//            listMap.add(lm);
//        }
//        map.put("maplist", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);



        try {
            ByteArrayOutputStream outPut = new ByteArrayOutputStream();
            workbook.write(outPut);
            byte[] bytes = outPut.toByteArray();
            String fileName = "业务结算单.xlsx";
            response.setCharacterEncoding("UTF-8");
            DownloadWriter.writeToResponse(response, bytes, "application/vnd.ms-excel", fileName);
        } catch (IOException e) {
            throw new RuntimeException("下载业务结算单出错：" + e.getMessage());
        }
        return;
    }
}
