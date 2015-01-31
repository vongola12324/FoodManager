package creepomb.foodmanager;

import android.content.*;
import android.database.sqlite.*;
import java.util.*;
import android.database.Cursor;

/**
 * Created by vongola12324 on 15/1/31.
 */
public class DBProcess {
    // 表格名稱
    public static final String TABLE_NAME = "item";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    public static final String FOODNAME_COLUMN = "name";
    public static final String AMOUNT_COLUMN = "amount";
    public static final String UNIT_COLUMN = "unit";
    public static final String CATEGORY_COLUMN = "category";
    public static final String OUTDATE_COLUMN = "outDated";
    public static final String STOREDLOC_COLUMN = "storedLoc";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FOODNAME_COLUMN + " TEXT NOT NULL, " +
                    AMOUNT_COLUMN + " INTEGER NOT NULL, " +
                    UNIT_COLUMN + " TEXT NOT NULL, " +
                    CATEGORY_COLUMN + " INTEGER NOT NULL, " +
                    OUTDATE_COLUMN + " DATE NOT NULL, " +
                    STOREDLOC_COLUMN + " INTEGER NOT NULL," + ")" ;
    /*
    private int id = 0;
    private String name = "";
    private int amount = 0;
    private String unit = "";
    private int category = 0;
    private Date outDated = new Date();
    private int storedLoc = 0;
    */


    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public DBProcess(Context context) {
        db = DBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public FoodItem insert(FoodItem item) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(FOODNAME_COLUMN, item.getName());
        cv.put(AMOUNT_COLUMN, item.getAmount());
        cv.put(UNIT_COLUMN, item.getUnit());
        cv.put(CATEGORY_COLUMN, item.getCategory());
        cv.put(OUTDATE_COLUMN, item.getOutDated().toString());
        cv.put(STOREDLOC_COLUMN, item.getStoredLoc());


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

    // 修改參數指定的物件
    public boolean update(FoodItem item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(FOODNAME_COLUMN, item.getName());
        cv.put(AMOUNT_COLUMN, item.getAmount());
        cv.put(UNIT_COLUMN, item.getUnit());
        cv.put(CATEGORY_COLUMN, item.getCategory());
        cv.put(OUTDATE_COLUMN, item.getOutDated().toString());
        cv.put(STOREDLOC_COLUMN, item.getStoredLoc());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    // 讀取所有記事資料
    public List<FoodItem> getAll() {
        List<FoodItem> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public FoodItem get(long id) {
        // 準備回傳結果用的物件
        FoodItem item = null;
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
    public FoodItem getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        FoodItem result = new FoodItem(cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), FoodItem.packDate(cursor.getLong(5)), cursor.getInt(6));

        result.setId(cursor.getInt(0));

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    // 建立範例資料
    public void sample() {
        FoodItem item = new FoodItem("伊賀", 1, "隻", 1, new GregorianCalendar(2075, 7, 17), 0);

        insert(item);
    }

}