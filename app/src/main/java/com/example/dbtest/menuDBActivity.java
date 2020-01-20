package com.example.dbtest;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
/*
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;
*/
import java.util.ArrayList;

/*
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.cafesimulatorgame.R;
import com.example.cafesimulatorgame.CustomAdapter;
import com.example.cafesimulatorgame.InfoClass;
import com.example.cafesimulatorgame.DbOpenHelper;
import com.example.cafesimulatorgame.DLog;
*/


public class menuDBActivity extends Activity {


    private static final String TAG = "menuDBActivity";
    private menuDB.DbOpenHelper mDbOpenHelper;

    private Cursor mCursor;

    /*
    private Cursor mCursor;
    private InfoClass mInfoClass;
*/
    private ArrayList<InfoClass> mInfoArray;

    // public CustomAdapter mAdapter;



    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        //setLayout();



        // DB Create and Open

        mDbOpenHelper = new menuDB.DbOpenHelper(this);

        mDbOpenHelper.open();



        mDbOpenHelper.insertColumn("핫아메리카노","종이컵" , "뜨거운물" , "커피" , null , null , null);

        mDbOpenHelper.insertColumn("아이스아메리카노","플라스틱컵" , "찬물" , "커피" , "얼음" , null , null);

        mDbOpenHelper.insertColumn("핫카페라떼","종이컵" , "뜨거운우유" , "커피" , null , null , null);

        mDbOpenHelper.insertColumn("아이스카페라떼","플라스틱컵" , "찬우유" , "커피" , "얼음" , null , null);

        mDbOpenHelper.insertColumn("핫연유라떼","종이컵" , "뜨거운우유" , "커피" , "연유" , null , null);

        mDbOpenHelper.insertColumn("아이스연유라떼","플라스틱컵" , "찬우유" , "커피" , "얼음" , "연유" , null);

        mDbOpenHelper.insertColumn("핫토피넛라떼","종이컵" , "뜨거운우유" , "커피" , "토피넛" , "클스데코" , null);

        mDbOpenHelper.insertColumn("아이스토피넛라떼","플라스틱컵" , "찬우유" , "커피" , "얼음" , "토피넛" , "클스데코");

        mDbOpenHelper.insertColumn("초코스무디","플라스틱컵" , "찬우유" , "초코" , "얼음" , "블렌더" , null);

        mDbOpenHelper.insertColumn("딸기스무디","플라스틱컵" , "찬우유" , "딸기" , "얼음" , "블렌더" , null);

        mDbOpenHelper.insertColumn("초코바나나주스","플라스틱컵" , "찬우유" , "초코" , "얼음" , "블렌더" , "바나나");

        mDbOpenHelper.insertColumn("딸기바나나주스","플라스틱컵" , "찬우유" , "딸기" , "얼음" , "블렌더" , "바나나");

        mDbOpenHelper.insertColumn("핫초코라떼","종이컵" , "뜨거운우유" , "초코" , null , null , null);

        mDbOpenHelper.insertColumn("아이스초코라떼","플라스틱컵" , "찬우유" , "초코" , "얼음" , null , null);

        mDbOpenHelper.insertColumn("클스핫초코라떼","종이컵" , "뜨거운우유" , "초코" , "마시멜로우" , null , null);

        mDbOpenHelper.insertColumn("클스아이스초코라떼","플라스틱컵" , "찬우유" , "초코" , "얼음" , "마시멜로우" , null);


//        startManagingCursor(mCursor);
        mInfoArray = new ArrayList<>();

        doWhileCursorToArray();

        for(InfoClass i : mInfoArray){

            DLog.d(TAG, "ID = " + i._id);

            DLog.d(TAG, "name = " + i.NAME);

            DLog.d(TAG, "cup = " + i.CUP);

            DLog.d(TAG, "ingredient1 = " + i.INGREDIENT1);

            DLog.d(TAG, "ingredient2 = " + i.INGREDIENT2);

            DLog.d(TAG, "ingredient3 = " + i.INGREDIENT3);

            DLog.d(TAG, "ingredient4 = " + i.INGREDIENT4);

            DLog.d(TAG, "ingredient5 = " + i.INGREDIENT5);

        }



        //mAdapter = new CustomAdapter(this, mInfoArray);

        //mListView.setAdapter(mAdapter);

        // mListView.setOnItemLongClickListener(longClickListener);



    }



    @Override

    protected void onDestroy() {

        mDbOpenHelper.close();

        super.onDestroy();

    }



    /*
     * ListView의 Item을 롱클릭 할때 호출 ( 선택한 아이템의 DB 컬럼과 Data를 삭제 한다. )

    private OnItemLongClickListener longClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<!--?--> arg0, View arg1, int position, long arg3) {

            DLog.e(TAG, "position = " + position);
            boolean result = mDbOpenHelper.deleteColumn(position + 1);
            DLog.e(TAG, "result = " + result);

            if(result){
                mInfoArray.remove(position);
                mAdapter.setArrayList(mInfoArray);
                mAdapter.notifyDataSetChanged();
            }else {
                Toast.makeText(getApplicationContext(), "INDEX를 확인해 주세요.",
                        Toast.LENGTH_LONG).show();
            }
            return false;
        }
    };
*/

    /*
     * DB에서 받아온 값을 ArrayList에 Add
     */

    private void doWhileCursorToArray(){

        mCursor = null;

        mCursor = mDbOpenHelper.getAllColumns();

        DLog.e(TAG, "COUNT = " + mCursor.getCount());

        while (mCursor.moveToNext()) {

            InfoClass mInfoClass = new InfoClass(

                    mCursor.getString(mCursor.getColumnIndex("_id")),

                    mCursor.getString(mCursor.getColumnIndex("NAME")),

                    mCursor.getString(mCursor.getColumnIndex("CUP")),

                    mCursor.getString(mCursor.getColumnIndex("INGREDIENT1")),

                    mCursor.getString(mCursor.getColumnIndex("INGREDIENT2")),

                    mCursor.getString(mCursor.getColumnIndex("INGREDIENT3")),

                    mCursor.getString(mCursor.getColumnIndex("INGREDIENT4")),

                    mCursor.getString(mCursor.getColumnIndex("INGREDIENT5"))

            );
            mInfoArray.add(mInfoClass);
        }
        mCursor.close();
    }

    /*

     * OnClick Button
     * @param v

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_add:
                mDbOpenHelper.insertColumn
                        (
                                mEditTexts[Constants.NAME].getText().toString().trim(),
                                mEditTexts[Constants.CONTACT].getText().toString().trim(),
                                mEditTexts[Constants.EMAIL].getText().toString().trim()
                        );
                mInfoArray.clear();
                doWhileCursorToArray();
                mAdapter.setArrayList(mInfoArray);
                mAdapter.notifyDataSetChanged();
                mCursor.close();

                break;

            default:
                break;
        }
    }
     */


    /*
     // Layout

    private EditText[] mEditTexts;

    private ListView mListView;

    private void setLayout(){

        mEditTexts = new EditText[]{

                (EditText)findViewById(R.id.et_name),

                (EditText)findViewById(R.id.et_contact),

                (EditText)findViewById(R.id.et_email)
        };

        mListView = (ListView) findViewById(R.id.lv_list);
    }
     */


}



