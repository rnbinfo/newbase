package com.rnb.newbase.toolkit.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.*;

public class EntityUtil {
    public static <T> T convertEntity(Object object1, Object object2, Class<T> clazz) {
        Map<String, Object> map1 = objectToMap(object1);
        Map<String, Object> map2 = objectToMap(object2);
        Iterator var5 = map1.entrySet().iterator();

        while(var5.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var5.next();
            map2.put(entry.getKey(), entry.getValue());
        }

        return JSON.parseObject(JSONObject.toJSONString(map2), clazz);
    }

    public static <T> T convertEntity(Object object1, Class<T> clazz) {
        Map<String, Object> map1 = objectToMap(object1);
        return JSON.parseObject(JSONObject.toJSONString(map1), clazz);
    }

    public static List convertEntityList(List<Object> list, Class clazz) {
        List resList = new ArrayList();
        for(Object object : list){
            Map<String, Object> map1 = objectToMap(object);
            resList.add(JSON.parseObject(JSONObject.toJSONString(map1), clazz));
        }
        return resList;
    }

    public static Map<String, Object> objectToMap(Object dto) {
        return (Map)JSON.parseObject(JSON.toJSONString(dto), new TypeReference<TreeMap<String, Object>>() {
        }, new Feature[0]);
    }
}
