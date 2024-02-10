import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbController(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1

        // CPU table
        private const val TABLE_CPU = "cpu"
        private const val CPU_ID = "ID"
        private const val CPU_NAME = "CPU_name"
        private const val CPU_PRICE = "CPU_price"
        private const val CPU_TIER = "Tier"
        private const val CPU_BASE_CLOCK = "Base_clk"
        private const val CPU_MAX_CLOCK = "Max_clk"
        private const val CPU_CORES = "Cores"
        private const val CPU_THREADS = "Threads"

        // GPU table
        private const val TABLE_GPU = "gpu"
        private const val GPU_ID = "ID"
        private const val GPU_NAME = "GPU_name"
        private const val GPU_PRICE = "GPU_price"
        private const val GPU_VRAM = "VRAM"
        private const val GPU_CLOCK_SPEED = "Clock_speed"
        private const val GPU_TIER = "Tier"

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

        // SQL statement for creating CPU table
        private const val CREATE_TABLE_GPU = "CREATE TABLE $TABLE_GPU (" +
                "$GPU_ID INTEGER PRIMARY KEY," +
                "$GPU_NAME TEXT," +
                "$GPU_PRICE INTEGER," +
                "$GPU_VRAM INTEGER," +
                "$GPU_CLOCK_SPEED INTEGER," +
                "$GPU_TIER INTEGER" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create CPU table
        db.execSQL(CREATE_TABLE_CPU)
        db.execSQL(CREATE_TABLE_GPU)


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
        // Insert data into GPU table
        db.execSQL(
            "INSERT INTO $TABLE_GPU ($GPU_ID, $GPU_NAME, $GPU_PRICE, $GPU_VRAM, $GPU_CLOCK_SPEED, $GPU_TIER) VALUES " +
                    "(1, 'NVIDIA GeForce RTX 4090', 200000, 24, 2600, 3), " +
                    "(2, 'NVIDIA GeForce RTX 3090', 150000, 24, 1695, 3), " +
                    "(3, 'NVIDIA GeForce RTX 4080', 120000, 16, 2520, 3), " +
                    "(4, 'NVIDIA GeForce RTX 3080', 110000, 10, 1710, 2), " +
                    "(5, 'NVIDIA GeForce RTX 4070 Ti', 80000, 12, 1710, 2), " +
                    "(6, 'NVIDIA GeForce RTX 3070 Ti', 70000, 8, 1830, 2), " +
                    "(7, 'NVIDIA GeForce RTX 4070', 55000, 12, 2480, 2), " +
                    "(8, 'NVIDIA GeForce RTX 3070', 40000, 8, 1725, 2), " +
                    "(9, 'NVIDIA GeForce RTX 3060 Ti', 35000, 8, 1700, 1), " +
                    "(10, 'NVIDIA GeForce RTX 4060', 30000, 8, 2505, 1), " +
                    "(11, 'NVIDIA GeForce RTX 3060', 27000, 12, 1777, 1), " +
                    "(12, 'NVIDIA GeForce RTX 3050', 22000, 8, 1450, 1), " +
                    "(13, 'NVIDIA GeForce GTX 1660s', 18000, 6, 1785, 0), " +
                    "(14, 'NVIDIA GeForce GTX 1650', 12000, 4, 1710, 0), " +
                    "(15, 'NVIDIA GeForce GT 730', 5000, 2, 902, 0), " +
                    "(16, 'AMD Radeon RX 7900 XTX', 120000, 24, 2620, 3), " +
                    "(17, 'AMD Radeon RX 7900 XT', 100000, 20, 2130, 3), " +
                    "(18, 'AMD Radeon RX 7800 XT', 60000, 16, 2430, 2), " +
                    "(19, 'AMD Radeon RX 7700 XT', 50000, 12, 2584, 2), " +
                    "(20, 'AMD Radeon RX 7600 XT', 35000, 16, 2799, 1), " +
                    "(21, 'AMD Radeon RX 7600', 30000, 8, 2745, 1), " +
                    "(22, 'AMD Radeon RX 6600', 20000, 8, 2491, 1), " +
                    "(23, 'AMD Radeon RX 6400', 12000, 4, 2325, 0) "
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop CPU table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CPU")
        // Drop GPU table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_GPU")
        // Recreate CPU and GPU tables
        onCreate(db)
    }
}