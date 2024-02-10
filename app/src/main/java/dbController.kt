import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbController(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1

        // Table name
        private const val TABLE_CPU = "cpu"

        // Column names for CPU table
        private const val CPU_COLUMN_ID = "ID"
        private const val CPU_COLUMN_NAME = "CPU_name"
        private const val CPU_COLUMN_PRICE = "CPU_price"
        private const val CPU_COLUMN_TIER = "Tier"
        private const val CPU_COLUMN_BASE_CLOCK = "Base_clk"
        private const val CPU_COLUMN_MAX_CLOCK = "Max_clk"
        private const val CPU_COLUMN_CORES = "Cores"
        private const val CPU_COLUMN_THREADS = "Threads"

        // SQL statement for creating CPU table
        private const val CREATE_TABLE_CPU = "CREATE TABLE $TABLE_CPU (" +
                "$CPU_COLUMN_ID INTEGER PRIMARY KEY," +
                "$CPU_COLUMN_NAME TEXT," +
                "$CPU_COLUMN_PRICE REAL," +
                "$CPU_COLUMN_TIER INTEGER," +
                "$CPU_COLUMN_BASE_CLOCK REAL," +
                "$CPU_COLUMN_MAX_CLOCK REAL," +
                "$CPU_COLUMN_CORES INTEGER," +
                "$CPU_COLUMN_THREADS INTEGER" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create CPU table
        db.execSQL(CREATE_TABLE_CPU)

        // Insert data into CPU table
        db.execSQL(
            "INSERT INTO $TABLE_CPU (" + CPU_COLUMN_ID + ", " + CPU_COLUMN_NAME + ", "
                    + CPU_COLUMN_PRICE + ", " + CPU_COLUMN_TIER + ", " + CPU_COLUMN_BASE_CLOCK + ", "
                    + CPU_COLUMN_MAX_CLOCK + ", " + CPU_COLUMN_CORES + ", " + CPU_COLUMN_THREADS + ") VALUES " +
                    "(1, 'Intel Core i9-13900K', 55000, 3, 3.00, 5.80, 24, 32), " +
                    "(2, 'Intel Core i9-12900K', 45000, 3, 3.20, 5.20, 16, 24), " +
                    "(3, 'Intel Core i7-12700K', 35000, 2, 3.60, 5.00, 12, 20), " +
                    "(4, 'Intel Core i7-11700K', 30000, 2, 3.60, 4.90, 8, 16), " +
                    "(5, 'Intel Core i5-12600K', 25000, 1, 3.70, 4.90, 10, 16), " +
                    "(6, 'Intel Core i3-10100', 12000, 0, 3.60, 4.30, 4, 8), " +
                    "(7, 'AMD Ryzen 9 5900X', 35000, 4, 3.70, 4.80, 12, 24), " +
                    "(8, 'AMD Ryzen 7 5800X', 25000, 3, 3.80, 4.70, 8, 16), " +
                    "(9, 'AMD Ryzen 7 5700X', 20000, 2, 3.40, 4.20, 8, 16), " +
                    "(10, 'AMD Ryzen 5 5600X', 18000, 1, 3.70, 4.60, 6, 12), " +
                    "(11, 'AMD Ryzen 3 3100', 8000, 0, 3.60, 3.90, 4, 8)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS cpu")
        db.execSQL("DROP TABLE IF EXISTS gpu")
        db.execSQL("DROP TABLE IF EXISTS ssd")
        db.execSQL("DROP TABLE IF EXISTS ram")
        onCreate(db)
    }
}
