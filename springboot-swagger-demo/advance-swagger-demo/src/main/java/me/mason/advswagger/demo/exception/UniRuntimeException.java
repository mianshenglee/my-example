package me.mason.advswagger.demo.exception;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * 统一业务异常
 *
 * @author mason
 */
public class UniRuntimeException extends RuntimeException {
    protected String errCode;
    protected String errShowMsg;

    public UniRuntimeException(String errCode, String errShowMsg, String message, Throwable cause, Object... args) {
        super(message, cause);
        this.errCode = errCode;
        if (Objects.isNull(args) || args.length <= 0) {
            this.errShowMsg = errShowMsg;
        } else {
            this.errShowMsg = MessageFormat.format(errShowMsg, args);
        }
    }
    public UniRuntimeException(String errCode, String errShowMsg, String message, Object... args) {
        this(errCode, errShowMsg, message, null, args);
    }

    public UniRuntimeException(String errCode, String errShowMsg, Object... args) {
        this(errCode, errShowMsg, null, null, args);
    }

    public UniRuntimeException(String message, Throwable cause) {
        this(null, null, message, cause, null);
    }

    public UniRuntimeException(String message) {
        this(null, null, message, null, null);
    }

    public UniRuntimeException(UniExceptionEnums enums, String message, Throwable cause, Object... args) {
        this(enums.getErrCode(), enums.getErrShowMsg(), message, cause, args);
    }

    public UniRuntimeException(UniExceptionEnums enums, String message, Object... args) {
        this(enums.getErrCode(), enums.getErrShowMsg(), message, null, args);
    }

    public UniRuntimeException(UniExceptionEnums enums, Object... args) {
        this(enums, null, args);
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
