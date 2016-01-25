package com.product.kyle.testforgradle.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by kyle on 8/20/15.
 */

public class DBHelper  extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "diaryOpenHelper.db";
    public final static int DATABASE_VERSION = 1;

    //创建数据库
    public DBHelper(Context context,String name,CursorFactory factory,int version)
    {
        super(context,name, factory, version);
    }
    //创建表等机构性文件
    public void onCreate(SQLiteDatabase db)
    {
        String sql ="create table diary"+
                "("+
                "_id integer primary key autoincrement,"+
                "topic varchar(100),"+
                "content varchar(1000)"+
                ")";
        db.execSQL(sql);
    }
    //若数据库版本有更新，则调用此方法
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {

        String sql = "drop table if exists diary";
        db.execSQL(sql);
        this.onCreate(db);
    }
}