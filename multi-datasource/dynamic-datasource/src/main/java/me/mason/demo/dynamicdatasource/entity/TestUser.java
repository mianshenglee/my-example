package me.mason.demo.dynamicdatasource.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

/**
* 用户对象 test_user
*
* @author mason
* @date 2020-01-08
*/
@Data
@TableName("test_user")
public class TestUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;
    /** 姓名 */
    private String name;
    /** 手机号 */
    private String phone;
    /** 职称职别 */
    private String title;
    /** 邮箱 */
    private String email;
    /** 性别 */
    private String gender;
    /** 出生时间 */
    private Date dateOfBirth;
    /** 1:已删除,0:未删除 */
    private Integer deleted;
    /** 创建时间 */
    private Date sysCreateTime;
    /** 创建人 */
    private String sysCreateUser;
    /** 更新时间 */
    private Date sysUpdateTime;
    /** 更新人 */
    private String sysUpdateUser;
    /** 版本号 */
    private Long recordVersion;

    public TestUser() {
    }

}
