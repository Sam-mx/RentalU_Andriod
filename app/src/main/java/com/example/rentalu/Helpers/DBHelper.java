package com.example.rentalu.Helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {

    private static String DBName = "RentalUDB.db";
    //Table Names
    private static String USER_TABLE = "user_table";
    private static String Prop_Table = "property_table";
    private static String PROPERTY_TABLE = "property_table";
    //USER_TABLE_FIELD
    private static String USER_ID = "user_id";
    private static String EMAIL = "email";
    private static String NAME = "name";
    private static String PASSWORD = "password";
    private static String PH_NO = "phone_no";

    //property table data
    private static String Ref_List_Num = "ref_list_num";
    private static String Prop_Type = "prop_type";
    private static String Bedroom = "bedroom";
    private static String Date_Time = "date_time";
    private static String Rent_Price = "rent_price";
    private static String Furniture = "furniture_status";
    private static String Remark = "remark";
    private static String Reporter_Name = "reporter_name";
    private static String Image = "image";
    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_table_create="CREATE TABLE " + USER_TABLE + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EMAIL + " TEXT," + NAME + " TEXT," + PASSWORD + " TEXT," +
                PH_NO + " TEXT" + ")";


        //creating property table
        String property_table_create = "CREATE TABLE " + Prop_Table + "(" +
                Ref_List_Num + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Prop_Type + " TEXT," + Bedroom + " TEXT," +
                Date_Time + " TEXT," + Rent_Price + " FLOAT," +
                Furniture + " TEXT," + Remark + " TEXT," +
                Reporter_Name + " TEXT," + Image + " BLOB)";

        db.execSQL(user_table_create);
        db.execSQL(property_table_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        db.execSQL("drop table if exists " + Prop_Table);
    }

    public void addUser(String email, String name, String password, String ph_no)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put(EMAIL,email);
        cv.put(NAME,name);
        cv.put(PASSWORD,password);
        cv.put(PH_NO,ph_no);

        db.insert(USER_TABLE,null,cv);
        db.close();
    }

    public ArrayList<UserModel> readUser(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor user_cursor = db.rawQuery("SELECT * FROM " +USER_TABLE,null);
        ArrayList<UserModel> userModelArrayList= new ArrayList<>();

        if(user_cursor.moveToFirst())
        {
            do {
                userModelArrayList.add(new UserModel(
                        user_cursor.getInt(0),
                        user_cursor.getString(1),
                        user_cursor.getString(2),
                        user_cursor.getString(3),
                        user_cursor.getString(4)));
            }while (user_cursor.moveToNext());
        }
        return userModelArrayList;
    }

    public void deleteUser(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(USER_TABLE,"user_id=?",new String[]{id});
        db.close();
    }

    public void updateUser(String user_id,String email,String name,String password,String phno)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(USER_ID,user_id);
        cv.put(EMAIL,email);
        cv.put(NAME,name);
        cv.put(PASSWORD,password);
        cv.put(PH_NO,phno);

        db.update(USER_TABLE,cv,"user_id=?",new String[]{user_id});
        db.close();
    }

    //For Property_Table

    public void addProperty(String propType, String bedroom,  Float rentprice, String furniture, String remark, String reporter_name, byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Prop_Type, propType);
        cv.put(Bedroom, bedroom);
        SimpleDateFormat date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        cv.put(Date_Time,String.valueOf(date));
        cv.put(Rent_Price, rentprice);
        cv.put(Furniture, furniture);
        cv.put(Remark, remark);
        cv.put(Reporter_Name, reporter_name);
        cv.put(Image, image);
        db.insert(Prop_Table, null, cv);
        db.close();
    }

    public ArrayList<PropertyModel> readProperty(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor prop_cursor = db.rawQuery("select * from " + Prop_Table, null);
        ArrayList<PropertyModel> propModelArrayList = new ArrayList<>();

        if(prop_cursor.moveToFirst()){
            do {
                // Get the image as a byte array
                byte[] imageByteArray = prop_cursor.getBlob(8);

                // Convert byte array to Bitmap
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

                propModelArrayList.add(new PropertyModel(
                        prop_cursor.getInt(0),
                        prop_cursor.getString(1),
                        prop_cursor.getString(2),
                        prop_cursor.getString(3),
                        prop_cursor.getFloat(4),
                        prop_cursor.getString(5),
                        prop_cursor.getString(6),
                        prop_cursor.getString(7),
                        imageBitmap
                ));
            }while(prop_cursor.moveToNext());
        }
        return propModelArrayList;
    }

    public ArrayList<PropertyModel> searchproperty(String id, String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor prop_cursor = db.rawQuery("SELECT * FROM " + Prop_Table + " WHERE " + Ref_List_Num + " = ? AND " + Reporter_Name + " = ?", new String[]{id, name});
        ArrayList<PropertyModel> propModelArrayList = new ArrayList<>();

        if(prop_cursor.moveToFirst()){
            do {
                // Get the image as a byte array
                byte[] imageByteArray = prop_cursor.getBlob(8);

                // Convert byte array to Bitmap
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

                propModelArrayList.add(new PropertyModel(
                        prop_cursor.getInt(0),
                        prop_cursor.getString(1),
                        prop_cursor.getString(2),
                        prop_cursor.getString(3),
                        prop_cursor.getFloat(4),
                        prop_cursor.getString(5),
                        prop_cursor.getString(6),
                        prop_cursor.getString(7),
                        imageBitmap
                ));
            }while(prop_cursor.moveToNext());
        }
        return propModelArrayList;
    }

    public void deleteProperty(String id,String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Prop_Table,"ref_list_num=? AND reporter_name=?",new String[]{id, name});
        db.close();
    }

    public void UpdateProperty(String ref_no,String propType, String bedroom,  Float rentprice, String furniture, String remark, String reporter_name, byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Prop_Type, propType);
        cv.put(Bedroom, bedroom);
        cv.put(Rent_Price, rentprice);
        cv.put(Furniture, furniture);
        cv.put(Remark, remark);
        cv.put(Reporter_Name, reporter_name);
        cv.put(Image, image);

        db.update(Prop_Table,cv,"ref_list_num=?",new String[]{ref_no});
        db.close();
    }

    //for authentication of user
    public class User {
        private int userId;
        private String name;

        public User(int userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        public int getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }
    }

    @SuppressLint("Range")
    public User getUser(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        User user = null;
        cursor = db.rawQuery("SELECT " + USER_ID + ", " + NAME + " FROM " + USER_TABLE + " WHERE " + EMAIL + "=? AND " + PASSWORD + "=?", new String[]{email, password});

        if (cursor != null && cursor.moveToFirst()) {
            int userId = cursor.getInt(cursor.getColumnIndex(USER_ID));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            user = new User(userId, name);
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return user;
    }

}
