package com.rnb.newbase.persistence.generator.customize.plugin.generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.ArrayList;
import java.util.List;

public class CustomModelGenerator {

    public static void addImportedType(TopLevelClass topLevelClass) {
        javaTypes().forEach(item->topLevelClass.addImportedType(item));
    }

    public static void addAnnotation(TopLevelClass topLevelClass) {
        addAnnons().forEach(item->topLevelClass.addAnnotation(item));
    }

    //注解对应的完整的类路径
    private static List<FullyQualifiedJavaType> javaTypes(){
        List<FullyQualifiedJavaType> javaTypes = new ArrayList<>(2);
        javaTypes.add(new FullyQualifiedJavaType("lombok.Data"));
        javaTypes.add(new FullyQualifiedJavaType("lombok.NoArgsConstructor"));
        return javaTypes;
    }
    //需要添加的注解
    private static List<String> addAnnons(){
        List<String> annons = new ArrayList<>(2);
        annons.add("@Data");
        annons.add("@NoArgsConstructor");
        return annons;
    }
}
