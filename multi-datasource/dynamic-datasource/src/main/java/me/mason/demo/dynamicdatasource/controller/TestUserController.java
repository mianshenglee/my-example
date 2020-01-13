package me.mason.demo.dynamicdatasource.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.mason.demo.dynamicdatasource.constants.DataSourceConstants;
import me.mason.demo.dynamicdatasource.context.DynamicDataSourceContextHolder;
import me.mason.demo.dynamicdatasource.entity.TestUser;
import me.mason.demo.dynamicdatasource.mapper.TestUserMapper;
import me.mason.demo.dynamicdatasource.service.TestUserService;
import me.mason.demo.dynamicdatasource.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

//        //默认master数据源查询
//        QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();
//        List<TestUser> resultData = testUserMapper.selectAll(queryWrapper.isNotNull("name"));
//        result.put(DataSourceConstants.DS_KEY_MASTER, resultData);
//
//        //切换数据源
//        DynamicDataSourceContextHolder.setContextKey(DataSourceConstants.DS_KEY_SLAVE);
//        //mp内置接口
//        List<TestUser> resultDataSlave = testUserMapper.selectList(null);
//        result.put(DataSourceConstants.DS_KEY_SLAVE, resultDataSlave);
//        //恢复数据源
//        DynamicDataSourceContextHolder.removeContextKey();

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
