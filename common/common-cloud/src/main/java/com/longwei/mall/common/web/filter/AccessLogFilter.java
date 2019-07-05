package com.longwei.mall.common.web.filter;

import com.longwei.mall.common.web.annotation.method.GenericResponse;
import com.longwei.mall.common.web.log.LoggerUtils;
import com.longwei.mall.common.web.log.MallRequestHolder;
import com.longwei.mall.common.web.log.RestfulApiAccessInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

@WebFilter(filterName = "AccessLogFilter", urlPatterns = "/*")
public class AccessLogFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(AccessLogFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		String[] staticSuffixs = new String[] { ".js",".gif",".jpg",".png",".css",".ico"};

		for (String suffix : staticSuffixs) {
			if (url.endsWith(suffix) ||
					url.indexOf("/actuator") > -1  ||
					url.indexOf("/swagger") > -1  ||
					url.indexOf("/v2") > -1  ||
					url.indexOf("/manage/") > -1) {
				filterChain.doFilter(request, response);
				return;
			}

		}


		Long startTime = System.currentTimeMillis();

		Map<String, String[]> paramMap = request.getParameterMap();
		StringBuffer oriStr = new StringBuffer();
		oriStr.append(request.getRequestURL());
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			String[] values = entry.getValue();
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				if (oriStr.length() != 0) {
					oriStr.append("&");
				}
				oriStr.append(entry.getKey()).append("=").append(value);

			}
		}



		StringBuffer headerVaules = new StringBuffer();
		Enumeration headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			if (headerVaules.length() != 0 ) {
				headerVaules.append(",");
			}
			String name = (String) headerNames.nextElement();
			headerVaules.append(name + ":").append(request.getHeader(name));
		}
		LOG.info("request info: " + oriStr);
		LOG.info("request headers-->" + headerVaules);

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String contentType = request.getContentType();
		if(contentType!=null && !"".equals(contentType))
		{
			if(contentType.contains("application/json") ||
					contentType.contains("application/xml") ||
					contentType.contains("text/plain")){

				httpRequest = new BufferedServletRequestWrapper( httpRequest );
				StringBuffer requestBody = new StringBuffer();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				IOUtils.copy(httpRequest.getInputStream(), os);
				requestBody.append(os.toString(StandardCharsets.UTF_8.name()));
				LOG.info("request body--->"+ requestBody);
			}
		}

		// 请求埋点开始
		RestfulApiAccessInfo accessInfo = new RestfulApiAccessInfo(request);

		filterChain.doFilter(httpRequest, response);
		Long endTime = System.currentTimeMillis();
		LOG.info(startTime + " end, cost "
				+ (endTime - startTime) + "ms:" + oriStr);

		// 请求埋点接口
		GenericResponse logResponse = MallRequestHolder.getResponse();
		if(logResponse != null){
			accessInfo.setResultCode(logResponse.getCode());
			Date end = new Date();
			accessInfo.setEndTime(end);
			accessInfo.setCost(end.getTime()- accessInfo.getStartTime().getTime());
			LoggerUtils.RESTFUL_API_LOGGER.info(accessInfo.toString());
		}
		MallRequestHolder.removeResponse();
	}

}