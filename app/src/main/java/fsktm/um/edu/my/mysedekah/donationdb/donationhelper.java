package fsktm.um.edu.my.mysedekah.donationdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fsktm.um.edu.my.mysedekah.campaigndb.campaigncontent;
import fsktm.um.edu.my.mysedekah.donationdb.donationitems.donation;

public class donationhelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "donations.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + donation.TABLE_NAME + "(" +
            donation.COLUMN_ID + " TEXT," +
            donation.COLUMN_USERID + " TEXT," +
            donation.COLUMN_CAMPAIGN + " TEXT," +
            donation.COLUMN_AMOUNT  + " TEXT,"+
            donation.COLUMN_DATE + " TEXT," +
            donation.COLUMN_CAMPAIGN_USER_ID + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + donation.COLUMN_ID;

    private String[] allColumn = {donation.COLUMN_ID, donation.COLUMN_USERID, donation.COLUMN_CAMPAIGN, donation.COLUMN_AMOUNT, donation.COLUMN_DATE, donation.COLUMN_CAMPAIGN_USER_ID};

    public donationhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertdonation(ContentValues values){
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert( donation.TABLE_NAME, null, values );
        database.close();
    }


    public boolean checkID(String id){

        String[] selectionArgs = {id};
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = null;
        String sql ="SELECT _ID FROM "+donation.TABLE_NAME+" WHERE _ID="+id;
        cursor = database.rawQuery(sql,null);

        if(cursor.getCount()>0){
            cursor.close();
            database.close();
            return true;
        }
        else{
            cursor.close();
            database.close();
            return false;
        }

    }

    public long getNextID(){
        SQLiteDatabase database = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(database, donation.TABLE_NAME);
        return count;
    }

    public donationcontent getDonation(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = null;
        String sql ="SELECT * FROM "+donation.TABLE_NAME+" WHERE _ID="+id;
        cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        donationcontent donation_content = new donationcontent();
        donation_content.set_id(cursor.getString(0));
        donation_content.setUser_id(cursor.getString(1));
        donation_content.setCampaign(cursor.getString(2));
        donation_content.setAmount(cursor.getString(3));
        donation_content.setDate(cursor.getString(4));
        donation_content.setCampaign_user_id(cursor.getString(5));
        return donation_content;
    }

    public List<donationcontent> getDonationsByUser(String user_id){
        SQLiteDatabase database = this.getWritableDatabase();
        List<donationcontent> list_donations = new ArrayList<donationcontent>();

        Cursor cursor = null;
        String sql ="SELECT * FROM "+donation.TABLE_NAME+" WHERE user_id="+user_id;
        cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            donationcontent donation_content = new donationcontent();
            donation_content.set_id(cursor.getString(0));
            donation_content.setUser_id(cursor.getString(1));
            donation_content.setCampaign(cursor.getString(2));
            donation_content.setAmount(cursor.getString(3));
            donation_content.setDate(cursor.getString(4));
            donation_content.setCampaign_user_id(cursor.getString(5));
            list_donations.add(donation_content);
            cursor.moveToNext();
        }
        return list_donations;
    }

    public void deleteDonation(String id){


        String selection = donation.COLUMN_ID + " Like ?";
        String [] selectionArgs = {id};

        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(donation.TABLE_NAME, selection, selectionArgs);

        database.close();
    }

    public void update(ContentValues values, String id){

        SQLiteDatabase database = this.getWritableDatabase();
        database.update(donation.TABLE_NAME, values, "_id=" + id, null);
        database.close();
    }



}

