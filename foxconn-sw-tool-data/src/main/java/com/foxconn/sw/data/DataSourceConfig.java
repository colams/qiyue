package com.foxconn.sw.data;

import com.foxconn.sw.data.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@MapperScan(basePackages = "com.foxconn.sw.data.mapper", sqlSessionFactoryRef = "sqlSessionFactoryBean")
@Configuration
public class DataSourceConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.pro")
    public DataSource dataSourcePro() {
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.demo")
    public DataSource dataSourceDemo() {
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    public AbstractRoutingDataSource dataSource(@Qualifier("dataSourcePro") DataSource dataSourcePro,
                                                @Qualifier("dataSourceDemo") DataSource dataSourceDemo) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSourcePro", dataSourcePro);
        targetDataSources.put("dataSourceDemo", dataSourceDemo);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataSourcePro); // 设置默认数据源
        return dynamicDataSource;
    }


    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        return bean.getObject();
    }

    public class DynamicDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            String currentUser = RequestContext.getEmployeeNo();
            if (StringUtils.isNotEmpty(currentUser) && currentUser.contains("demo")) {
                return "dataSourceDemo";
            } else {
                return "dataSourcePro";
            }
        }
    }

}
