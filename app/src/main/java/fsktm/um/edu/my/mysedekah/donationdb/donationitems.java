package fsktm.um.edu.my.mysedekah.donationdb;

import android.provider.BaseColumns;

public class donationitems {

    public donationitems(){}

    public static abstract class donation implements BaseColumns {

        public static final String TABLE_NAME = "donations";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USERID = "user_id";
        public static final String COLUMN_CAMPAIGN = "campaign_name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CAMPAIGN_USER_ID = "campaign_user_id";
    }

}
