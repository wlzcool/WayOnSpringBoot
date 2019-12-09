package com.wayfather.springbooteasypoi.common;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author IBM
 * @className DownloadWriter
 * @description TODO
 * @date 2019/12/9 11:53
 **/
public class DownloadWriter {
    /**
     * 输出字节数据到浏览器客户端以便用户下载
     *
     * @param response
     * @param bytesData   文件内容字节数组
     * @param contentType 文件类型
     * @param fileName    文件名称
     * @throws IOException
     */
    public static void writeToResponse(HttpServletResponse response, byte[] bytesData, String contentType, String fileName) throws IOException {
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        response.setContentLength(bytesData.length);
        response.getOutputStream().write(bytesData);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}