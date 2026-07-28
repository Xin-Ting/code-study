package com.example.study;

import com.example.study.strategy.case01.HandlerFactory;
import com.example.study.strategy.case01.HandlerSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StrategyHandlerSupportImplTest {

    @Autowired
    private HandlerFactory handlerFactory;

    @Test
    void testHandleMessage() {
        System.out.println("=== 测试短信通知 ===");
        HandlerSupport handlerSupport = handlerFactory.get("message");
        handlerSupport.handle("短信通知：您有新的订单待处理");
    }

    @Test
    void testHandleWechat() {
        System.out.println("=== 测试微信通知 ===");
        HandlerSupport handlerSupport = handlerFactory.get("wechat");
        handlerSupport.handle("您有新的订单待处理");
    }

}
