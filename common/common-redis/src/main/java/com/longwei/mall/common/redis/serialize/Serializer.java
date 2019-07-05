package com.longwei.mall.common.redis.serialize;

public interface Serializer {

	byte[] serialize(Object obj);
	Object deserialize(byte[] bytes);

}
