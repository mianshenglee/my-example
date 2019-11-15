package me.mason.advswagger.demo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.mason.advswagger.demo.exception.UniExceptionEnums;
import me.mason.advswagger.demo.exception.UniRuntimeException;

/**
 *  统一返回类
 * @author mason
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult<T> {
	@ApiModelProperty(value="返回状态",example = "true")
	private boolean success;
	@ApiModelProperty(value="错误信息代码")
	private String errCode;
	@ApiModelProperty(value="错误显示信息")
	private String errShowMsg;
	@ApiModelProperty(value="错误信息")
	private String errMsg;
	@ApiModelProperty(value = "返回数据",notes = "若返回错误信息，此数据为null或错误信息对象")
	private T resultData;

	public static ResponseResult of(boolean success,Object resultData,String errCode,String errShowMsg, String errMsg) {
		return new ResponseResult(success,errCode, errShowMsg,errMsg,resultData);
	}

	public static ResponseResult ok(Object resultData) {
		return of(true,resultData,null,null,null);
	}

	public static ResponseResult err(String errCode,String errShowMsg,String errMsg) {
		return of(false,null, errCode,errShowMsg,errMsg);
	}

	public static ResponseResult err(UniExceptionEnums uniExceptionEnums) {
		return of(false,null, uniExceptionEnums.getErrCode(),uniExceptionEnums.getErrShowMsg(),null);
	}

	public static ResponseResult err(UniExceptionEnums uniExceptionEnums,Exception e) {
		return of(false,null, uniExceptionEnums.getErrCode(),uniExceptionEnums.getErrShowMsg(),e.getMessage());
	}

	public static ResponseResult err(UniRuntimeException e) {
		return of(false,null, e.getErrCode(),e.getErrShowMsg(),e.getMessage());
	}
}
