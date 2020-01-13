package me.mason.demo.dynamicdatasource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.mason.demo.dynamicdatasource.annotation.DS;
import me.mason.demo.dynamicdatasource.constants.DataSourceConstants;
import me.mason.demo.dynamicdatasource.entity.TestUser;
import me.mason.demo.dynamicdatasource.mapper.TestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务类，数据源注解在方法
 *
 * @author: mason
 * @since: 2020/1/9
 **/
@Service
public class TestUserService {
    @Autowired
    private TestUserMapper testUserMapper;


    /**
     * 查询master库User
     * @return
     */
    @DS(DataSourceConstants.DS_KEY_MASTER)
    public List<TestUser> getMasterUser(){
        QueryWrapper<TestUser> queryWrapper = new QueryWrapper<>();
        return testUserMapper.selectAll(queryWrapper.isNotNull("name"));
    }

    /**
     * 查询slave库User
     * @return
     */
    @DS(DataSourceConstants.DS_KEY_SLAVE)
    public List<TestUser> getSlaveUser(){
        return testUserMapper.selectList(null);
    }

}
