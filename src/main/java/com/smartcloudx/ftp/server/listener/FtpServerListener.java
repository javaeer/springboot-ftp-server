/*
 *    Copyright (c) 2018-2025, javaeer All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the smartcloudx.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: javaeer (javaeer@aliyun.com)
 */
package com.smartcloudx.ftp.server.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @Author javaeer(javaeer @ aliyun.com)
 * @Date 2018/12/18 14:33
 * @Version 1.0
 */
public class FtpServerListener implements ServletContextListener {

    public static final String FTPSERVER_CONTEXT_NAME = "ftpServer";

    private Log logger = LogFactory.getLog(this.getClass());

    public String ftpPath = "/home/resource";
    public String ftpUserName = "test";
    public String ftpPassword = "123456";
    public int ftpPort = 2221;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            // 获取操作系统
            String osName = System.getProperty("os.name");

            // 如果是windows系統
            if (osName.toLowerCase().indexOf("windows") > -1) {
                ftpPath = "C:\\resource";
            } else if (osName.toLowerCase().indexOf("mac") > -1){
                ftpPath = "/Users/laughtiger/Documents/resource";
            } else {
                ftpPath = "/home/resource";
            }

            // 判断该路径是否存在，如果不存在则创建
            File file = new File(ftpPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            FtpServerFactory factory = new FtpServerFactory();
            ListenerFactory listenerFactory = new ListenerFactory();
            listenerFactory.setPort(ftpPort);
            factory.addListener("default",listenerFactory.createListener());

            FtpServer server = factory.createServer();

            BaseUser user = new BaseUser();
            user.setName(ftpUserName);
            user.setPassword(ftpPassword);
            user.setHomeDirectory(ftpPath);

            List<Authority> authorities = new ArrayList<Authority>();
            authorities.add(new WritePermission());
            user.setAuthorities(authorities);

            factory.getUserManager().save(user);

            sce.getServletContext().setAttribute(FTPSERVER_CONTEXT_NAME, server);

            // 启动FTP服务
            logger.info("启动FTP服务");



            server.start();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DefaultFtpServer server = (DefaultFtpServer) sce.getServletContext().getAttribute("FTPSERVER_CONTEXT_NAME");
        if (server != null) {
            logger.info("关闭FTP服务");
            server.stop();
            sce.getServletContext().removeAttribute(FTPSERVER_CONTEXT_NAME);
        } else {
        }
    }
}
