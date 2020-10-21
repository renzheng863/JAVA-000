package com.demo.jvm0104;

import java.io.*;

public class AbstractClassLoader extends ClassLoader {

    protected String path;

    public AbstractClassLoader(String path) {
        super(null);
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            //String fullPath = path + File.separator + name.replaceAll("\\.", "/") + ".class";
            String fullPath = path + name+".xlass";
            File file = new File(fullPath);
            byte[] data = getClassFileBytes(file);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*private byte[] getClassFileBytes(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream   bis = new BufferedInputStream(fis);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             int len1 = 0;
             byte[] data = new byte[1024];
            while (true) {
                int i = fileC.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            return baos.toByteArray();
        }
    }*/
    protected byte[] getClassFileBytes(File file)  {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            baos = new ByteArrayOutputStream();
            int len;
            byte[] data = new byte[1024];
            while ((len = bis.read(data)) != -1) {
                baos.write(data,0,len);
            }
            byte[] bytes = baos.toByteArray();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (baos != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }
}
