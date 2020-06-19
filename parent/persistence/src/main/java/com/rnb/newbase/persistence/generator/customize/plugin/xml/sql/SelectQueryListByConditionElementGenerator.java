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
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                sqlId));
        answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
                "resultMap"));

        StringBuilder sb = new StringBuilder();
        sb.append("select "); //$NON-NLS-1$

        answer.addElement(new TextElement(sb.toString()));
        XmlElement include = new XmlElement("include"); //$NON-NLS-1$
        include.addAttribute(new Attribute("refid", //$NON-NLS-1$
                "baseColumn"));
        answer.addElement(include);
        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement where = new XmlElement("where"); //$NON-NLS-1$
        XmlElement include2 = new XmlElement("include"); //$NON-NLS-1$
        include2.addAttribute(new Attribute("refid", //$NON-NLS-1$
                "conditionColumn"));
        where.addElement(include2);
        answer.addElement(where);
        sb.setLength(0);
        sb.append("ORDER BY");
        sb.append("\n");
        sb.append("        ");
        sb.append("`id` ASC");
        answer.addElement(new TextElement(sb.toString()));
        parentElement.addElement(answer);
    }
}
