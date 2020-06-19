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
        XmlElement answer = new XmlElement("update"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                sqlId));
        StringBuilder sb = new StringBuilder();
        String parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }

        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType));
        sb.append("update "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement dynamicElement = new XmlElement("set"); //$NON-NLS-1$
        answer.addElement(dynamicElement);
        XmlElement includeElement = new XmlElement("include"); //$NON-NLS-1$
        includeElement.addAttribute(new Attribute("refid","setColumn"));
        dynamicElement.addElement(includeElement);
        StringBuilder whereSql = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            whereSql.append("where "); //$NON-NLS-1$
            whereSql.append("`");
            whereSql.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            whereSql.append("`");
            whereSql.append(" = "); //$NON-NLS-1$
            whereSql.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            answer.addElement(new TextElement(whereSql.toString()));
        }

        parentElement.addElement(answer);
    }
}
