package com.hongdatchy.common;

public class AppConfig {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // chứa đường dẫn đến thư viện jdbc
    public static final String URL_DATABASE = "jdbc:mysql://localhost:3306/thiet_bi_dien_v2"; // đường dẫn để kết nối đến schema
    public static final String USERNAME = "root";
    public static final String PASSWORD  = "hongdat10"; // mật khẩu của mysql

    public static String PREFIX = "hongdatchy";
    public static int expiredTime = 30 * 60 * 1000; // 30 minutes
}
