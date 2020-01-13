package me.mason.demo.basicmultidatasource.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * slave数据源的mybatis配置
 *
 * @author: mason
 * @since: 2020/1/8
 **/
@Configuration
@MapperScan(basePackages = "me.mason.demo.basicmultidatasource.mapper.slave", sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveMybatisConfig {
    /**
     * 注意，此处需要使用MybatisSqlSessionFactoryBean，不是SqlSessionFactoryBean，
     * 否则，使用mybatis-plus的内置函数时就会报invalid bound statement (not found)异常
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slave") DataSource dataSource) throws Exception {
        // 设置数据源
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //mapper的xml文件位置
        String locationPattern = "classpath*:/mapper/slave/*.xml";
        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));
        //对应数据库的entity位置
        String typeAliasesPackage = "me.mason.demo.basicmultidatasource.entity.slave";
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);

        return mybatisSqlSessionFactoryBean.getObject();
    }
}
