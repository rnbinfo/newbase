package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.internal.util.StringUtility;


public class ConditionColumnElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "conditionColumn";
    public ConditionColumnElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql");
        answer.addAttribute(new Attribute("id", sqlId));
        StringBuilder sb = new StringBuilder();
        for(IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            XmlElement selectNotNullElement = new XmlElement("if"); 
            sb.setLength(0);
            sb.append("null != condition.");
            sb.append(introspectedColumn.getJavaProperty());
            selectNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            sb.setLength(0);
            // 添加and
            sb.append(" and ");
            sb.append("`");
            sb.append(introspectedColumn.getActualColumnName());
            sb.append("`");
            // 添加等号
            sb.append(" = ");
            sb.append("#{condition.");
            sb.append(introspectedColumn.getJavaProperty());
            sb.append('}');
            selectNotNullElement.addElement(new TextElement(sb.toString()));
            answer.addElement(selectNotNullElement);
        }
        parentElement.addElement(answer);
    }
}
