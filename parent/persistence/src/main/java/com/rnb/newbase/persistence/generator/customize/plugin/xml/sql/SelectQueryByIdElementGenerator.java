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
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                sqlId));
        answer.addAttribute(new Attribute("resultMap", //$NON-NLS-1$
                "resultMap"));

        StringBuilder sb = new StringBuilder();
        sb.append("select "); //$NON-NLS-1$

//        if (stringHasValue(introspectedTable
//                .getSelectByPrimaryKeyQueryId())) {
//            sb.append('\'');
//            sb.append(introspectedTable.getSelectByPrimaryKeyQueryId());
//            sb.append("' as QUERYID,"); //$NON-NLS-1$
//        }
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

        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                sb.append("  and "); //$NON-NLS-1$
            } else {
                sb.append("where "); //$NON-NLS-1$
                and = true;
            }
            sb.append("`");
            sb.append(MyBatis3FormattingUtilities
                    .getAliasedEscapedColumnName(introspectedColumn));
            sb.append("`");
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            answer.addElement(new TextElement(sb.toString()));
        }
        parentElement.addElement(answer);
    }
}
