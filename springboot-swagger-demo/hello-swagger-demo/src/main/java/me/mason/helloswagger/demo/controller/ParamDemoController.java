package me.mason.helloswagger.demo.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import me.mason.helloswagger.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Swagger测试控制器
 *
 * @author mason
 * @since 2019/11/4
 */
@RequestMapping("/param")
@RestController
@Slf4j
public class ParamDemoController {

    /**
     * HTTP GET请求
     * 无注解下获取参数，要求参数名称与HTTP请求参数名称一致，可以为空
     * @param intVal 整数
     * @param longVal 长整型
     * @param str 字符串
     * @return 响应JSON参数
     */
    @GetMapping("/no/annotation")
    public Map<String,Object> noAnnotation(Integer intVal, Long longVal, String str){
        Map<String,Object> paramsMap = MapUtil.newHashMap();
        paramsMap.put("intVal",intVal);
        paramsMap.put("longVal",longVal);
        paramsMap.put("str",str);

        return paramsMap;
    }

    /**
     * HTTP GET请求
     * 使用注解RequestParam获取参数，指定HTTP参数和方法参数映射关系，且不能为空（但可通过required设置false来允许为空）
     * @param intVal 整数
     * @param longVal 长整型
     * @param str 字符串
     * @return 响应JSON参数
     */
    @GetMapping("/annotation")
    public Map<String,Object> annotation(@RequestParam("int_val") Integer intVal,
                                         @RequestParam("long_val") Long longVal,
                                         @RequestParam(value="str_val", required = false) String str){
        Map<String,Object> paramsMap = MapUtil.newHashMap();
        paramsMap.put("intVal",intVal);
        paramsMap.put("longVal",longVal);
        paramsMap.put("str",str);

        return paramsMap;
    }

    /**
     * HTTP GET请求
     * 使用数组做为参数，数组元素使用逗号(,)分隔
     * @param intArr 整数
     * @param longArr 长整型
     * @param strArr 字符串
     * @return 响应JSON参数
     */
    @GetMapping("/array")
    public Map<String,Object> requestArray(int[]intArr,long[]longArr,String[] strArr){
        Map<String,Object> paramsMap = MapUtil.newHashMap();
        paramsMap.put("intArr",intArr);
        paramsMap.put("longArr",longArr);
        paramsMap.put("strArr",strArr);

        return paramsMap;
    }
    /**
     * HTTP POST请求
     * 使用JSON传递参数，通过RequestBody注解得到JSON参数,映射转换为User对象
     * @param user 整数
     * @return 响应JSON参数
     */
    @PostMapping("/add/user")
    public User addUser(@RequestBody User user){
        log.debug(user.toString());
        user.setId(RandomUtil.randomInt(50));
        return user;
    }

    /**
     * HTTP POST请求
     * List参数，数组元素使用逗号(,)分隔
     * @param userList 整数
     * @return 响应JSON参数
     */
    @PostMapping("/add/uers")
    public List<User> addUsers(@RequestBody List<User> userList){
        log.debug("size:"+userList.size());
        userList.forEach(user -> user.setId(RandomUtil.randomInt(50)));
        return userList;
    }

    /**
     * HTTP GET请求
     * 使用URL传递参数，"{}"为占位符，如"/my/users/10"
     * @param id 参数Id
     * @return 响应JSON参数
     */
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id){
        log.debug("get user by id:" + id);
        User user = User.builder().id(id)
                .age(RandomUtil.randomInt(50))
                .name(RandomUtil.randomString(10))
                .build();
        return user;
    }



}
