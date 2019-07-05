package com.longwei.mall.common.web.client.ribbon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.longwei.mall.common.web.annotation.method.GenericResponse;
import com.longwei.mall.common.web.constants.BaseResultCode;
import com.longwei.mall.common.web.exception.BusinessException;
import com.longwei.mall.common.web.exception.ErrorCodeException;
import com.longwei.mall.common.web.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public abstract class AbstractClient {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractClient.class);
    protected String instanceUrl;
    protected RestTemplate restTemplate;
    public AbstractClient(){

    }
    public AbstractClient(RestTemplate restTemplate,String instanceUrl){
        this.restTemplate = restTemplate;
        this.instanceUrl = instanceUrl;
    }

    protected  <T> T getForObject(String uri, TypeReference<GenericResponse<T>> typeReference, Map<String, ?> uriVariables){
        LOG.info("begin invoke mocriservice interface: " + getInstanceUrl() + uri);
        try {
            GenericResponse<T> response = null;
            String respStr = null;
            if(uriVariables == null || uriVariables.isEmpty()){
                respStr = restTemplate.getForObject(getInstanceUrl() + uri, String.class );
            }else{
                respStr =  restTemplate.getForObject(getInstanceUrl() + uri, String.class , uriVariables);
            }
            response = JSON.parseObject(respStr, typeReference);
            checkResponseError(response);
            return response.getResult();
        } catch (RestClientException e) {
            throw new SystemException(BaseResultCode.SYSTEM_ERROR, e);
        }
    }
    protected  <T> T postForObject(String uri, Object request,TypeReference<GenericResponse<T>> typeReference, Map<String, ?> uriVariables){
        LOG.info("begin invoke mocriservice interface: " + getInstanceUrl() + uri);
        try {
            GenericResponse<T> response = null;
            String respStr = null;
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//            headers.add("x-forwarded-for", HttpAccessUtil.getIpAddress());

            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(request), headers);

            if(uriVariables == null || uriVariables.isEmpty()){
                respStr = restTemplate.postForObject(getInstanceUrl() + uri, formEntity, String.class);
            }else{
                respStr =  restTemplate.postForObject(getInstanceUrl() + uri, formEntity, String.class , uriVariables);
            }
            response = JSON.parseObject(respStr, typeReference);
            checkResponseError(response);
            return response.getResult();
        } catch (RestClientException e) {
            throw new SystemException(BaseResultCode.SYSTEM_ERROR, e);
        }
    }

    /**
     * hytircx方法统一处理类。
     * @param e
     * @throws RuntimeException
     */
    protected  RuntimeException commHytircxHandler(Throwable e) {
        RuntimeException re = null;
        if (e instanceof BusinessException) {
            re = (BusinessException)e;
        }
        else if(e instanceof SystemException){
            re = (SystemException)e;
        }else{
            re = new ErrorCodeException(BaseResultCode.UNKOWN_ERROR,BaseResultCode.UNKOWN_ERROR_MSG, e);
        }
        return re;
    }

    private void checkResponseError(GenericResponse response){
        if(response == null
                 || BaseResultCode.SYSTEM_ERROR.equals(response.getCode())){
            throw new SystemException(BaseResultCode.SYSTEM_ERROR);
        }
    }

    protected String getInstanceUrl(){
        return instanceUrl;
    }

}
