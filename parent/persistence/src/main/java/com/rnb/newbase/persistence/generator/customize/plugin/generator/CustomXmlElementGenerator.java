package com.rnb.newbase.persistence.generator.customize.plugin.generator;

import com.rnb.newbase.persistence.generator.customize.plugin.xml.sql.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class CustomXmlElementGenerator extends AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {
        addConditionColumn(parentElement);
        addSetColumn(parentElement);
        addBaseColumn(parentElement);
        addResultMap(parentElement);
        addInsertSql(parentElement);
        addUpdateSql(parentElement);
        addSelectQueryById(parentElement);
        addSelectQueryListByCondition(parentElement);
    }
    //conditionColumn
    private void addConditionColumn(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new ConditionColumnElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
    //setColumn
    private void addSetColumn(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new SetColumnElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
    //baseColumn
    private void addBaseColumn(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new BaseColumnElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
    //resultMap
    private void addResultMap(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new ResultMapElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
    //insert
    private void addInsertSql(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new InsertSqlElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
    //update
    private void addUpdateSql(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new UpdateSqlElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
    //select queryById
    private void addSelectQueryById(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new SelectQueryByIdElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }
    //select queryListByCondition
    private void addSelectQueryListByCondition(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new SelectQueryListByConditionElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void initializeAndExecuteGenerator(
            AbstractXmlElementGenerator elementGenerator,
            XmlElement parentElement) {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.setProgressCallback(progressCallback);
        parentElement.addElement(new TextElement(""));
        elementGenerator.addElements(parentElement);
    }
}
