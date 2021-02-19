package com.rnb.newbase.persistence.generator;

import org.mybatis.generator.config.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisConfig {
    private boolean overwrite = true;
    private Configuration config;
    private List<String> warnings;

    public static String CUSTOM_PLUGIN = "com.rnb.newbase.persistence.generator.customize.plugin.CustomPlugin";
    public static String CUSTOM_MYSQL_COMMENT_GENERATOR = "com.rnb.newbase.persistence.generator.customize.plugin.comment.MySQLCommentGenerator";
    public static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static String CONNECTION_URL = "jdbc:mysql://localhost:3306/rnb_traffic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    public static String CUSTOM_JAVA_TYPE_RESOLVER = "com.rnb.newbase.persistence.generator.customize.plugin.type.CustomJavaTypeResolver";
    public static String TARGET_PROJECT = "D:\\generator\\src\\main\\java\\";
    public static String ENTITY_TARGET_PACKAGE = "com.rnb.traffic.entity.po";
    public static String ENTITY_TARGET_PROJECT = "D:\\generator\\src\\main\\java\\";
    public static String XML_TARGET_PACKAGE = "com.rnb.traffic.xml";
    public static String MAPPER_TARGET_PACKAGE = "com.rnb.traffic.persistence.mapper";

    public boolean isOverwrite() {
        return overwrite;
    }

    public Configuration getConfig() {
        return config;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public MybatisConfig() {
        //配置xml配置项
        List<String> warnings = new ArrayList<String>();
        Configuration config = new Configuration();
        Context context = new Context(ModelType.CONDITIONAL);
        context.setTargetRuntime("MyBatis3");
        context.setId("DB2Tables");
        context.addProperty("mergeable","true");
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        //自定义插件
        pluginConfiguration.setConfigurationType(CUSTOM_PLUGIN);
        context.addPluginConfiguration(pluginConfiguration);
        //设置注释
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.setConfigurationType(CUSTOM_MYSQL_COMMENT_GENERATOR);
        commentGeneratorConfiguration.addProperty("suppressDate","true");
        commentGeneratorConfiguration.addProperty("addRemarkComments","true");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
        //设置连接数据库
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass(DRIVER_CLASS);
        jdbcConnectionConfiguration.setConnectionURL(CONNECTION_URL);
        jdbcConnectionConfiguration.addProperty("nullCatalogMeansCurrent", "true");
        jdbcConnectionConfiguration.setPassword("Mysql@local");
        jdbcConnectionConfiguration.setUserId("root");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
        //mysql java类型转换
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        javaTypeResolverConfiguration.setConfigurationType(CUSTOM_JAVA_TYPE_RESOLVER);
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
        //生成实体类的地址
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage(ENTITY_TARGET_PACKAGE);
        javaModelGeneratorConfiguration.setTargetProject(ENTITY_TARGET_PROJECT);
        javaModelGeneratorConfiguration.addProperty("enableSubPackages","true");
        javaModelGeneratorConfiguration.addProperty("trimStrings","true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        //生成的xml的地址
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        String targetProject = TARGET_PROJECT;
        if(TARGET_PROJECT.contains("java")){
            targetProject = TARGET_PROJECT.substring(0,TARGET_PROJECT.length()-4);
        }
        sqlMapGeneratorConfiguration.setTargetProject(targetProject);
        sqlMapGeneratorConfiguration.setTargetPackage(XML_TARGET_PACKAGE);
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        //生成注解接口
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_TARGET_PACKAGE);
        javaClientGeneratorConfiguration.setTargetProject(TARGET_PROJECT);
        //注解形式 ANNOTATEDMAPPER xml形式 XMLMAPPER
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        javaClientGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        //table
        Map<String,String> tables = new HashMap<>();
        //TODO 配置所有需求表
        tables.put("table_name","TableName");
        generateTable(tables, context);
        config.addContext(context);
        this.config = config;
        this.warnings = warnings;
    }

    private void generateTable(Map<String,String> tables, Context context) {
        tables.forEach((k,v)->{
            TableConfiguration tableConfiguration = new TableConfiguration(context);
            tableConfiguration.setTableName(k);
            tableConfiguration.setDomainObjectName(v);
            tableConfiguration.setUpdateByPrimaryKeyStatementEnabled(false);
            tableConfiguration.setDeleteByPrimaryKeyStatementEnabled(false);
            tableConfiguration.setSelectByPrimaryKeyStatementEnabled(false);
            tableConfiguration.setCountByExampleStatementEnabled(false);
            tableConfiguration.setUpdateByExampleStatementEnabled(false);
            tableConfiguration.setDeleteByExampleStatementEnabled(false);
            tableConfiguration.setSelectByExampleStatementEnabled(false);
            tableConfiguration.addProperty("useActualColumnNames","false");
            tableConfiguration.setGeneratedKey(new GeneratedKey("id","JDBC",true,"post"));
            context.addTableConfiguration(tableConfiguration);
        });
    }
}
