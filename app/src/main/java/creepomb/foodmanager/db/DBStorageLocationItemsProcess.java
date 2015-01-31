package creepomb.foodmanager.db;

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
}
