package com.oguzhanuzman.github.gardirop;

public class Constants {
    public static class Format {
        public static final String JACKSON_DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    }
    public static class Security {
        public static class Role {
            public static final String MEMBER = "ROLE_MEMBER";
            public static final String ADMIN = "ROLE_ADMIN";
        }
    }

    public static class AWS {
        public static class S3 {
            public static final String BUCKET = "gardirop-resource-bucket";
            public static final String PRODUCT = "product";
        }
    }
}
