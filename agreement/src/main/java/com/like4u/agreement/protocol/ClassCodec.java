package com.like4u.agreement.protocol;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/19 16:22
 *
 * 对Class类型对象进行序列化，往JSONElement中添加相关描述来实现
 * 将Class对象的全限定名（类的完整名称，包括包名）作为字符串包装在JsonPrimitive对象中
 * 当您使用Gson进行反序列化时，可以根据JSON的结构从JsonElement中获取相应的数据类型
 */
public
class ClassCodec implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {

    @Override
    public Class<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        String str = jsonElement.getAsString();
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonElement serialize(Class<?> aClass, Type type, JsonSerializationContext jsonSerializationContext) {

        return new JsonPrimitive(aClass.getName());
    }
}
