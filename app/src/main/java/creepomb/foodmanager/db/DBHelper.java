package creepomb.foodmanager.db;

import android.content.Context;
import android.database.sqlite.*;

import creepomb.foodmanager.MainActivity;
import creepomb.foodmanager.util.StorageLocationItem;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FoodManager.db";
    public static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBFoodItemsProcess.CREATE_TABLE);

        db.execSQL(DBStorageLocationItemsProcess.CREATE_TABLE);
        //建立初始化資料
        db.execSQL("INSERT INTO " + DBStorageLocationItemsProcess.TABLE_NAME +
                "(" + DBStorageLocationItemsProcess.KEY_ID + "," + DBStorageLocationItemsProcess.ICONINDEX_COLUMN + "," + DBStorageLocationItemsProcess.NAME_COLUMN + ")" +
                "VALUES (1, 1, '冰箱');");
        db.execSQL("INSERT INTO " + DBStorageLocationItemsProcess.TABLE_NAME +
                "(" + DBStorageLocationItemsProcess.KEY_ID + "," + DBStorageLocationItemsProcess.ICONINDEX_COLUMN + "," + DBStorageLocationItemsProcess.NAME_COLUMN + ")" +
                "VALUES (2, 2, '零食櫃');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBFoodItemsProcess.TABLE_NAME);
        onCreate(db);
    }
}
