package com.example.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.app.models.CartModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final static String DBName="mydatabase.db";
    final static int DBVersion=1;

    public DBHelper(@Nullable Context context) {
        super(context,DBName,null,DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase DB1) {
        DB1.execSQL("create table UserTable(id integer primary key,Name Text,Email Text,Password Text)");
        DB1.execSQL("create table orders" +
                "(OrderId integer primary key autoincrement," +
                "Image int," +
                "Quantity int," +
                "FoodName text," +
                "Description text," +
                "Name text," +
                "Phone text," +
                "Price int)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB2, int i, int i1) {
        DB2.execSQL("drop table if exists UserTable");
        DB2.execSQL("drop table if exists orders");
        onCreate(DB2);
    }

    public boolean insertUserData(String name,String email,String password){
        SQLiteDatabase DB3=getWritableDatabase();

        ContentValues values1=new ContentValues();
        values1.put("Name",name);
        values1.put("Email",email);
        values1.put("Password",password);
        Long query=DB3.insert("UserTable",null,values1);
        if (query==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkEmail(String email){
        SQLiteDatabase DB3=getWritableDatabase();
        Cursor cursor=DB3.rawQuery("select * from UserTable where Email=?",new String[]{email});
        if (cursor.getCount()>1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkEmailAndPassword(String email,String password){
        SQLiteDatabase DB3=getWritableDatabase();
        Cursor cursor=DB3.rawQuery("select * from UserTable where Email=? and Password=?",new String[]{email,password});
        if (cursor.getCount()>1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean insertOrder(int image,int quantity,String foodName,String description,String name,String phone,int price){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values2=new ContentValues();
        values2.put("Image",image);
        values2.put("FoodName",foodName);
        values2.put("Quantity",quantity);
        values2.put("Description",description);
        values2.put("Name",name);
        values2.put("Phone",phone);
        values2.put("Price",price);
        Long id=database.insert("orders",null,values2);
        if (id<=0){
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<CartModel> getOrders(){
        ArrayList<CartModel> orders=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select Image,FoodName,OrderId,Price from orders",null);
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                CartModel cartModel=new CartModel();
                cartModel.setImage(cursor.getInt(0));
                cartModel.setName(cursor.getString(1));
                cartModel.setOrderNumber(cursor.getInt(2)+"");
                //cartModel.setPrice(cursor.getInt(3)+String.format("%d"));
                cartModel.setPrice(cursor.getInt(3)+"");
                orders.add(cartModel);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){
        SQLiteDatabase database=getWritableDatabase();
        Cursor cursor=database.rawQuery("select * from orders where OrderId="+id,null);
        if (cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }

    public boolean updateOrder(int image,int quantity,String foodName,String description,String name,String phone,int price,int id){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values2=new ContentValues();
        values2.put("Image",image);
        values2.put("FoodName",foodName);
        values2.put("Quantity",quantity);
        values2.put("Description",description);
        values2.put("Name",name);
        values2.put("Phone",phone);
        values2.put("Price",price);
        int row=database.update("orders",values2,"OrderId="+id,null);
        if (row<=0){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteOrder(int id){
        SQLiteDatabase database=getWritableDatabase();
        int del=database.delete("orders","OrderId="+id,null);
        if (del<=0){
            return false;
        }
        else {
            return true;
        }
    }
}