package me.mason.demo.proxy.springaop.controller;

import me.mason.demo.proxy.dynamicproxy.cglib.CglibTimeProxyInterceptor;
import me.mason.demo.proxy.springaop.model.User;
import me.mason.demo.proxy.springaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User控制器
 *
 * @author mason
 * @since 2019/12/19
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询单个用户
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) throws Exception {
        UserService proxy = CglibTimeProxyInterceptor.createProxy(UserService.class);
        //由于没有使用接口，不能使用jdk代理
//        JdkTimeProxyHandler handler = new JdkTimeProxyHandler();
//        UserService proxy = (UserService)handler.createPorxy(userService);
        return proxy.getUserById(userId);
    }

    @GetMapping()
    public List<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * 添加单个用户
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * 更新单个用户
     *
     * @param user
     * @return
     */
    @PutMapping("/user")
    public int updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 删除单个用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/user")
    public int deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

}
