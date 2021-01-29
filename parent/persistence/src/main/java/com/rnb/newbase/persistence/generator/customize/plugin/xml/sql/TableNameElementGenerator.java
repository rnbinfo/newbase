package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;


public class TableNameElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "tableName";
    public TableNameElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql");
        answer.addAttribute(new Attribute("id", sqlId));
        StringBuilder sb = new StringBuilder();
        sb.append("`");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        sb.append("`");
        if (sb.length() > 0) {
            answer.addElement(new TextElement(sb.toString()));
        }
        parentElement.addElement(answer);
    }
}
