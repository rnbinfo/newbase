package com.rnbbusiness.demo.config;

import com.rnbbusiness.newbase.persistence.config.RnbbusinessDruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.rnbbusiness.demo.dao.test1", "com.rnbbusiness.newbase.security.dao.mapper"}, sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class Test1DataSourceConfig {
    @Value("${spring.datasource.test1.mapper}")
    private String mapperLocation;

    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DataSource test1DataSource() {
        return DataSourceBuilder.create().type(RnbbusinessDruidDataSource.class).build();
    }

    @Bean(name = "test1SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        return factoryBean.getObject();
    }
    @Bean(name="test1TransactionManager")
    public DataSourceTransactionManager test1TransactionManager(@Qualifier("test1DataSource") DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "test1SqlSessionTemplate")
    public SqlSessionTemplate test1SqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
