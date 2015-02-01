package creepomb.foodmanager.db;

import android.content.Context;
import android.database.sqlite.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import creepomb.foodmanager.MainActivity;
import creepomb.foodmanager.util.Category;
import creepomb.foodmanager.util.FoodItem;
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

        db.execSQL(DBCategoryProcess.CREATE_TABLE);

        //建立初始化資料
        db.execSQL("INSERT INTO " + DBStorageLocationItemsProcess.TABLE_NAME +
                "(" + DBStorageLocationItemsProcess.KEY_ID + "," + DBStorageLocationItemsProcess.ICONINDEX_COLUMN + "," + DBStorageLocationItemsProcess.NAME_COLUMN + ")" +
                "VALUES (1, 1, '冰箱');");
        db.execSQL("INSERT INTO " + DBStorageLocationItemsProcess.TABLE_NAME +
                "(" + DBStorageLocationItemsProcess.KEY_ID + "," + DBStorageLocationItemsProcess.ICONINDEX_COLUMN + "," + DBStorageLocationItemsProcess.NAME_COLUMN + ")" +
                "VALUES (2, 2, '零食櫃');");
        List<String> CategoryList = new ArrayList<String>();
        CategoryList.add("蔬菜");
        CategoryList.add("水果");
        CategoryList.add("乾糧");
        CategoryList.add("肉品");
        CategoryList.add("海鮮");
        CategoryList.add("熟食");
        CategoryList.add("其他");
        for(String str:CategoryList) {
            db.execSQL(DBCategoryProcess.getSQL_INSERT(new Category(str)));
        }

        db.execSQL(DBFoodItemsProcess.getSQL_INSERT(new FoodItem("香蕉", 5, "串", 0, FoodItem.getDateInMilli(2015, 2, 7), 1)));
        db.execSQL(DBFoodItemsProcess.getSQL_INSERT(new FoodItem("蘋果", 1, "顆", 0, FoodItem.getDateInMilli(2015, 2, 5), 0)));
        db.execSQL(DBFoodItemsProcess.getSQL_INSERT(new FoodItem("泡麵", 10, "箱", 1, FoodItem.getDateInMilli(2016, 2, 1), 0)));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBFoodItemsProcess.TABLE_NAME);
        onCreate(db);
    }
}
