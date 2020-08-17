package com.rnb.demo.persistence.generator;

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
    public static String CONNECTION_URL = "jdbc:mysql://localhost:3306/rnb_energy?characterEncoding=UTF-8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
    public static String CUSTOM_JAVA_TYPE_RESOLVER = "com.rnb.newbase.persistence.generator.customize.plugin.type.CustomJavaTypeResolver";
    public static String PROJECT_ENTITY_TARGET = "/Users/jevons/Work/source/rnb/energy/application/entity/src/main/java/";
    public static String PROJECT_PERSISTENCE_TARGET = "/Users/jevons/Work/source/rnb/energy/application/persistence/src/main/java/";
    public static String ENTITY_TARGET_PACKAGE = "com.rnb.energy.entity.po";
    public static String PERSISTENCE_TARGET_PACKAGE = "com.rnb.energy.persistence.mapper";
    public static String PROJECT_XML_TARGET = "/Users/jevons/Work/source/rnb/energy/application/persistence/src/main/resources/";
    public static String XML_TARGET_PACKAGE = "mapper";

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
        jdbcConnectionConfiguration.setPassword("Mysql@local");
        jdbcConnectionConfiguration.setUserId("root");
        jdbcConnectionConfiguration.addProperty("nullCatalogMeansCurrent", "true");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
        //mysql java类型转换
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        javaTypeResolverConfiguration.setConfigurationType(CUSTOM_JAVA_TYPE_RESOLVER);
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);
        //生成实体类的地址
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage(ENTITY_TARGET_PACKAGE);
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_ENTITY_TARGET);
        javaModelGeneratorConfiguration.addProperty("enableSubPackages","true");
        javaModelGeneratorConfiguration.addProperty("trimStrings","true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        //生成的xml的地址
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_XML_TARGET);
        sqlMapGeneratorConfiguration.setTargetPackage(XML_TARGET_PACKAGE);
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        //生成注解接口
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetPackage(PERSISTENCE_TARGET_PACKAGE);
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PERSISTENCE_TARGET);
        //注解形式 ANNOTATEDMAPPER xml形式 XMLMAPPER
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        javaClientGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        //table
        Map<String,String> tables = new HashMap<>();
        //TODO 配置所有需求表
        tables.put("accessor_operator","AccessorOperator");
        tables.put("config_accessor","ConfigAccessor");
        tables.put("fuel_station","FuelStation");
        tables.put("fuel_station_provide","FuelStationProvide");
        tables.put("trade_order","TradeOrder");
        tables.put("user_info","UserInfo");
        tables.put("user_channel_token","UserChannelToken");
        tables.put("operator_info","OperatorInfo");
        tables.put("system_data_dictionary","SystemDataDictionary");
        tables.put("system_parameter","SystemParameter");
        tables.put("system_operate_log","SystemOperateLog");
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
