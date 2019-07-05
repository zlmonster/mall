package com.longwei.mall.common.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 含错误码的异常类，需要继承HystrixBadRequestException
 */
public class ErrorCodeException extends RuntimeException {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = -6862869612441955620L;
	
	/**错误码*/
	private String errorCode;

	private String errorMessage;

	/**异常结果返回*/
	private Object result;
	
	public ErrorCodeException(String errorCode) {
		this(errorCode, null, null);
	}

	public ErrorCodeException(String errorCode, String message) {
		this(errorCode, message, null);
	}
	
	public ErrorCodeException(String errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public ErrorCodeException(String errorCode, String message, Throwable cause) {
		super("ErrorCode: "+ errorCode + ", Message: "+message, cause);
		this.errorMessage=message;
		Assert.hasLength(errorCode, "Error code must be meaningful");
		this.errorCode = errorCode;
	}

	public ErrorCodeException(String errorCode, String message,Object result) {
		super("ErrorCode: "+ errorCode + ", Message: "+message);
		this.errorMessage=message;
		Assert.hasLength(errorCode, "Error code must be meaningful");
		this.errorCode = errorCode;
		this.result = result;
	}


	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public Object getResult(){
		return this.result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
