package creepomb.foodmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import creepomb.foodmanager.util.Category;
import creepomb.foodmanager.util.FoodItem;

/**
 * Created by vongola12324 on 15/1/31.
 */
public class DBCategoryProcess {
    // 表格名稱
    public static final String TABLE_NAME = "FoodCategory";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    public static final String CATEGORYNAME_COLUMN = "name";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CATEGORYNAME_COLUMN + " TEXT NOT NULL " + ")" ;

    private DBHelper helper;

    public DBCategoryProcess(DBHelper helper) {
        this.helper = helper;
    }

    // 新增參數指定的物件
    public Category insert(Category item) {
        SQLiteDatabase db = helper.getWritableDatabase();

        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(CATEGORYNAME_COLUMN, item.getName());


        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        item.setId(id);
        // 回傳結果
        return item;
    }

    public boolean update(Category item) {
        SQLiteDatabase db = helper.getWritableDatabase();

        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(CATEGORYNAME_COLUMN, item.getName());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 讀取所有記事資料
    public List<Category> getAll() {
        SQLiteDatabase db = helper.getReadableDatabase();

        List<Category> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public Category get(long id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        // 準備回傳結果用的物件
        Category item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    // 把Cursor目前的資料包裝為物件
    public Category getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Category result = new Category(cursor.getString(1));

        result.setId(cursor.getInt(0));

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        SQLiteDatabase db = helper.getReadableDatabase();

        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    public static String getSQL_INSERT(Category item) {
        return "INSERT INTO " + TABLE_NAME +
                "(" + CATEGORYNAME_COLUMN + ")" +
                "VALUES ('" + item.getName() +  "');";
    }

}
