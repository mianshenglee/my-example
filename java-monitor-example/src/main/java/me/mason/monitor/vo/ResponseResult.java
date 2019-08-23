package me.mason.monitor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回类
 *
 * @author mason
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult<T> {
    /**
     * 返回状态
     */
    private boolean success;
    /**
     * 错误信息代码
     */
    private String errCode;
    /**
     * 错误显示信息
     */
    private String errShowMsg;
    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 返回数据
     */
    private T resultData;

    public static ResponseResult of(boolean success, Object resultData, String errCode, String errShowMsg, String errMsg) {
        return new ResponseResult(success, errCode, errShowMsg, errMsg, resultData);
    }

    public static ResponseResult ok(Object resultData) {
        return of(true, resultData, null, null, null);
    }

    public static ResponseResult err(String errCode, String errShowMsg, String errMsg) {
        return of(false, null, errCode, errShowMsg, errMsg);
    }
}
