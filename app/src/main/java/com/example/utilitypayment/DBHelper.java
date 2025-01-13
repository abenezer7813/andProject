package com.example.utilitypayment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import android.database.SQLException;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_NAME = "UTILITY_DB2";
    public static final int VERSION = 2;

    public static final String table_name = "utility_table";
    public static final String id_column = "id";
    public static final String f_name_column = "first_name";
    public static final String l_name_column = "last_name";
    public static final String p_number_column = "phone_number";
    public static  final String password_column = "password";



    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name +
                "(" + id_column + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                f_name_column + " TEXT, " +
                l_name_column + " TEXT, " +
                p_number_column + " INTEGER, " +
                password_column + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);

    }

    public long addData(String f_name, String l_name, int p_number, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(f_name_column, f_name);
        cv.put(l_name_column, l_name);
        cv.put(p_number_column, p_number);
        cv.put(password_column, password);
        long result = db.insert(table_name, null, cv);
        db.close(); // Close the database connection
        return result;
    }


    // **Update Operation**
    public int updateData(int id, String f_name, String l_name, int p_number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(f_name_column, f_name);
        values.put(l_name_column, l_name);
        values.put(p_number_column, p_number);
        values.put(password_column, password_column);

        int rowsAffected = db.update(
                table_name,
                values,
                id_column + "=?", //Where clause filter by id
                new String[]{String.valueOf(id)} //Where clause parameter for where clause
        );
        db.close();
        return rowsAffected;
    }

    public boolean login(String phone_number, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor=  db.rawQuery("SELECT "+password_column+" FROM "+table_name+" WHERE "+p_number_column+"=?",new String[]{phone_number});
      if(cursor.getCount()>0){
        cursor.moveToFirst();
        cursor.getString(0);
        if(cursor.getString(0).equals(password)){
            return true;
        }
        return false;
    }
        return false;
    }
    // **Read (Retrieve) Single Record Operation**
    public MyData getDataById(int id) {
        SQLiteDatabase db = this.getReadableDatabase(); // Get a readable database
        Cursor cursor = db.query(
                table_name,  // table name
                null, // column to return - null will return all
                id_column + "=?", // selection clause - filter by id
                new String[]{String.valueOf(id)}, // selection arguments for where clause
                null, // groupBy
                null, // having
                null // orderBy
        );

        MyData data = null;
        if (cursor != null && cursor.moveToFirst()) {
            data = new MyData(
                    cursor.getInt(cursor.getColumnIndexOrThrow(id_column)),
                    cursor.getString(cursor.getColumnIndexOrThrow(f_name_column)),
                    cursor.getString(cursor.getColumnIndexOrThrow(l_name_column)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(p_number_column)),
                    cursor.getString(cursor.getColumnIndexOrThrow(password_column)));
            cursor.close(); // close the cursor
        }
        db.close();
        return data;
    }
    // **Read (Retrieve) All Records Operation**
    public List<MyData> getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<MyData> dataList = new ArrayList<>();
        Cursor cursor = db.query(
                table_name,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MyData data = new MyData(
                        cursor.getInt(cursor.getColumnIndexOrThrow(id_column)),
                        cursor.getString(cursor.getColumnIndexOrThrow(f_name_column)),
                        cursor.getString(cursor.getColumnIndexOrThrow(l_name_column)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(p_number_column)),
                        cursor.getString(cursor.getColumnIndexOrThrow(password_column))


                );
                dataList.add(data);
            } while(cursor.moveToNext());

            cursor.close(); // Close cursor
        }
        db.close(); // Close db connection
        return dataList;

    }
    //// **Delete Operation**
public int deleteData(int id) {
    SQLiteDatabase db = this.getWritableDatabase();
    int rowsAffected = db.delete(
            table_name,
            id_column + "=?", //Where clause filter by id
            new String[]{String.valueOf(id)}//Where clause parameters for where clause
    );
    db.close();
    return rowsAffected;
}

    // Simple class to represent the data (to make accessing data easier)
    public static class MyData {
        int id;
        public String f_name;
        public String l_name;
        public int p_number;
        public String password;



        public MyData(int id, String f_name, String l_name, int p_number,String password){
            this.id = id;
            this.f_name = f_name;
            this.l_name = l_name;
            this.p_number = p_number;
            this.password = password;
        }
    }

    }












// ------- CRUD Operations --------










//
//// **Delete All Data (optional)**
//public int deleteAllData(){
//    SQLiteDatabase db = this.getWritableDatabase();
//    int rowsAffected = db.delete(TABLE_NAME, null, null);
//    db.close();
//    return rowsAffected;
//}
//
//
//
//
//}


