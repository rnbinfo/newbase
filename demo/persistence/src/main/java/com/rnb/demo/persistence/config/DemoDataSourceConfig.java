package com.rnb.demo.persistence.config;

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
@MapperScan(basePackages = {"com.rnb.demo.persistence.mapper", "com.rnb.newbase.security.persistent.mapper"}, sqlSessionTemplateRef = "demoSqlSessionTemplate")
public class DemoDataSourceConfig {
    @Value("${spring.datasource.demo.mapper}")
    private String mapperLocation;

    @Bean(name = "demoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.demo")
    public DataSource agentDataSource() {
        return DataSourceBuilder.create().type(RnbDruidDataSource.class).build();
    }

    @Bean(name = "demoSqlSessionFactory")
    public SqlSessionFactory demoSqlSessionFactory(@Qualifier("demoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // 将security的mapper导入
        PathMatchingResourcePatternResolver mapperResourceResolver = new PathMatchingResourcePatternResolver();
        Resource[] resourcesForApplication = mapperResourceResolver.getResources(mapperLocation);
        Resource[] resourcesForSecurity = mapperResourceResolver.getResources(RnbDruidDataSource.securityMapperLocation);
        Resource[] resources = new Resource[resourcesForSecurity.length + resourcesForApplication.length];
        System.arraycopy(resourcesForApplication, 0, resources, 0, resourcesForApplication.length);
        System.arraycopy(resourcesForSecurity, 0, resources, resourcesForApplication.length, resourcesForSecurity.length);
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();
    }

    @Bean(name="demoTransactionManager")
    public DataSourceTransactionManager demoTransactionManager(@Qualifier("demoDataSource") DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "demoSqlSessionTemplate")
    public SqlSessionTemplate demoSqlSessionTemplate(@Qualifier("demoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
