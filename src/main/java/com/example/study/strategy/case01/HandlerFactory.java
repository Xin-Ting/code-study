package com.example.study.strategy.case01;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/5/26 23:03
 * @description TODO
 */
@Service
public class HandlerFactory {

    private final Map<String, HandlerSupport> handlerSupportMap;

    /**
     * 构造器注入
     * 构造器注入，不是 @Autowired 字段注入。
     * @Component
     * public class HandlerFactory {
     *     private final Map<String,Handler> handlerMap;
     *     public HandlerFactory(
     *             Map<String,Handler> handlerMap){
     *         this.handlerMap = handlerMap;
     *     }
     * }
     * 没有 @Autowired，Spring是怎么知道要注入？
     * 答案是：
     * Spring支持构造器自动注入。
     * Spring有三种常见注入方式
     * 1. 字段注入
     * @Component
     * public class HandlerFactory {
     *     @Autowired
     *     private Map<String,Handler> handlerMap;
     * }
     * Spring启动：
     * 发现：
     * Map<String,Handler>类型。
     * 自动找所有实现类，注入进去。
     * 这种写法没问题，但是现代Spring里一般不太推荐。
     * 2. Setter注入
     * 例如：
     * @Component
     * public class HandlerFactory {
     *     private Map<String,Handler>
     *             handlerMap;
     *     @Autowired
     *     public void setHandlerMap(
     *             Map<String,Handler>
     *                     handlerMap){
     *         this.handlerMap=handlerMap;
     *     }
     * }
     * 这个现在用得少。
     * 3. 构造器注入（推荐）
     * @Component
     * public class HandlerFactory {
     *     private final Map<String,Handler>
     *             handlerMap;
     *     public HandlerFactory(
     *         Map<String,Handler>
     *                 handlerMap){
     *         this.handlerMap=handlerMap;
     *     }
     * }
     * 关键点在 Spring4.3+。
     * 如果：只有一个构造函数。
     * 那么：@Autowired可以省略。
     * Spring会自动注入。
     * 等价于：
     * @Component
     * public class HandlerFactory {
     *     private final Map<String,Handler>
     *             handlerMap;
     *     @Autowired
     *     public HandlerFactory(
     *         Map<String,Handler>
     *                 handlerMap){
     *         this.handlerMap=handlerMap;
     *     }
     * }
     * 两者一样。
     * 所以：
     * “为什么Factory里的Map不需要注入？”
     * 准确说法应该是：
     * 它已经被注入了，只是通过构造器注入完成。
     * Spring到底怎么注入Map？
     * 假设：
     * 接口
     * public interface Handler {
     * }
     * 实现类
     * @Service("db")
     * class DbHandler implements Handler
     * @Service("cache")
     * class CacheHandler implements Handler
     * Spring启动。
     * IOC容器里有：
     * db -> DbHandler
     * cache -> CacheHandler
     * 看到：
     * Factory需要：
     * Map<String,Handler>
     * Spring会自动推断：
     * “哦，你要的是所有Handler实现。”
     * 于是自动构造：
     * {
     *    "db": DbHandler实例,
     *    "cache": CacheHandler实例
     * }
     * 然后传给构造器。
     * 相当于Spring内部偷偷干了：
     * new HandlerFactory(
     *     generatedMap
     * )
     * 这也是为什么：
     * Map方案可以替代自己List遍历+buildMap。
     * 因为Spring已经帮你干了。
     * 再补充一个工程实践点。
     * 为什么更推荐：
     * 构造器注入 > 字段注入
     * 例如：
     *
     * @Component
     * public class HandlerFactory {
     *     private final Map<String,Handler>
     *             handlerMap;
     * }
     * 好处1：依赖明确
     * 看构造器一眼知道。
     * 这个类依赖：
     * Map<String,Handler>
     * 字段注入隐藏依赖。
     * 不容易看出来。
     * 好处2：final不可变
     * private final handlerMap;
     * 初始化后不能被改，不能指向新的Map。
     * 更安全。
     * 好处3：方便单测
     * 测试里可以直接：
     * new HandlerFactory(mockMap)
     * 不用Spring容器。
     * 所以现代Spring开发里很多团队习惯：
     * 优先构造器注入。
     */
    public HandlerFactory(Map<String, HandlerSupport> handlerSupportMap) {
        this.handlerSupportMap = handlerSupportMap;
    }

    public HandlerSupport get(String type) {
        HandlerSupport handler = handlerSupportMap.get(type);
        if (handler != null) {
            return handler;
        } else {
            throw new IllegalArgumentException("不支持的通知类型: " + type);
        }
    }


}