package me.mason.demo.dynamicdatasource.vo;


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
     * 错误显示信息，用于前端显示
     */
    private String errShowMsg;
    /**
     * 错误信息栈
     */
    private String errMsg;
    /**
     * 返回内容
     */
    private T resultData;

    public static ResponseResult of(boolean success, Object resultData, String errCode, String errShowMsg, String errMsg) {
        return new ResponseResult(success, errCode, errShowMsg, errMsg, resultData);
    }

    public static ResponseResult success(Object resultData) {
        return of(true, resultData, null, null, null);
    }

    public static ResponseResult error(String errShowMsg) {
        return of(false, null, null, errShowMsg, null);
    }

    public static ResponseResult error(String errCode, String errShowMsg, String errMsg) {
        return of(false, null, errCode, errShowMsg, errMsg);
    }
}
