package me.mason.advswagger.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.mason.advswagger.demo.annotation.ApiVersion;
import me.mason.advswagger.demo.common.ApiVersionConstant;
import me.mason.advswagger.demo.model.User;
import me.mason.advswagger.demo.service.UserService;
import me.mason.advswagger.demo.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User控制器
 *
 * @author mason
 * @since 2019/10/31
 */
@RestController
@RequestMapping("/users")
@Api(tags = "用户管理接口", description = "User Controller")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询单个用户
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据ID获取单个用户信息", notes = "根据ID返回用户对象")
    @GetMapping("/{userId}")
    public ResponseResult<User> getUserById(@ApiParam(value = "用户列表") @PathVariable long userId) {
        return ResponseResult.ok(userService.getUserById(userId));
    }

    /**
     * 查询多个用户
     * @return
     */
    @ApiVersion(group = {ApiVersionConstant.VERVION_100})
    @ApiOperation(value = "获取所有用户", notes = "返回全部用户")
    @GetMapping()
    public ResponseResult<List<User>> getUsers() {
        return ResponseResult.ok(userService.getUsers());
    }


    /**
     * 添加多个用户
     * @param users
     * @return
     */
    @ApiOperation(value = "添加多个用户", notes = "使用JSON以数组形式添加多个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "users", value = "用户JSON数组", required = true, dataType = "User",allowMultiple = true)
    })
    @PostMapping()
    public ResponseResult<String> addUsers(@RequestBody List<User> users) {
        if (userService.addUsers(users) > 0) {
            return ResponseResult.ok("users added");
        } else {
            return ResponseResult.err("ADD_USER_ERROR", "添加多个用户失败", "add exception");
        }
    }

    /**
     * 添加单个用户
     * @param user
     * @return
     */
    @ApiOperation(value = "添加一个用户", notes = "使用JSON形式添加一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token，格式: Bearer &lttoken&gt", required = false, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "user", value = "用户对象(JSON)", required = true, dataType = "User")
    })
    @PostMapping("/user")
    public ResponseResult<String> addUser(@RequestBody User user) {
        if (userService.addUser(user) > 0) {
            return ResponseResult.ok("user added");
        } else {
            return ResponseResult.err("ADD_USER_ERROR", "添加用户失败", "add exception");
        }
    }

    /**
     * 更新单个用户
     * @param user
     * @return
     */
    @ApiVersion(group = {ApiVersionConstant.VERVION_100})
    @ApiOperation(value = "更新某个用户", notes = "使用JSON形式添加一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户对象(JSON)", required = true, dataType = "User")
    })
    @PutMapping("/user")
    public ResponseResult<String> updateUser(@RequestBody User user) {
        if (userService.updateUser(user) > 0) {
            return ResponseResult.ok("user updated");
        } else {
            return ResponseResult.err("UPDATE_USER_ERROR", "更新用户失败", "update exception");
        }
    }

    /**
     * 删除单个用户
     * @param userId
     * @return
     */
    @ApiVersion(group = {ApiVersionConstant.VERVION_100})
    @ApiOperation(value = "删除某个用户", notes = "根据用户ID删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "long",paramType = "query")
    })
    @DeleteMapping("/user")
    public ResponseResult<String> deleteUser(long userId) {
        if (userService.deleteUser(userId) > 0) {
            return ResponseResult.ok("user deleted");
        } else {
            return ResponseResult.err("DELETE_USER_ERROR", "删除用户失败", "delete exception");
        }
    }

}
