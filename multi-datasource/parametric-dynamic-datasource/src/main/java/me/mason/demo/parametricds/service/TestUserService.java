package me.mason.demo.parametricds.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.mason.demo.parametricds.annotation.DS;
import me.mason.demo.parametricds.constants.DataSourceConstants;
import me.mason.demo.parametricds.entity.TestUser;
import me.mason.demo.parametricds.mapper.TestUserMapper;
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
        List<TestUser> resultData = testUserMapper.selectAll(queryWrapper.isNotNull("name"));
        return resultData;
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
