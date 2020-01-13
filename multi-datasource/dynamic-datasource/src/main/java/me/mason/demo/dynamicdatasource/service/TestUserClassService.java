package me.mason.demo.dynamicdatasource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.mason.demo.dynamicdatasource.annotation.DS;
import me.mason.demo.dynamicdatasource.constants.DataSourceConstants;
import me.mason.demo.dynamicdatasource.entity.TestUser;
import me.mason.demo.dynamicdatasource.mapper.TestUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 服务类，数据源注解在类，
 *
 * @author: mason
 * @since: 2020/1/9
 **/
@DS(DataSourceConstants.DS_KEY_SLAVE)
public class TestUserClassService {
    @Autowired
    private TestUserMapper testUserMapper;

    /**
     * 查询slave库User
     * @return
     */
    public List<TestUser> getSlaveUser(){
        return testUserMapper.selectList(null);
    }
}
