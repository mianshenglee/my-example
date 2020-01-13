package me.mason.demo.dynamicdatasource.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import me.mason.demo.dynamicdatasource.entity.TestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户 Mapper
 *
 * @author mason
 * @date 2020-01-08
 */
@Repository
public interface TestUserMapper extends BaseMapper<TestUser> {

    /**
     * 自定义查询
     * @param wrapper 条件构造器
     * @return
     */
    List<TestUser> selectAll(@Param(Constants.WRAPPER) Wrapper<TestUser> wrapper);

}
