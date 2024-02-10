import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbController(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1

        // Table name
        private const val TABLE_CPU = "cpu"

        // Column names for CPU table
        private const val CPU_ID = "ID"
        private const val CPU_NAME = "CPU_name"
        private const val CPU_PRICE = "CPU_price"
        private const val CPU_TIER = "Tier"
        private const val CPU_BASE_CLOCK = "Base_clk"
        private const val CPU_MAX_CLOCK = "Max_clk"
        private const val CPU_CORES = "Cores"
        private const val CPU_THREADS = "Threads"

        // SQL statement for creating CPU table
        private const val CREATE_TABLE_CPU = "CREATE TABLE $TABLE_CPU (" +
                "$CPU_ID INTEGER PRIMARY KEY," +
                "$CPU_NAME TEXT," +
                "$CPU_PRICE REAL," +
                "$CPU_TIER INTEGER," +
                "$CPU_BASE_CLOCK REAL," +
                "$CPU_MAX_CLOCK REAL," +
                "$CPU_CORES INTEGER," +
                "$CPU_THREADS INTEGER" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create CPU table
        db.execSQL(CREATE_TABLE_CPU)

        // Insert data into CPU table
        db.execSQL(
            "INSERT INTO $TABLE_CPU (" + CPU_ID + ", " + CPU_NAME + ", "
                    + CPU_PRICE + ", " + CPU_TIER + ", " + CPU_BASE_CLOCK + ", "
                    + CPU_MAX_CLOCK + ", " + CPU_CORES + ", " + CPU_THREADS + ") VALUES " +
                    "(1, 'Intel Core i9-13900K', 55000, 3, 3.00, 5.80, 24, 32), " +
                    "(2, 'Intel Core i9-12900K', 45000, 3, 3.20, 5.20, 16, 24), " +
                    "(3, 'Intel Core i7-13700K', 40000, 2, 2.50, 5.40, 16, 24), " +
                    "(4, 'Intel Core i7-12700K', 35000, 2, 3.60, 5.00, 12, 20), " +
                    "(5, 'Intel Core i5-13600K', 30000, 1, 2.60, 5.10, 14, 20), " +
                    "(6, 'Intel Core i5-12600K', 25000, 1, 3.70, 4.90, 10, 16), " +
                    "(7, 'Intel Core i5-13400', 21000, 1, 2.50, 4.60, 10, 16), " +
                    "(8, 'Intel Core i3-13100', 15000, 0, 3.40, 4.50, 4, 8), " +
                    "(9, 'Intel Core i3-12100', 11000, 0, 3.30, 4.30, 4, 8), " +
                    "(10, 'AMD Ryzen 9 7950X', 52000, 3, 4.50, 5.70, 16, 32), " +
                    "(11, 'AMD Ryzen 9 7900X', 45000, 3, 4.70, 5.60, 12, 24), " +
                    "(12, 'AMD Ryzen 9 5900X', 30000, 2, 3.70, 4.80, 12, 24), " +
                    "(13, 'AMD Ryzen 7 7700X', 35000, 2, 4.50, 5.40, 8, 16), " +
                    "(14, 'AMD Ryzen 7 5700X', 18000, 2, 3.80, 4.80, 8, 16), " +
                    "(15, 'AMD Ryzen 5 7600X', 23000, 1, 3.80, 5.10, 6, 12), " +
                    "(16, 'AMD Ryzen 5 5600X', 16000, 1, 3.70, 4.60, 6, 12), " +
                    "(17, 'AMD Ryzen 5 5500', 10000, 0, 3.60, 4.20, 6, 12), " +
                    "(18, 'AMD Ryzen 5 3600', 8000, 0, 3.60, 4.20, 6, 12) "
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
