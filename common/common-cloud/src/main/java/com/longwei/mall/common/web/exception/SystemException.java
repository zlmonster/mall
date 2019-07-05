package com.longwei.mall.common.web.exception;

public class SystemException extends ErrorCodeException {

    private static final long serialVersionUID = -5169960321643614644L;

    public SystemException(String businessErrorCode) {
        super(businessErrorCode);
    }

    public SystemException(String businessErrorCode, String message, Throwable cause) {
        super(businessErrorCode, message, cause);
    }

    public SystemException(String businessErrorCode, String message) {
        super(businessErrorCode, message);
    }

    public SystemException(String businessErrorCode, Throwable cause) {
        super(businessErrorCode, cause);
    }
    public SystemException(String businessErrorCode, String message, Object result) {
        super(businessErrorCode,message,result);
    }



    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
