package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;


public class SelectQuerySortedListByConditionElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "querySortedListByCondition";
    public SelectQuerySortedListByConditionElementGenerator() {
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
        XmlElement where = new XmlElement("where"); 
        XmlElement include2 = new XmlElement("include"); 
        include2.addAttribute(new Attribute("refid", "conditionColumn"));
        where.addElement(include2);
        answer.addElement(where);
        // 生成order
        // <choose>
        //			<when test="sorts != null and sorts.size > 0">
        //				ORDER BY
        //				<foreach item="value" index="key" collection="sorts.entrySet()" separator=",">
        //					`${key}` ${value}
        //				</foreach>
        //			</when>
        //			<otherwise>
        //				ORDER BY `id` DESC
        //			</otherwise>
        //		</choose>
        sb.setLength(0);
        XmlElement choose = new XmlElement("choose");
        XmlElement when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "sorts != null and sorts.size > 0"));
        sb.append("ORDER BY\n");
        when.addElement(new TextElement(sb.toString()));
        XmlElement foreach = new XmlElement("foreach");
        foreach.addAttribute(new Attribute("item", "value"));
        foreach.addAttribute(new Attribute("index", "key"));
        foreach.addAttribute(new Attribute("collection", "sorts.entrySet()"));
        foreach.addAttribute(new Attribute("separator", ","));
        foreach.addElement(new TextElement("`${key}` ${value}"));
        when.addElement(foreach);
        XmlElement otherwise = new XmlElement("otherwise");
        String otherwiseOrder = "ORDER BY `id` DESC";
        otherwise.addElement(new TextElement(otherwiseOrder));
        choose.addElement(when);
        choose.addElement(otherwise);
        answer.addElement(choose);
        parentElement.addElement(answer);
    }
}
