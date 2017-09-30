package com.viewhigh.bi.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;

/**
 * Created by zzq on 2017/6/14.
 */
public class FileUtil {
    /**
     * 读取字节码文件同目录下的资源文件
     *
     * @param fileName
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T readYmlByClassPath(String fileName, Class<T> type) {
        T t = null;
        //优先加载资源文件目录
        String resourcesPath = "classpath:" + fileName + ".yml";

        t = yml2Obj(resourcesPath, type);
        if (t == null) {
            String configPath = "file:config/" + fileName + ".yml";
            t = yml2Obj(configPath, type);
        }
        return t;
    }

    /**
     * yml文件映射实体
     *
     * @param ymlFilePathAllName
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T yml2Obj(String ymlFilePathAllName, Class<T> type) {
        T ret = null;
        if (StringUtils.isBlank(ymlFilePathAllName) || type == null)
            return ret;

        Yaml yaml = new Yaml();

        try {
            File yml = ResourceUtils.getFile(ymlFilePathAllName.trim());

            if (yml == null)
                return ret;

            ret = yaml.loadAs(new FileInputStream(yml), type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * 判断classes中文件是否存在，不能够读取jar中的文件，虽然判断存在
     *
     * @param filePathAllName
     * @return
     */
    public static boolean existResourceFile(String filePathAllName) {
        if (filePathAllName.startsWith("classpath:")) {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            String path = filePathAllName.substring("classpath:".length());
            URL url = cl != null ? cl.getResource(path) : ClassLoader.getSystemResource(path);
            if (url == null)
                return false;
            return true;
        }
        return false;
    }
}