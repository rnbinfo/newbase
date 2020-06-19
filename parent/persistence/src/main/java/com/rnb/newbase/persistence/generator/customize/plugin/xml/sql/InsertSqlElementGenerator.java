package com.rnb.newbase.persistence.generator.customize.plugin.xml.sql;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.config.GeneratedKey;

import java.util.ArrayList;
import java.util.List;


public class InsertSqlElementGenerator extends AbstractXmlElementGenerator {
    private String sqlId = "insert";
    private Boolean isSimple = true;
    public InsertSqlElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("insert"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                sqlId));

        StringBuilder insertClause = new StringBuilder();

        FullyQualifiedJavaType parameterType;
        if (isSimple) {
            parameterType = new FullyQualifiedJavaType(
                    introspectedTable.getBaseRecordType());
        } else {
            parameterType = introspectedTable.getRules()
                    .calculateAllFieldsClass();
        }

        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType.getFullyQualifiedName()));

        GeneratedKey gk = introspectedTable.getGeneratedKey();
        if (gk != null) {
            introspectedTable.getColumn(gk.getColumn()).ifPresent(introspectedColumn -> {
                // if the column is null, then it's a configuration error. The
                // warning has already been reported
                if (gk.isJdbcStandard()) {
                    answer.addAttribute(new Attribute(
                            "useGeneratedKeys", "true")); //$NON-NLS-1$ //$NON-NLS-2$
                    answer.addAttribute(new Attribute(
                            "keyProperty", introspectedColumn.getJavaProperty())); //$NON-NLS-1$
                    answer.addAttribute(new Attribute(
                            "keyColumn", introspectedColumn.getActualColumnName())); //$NON-NLS-1$
                } else {
                    answer.addElement(getSelectKey(introspectedColumn, gk));
                }
            });
        }

        insertClause.append("insert into "); //$NON-NLS-1$
        insertClause.append(introspectedTable
                .getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" ("); //$NON-NLS-1$
        answer.addElement(new TextElement(insertClause.toString()));
        insertClause.setLength(0);
        OutputUtilities.xmlIndent(insertClause, 1);
        StringBuilder valuesClause = new StringBuilder();
        valuesClause.append("values ("); //$NON-NLS-1$
        List<String> valuesClauses = new ArrayList<>();
        List<IntrospectedColumn> columns =
                ListUtilities.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns());
        for (int i = 0; i < columns.size(); i++) {
            IntrospectedColumn introspectedColumn = columns.get(i);
            if(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn).equals("modify_time")){
                continue;
            }
            insertClause.append("`");
            insertClause.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            if(i==0){
                valuesClause.append("\n");
                valuesClause.append("        ");
            }
            if(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn).equals("create_time")){
                valuesClause.append("now()");
            }else{
                valuesClause.append(MyBatis3FormattingUtilities
                        .getParameterClause(introspectedColumn));
            }
            insertClause.append("`");
            if (i + 1 < columns.size()) {
                insertClause.append(", "); //$NON-NLS-1$
                valuesClause.append(", "); //$NON-NLS-1$
            }

            //换行和缩进
            answer.addElement(new TextElement(insertClause.toString()));
            insertClause.setLength(0);
            OutputUtilities.xmlIndent(insertClause, 1);

            valuesClauses.add(valuesClause.toString());
            valuesClause.setLength(0);
            OutputUtilities.xmlIndent(valuesClause, 1);

        }

        insertClause.append(')');
        answer.addElement(new TextElement(insertClause.toString()));

        valuesClause.append(')');
        valuesClauses.add(valuesClause.toString());

        for (String clause : valuesClauses) {
            answer.addElement(new TextElement(clause));
        }

        parentElement.addElement(answer);
    }
}
