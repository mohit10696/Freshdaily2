package com.example.freshdaily;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DbAdapter {

    //define static variable
    public static int dbversion =6;
    public static String dbname = "order";
    public static String dbTable2 = "cart";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context,dbname,null, dbversion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS "+dbTable2+"(_id INTEGER PRIMARY KEY autoincrement,pid integer )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }

    //establsh connection with SQLiteDataBase
    private final Context c;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlDb;

    public DbAdapter(Context context) {
        this.c = context;
    }
    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(c);
        sqlDb = dbHelper.getWritableDatabase();
        return this;
    }

    //insert data
    public void insert(String id) {
            sqlDb.execSQL("INSERT INTO cart(pid) VALUES('" + id + "')");
    }

    public void delete(String id) {
        sqlDb.execSQL("DELETE FROM cart WHERE _id="+id);
    }

    public void deleteAll() {
        sqlDb.execSQL("DELETE FROM cart");
    }

    //fetch data
    public Cursor fetch() {
        String query = "SELECT distinct pid FROM cart" ;
        Cursor row = sqlDb.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }


}