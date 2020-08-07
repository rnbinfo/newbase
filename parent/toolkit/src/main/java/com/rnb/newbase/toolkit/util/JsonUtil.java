package com.rnb.newbase.toolkit.util;

import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    // 建立Jackson的ObjectMapper对象
    private static ObjectMapper objectMapper = new ObjectMapper();

    // 建立Json操作中的日期格式
    private static final String JSON_STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    // DateTimeUtil.STANDARD_FORMAT = "yyyy-mm-dd HH:mm:ss";
    // 日期格式如果设置为这个，会出现月份出错的问题（先是5月变3月，然后就不断增加，甚至超过12月），具体原因待查


    /**
     * 完成对象序列化为字符串
     * @param obj 源对象
     * @param <T>
     * @return
     */
    public static <T> String objectToString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 完成对象序列化为字符串，但是字符串会保证一定的结构性（提高可读性，增加字符串大小）
     * @param obj 源对象
     * @param <T>
     * @return
     */
    public static <T> String objectToStringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 完成字符串反序列化为对象
     * @param str 源字符串
     * @param clazz 目标对象的Class
     * @param <T>
     * @return
     */
    public static <T> T stringToObject(String str, Class<T> clazz) {
        if (StringUtil.isBlank(str) || clazz == null) {
            return null;
        }
        try {
            return (clazz == String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //jackson在反序列化时，如果传入List，会自动反序列化为LinkedHashMap的List
    //所以重载一下方法，解决之前String2Obj无法解决的问题

    /**
     * 进行复杂类型反序列化工作 （自定义类型的集合类型）
     *
     * @param str 源字符串
     * @param typeReference 包含elementType与CollectionType的typeReference
     * @param <T>
     * @return
     */
    public static <T> T stringToObject(String str, TypeReference<T> typeReference) {
        if (StringUtil.isBlank(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) ((typeReference.getType().equals(String.class)) ? str : objectMapper.readValue(str, typeReference.getClass()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 进行复杂类型反序列化工作（可变类型数量的）
     *
     * @param str             需要进行反序列化的字符串
     * @param collectionClass 需要反序列化的集合类型 由于这里的类型未定，且为了防止与返回值类型T冲突，故采用<?>表示泛型
     * @param elementClasses  集合中的元素类型（可多个）   此处同上通过<?>...表示多个未知泛型
     * @param <T>             返回值的泛型类型是由javatype获取的
     * @return
     */
    public static <T> T stringToObject(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
