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
package com.smartcloudx.ftp.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * </p>
 *
 * @Author javaeer(javaeer @ aliyun.com)
 * @Date 2018/12/18 14:04
 * @Version 1.0
 */
@RestController
public class FtpController {



    @RequestMapping(value = "/")
    public String started() {
        return "FTP-Server started!";
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return null;
    }
}
