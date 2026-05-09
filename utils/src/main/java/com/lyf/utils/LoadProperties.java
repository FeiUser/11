package com.lyf.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class LoadProperties {

    static {
        Properties props = new Properties();
        String property = System.getProperty("user.dir");
        System.out.println(property);
        try (InputStream in = new FileInputStream(property + "/noweb_boot/config/c3p0.properties")) {
            props.load(in);
            System.out.println(props.getProperty("test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        System.out.println("start");
    }
}
