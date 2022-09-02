package com.util;

public class Constant {
    /**
     * PR
     *
     * @Date 2022-07-27
     * @Author kimwoosik
     * @Description
     * Server Token Life Time
     */
    public static final int TOKEN_END_MINUTES = 30;
    /**
     * PR
     *
     * @Date 2022-07-26
     * @Author kimwoosik
     * @Description
     * Database Setting Static Class
     */
    public static class DatabaseSetting {
        public static final String DATABASE_SOURCE = "LOCAL1";
        /**
         * PR
         *
         * @Date 2022-07-26
         * @Author kimwoosik
         * @Description Database Connection Pool Size
         */
        public static final int POOL_SIZE = 5;
    }

    /**
     * PR
     *
     * @Date 2022-07-26
     * @Author kimwoosik
     * @Description Session 설정 시간 변수
     */
    public static int SESSION_INTERVAL = 9999;
    public static final String VERSION = "0.1a";

    /**
     * PR
     *
     * @Date 2022-07-26
     * @Author kimwoosik
     * @Description LogInterceptor Logging
     */
    public static class LogSetting {
        public static final boolean HEADER_LOG = true;
        public static final boolean PARAMETER_LOG = true;
        public static final boolean PAYLOAD_LOG = true;
        public static final boolean REQUEST_BODY_LOG = true;
        public static final boolean RESPONSE_BODY_LOG = true;
    }

    /**
     * Time Constant
     */
    public static final int SEC = 60;
    public static final int MIN = 60;
    public static final int HOUR = 24;
    public static final int DAY = 30;
    public static final int MONTH = 12;
}