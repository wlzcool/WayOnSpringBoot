package com.wayfather.springbootnpoi.Controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author IBM
 * @className HutoolController
 * @description TODO
 * @date 2019/11/18 9:33
 **/
@RequestMapping("/hutoolnpoi")
public class HutoolController {
    @RequestMapping("/downloadExcelWithHuTool")
    public void downloadExcelWithHuTool(  HttpServletResponse response) {
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        try {
            //自定义标题别名
            writer.addHeaderAlias("id", "编号");
            writer.addHeaderAlias("nhBusinessNumber", "内行业务编号");
            writer.addHeaderAlias("transno", "网银流水编号");
            writer.addHeaderAlias("accountStatementId", "流水表主键");
            writer.addHeaderAlias("errorInfo", "错误信息");
            writer.addHeaderAlias("createDate", "稽核时间");
            writer.addHeaderAlias("orgName", "内行收款方简称");
            writer.addHeaderAlias("orgFullName", "内行收款单位全称");
            writer.addHeaderAlias("payOrgName", "内行付款方简称");
            writer.addHeaderAlias("payOrgFullName", "内行付款方全称");
            writer.addHeaderAlias("bankCode", "内行收款方外部付款银行账号");
            writer.addHeaderAlias("payBankCode", "内行付款方外部收款银行账号");
            writer.addHeaderAlias("depositMoney", "内行业务全额");
            writer.addHeaderAlias("accountno", "本方收款帐号");
            writer.addHeaderAlias("oppaccountno", "对方付款账号");
            writer.addHeaderAlias("amount", "网银流水全额");
            writer.addHeaderAlias("verifyDate", "内行确认时间");
            writer.addHeaderAlias("successTime", "银行成功返回时间");
            writer.addHeaderAlias("oppAccountName", "对方账户名称");
            writer.addHeaderAlias("note", "备注");

            // 合并单元格后的标题行，使用默认标题样式
            writer.merge(19, "河钢稽核不正常数据");
            // 一次性写出内容，使用默认样式，强制输出标题
            //writer.write(p.getList(), true);
            String fileName = "河钢稽核报告.xls";
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            writer.flush(response.getOutputStream());
            // 关闭writer，释放内存
            writer.close();
            //此处记得关闭输出Servlet流
            IoUtil.close(response.getOutputStream());

        } catch (Exception e) {

        }
        return;
    }
}
