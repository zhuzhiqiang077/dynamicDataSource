package com.viewhigh.bi.common.utils;

import java.io.*;
import java.util.List;

/**
 * Created by zzq on 2017/9/28.
 */
public class ObjectUtil {
    /**
     * 对象深copy
     * @param src
     * @param <T>
     * @return
     */
    public static <T> List<T> deepCopy(List<T> src) {
        //先将原list对象输出为字节数组
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将字节数组放入到新的输入流中，并以List的形式返回
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());

            ObjectInputStream in = new ObjectInputStream(byteIn);

            return (List<T>) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
