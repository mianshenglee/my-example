package me.mason.demo.proxy.springaop.service;

import lombok.extern.slf4j.Slf4j;
import me.mason.demo.proxy.springaop.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User服务类
 *
 * @author mason
 * @since 2019/10/31
 */
@Slf4j
@Service
public class UserService{
    /**
     * 根据ID获取用户
     *
     * @param id 用户ID
     * @return
     */
    public User getUserById(long id) {
        return User.builder().id(id)
                .age(new Random().nextInt(50))
                .name("test" + id)
                .build();
    }

    /**
     * 返回用户数组
     *
     * @return
     */
    public List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        int len = 10;
        for (int i = 1; i <= len; i++) {
            users.add(User.builder().id(i)
                    .name("test" + i)
                    .age(i * 5)
                    .build());
        }

        return users;
    }

    /**
     * 添加单个用户
     * @param user
     * @return
     */
    public int addUser(User user) {
        Random random = new Random();
        user.setId(random.nextInt(100));
        log.debug("add user:" + user.toString());
        return 1;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateUser(User user){
        log.debug("update user" + user.toString());
        return 1;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public int deleteUser(long userId){
        log.debug("delete user:" + userId);
        return 1;
    }
}
