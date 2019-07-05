package com.longwei.mall.common.web.exception;

/**
 * 业务异常类，只用于业务打断。
*/
public class BusinessException extends ErrorCodeException {

	private static final long serialVersionUID = -5169960321643614644L;
	public BusinessException(String businessErrorCode) {
		super(businessErrorCode);
	}

	public BusinessException(String businessErrorCode, String message, Throwable cause) {
		super(businessErrorCode, message, cause);
	}

	public BusinessException(String businessErrorCode, String message) {
		super(businessErrorCode, message);
	}

	public BusinessException(String businessErrorCode, Throwable cause) {
		super(businessErrorCode, cause);
	}
	public BusinessException(String businessErrorCode, String message, Object result) {
		super(businessErrorCode,message,result);
	}



	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
