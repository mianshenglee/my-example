package me.mason.demo.parametricds.proxy;

import me.mason.demo.parametricds.config.DynamicDataSource;
import me.mason.demo.parametricds.context.DynamicDataSourceContextHolder;
import me.mason.demo.parametricds.context.SpringContextHolder;
import me.mason.demo.parametricds.util.DataSourceUtil;
import me.mason.demo.parametricds.vo.DbInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理数据源
 *
 * @author: mason
 * @since: 2020/1/10
 **/
public class JdkParamDsMethodProxy implements InvocationHandler {
    /**
     * 数据源key
     */
    private String dataSourceKey;
    /**
     * 数据库连接信息
     */
    private DbInfo dbInfo;

    /**
     * 代理目标对象
     */
    private Object targetObject;

    public JdkParamDsMethodProxy(Object targetObject, String dataSourceKey, DbInfo dbInfo) {
        this.targetObject = targetObject;
        this.dataSourceKey = dataSourceKey;
        this.dbInfo = dbInfo;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理:"+method.getName());
        //切换数据源
        DataSourceUtil.addDataSourceToDynamic(dataSourceKey, dbInfo);
        DynamicDataSourceContextHolder.setContextKey(dataSourceKey);
        //调用方法
        Object result = method.invoke(targetObject, args);
        DynamicDataSource dynamicDataSource = SpringContextHolder.getContext().getBean(DynamicDataSource.class);
        dynamicDataSource.del(dataSourceKey);
        DynamicDataSourceContextHolder.removeContextKey();

        return result;
    }

    /**
     * 创建代理
     * @param targetObject
     * @param dataSourceKey
     * @param dbInfo
     * @return
     * @throws Exception
     */
    public static Object createProxyInstance(Object targetObject, String dataSourceKey, DbInfo dbInfo) throws Exception {
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader()
                , targetObject.getClass().getInterfaces(), new JdkParamDsMethodProxy(targetObject, dataSourceKey, dbInfo));
    }
}
