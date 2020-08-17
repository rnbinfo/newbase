package com.rnb.demo.server.controller;

import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.enums.remote.ChannelEnergy;
import com.rnb.demo.entity.po.user.UserInfo;
import com.rnb.demo.server.remote.tuanyou.TuanyouRemoteService;
import com.rnb.demo.service.cache.DataDictionaryCache;
import com.rnb.demo.service.cache.ParameterCache;
import com.rnb.demo.service.service.user.UserInfoService;
import com.rnb.newbase.controller.base.BaseController;
import com.rnb.newbase.toolkit.util.DateUtil;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

public abstract class AbstractController extends BaseController {
    @Resource
    protected ParameterCache parameterCache;
    @Resource
    protected DataDictionaryCache dataDictionaryCache;
    @Resource
    protected TuanyouRemoteService tuanyouRemoteService;
    @Resource
    protected UserInfoService userInfoService;
    @Resource
    private UserChannelTokenService userChannelTokenService;

    /**
     * 根据接入id和手机号，获取渠道token
     * @param accessorId
     * @param mobile
     * @return
     */
    protected String getToken(BigInteger accessorId, String mobile) {
        UserInfo userInfo = userInfoService.getUserInfo(accessorId, mobile);
        String channel = dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.CHANNEL_ENERGY, ChannelEnergy.TUANYOU.getValue());
        UserChannelToken condition = new UserChannelToken();
        condition.setChannel(channel);
        condition.setUserId(userInfo.getId());
        UserChannelToken existedUserChannelToken = userChannelTokenService.queryOneByCondition(condition);
        // 不存在或接近过期时间时，则发送接口更新一次token
        Date now = new Date();
        if (existedUserChannelToken == null || DateUtil.getTimeDifference(now, existedUserChannelToken.getExpireTime(), Calendar.MINUTE) < 60) {
            String token = tuanyouRemoteService.loginSimpleApp(mobile);
            if (existedUserChannelToken == null) {
                existedUserChannelToken = new UserChannelToken();
                existedUserChannelToken.setUserId(userInfo.getId());
                existedUserChannelToken.setChannel(channel);
            }
            existedUserChannelToken.setToken(token);
            existedUserChannelToken.setExpireTime(DateUtil.adjustTime(now, Calendar.HOUR, 1));
            userChannelTokenService.insertOrUpdate(existedUserChannelToken);
            return token;
        } else {
            return existedUserChannelToken.getToken();
        }
    }
}
