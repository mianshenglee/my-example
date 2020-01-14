package me.mason.demo.parametricds.config;

import me.mason.demo.parametricds.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 *
 * @author: mason
 * @since: 2020/1/9
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    private Map<Object, Object> backupTargetDataSources;

    /**
     * 自定义构造函数
     * @param defaultDataSource
     * @param targetDataSource
     */
    public DynamicDataSource(DataSource defaultDataSource,Map<Object, Object> targetDataSource){
        backupTargetDataSources = targetDataSource;
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(backupTargetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 添加新数据源
     * @param key
     * @param dataSource
     */
    public void addDataSource(String key, DataSource dataSource){
        this.backupTargetDataSources.put(key,dataSource);
        super.setTargetDataSources(this.backupTargetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }
}
