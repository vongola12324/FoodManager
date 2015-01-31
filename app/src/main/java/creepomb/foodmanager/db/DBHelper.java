package creepomb.foodmanager.db;

import android.content.Context;
import android.database.sqlite.*;


class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FoodManager.db";
    public static final int VERSION = 1;
    private static SQLiteDatabase database;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DBHelper(context, DATABASE_NAME, null, VERSION).getWritableDatabase();
        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBFoodItemsProcess.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBFoodItemsProcess.TABLE_NAME);
        onCreate(db);
    }
}
