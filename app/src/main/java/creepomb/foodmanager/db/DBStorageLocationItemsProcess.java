package creepomb.foodmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import creepomb.foodmanager.util.FoodItem;
import creepomb.foodmanager.util.StorageLocationItem;

public class DBStorageLocationItemsProcess {
    // 表格名稱
    public static final String TABLE_NAME = "StorageLocation";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    public static final String NAME_COLUMN = "name";
    public static final String ICONINDEX_COLUMN = "iconIndex";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ICONINDEX_COLUMN + " INTEGER NOT NULL, " +
                    NAME_COLUMN + " TEXT NOT NULL " + ")" ;

    private DBHelper helper;

    public DBStorageLocationItemsProcess(DBHelper helper) {
        this.helper = helper;
    }

    public boolean delete(long id){
        SQLiteDatabase db = helper.getWritableDatabase();

        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    // 讀取所有記事資料
    public List<StorageLocationItem> getAll() {
        List<StorageLocationItem> result = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 把Cursor目前的資料包裝為物件
    public StorageLocationItem getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        StorageLocationItem result = new StorageLocationItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2));

        // 回傳結果
        return result;
    }

    public StorageLocationItem insert(StorageLocationItem item) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(NAME_COLUMN, item.name);
        cv.put(ICONINDEX_COLUMN, item.iconIndex);

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        item.id = id;
        // 回傳結果
        return item;

    }
}
