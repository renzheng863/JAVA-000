package com.demo.jvm0104;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class HelloByteCode extends AbstractClassLoader{
    public HelloByteCode(String path) {
        super(path);
    }
        public static void main(String[] args) throws IOException {
            String path = "E:\\JAVA\\Demo\\out\\production\\Demo\\com\\demo\\jvm0104\\";
            HelloByteCode obj = new HelloByteCode(path);
            String className = "Hello";
            try {
                Class hello = obj.loadClass(className);
                Method method = hello.getMethod("hello");
                method.invoke(hello.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
