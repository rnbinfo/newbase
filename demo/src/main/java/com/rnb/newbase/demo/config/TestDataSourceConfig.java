package com.rnb.newbase.demo.config;

import com.rnb.newbase.persistence.config.RnbDruidDataSource;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.rnb.newbase.demo.mapper", "com.rnb.newbase.security.persistent.mapper"}, sqlSessionTemplateRef = "testSqlSessionTemplate")
public class TestDataSourceConfig {
    @Value("${spring.datasource.test.mapper}")
    private String mapperLocation;

    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().type(RnbDruidDataSource.class).build();
    }

    @Bean(name = "testSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("testDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver mapperResourceResolver = new PathMatchingResourcePatternResolver();
        Resource[] resourcesForApplication = mapperResourceResolver.getResources(mapperLocation);
        Resource[] resourcesForSecurity = mapperResourceResolver.getResources(RnbDruidDataSource.securityMapperLocation);
        Resource[] resources = new Resource[resourcesForSecurity.length + resourcesForApplication.length];
        System.arraycopy(resourcesForApplication, 0, resources, 0, resourcesForApplication.length);
        System.arraycopy(resourcesForSecurity, 0, resources, resourcesForApplication.length, resourcesForSecurity.length);
        factoryBean.setMapperLocations(resourcesForSecurity);
        return factoryBean.getObject();
    }

    @Bean(name="testTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("testDataSource") DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "testSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("testSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
