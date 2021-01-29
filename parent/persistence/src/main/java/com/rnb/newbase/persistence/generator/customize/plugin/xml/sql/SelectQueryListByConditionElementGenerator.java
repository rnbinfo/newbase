package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;


public class SelectQueryListByConditionElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "queryListByCondition";
    public SelectQueryListByConditionElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", sqlId));
        answer.addAttribute(new Attribute("resultMap", "resultMap"));
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        answer.addElement(new TextElement(sb.toString()));
        XmlElement include = new XmlElement("include"); 
        include.addAttribute(new Attribute("refid", "baseColumn"));
        answer.addElement(include);
        sb.setLength(0);
        sb.append("from "); 
        //sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "tableName"));
        answer.addElement(include);
        answer.addElement(new TextElement(sb.toString()));
        XmlElement where = new XmlElement("where"); 
        XmlElement include2 = new XmlElement("include"); 
        include2.addAttribute(new Attribute("refid", "conditionColumn"));
        where.addElement(include2);
        answer.addElement(where);
        sb.setLength(0);
        sb.append("ORDER BY");
        sb.append("\n");
        sb.append("        ");
        sb.append("`id` DESC");
        answer.addElement(new TextElement(sb.toString()));
        parentElement.addElement(answer);
    }
}
