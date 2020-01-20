package com.example.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.sql.SQLException;

final class menuDB {
    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "메뉴이름";
        public static final String CUP = "컵종류";
        public static final String INGREDIENT1 = "재료1";
        public static final String INGREDIENT2 = "재료2";
        public static final String INGREDIENT3 = "재료3";
        public static final String INGREDIENT4 = "재료4";
        public static final String INGREDIENT5 = "재료5";
        public static final String _TABLENAME = "카페메뉴";

        public static final String _CREATE =

                "create table " + _TABLENAME + "("

                        + _ID + " text not null, "

                        + NAME + " text not null , "

                        + CUP + " text not null , "

                        + INGREDIENT1 + " text not null ,"

                        + INGREDIENT2 + " text not null ,"

                        + INGREDIENT3 + " text not null ,"

                        + INGREDIENT4 + " text not null ,"

                        + INGREDIENT5 + " text not null );";
    }
    /*
        public class DbOpenHelper {

            private static final String DATABASE_NAME = "메뉴이름";

            private static final int DATABASE_VERSION = 1;

            public SQLiteDatabase mDB;

            private DatabaseHelper mDBHelper;

            private Context mCtx;

            private class DatabaseHelper extends SQLiteOpenHelper {
                // 생성자
                public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

                    super(context, name, factory, version);

                }


                // 최초 DB를 만들때 한번만 호출된다.
                @Override
                public void onCreate(SQLiteDatabase db) {
                    db.execSQL(menuDB.CreateDB._CREATE);
                }

                // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                    db.execSQL("DROP TABLE IF EXISTS " + menuDB.CreateDB._TABLENAME);

                    onCreate(db);

                }

            }


            public DbOpenHelper(Context context) {
                this.mCtx = context;
            }


            public DbOpenHelper open() throws SQLException {

                mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);

                mDB = mDBHelper.getWritableDatabase();

                return this;

            }


            public void close() {

                mDB.close();

            }


        }
    */
    public static class DbOpenHelper {

        private static final String DATABASE_NAME = "카페메뉴.db";
        private static final int DATABASE_VERSION = 1;
        public static SQLiteDatabase mDB;
        private DatabaseHelper mDBHelper;
        private Context mCtx;

        private class DatabaseHelper extends SQLiteOpenHelper{

            // 생성자
            public DatabaseHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }

            // 최초 DB를 만들때 한번만 호출된다.
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(CreateDB._CREATE);

            }

            // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS "+ CreateDB._TABLENAME);
                onCreate(db);
            }
        }

        public DbOpenHelper(Context context){
            this.mCtx = context;
        }

        public DbOpenHelper open() throws android.database.SQLException {
            mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
            mDB = mDBHelper.getWritableDatabase();
            return this;
        }

        public void close(){
            mDB.close();
        }

        // Insert DB
        public long insertColumn(String name, String cup, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5){
            ContentValues values = new ContentValues();
            values.put(CreateDB.NAME, name);
            values.put(CreateDB.CUP, cup);
            values.put(CreateDB.INGREDIENT1, ingredient1);
            values.put(CreateDB.INGREDIENT2, ingredient2);
            values.put(CreateDB.INGREDIENT3, ingredient3);
            values.put(CreateDB.INGREDIENT4, ingredient4);
            values.put(CreateDB.INGREDIENT5, ingredient5);
            return mDB.insert(CreateDB._TABLENAME, null, values);
        }

        // Update DB
        public boolean updateColumn(long id , String name, String cup, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5){
            ContentValues values = new ContentValues();
            values.put(CreateDB.NAME, name);
            values.put(CreateDB.CUP, cup);
            values.put(CreateDB.INGREDIENT1, ingredient1);
            values.put(CreateDB.INGREDIENT2, ingredient2);
            values.put(CreateDB.INGREDIENT3, ingredient3);
            values.put(CreateDB.INGREDIENT4, ingredient4);
            values.put(CreateDB.INGREDIENT5, ingredient5);
            return mDB.update(CreateDB._TABLENAME, values, "_id="+id, null) > 0;
        }

        // Delete ID
        public boolean deleteColumn(long id){
            return mDB.delete(CreateDB._TABLENAME, "_id="+id, null) > 0;
        }

        // Delete Contact
        public boolean deleteColumn(String number){
            return mDB.delete(CreateDB._TABLENAME, "contact="+number, null) > 0;
        }

        // Select All
        public Cursor getAllColumns(){
            return mDB.query(CreateDB._TABLENAME, null, null, null, null, null, null);
        }

        // ID 컬럼 얻어 오기
        public Cursor getColumn(long id){
            Cursor c = mDB.query(CreateDB._TABLENAME, null,
                    "_id="+id, null, null, null, null);
            if(c != null && c.getCount() != 0)
                c.moveToFirst();
            return c;
        }

        // 이름 검색 하기 (rawQuery)
        public Cursor getMatchName(String name){
            Cursor c = mDB.rawQuery( "select * from 카페메뉴 where name=" + "'" + name + "'" , null);
            return c;
        }


    }
}

