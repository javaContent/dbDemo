package com.example.one.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 数据库从库（写链接）
 */
@Configuration
@MapperScan(basePackages = { "com.example.one.dao.operation"}, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class MybatisOperationConfig {

    @Bean(name="slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

//    @Autowired
//    @Qualifier("master")
//    private DataSource dataSource;

    @Autowired
    private MybatisProperties properties;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired(required = false)
    private Interceptor[] interceptors;

    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    /**
     * mybatis-plus分页插件
     */
//    @Bean
//    @Primary
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor page = new PaginationInterceptor();
//        page.setDialectType("mysql");
//        return page;
//    }

    /**
     * 这里全部使用mybatis-autoconfigure 已经自动加载的资源。不手动指定 配置文件和mybatis-boot的配置文件同步
     *
     * @return
     */
    /*@Primary
    @Bean(name = "masterSqlSessionFactory")
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(master());
        mybatisPlus.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        // mybatisPlus.setConfiguration(properties.getConfiguration());
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            mybatisPlus.setPlugins(this.interceptors);
        }
        // MP 全局配置，更多内容进入类看注释
        GlobalConfiguration globalConfig = new GlobalConfiguration();
//        globalConfig.setDbType(DBType.MYSQL.name());
        // ID 策略 AUTO->`0`("数据库ID自增")
        // INPUT->`1`(用户输入ID") ID_WORKER->`2`("全局唯一ID") UUID->`3`("全局唯一ID")
//        globalConfig.setIdType(0);
        mybatisPlus.setGlobalConfig(globalConfig);
        // MybatisConfiguration mc = new MybatisConfiguration();
        // mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        // mybatisPlus.setConfiguration(mc);
        if (this.databaseIdProvider != null) {
            mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
        }
        return mybatisPlus;
    }*/

//    @Primary
//    @Bean(name = "masterSqlSessionFactory")
//    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws IOException {
//        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
//        mybatisPlus.setDataSource(dataSource);
//        mybatisPlus.setVfs(SpringBootVFS.class);
//        String configLocation = this.properties.getConfigLocation();
//        if (!StringUtils.isEmpty(configLocation)) {
//            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(configLocation));
//        }
//
//        mybatisPlus.setConfiguration(properties.getConfiguration());
//        mybatisPlus.setPlugins(this.interceptors);
//        MybatisConfiguration mc = new MybatisConfiguration();
//        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
//        mc.setMapUnderscoreToCamelCase(false);// 数据库和java都是驼峰，就不需要
//        mybatisPlus.setConfiguration(mc);
//        if (this.databaseIdProvider != null) {
//            mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
//        }
//        mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
//        mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
//        mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
//        // 设置mapper.xml文件的路径
////        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
////        Resource[] resource = resolver.getResources("classpath:mapper/*.xml");
////        mybatisPlus.setMapperLocations(resource);
//        return mybatisPlus;
//    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slave") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mapper/operation/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }






}
