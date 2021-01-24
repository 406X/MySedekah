package fsktm.um.edu.my.mysedekah.campaigndb;

import android.provider.BaseColumns;

public class campaignitems {

    public campaignitems(){}

    public static abstract class campaign implements BaseColumns {

        public static final String TABLE_NAME = "campaign";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_IMG = "image";
    }

}
