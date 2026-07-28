package com.example.study.strategy.case01;

import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/5/26 23:00
 * @description TODO
 */
@Service("wechat")
public class WeChatHandlerSupportImpl implements HandlerSupport{
    @Override
    public void handle(String message) {
        System.out.println("【微信通知】处理消息: " + message);
        System.out.println("正在发送微信模板消息...");
    }
}