package fsktm.um.edu.my.mysedekah.userdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fsktm.um.edu.my.mysedekah.userdb.useritems.user;

public class userhelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "userdata.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + user.TABLE_NAME + "(" +
           user.COLUMN_ID + " TEXT," +
            user.COLUMN_EMAIL + " TEXT," +
            user.COLUMN_PASS + " TEXT," +
            user.COLUMN_NAME  + " TEXT," +
            user.COLUMN_PHONE  + " TEXT,"+
            user.COLUMN_BANK + " TEXT," +
            user.COLUMN_BANKNUM  + " TEXT,"+
            user.COLUMN_APPLYSTATUS  + " TEXT,"+
            user.COLUMN_ACCTYPE  + " TEXT)";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_BANK= "bank";
    public static final String COLUMN_BANKNUM= "bank_num";
    public static final String COLUMN_APPLYSTATUS= "applystatus";
    public static final String COLUMN_ACCTYPE = "acctype";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + user.COLUMN_ID;

    private String[] allColumn = {user.COLUMN_ID, user.COLUMN_EMAIL, user.COLUMN_PASS, user.COLUMN_NAME, user.COLUMN_PHONE,
            user.COLUMN_BANK, user.COLUMN_BANKNUM,  user.COLUMN_APPLYSTATUS, user.COLUMN_ACCTYPE};

    public userhelper(Context context) {
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

    public void insertUser(ContentValues values){
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert( user.TABLE_NAME, null, values );
        database.close();
    }

    public long getNextID(){
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, user.TABLE_NAME);
            return count;
        }
        catch(SQLiteException e){
            return 0;
        }
    }

    public boolean checkEmail(String email){

        String[] selectionArgs = {email};
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT _ID FROM "+user.TABLE_NAME+" WHERE email=?",new String[]{email});

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
    public List<usercontent> getAllUsers(){
        List<usercontent> list_campaigns = new ArrayList<usercontent>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(user.TABLE_NAME, allColumn , null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            usercontent user_content = new usercontent();
            user_content.set_id(cursor.getString(0));
            user_content.setEmail(cursor.getString(1));
            user_content.setPass(cursor.getString(2));
            user_content.setName(cursor.getString(3));
            user_content.setPhone(cursor.getString(4));
            user_content.setBank(cursor.getString(5));
            user_content.setBanknum(cursor.getString(6));
            user_content.setApplystatus(cursor.getString(7));
            user_content.setAcctype(cursor.getString(8));
            list_campaigns.add(user_content);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return list_campaigns;
    }

    public usercontent getUser(String email){
        String[] selectionArgs = {email};
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM "+user.TABLE_NAME+" WHERE email=?",new String[]{email});
        cursor.moveToFirst();
        usercontent user_content = new usercontent();
        user_content.set_id(cursor.getString(0));
        user_content.setEmail(cursor.getString(1));
        user_content.setPass(cursor.getString(2));
        user_content.setName(cursor.getString(3));
        user_content.setPhone(cursor.getString(4));
        user_content.setBank(cursor.getString(5));
        user_content.setBanknum(cursor.getString(6));
        user_content.setApplystatus(cursor.getString(7));
        user_content.setAcctype(cursor.getString(8));
        database.close();
        return user_content;
    }

    public String getNone(){
        return "None";
    }

    public void deleteUser(String email){


        String selection = user.COLUMN_EMAIL + " Like ?";
        String [] selectionArgs = {email};

        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(user.TABLE_NAME, selection, selectionArgs);

        database.close();
    }

    public void update(ContentValues values, String email){

        SQLiteDatabase database = this.getWritableDatabase();
        database.update(user.TABLE_NAME, values, "email=" + email, null);
        database.close();
    }



}

