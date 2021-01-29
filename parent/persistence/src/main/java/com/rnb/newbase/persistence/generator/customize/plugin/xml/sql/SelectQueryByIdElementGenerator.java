package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;


public class SelectQueryByIdElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "queryById";

    public SelectQueryByIdElementGenerator() {
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
        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                sb.append("  and "); 
            } else {
                sb.append("where "); 
                and = true;
            }
            sb.append("`");
            sb.append(MyBatis3FormattingUtilities
                    .getAliasedEscapedColumnName(introspectedColumn));
            sb.append("`");
            sb.append(" = "); 
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            answer.addElement(new TextElement(sb.toString()));
        }
        parentElement.addElement(answer);
    }
}
