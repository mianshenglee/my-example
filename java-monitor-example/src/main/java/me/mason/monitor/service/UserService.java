package me.mason.monitor.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import me.mason.monitor.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 用户服务类
 *
 * @author mason
 * @date 2019/6/1
 */
@Slf4j
@Service
public class UserService {

    private List<User> userList = new ArrayList<>();
    /**
     * 根据ID获取用户
     *
     * @return
     */
    public User getUser() {
        return mockUser();
    }

    /**
     * 获取用户数组
     *
     * @return
     */
    public List<User> getUsers(int num) {
        userList.clear();
        for(int i=0 ; i < num; i++){
            userList.add(mockUser());
        }
        return userList;
    }


    /**
     * 内存溢出
     *
     * @return
     */
    public void getUserWithOOM() throws InterruptedException {

        while(true){
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(1);
            userList.add(mockUser());
            log.debug("add user count:" + userList.size());
        }
    }

    /**
     * 模拟用户
     *
     * @return
     */
    private User mockUser() {
        String userId = UUID.randomUUID().toString();
        return User.builder().id(userId)
                .name("user-" + userId)
                .dateOfBirth(DateUtil.date().toJdkDate())
                .gender("m")
                .email("user-" + userId + "@test.com")
                .build();
    }
}
