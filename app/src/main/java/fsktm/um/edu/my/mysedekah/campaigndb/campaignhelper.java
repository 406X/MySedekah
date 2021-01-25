package fsktm.um.edu.my.mysedekah.campaigndb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fsktm.um.edu.my.mysedekah.campaigndb.campaignitems.campaign;
import fsktm.um.edu.my.mysedekah.campaigndb.campaigncontent;

public class campaignhelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + campaign.TABLE_NAME + "(" +
            campaign.COLUMN_ID + " TEXT," +
            campaign.COLUMN_TITLE + " TEXT," +
            campaign.COLUMN_DESC + " TEXT," +
            campaign.COLUMN_IMG  + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + campaign.COLUMN_ID;

    private String[] allColumn = {campaign.COLUMN_ID, campaign.COLUMN_TITLE, campaign.COLUMN_DESC, campaign.COLUMN_IMG};

    public campaignhelper(Context context) {
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

    public void insertcampaign(ContentValues values){
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert( campaign.TABLE_NAME, null, values );

        database.close();
    }


    public boolean checkID(String id){

        String[] selectionArgs = {id};
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = null;
        String sql ="SELECT _ID FROM "+campaign.TABLE_NAME+" WHERE _ID="+id;
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
    public List<campaigncontent> getAllCampaigns(){
        List<campaigncontent> list_campaigns = new ArrayList<campaigncontent>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(campaign.TABLE_NAME, allColumn , null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            campaigncontent campaign_content = new campaigncontent();
            campaign_content.set_id(cursor.getString(0));
            campaign_content.setTitle(cursor.getString(1));
            campaign_content.setDesc(cursor.getString(2));
            campaign_content.setImg(cursor.getBlob(3));
            list_campaigns.add(campaign_content);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return list_campaigns;
    }

    public campaigncontent getCampaign(String id){
        String[] selectionArgs = {id};
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = null;
        String sql ="SELECT * FROM "+campaign.TABLE_NAME+" WHERE _ID="+id;
        cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        campaigncontent campaign_content = new campaigncontent();
        campaign_content.set_id(cursor.getString(0));
        campaign_content.setTitle(cursor.getString(1));
        campaign_content.setDesc(cursor.getString(2));
        campaign_content.setImg(cursor.getBlob(3));
        database.close();
        return campaign_content;
    }


    public void deleteCampaign(String id){


        String selection = campaign.COLUMN_ID + " Like ?";
        String [] selectionArgs = {id};

        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(campaign.TABLE_NAME, selection, selectionArgs);

        database.close();
    }

    public void update(ContentValues values, String id){

        SQLiteDatabase database = this.getWritableDatabase();
        database.update(campaign.TABLE_NAME, values, "_id=" + id, null);
        database.close();
    }



}

