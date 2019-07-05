package com.longwei.mall.common.web.client.feign;

import com.longwei.mall.common.util.dozer.DozerMapperUtil;
import com.longwei.mall.common.web.annotation.method.GenericResponse;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class GenericResponseEntityDecoder implements Decoder {

	private Decoder decoder;

	public GenericResponseEntityDecoder(Decoder decoder) {
		this.decoder = decoder;
	}

	@Override
	public Object decode(final Response response, Type type) throws IOException,
			FeignException {

		if (isParameterizeHttpEntity(type)) {
			type = ((ParameterizedType) type).getActualTypeArguments()[0];
			Object decodedObject = decoder.decode(response, type);

			return createResponse(decodedObject, response);
		} else if (isHttpEntity(type)) {
			return createResponse(null, response);
		} else {

			GenericResponse genericResponse = (GenericResponse) decoder.decode(response, GenericResponse.class);
			try {
				if (type instanceof ParameterizedType) {
					type = ((ParameterizedType) type).getActualTypeArguments()[0];
					return DozerMapperUtil.getInstance().mapList((List) genericResponse.getResult(), (Class<?>) type);
				} else {
					Object object = Class.forName(type.getTypeName()).newInstance();
					DozerMapperUtil.getInstance().copy(genericResponse.getResult(), object);
					return object;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	private boolean isParameterizeHttpEntity(Type type) {
		if (type instanceof ParameterizedType) {
			return isHttpEntity(((ParameterizedType) type).getRawType());
		}
		return false;
	}

	private boolean isHttpEntity(Type type) {
		if (type instanceof Class) {
			Class c = (Class) type;
			return HttpEntity.class.isAssignableFrom(c);
		}
		return false;
	}

	private <T> ResponseEntity<T> createResponse(Object instance, Response response) {

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		for (String key : response.headers().keySet()) {
			headers.put(key, new LinkedList<>(response.headers().get(key)));
		}

		return new ResponseEntity<>((T) instance, headers, HttpStatus.valueOf(response
				.status()));
	}
}
