package com.rnb.demo.service.message.sms;

import com.alibaba.fastjson.JSON;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.toolkit.util.DateUtil;
import com.rnb.newbase.toolkit.util.StringUtil;
import com.rnb.demo.service.constant.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class SmsSendService {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//    @Resource
//    private RabbitTemplate rabbitTemplate;
//
//    @Value("${queue.message.sms.sender.name}")
//    private String queueMessageSmsSender;
//
//    private static final String SMS_SENT_COUNT_PREFIX = "SMS_SENT_COUNT_";
//    private static final String SMS_SENT_LAST_TIME_PREFIX = "SMS_SENT_LAST_TIME_";
//    private static final Integer SMS_SEND_PERIOD = 5;
//
//    /**
//     * 查询当日发送次数是否超标
//     * @param mobile
//     * @return
//     */
//    public boolean checkSentCountDaily(String mobile) {
//        String countKey = SMS_SENT_COUNT_PREFIX + mobile;
//        String countValue = stringRedisTemplate.opsForValue().get(countKey);
//        if (StringUtil.isNotBlank(countValue) && Integer.valueOf(countValue) > 20) {
//            logger.error("Verify code request failed! Exceed the maximum of daily times");
//            throw new RnbRuntimeException(ExceptionInfo.MESSAGE_SMS_SENT_EXCEED_DAILY);
//        }
//        return true;
//    }
//
//    /**
//     * 查询最近发送时间是否满足间隔
//     * @param mobile
//     * @return
//     */
//    public boolean checkSentPeriod(String mobile) {
//        String lastTimeKey = SMS_SENT_LAST_TIME_PREFIX + mobile;
//        String lastTimeValue = stringRedisTemplate.opsForValue().get(lastTimeKey);
//        if (StringUtil.isNotBlank(lastTimeValue)) {
//            Date lastTime = DateUtil.strToDate(lastTimeValue, DateUtil.DATETIME_ISO_FORMAT);
//            if (DateUtil.adjustTime(lastTime, Calendar.MINUTE, SMS_SEND_PERIOD).compareTo(new Date()) > 0) {
//                logger.error("Verify code request failed! ");
//                throw new RnbRuntimeException(ExceptionInfo.MESSAGE_SMS_SENT_TOO_FREQUENTLY);
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 发送注册验证码
//     * @param mobile
//     * @param verifyCode
//     */
//    public void sendRegisterVerifyCode(String mobile, String verifyCode) {
//        // 查询当日发送次数是否超标
//        checkSentCountDaily(mobile);
//        // 查询最近发送时间是否满足间隔
//        checkSentPeriod(mobile);
//        //送入短信发送队列
//        SmsEntity smsEntity = new SmsEntity();
//        String[] mobiles = {mobile};;
//        smsEntity.setMobiles(mobiles);
//        smsEntity.setAction("sendRegisterVerifyCode");
//        String[] contents = {verifyCode, "5"};
//        smsEntity.setContents(contents);
//        rabbitTemplate.convertAndSend(queueMessageSmsSender, JSON.toJSONString(smsEntity));
//        updateSentCountDaily(mobile);
//        updateSentLastTime(mobile);
//    }
//
//    /**
//     * 更新发送次数
//     * @param mobile
//     */
//    private void updateSentCountDaily(String mobile) {
//        String countKey = SMS_SENT_COUNT_PREFIX + mobile;
//        String countValue = stringRedisTemplate.opsForValue().get(countKey);
//        Long count = 1l;
//        if (StringUtil.isNotBlank(countValue)) {
//            count = Long.valueOf(countValue) + 1;
//        }
//        Date now = new Date();
//        Date nextDate = DateUtil.getNextDate(now);
//        Long remainedTime = DateUtil.getTimeDifference(now, nextDate, Calendar.MINUTE);
//        stringRedisTemplate.opsForValue().set(countKey, count.toString(), remainedTime, TimeUnit.MINUTES);
//    }
//
//    /**
//     * 更新最近发送时间
//     * @param mobile
//     */
//    private void updateSentLastTime(String mobile) {
//        String lastTimeKey = SMS_SENT_LAST_TIME_PREFIX + mobile;
//        stringRedisTemplate.opsForValue().set(lastTimeKey, DateUtil.dateToStr(new Date(), DateUtil.DATETIME_ISO_FORMAT), SMS_SEND_PERIOD, TimeUnit.MINUTES);
//    }
}