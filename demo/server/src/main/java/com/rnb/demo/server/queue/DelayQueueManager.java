package com.rnb.demo.server.queue;

import com.rnb.demo.service.cache.DataDictionaryCache;
import com.rnb.demo.service.service.user.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

@Component
public class DelayQueueManager implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private DelayQueue<DelayQueueMessage> delayQueue = new DelayQueue<>();

    @Resource
    private DataDictionaryCache dataDictionaryCache;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 加入到延时队列中
     *
     * @param delayQueueMessage
     */
    public void put(DelayQueueMessage delayQueueMessage) {
        logger.info("加入延时任务：{}", delayQueueMessage.toString());
        delayQueue.put(delayQueueMessage);
    }

    /**
     * 取消延时任务
     *
     * @param delayQueueMessage
     * @return
     */
    public boolean remove(DelayQueueMessage delayQueueMessage) {
        logger.info("取消延时任务：{}", delayQueueMessage.toString());
        return delayQueue.remove(delayQueueMessage);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("初始化延时队列");
        Executors.newSingleThreadExecutor().execute(new Thread(this::excuteThread));
    }

    private void excuteThread() {
        while (true) {
            try {
                DelayQueueMessage delayQueueMessage = delayQueue.take();
                processTask(delayQueueMessage);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void processTask(DelayQueueMessage delayQueueMessage) {
        logger.info("执行延时任务：{}", delayQueueMessage);
    }
}
