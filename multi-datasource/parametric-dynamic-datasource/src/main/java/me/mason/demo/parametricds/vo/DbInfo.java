package me.mason.demo.parametricds.vo;

import lombok.Data;

/**
 * 数据库连接信息
 *
 * @author: mason
 * @since: 2020/1/9
 **/
@Data
public class DbInfo {
    private String ip;
    private String port;
    private String dbName;
    private String driveClassName;
    private String username;
    private String password;
}
