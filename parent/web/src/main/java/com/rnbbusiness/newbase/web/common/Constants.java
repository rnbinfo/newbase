package com.rnbbusiness.newbase.web.common;

public class Constants {

    public static enum RequestSource {
        INNER("内部", "inner"), OUTER("外部", "inner");

        private String name;
        private String value;

        private RequestSource(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
