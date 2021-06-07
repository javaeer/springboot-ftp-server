package com.smartcloudx.ftp.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * </p>
 *
 * @Author javaeer(javaeer @ aliyun.com)
 * @Date 2020/2/18 14:47
 * @Version 1.0
 */
public class FtpClientUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(FtpClientUtils.class);

    public static final String ftpPath = "/ok";
    public static final String ftpHostName = "119.3.235.54";
    public static final String ftpUserName = "ftp-3d-user";
    public static final String ftpPassword = "[ftp@3d@user]";
    public static final int ftpPort = 2221;

    static FTPClient getFtpClient() {

        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHostName, ftpPort);
            ftpClient.login(ftpUserName, ftpPassword);
            ftpClient.setBufferSize(1024 * 1024 * 5);
            ftpClient.setConnectTimeout(50000);
            ftpClient.setControlKeepAliveTimeout(60);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.enterLocalPassiveMode();
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                LOGGER.error("连接服务器失败");
                ftpClient.disconnect();
            }else {
                LOGGER.info("成功建立了链接");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }


    public static void upload() {

        FTPClient ftpClient = getFtpClient();

        InputStream in = null;
        try {
            // 设置PassiveMode传输
            ftpClient.enterLocalPassiveMode();
            //设置二进制传输，使用BINARY_FILE_TYPE，ASC容易造成文件损坏
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //判断FPT目标文件夹时候存在不存在则创建
            if (!ftpClient.changeWorkingDirectory(ftpPath)) {
                ftpClient.makeDirectory(ftpPath);
            }
            //跳转目标目录
            ftpClient.changeWorkingDirectory(ftpPath);

            //上传文件
            File file = new File("/Users/laughtiger/Downloads/千峰达摩院 微服务2.0/架构师 vue.zip");
            in = new FileInputStream(file);
            String tempName = ftpPath + File.separator + file.getName();
            boolean flag = ftpClient.storeFile(new String(tempName.getBytes("UTF-8"), "ISO-8859-1"), in);
            if (flag) {
                LOGGER.info("上传成功");
            }else {
                LOGGER.info("上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        FtpClientUtils ftpClientUtils = new FtpClientUtils();
        ftpClientUtils.upload();
    }
}
