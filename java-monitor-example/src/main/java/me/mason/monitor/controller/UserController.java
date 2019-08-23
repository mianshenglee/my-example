package me.mason.monitor.controller;

import me.mason.monitor.entity.User;
import me.mason.monitor.service.UserService;
import me.mason.monitor.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * user控制器
 *
 * @author mason
 * @date 2019/6/1
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public ResponseResult<User> getUser() {
        User user = userService.getUser();
        return ResponseResult.ok(user);
    }

    @GetMapping("/users")
    public ResponseResult<User> getUsers(int num) {
        List<User> users = userService.getUsers(num);
        return ResponseResult.ok(users);
    }

    @GetMapping("/oom")
    public ResponseResult<User> getUserWithOOM() {
        new Thread(() -> {
            try {
                userService.getUserWithOOM();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseResult.ok("done");
    }

    @GetMapping("/exception")
    public ResponseResult<String> getException() {
        try {
            System.out.println("start");
            int i = 1 / 0;
            System.out.println("end");
        } catch (Exception e) {
        }
        return ResponseResult.ok("done");
    }
}
