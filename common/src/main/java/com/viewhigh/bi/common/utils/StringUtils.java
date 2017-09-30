package com.viewhigh.bi.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zzq on 2017/6/13.
 */
public class StringUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;

        //获取数组的总长度
        for (T[] array : rest) {
            totalLength += array.length;
        }

        //建立完整长度的数组
        T[] result = Arrays.copyOf(first, totalLength);

        //以首个数组长度作为偏移量copy
        int offset = first.length;

        //将其它数组copy到最终数组中
        for (T[] array : rest) {
            //原数组，原数组开始copy的位置，目标数组，目标数组的copy位置，copy到目标数组的长度
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    public static Object[] concatAll(List<Object[]> rest) {
        int totalLength = 0;

        //获取数组的总长度
        for (Object[] array : rest) {
            totalLength += array.length;
        }

        Object result[] = new Object[totalLength];

        int offset = 0;

        for (Object[] array : rest) {
            //原数组，原数组开始copy的位置，目标数组，目标数组的copy位置，copy到目标数组的长度
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delim) {
        return collectionToDelimitedString(coll, delim, null, null);
    }

    public static String collectionToDelimitedString(Collection<?> coll) {
        return collectionToDelimitedString(coll, null, null, null);
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
        if (isEmpty(coll)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator it = coll.iterator();

            while (it.hasNext()) {
                if (!org.apache.commons.lang3.StringUtils.isBlank(prefix))
                    sb.append(prefix);

                sb.append(it.next());

                if (!org.apache.commons.lang3.StringUtils.isBlank(suffix))
                    sb.append(suffix);

                if (!org.apache.commons.lang3.StringUtils.isBlank(delim) && it.hasNext())
                    sb.append(delim);

            }

            return sb.toString();
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 由英文字母以及数字组合的随机数
     * 去除了容易看错的l 1 0 o
     *
     * @param length 需要生成的字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijkmnpqrstuvwxyz23456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getCurrDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date());//可以方便地修改日期格式
    }

    public static Date getCurrDate() {
        return new Date(System.currentTimeMillis());//可以方便地修改日期格式
    }

    public static Date getDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").parse(date);//可以方便地修改日期格式
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
