package me.mason.advswagger.demo.exception;

/**
 * 统一响应枚举
 *
 * @author mason
 */
public enum UniExceptionEnums {
    //通用异常
    SYSTEM_ERROR("systemError","系统异常"),
    DATABASE_ERROR("databaseError","数据库异常"),
    ILLEGAL_ARGUMENT("illegalArgument","参数不合法{0}"),

    //业务异常
    NO_USER_EXIST("noUserExist","用户{0}不存在"),
    INVALID_PASSWORD("invalidPassword","密码错误"),
    NO_PERMISSION("noPermission","无权限执行此操作"),
    USERNAME_PSW_NOT_MATCH("usernamePswNotMatch","用户名和密码不匹配"),
    INVALID_MOBILE("invalidMobile","无效的手机号码"),
    INVALID_EMAIL("invalidEmail","无效的邮箱"),
    NOT_LOGIN("notLogin","未登陆"),
    CAPTCHA_ERROR("captchaError","验证码不正确");

    private String errCode;
    private String errShowMsg;

    UniExceptionEnums(String errCode, String errShowMsg) {
        this.errCode = errCode;
        this.errShowMsg = errShowMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrShowMsg() {
        return errShowMsg;
    }

    public void setErrShowMsg(String errShowMsg) {
        this.errShowMsg = errShowMsg;
    }
}
