package com.example.lyonhw3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_EMAIL = "email";
    static final String KEY_PHONE = "phone";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "HW9";
    static final String DATABASE_TABLE = "contacts";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE=
            "create table contacts(_id integer primary key autoincrement, "
                    + "name text not null, email text not null, phone text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    public DBAdapter(Context context){
        this.context=context;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(DATABASE_CREATE);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading database from " + oldVersion + " to " + newVersion
                    + ", which will destroy all old data!");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        DBHelper.close();
    }
    public long insertContact(String name, String email, String phone){
        ContentValues initial = new ContentValues();
        initial.put(KEY_NAME, name);
        initial.put(KEY_EMAIL, email);
        initial.put(KEY_PHONE, phone);
        return db.insert(DATABASE_TABLE, null, initial);
    }
    public boolean deleteContact(long rowID){
        return db.delete(DATABASE_TABLE, KEY_ROWID +"=" + rowID, null)>0;
    }
    public Cursor getAll(){
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL, KEY_PHONE},
                null, null, null, null, null);
    }
    public Cursor getContact(long rowID) throws SQLException{
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
                        KEY_EMAIL, KEY_PHONE}, KEY_ROWID + "=" + rowID, null, null,
                null, null, null);
        if(mCursor != null){mCursor.moveToFirst();}
        return mCursor;
    }
    public boolean updateContact(long rowId, String name, String email, String phone){
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_EMAIL, email);
        args.put(KEY_PHONE, phone);
        return db.update(DATABASE_TABLE, args, KEY_ROWID +"="+rowId, null)>0;
    }

}
