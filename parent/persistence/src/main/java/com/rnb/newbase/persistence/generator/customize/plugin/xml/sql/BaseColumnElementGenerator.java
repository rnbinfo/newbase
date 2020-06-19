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
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                sqlId));
        StringBuilder sb = new StringBuilder();
        Iterator<IntrospectedColumn> iter = introspectedTable
                .getNonBLOBColumns().iterator();
        while (iter.hasNext()) {
            sb.append("`");
            sb.append(MyBatis3FormattingUtilities.getSelectListPhrase(iter
                    .next()));
            sb.append("`");
            if (iter.hasNext()) {
                sb.append(", "); //$NON-NLS-1$
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
