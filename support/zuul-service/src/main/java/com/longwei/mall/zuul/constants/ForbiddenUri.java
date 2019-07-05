package com.longwei.mall.zuul.constants;

/**
 * @ClassName ForbiddenUri
 * @Description 禁止访问的地址配置
 * @Author lizhilong
 * @Date 2019-05-27 21:44
 * @ModifyDate 2019-05-27 21:44
 * @Version 1.0
 */
public class ForbiddenUri {
    private static final String[] FORBIDDEN_URIS =
            new String[]{
                "swagger-ui.html",
                "swagger",
                "v2/api-docs"
            };

    /**
     * 拦截请求uri
     * @param uri
     * @return
     */
    public static boolean filter(final String uri){
        for(String s : FORBIDDEN_URIS){
            if(uri.contains(s)){
                return true;
            }
        }
        return false;
    }
}
