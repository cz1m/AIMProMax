package com.like4u.agreement.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/17 19:31
 */
public
enum Algorithm implements Serializer {
    Java {
        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                return clazz.cast(ois.readObject());

                //return (T) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public <T> byte[] serialize(T object) {

            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(object);
                return bos.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("序列化失败", e);
            }

        }
    },

    Json {
        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
            String json = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("json"+json);
            return gson.fromJson(json, clazz);
        }

        @Override
        public <T> byte[] serialize(T object) {

            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
            String json = gson.toJson(object);
            System.out.println("json"+json);
            return json.getBytes(StandardCharsets.UTF_8);

        }
    }
}
