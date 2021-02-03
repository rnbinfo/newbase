package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.internal.util.StringUtility;


public class SetColumnElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "setColumn";
    public SetColumnElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql");
        answer.addAttribute(new Attribute("id", sqlId));
        StringBuilder sb = new StringBuilder();
        for(IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            String columnName = MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn);
            if (!"id".equals(columnName) && !"create_time".equals(columnName) && !"modify_time".equals(columnName)) {
                sb.setLength(0);
                sb.append("`");
                sb.append(columnName);
                sb.append("`");
                // 添加等号
                sb.append(" = ");
                sb.append("#{");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append('}');
                sb.append(", \n");
            }
        }
        answer.addElement(new TextElement(sb.toString()));
        parentElement.addElement(answer);
    }
}
