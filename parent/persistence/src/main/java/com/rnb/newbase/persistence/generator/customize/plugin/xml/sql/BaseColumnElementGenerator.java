package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import java.util.Iterator;


public class BaseColumnElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "baseColumn";
    public BaseColumnElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql");
        answer.addAttribute(new Attribute("id", sqlId));
        StringBuilder sb = new StringBuilder();
        Iterator<IntrospectedColumn> iter = introspectedTable.getAllColumns().iterator();
        while (iter.hasNext()) {
            answer.addElement(new TextElement(sb.toString()));
            XmlElement include = new XmlElement("include");
            include.addAttribute(new Attribute("refid", "tableName"));
            answer.addElement(include);
            answer.addElement(new TextElement(sb.toString()));
            sb.append(".`");
            sb.append(iter.next().getActualColumnName());
            sb.append("`");
            if (iter.hasNext()) {
                sb.append(", \n");
            }
            answer.addElement(new TextElement(sb.toString()));
            sb.setLength(0);
        }
        if (sb.length() > 0) {
            answer.addElement(new TextElement(sb.toString()));
        }
        parentElement.addElement(answer);
    }
}
