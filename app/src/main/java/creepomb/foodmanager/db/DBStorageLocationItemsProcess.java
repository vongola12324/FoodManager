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
                    NAME_COLUMN + " TEXT NOT NULL, " +
                    ICONINDEX_COLUMN + " INTEGER NOT NULL, " + ")" ;

    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DBStorageLocationItemsProcess(Context context) {
        db = DBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    // 讀取所有記事資料
    public List<StorageLocationItem> getAll() {
        List<StorageLocationItem> result = new ArrayList<>();
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
}
