package vivek.felight.com.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;

import java.sql.SQLData;

/**
 * Created by Vivek on 11/18/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Contacts.db";
    public static final String TABLE_NAME = "Contacts_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "EMAIL";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,email);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }


    public boolean updateData(String id, String name, String email){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,email);
        db.update(TABLE_NAME,contentValues,"ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id ){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(TABLE_NAME," ID = ?", new String[]{id});

    }

}