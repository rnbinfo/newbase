package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;


public class UpdateSqlElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "update";
    public UpdateSqlElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("update");
        answer.addAttribute(new Attribute("id", sqlId));
        StringBuilder sb = new StringBuilder();
        String parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }

        answer.addAttribute(new Attribute("parameterType", parameterType));
        sb.append("update "); 
        // sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "tableName"));
        answer.addElement(include);
        XmlElement dynamicElement = new XmlElement("set"); 
        answer.addElement(dynamicElement);
        XmlElement includeElement = new XmlElement("include"); 
        includeElement.addAttribute(new Attribute("refid","setColumn"));
        dynamicElement.addElement(includeElement);
        StringBuilder whereSql = new StringBuilder().append("where `id` = #{id}");
        answer.addElement(new TextElement(whereSql.toString()));
        parentElement.addElement(answer);
    }
}
