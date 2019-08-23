package me.mason.monitor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * entity基类
 *
 * @author mason
 * @since 2019/6/6
 */
@Data
public class BaseEntidy {
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sysCreateTime;

    /**
     * 创建人
     */
    private String sysCreateUser;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sysUpdateTime;

    /**
     * 更新人
     */
    private String sysUpdateUser;

}
