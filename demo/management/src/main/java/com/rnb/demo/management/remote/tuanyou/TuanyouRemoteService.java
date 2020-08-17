package com.rnb.demo.management.remote.tuanyou;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rnb.demo.entity.remote.tuanyou.SyncStation;
import com.rnb.newbase.controller.base.BaseRemoteServerProperties;
import com.rnb.newbase.controller.base.BaseRemoteService;
import com.rnb.newbase.toolkit.security.MD5Sign;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TuanyouRemoteService extends BaseRemoteService {
    @Resource
    private TuanyouProperties tuanyouProperties;
    @Override
    public BaseRemoteServerProperties getRemoteServerProperties() {
        return tuanyouProperties;
    }

    public List<SyncStation> syncStations() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("channelId", tuanyouProperties.getChannelId());
        parameters.put("app_key", tuanyouProperties.getKey());
        parameters.put("timestamp", String.valueOf(System.currentTimeMillis()));
        parameters.put("sign", generateSign(parameters));
        try {
            String response = doFormUrlEncodedPost(tuanyouProperties.getRequestUris().get("syncStations"), parameters, 3000);
            JSONObject responseObject = JSON.parseObject(response);
            String syncStationsContent = responseObject.getString("result");
            if (StringUtil.isNotBlank(syncStationsContent)) {
                List<SyncStation> syncStations = JSON.parseObject(syncStationsContent, new TypeReference<List<SyncStation>>() {});
                return syncStations;
            } else {
                logger.error("Synchronize stations FAILED! Reponse from tuan you [{}]", response);
            }
        } catch (Exception e) {
            logger.error("Synchronize stations FAILED! Post to tuan you error, Exception => ", e);
        }
        return null;
    }

    private String generateSign(Map<String, String> parameters) {
        String plainText = tuanyouProperties.getSecret();
        Set set = parameters.keySet();
        Object[] keys = set.toArray();
        Arrays.sort(keys);
        for (Object key : keys) {
            plainText = plainText + key + parameters.get(key);
        }
        plainText = plainText + tuanyouProperties.getSecret();
        String sign = MD5Sign.encodeAsMd5(plainText, "UTF-8");
        return sign;
    }

}
