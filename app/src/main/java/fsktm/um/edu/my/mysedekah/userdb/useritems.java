package fsktm.um.edu.my.mysedekah.userdb;

import android.provider.BaseColumns;

public class useritems {

    public useritems(){}

    public static abstract class user implements BaseColumns {

        public static final String TABLE_NAME = "user";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASS = "password";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_BANK= "bank";
        public static final String COLUMN_BANKNUM= "bank_num";
        public static final String COLUMN_APPLYSTATUS= "applystatus";
        public static final String COLUMN_ACCTYPE = "acctype";
    }

}
