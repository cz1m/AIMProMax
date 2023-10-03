package com.like4u.agreement.protocol;


/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/17 16:30
 */
public interface Serializer {

    <T> T deserialize(Class<T> clazz,byte[] bytes);

    <T> byte[] serialize(T object);


}
