package me.mason.demo.parametricds.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.mason.demo.parametricds.constants.DataSourceConstants;
import me.mason.demo.parametricds.context.DynamicDataSourceContextHolder;
import me.mason.demo.parametricds.entity.TestUser;
import me.mason.demo.parametricds.mapper.TableMapper;
import me.mason.demo.parametricds.mapper.TestUserMapper;
import me.mason.demo.parametricds.proxy.JdkParamDsMethodProxy;
import me.mason.demo.parametricds.service.TestUserService;
import me.mason.demo.parametricds.util.DataSourceUtil;
import me.mason.demo.parametricds.vo.DbInfo;
import me.mason.demo.parametricds.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 Controller
 *
 * @author mason
 * @date 2020-01-08
 */
@RestController
@RequestMapping("/user")
public class TestUserController {

    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private TestUserService testUserService;

    @Autowired
    private TableMapper tableMapper;

    /**
     * 根据数据库连接信息获取表信息
     * @param dbInfo，包括ip,port,dbName,driveClassName,username,password
     * @return
     */
    @GetMapping("table")
    public Object findWithDbInfo(DbInfo dbInfo) throws Exception {
        /**
         * http://localhost:8080/dd/user/table?ip=localhost&port=3310&dbName=mytest&username=root&password=111111
         * **/
        //数据源key
        String newDsKey = System.currentTimeMillis()+"";
//        //添加数据源
//        DataSourceUtil.addDataSourceToDynamic(newDsKey,dbInfo);
//        DynamicDataSourceContextHolder.setContextKey(newDsKey);
//        //查询表信息
//        List<Map<String, Object>> tables = tableMapper.selectTableList();
//        DynamicDataSourceContextHolder.removeContextKey();

        //使用代理切换数据源
        TableMapper tableMapperProxy = (TableMapper)JdkParamDsMethodProxy.createProxyInstance(tableMapper, newDsKey, dbInfo);
        List<Map<String, Object>> tables = tableMapperProxy.selectTableList();
        return ResponseResult.success(tables);
    }

    /**
     * 查询
     */
    @GetMapping("/find")
    public Object find(int id) {
        TestUser testUser = testUserMapper.selectOne(new QueryWrapper<TestUser>().eq("id" , id));
        if (testUser != null) {
            return ResponseResult.success(testUser);
        } else {
            return ResponseResult.error("没有找到该对象");
        }
    }

    /**
     * 查询全部
     */
    @GetMapping("/listall")
    public Object listAll() {
        int initSize = 2;
        Map<String, Object> result = new HashMap<>(initSize);
        //默认master数据源查询
        List<TestUser> masterUser = testUserService.getMasterUser();
        result.put(DataSourceConstants.DS_KEY_MASTER, masterUser);
        //从slave数据源查询
        List<TestUser> slaveUser = testUserService.getSlaveUser();
        result.put(DataSourceConstants.DS_KEY_SLAVE, slaveUser);
        //返回数据
        return ResponseResult.success(result);
    }

}
