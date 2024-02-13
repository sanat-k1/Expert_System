import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbController(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "my_database.db"
        const val DATABASE_VERSION = 1

        // CPU table
        const val TABLE_CPU = "cpu"
        const val CPU_ID = "ID"
        const val CPU_NAME = "CPU_name"
        const val CPU_PRICE = "CPU_price"
        const val CPU_TIER = "Tier"
        const val CPU_BASE_CLOCK = "Base_clk"
        const val CPU_MAX_CLOCK = "Max_clk"
        const val CPU_CORES = "Cores"
        const val CPU_THREADS = "Threads"
        const val CPU_IMG = "Image"

        // GPU table
        const val TABLE_GPU = "gpu"
        const val GPU_ID = "ID"
        const val GPU_NAME = "GPU_name"
        const val GPU_PRICE = "GPU_price"
        const val GPU_VRAM = "VRAM"
        const val GPU_CLOCK_SPEED = "Clock_speed"
        const val GPU_TIER = "Tier"
        const val GPU_IMG = "Image"

        // SSD table
        const val TABLE_SSD = "ssd"
        const val SSD_ID = "ID"
        const val SSD_PRICE = "SSD_price"
        const val SSD_CAPACITY = "Capacity"

        // RAM table
        const val TABLE_RAM = "ram"
        const val RAM_ID = "ID"
        const val RAM_PRICE = "RAM_price"
        const val RAM_CAPACITY = "Capacity"
        const val RAM_TYPE = "Type"

        // SQL statement for creating CPU table
        const val CREATE_TABLE_CPU = "CREATE TABLE $TABLE_CPU (" +
                "$CPU_ID INTEGER PRIMARY KEY," +
                "$CPU_NAME TEXT," +
                "$CPU_PRICE REAL," +
                "$CPU_TIER INTEGER," +
                "$CPU_BASE_CLOCK REAL," +
                "$CPU_MAX_CLOCK REAL," +
                "$CPU_CORES INTEGER," +
                "$CPU_THREADS INTEGER," +
                "$CPU_IMG TEXT" +
                ")"

        // SQL statement for creating GPU table
        const val CREATE_TABLE_GPU = "CREATE TABLE $TABLE_GPU (" +
                "$GPU_ID INTEGER PRIMARY KEY," +
                "$GPU_NAME TEXT," +
                "$GPU_PRICE INTEGER," +
                "$GPU_VRAM INTEGER," +
                "$GPU_CLOCK_SPEED INTEGER," +
                "$GPU_TIER INTEGER," +
                "$GPU_IMG TEXT" +
                ")"

        // SQL statement for creating SSD table
        const val CREATE_TABLE_SSD = "CREATE TABLE $TABLE_SSD (" +
                "$SSD_ID INTEGER PRIMARY KEY," +
                "$SSD_PRICE INTEGER," +
                "$SSD_CAPACITY TEXT" +
                ")"

        // SQL statement for creating RAM table
        const val CREATE_TABLE_RAM = "CREATE TABLE $TABLE_RAM (" +
                "$RAM_ID INTEGER PRIMARY KEY," +
                "$RAM_PRICE INTEGER," +
                "$RAM_CAPACITY INTEGER," +
                "$RAM_TYPE TEXT" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create CPU table
        db.execSQL(CREATE_TABLE_CPU)
        // Create GPU table
        db.execSQL(CREATE_TABLE_GPU)
        // Create SSD table
        db.execSQL(CREATE_TABLE_SSD)
        // Create RAM table
        db.execSQL(CREATE_TABLE_RAM)


        // Insert data into CPU table
        db.execSQL(
            "INSERT INTO $TABLE_CPU ($CPU_ID, $CPU_NAME, $CPU_PRICE, $CPU_TIER, $CPU_BASE_CLOCK, $CPU_MAX_CLOCK, $CPU_CORES, $CPU_THREADS, $CPU_IMG) VALUES " +
                    "(1, 'Intel Core i9-13900K', 55000, 3, 3.00, 5.80, 24, 32, 'i13900k'), " +
                    "(2, 'Intel Core i9-12900K', 45000, 3, 3.20, 5.20, 16, 24, 'i12900k'), " +
                    "(3, 'Intel Core i7-13700K', 40000, 2, 2.50, 5.40, 16, 24, 'i13700k'), " +
                    "(4, 'Intel Core i7-12700K', 35000, 2, 3.60, 5.00, 12, 20, 'i12700k'), " +
                    "(5, 'Intel Core i5-13600K', 30000, 1, 2.60, 5.10, 14, 20, 'i13600k'), " +
                    "(6, 'Intel Core i5-12600K', 25000, 1, 3.70, 4.90, 10, 16, 'i12600k'), " +
                    "(7, 'Intel Core i5-13400', 21000, 1, 2.50, 4.60, 10, 16, 'i13400'), " +
                    "(8, 'Intel Core i3-13100', 15000, 0, 3.40, 4.50, 4, 8, 'i13100'), " +
                    "(9, 'Intel Core i3-12100', 11000, 0, 3.30, 4.30, 4, 8, 'i12100'), " +
                    "(10, 'AMD Ryzen 9 7950X', 52000, 3, 4.50, 5.70, 16, 32, 'r7950x'), " +
                    "(11, 'AMD Ryzen 9 7900X', 45000, 3, 4.70, 5.60, 12, 24, 'r7900x'), " +
                    "(12, 'AMD Ryzen 9 5900X', 30000, 2, 3.70, 4.80, 12, 24, 'r5900x'), " +
                    "(13, 'AMD Ryzen 7 7700X', 35000, 2, 4.50, 5.40, 8, 16, 'r7700x'), " +
                    "(14, 'AMD Ryzen 7 5700X', 18000, 2, 3.80, 4.80, 8, 16, 'r5700x'), " +
                    "(15, 'AMD Ryzen 5 7600X', 23000, 1, 3.80, 5.10, 6, 12, 'r7600x'), " +
                    "(16, 'AMD Ryzen 5 5600X', 16000, 1, 3.70, 4.60, 6, 12, 'r5600x'), " +
                    "(17, 'AMD Ryzen 5 5500', 10000, 0, 3.60, 4.20, 6, 12, 'r5500'), " +
                    "(18, 'AMD Ryzen 5 3600', 8000, 0, 3.60, 4.20, 6, 12, 'r7950x" +
                    "3600') "
        )
        // Insert data into GPU table
        db.execSQL(
            "INSERT INTO $TABLE_GPU ($GPU_ID, $GPU_NAME, $GPU_PRICE, $GPU_VRAM, $GPU_CLOCK_SPEED, $GPU_TIER, $GPU_IMG) VALUES " +
                    "(1, 'NVIDIA GeForce RTX 4090', 200000, 24, 2600, 3, 'n4090'), " +
                    "(2, 'NVIDIA GeForce RTX 3090', 150000, 24, 1695, 3, 'n3090'), " +
                    "(3, 'NVIDIA GeForce RTX 4080', 120000, 16, 2520, 3, 'n4080'), " +
                    "(4, 'NVIDIA GeForce RTX 3080', 110000, 10, 1710, 2, 'n3080'), " +
                    "(5, 'NVIDIA GeForce RTX 4070 Ti', 80000, 12, 1710, 2, 'n4070ti'), " +
                    "(6, 'NVIDIA GeForce RTX 3070 Ti', 70000, 8, 1830, 2, 'n3070ti'), " +
                    "(7, 'NVIDIA GeForce RTX 4070', 55000, 12, 2480, 2, 'n4070'), " +
                    "(8, 'NVIDIA GeForce RTX 3070', 40000, 8, 1725, 2, 'n3070'), " +
                    "(9, 'NVIDIA GeForce RTX 3060 Ti', 35000, 8, 1700, 1, 'n3060ti'), " +
                    "(10, 'NVIDIA GeForce RTX 4060', 30000, 8, 2505, 1, 'n4060'), " +
                    "(11, 'NVIDIA GeForce RTX 3060', 27000, 12, 1777, 1, 'n3060'), " +
                    "(12, 'NVIDIA GeForce RTX 3050', 22000, 8, 1450, 1, 'n3050'), " +
                    "(13, 'NVIDIA GeForce GTX 1660s', 18000, 6, 1785, 0, 'n1660s'), " +
                    "(14, 'NVIDIA GeForce GTX 1650', 12000, 4, 1710, 0, 'n1650'), " +
                    "(15, 'NVIDIA GeForce GT 730', 5000, 2, 902, 0, 'n730'), " +
                    "(16, 'AMD Radeon RX 7900 XTX', 120000, 24, 2620, 3, 'r7900xtx'), " +
                    "(17, 'AMD Radeon RX 7900 XT', 100000, 20, 2130, 3, 'r7900xt'), " +
                    "(18, 'AMD Radeon RX 7800 XT', 60000, 16, 2430, 2, 'r7800xt'), " +
                    "(19, 'AMD Radeon RX 7700 XT', 50000, 12, 2584, 2, 'r7700xt'), " +
                    "(20, 'AMD Radeon RX 7600 XT', 35000, 16, 2799, 1, 'r7600xt'), " +
                    "(21, 'AMD Radeon RX 7600', 30000, 8, 2745, 1, 'r7600'), " +
                    "(22, 'AMD Radeon RX 6600', 20000, 8, 2491, 1, 'r6600'), " +
                    "(23, 'AMD Radeon RX 6400', 12000, 4, 2325, 0, 'r6400') "
        )
        // Insert data into SSD table
        db.execSQL(
            "INSERT INTO $TABLE_SSD ($SSD_ID, $SSD_PRICE, $SSD_CAPACITY) VALUES " +
                    "(1, 1500, '256'), " +
                    "(2, 3000, '512'), " +
                    "(3, 6000, '1024'), " +
                    "(4, 10000, '2048'), " +
                    "(5, 20000, '4096')"
        )

        // Insert data into RAM table
        db.execSQL(
            "INSERT INTO $TABLE_RAM ($RAM_ID, $RAM_PRICE, $RAM_CAPACITY, $RAM_TYPE) VALUES " +
                    "(1, 2000, 8, 'DDR4'), " +
                    "(2, 4000, 16, 'DDR4'), " +
                    "(3, 8000, 32, 'DDR4'), " +
                    "(4, 15000, 64, 'DDR4'), " +
                    "(5, 32000, 128, 'DDR4')"
        )


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop CPU table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CPU")
        // Drop GPU table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_GPU")
        // Drop SSD table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SSD")
        // Drop RAM table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RAM")
        // Recreate CPU, GPU, SSD, and RAM tables
        onCreate(db)
    }

    fun get_cpuInfo(price: Int? = null, type: String? = null): Triple<String, String, String>? {
        return try {
            val db = readableDatabase
            val cursor: Cursor?

            // Build the SQL query based on the provided parameters
            val query = when {
                price != null && type != "any" -> {
                    "SELECT $CPU_NAME, $CPU_PRICE, $CPU_IMG FROM $TABLE_CPU WHERE $CPU_PRICE <= ? AND $CPU_NAME LIKE '%$type%'"
                }
                type == "any" -> {
                    "SELECT $CPU_NAME, $CPU_PRICE, $CPU_IMG FROM $TABLE_CPU WHERE $CPU_PRICE <= ? "
                }
                else -> {
                    // No valid parameters provided
                    return null
                }
            }

            // Execute the query with appropriate arguments
            cursor = db.rawQuery(query, arrayOf(price.toString()))

            cursor.use {
                if (cursor.moveToFirst()) {
                    Triple(cursor.getString(0), cursor.getString(1), cursor.getString(2))
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            // Handle database query error gracefully
            e.printStackTrace()
            null
        }
    }//gpu query


    fun get_cpuInfo2(price: Int? = null, type: String? = null): Triple<Int, Int, Int>? {
        return try {
            val db = readableDatabase
            val cursor: Cursor?

            // Build the SQL query based on the provided parameters
            val query = when {
                price != null && type != "any" -> {
                    "SELECT $CPU_CORES, $CPU_MAX_CLOCK, $CPU_PRICE FROM $TABLE_CPU WHERE $CPU_PRICE <= ? AND $CPU_NAME LIKE '%$type%'"
                }
                price != null -> {
                    "SELECT $CPU_CORES, $CPU_MAX_CLOCK, $CPU_PRICE  FROM $TABLE_CPU WHERE $CPU_PRICE <= ? "
                }
                else -> {
                    // No valid parameters provided
                    return null
                }
            }

            // Execute the query with appropriate arguments
            cursor = db.rawQuery(query, arrayOf(price.toString()))

            cursor.use {
                if (cursor.moveToFirst()) {
                    val cores = cursor.getInt(0)
                    val clock = cursor.getInt(1)
                    val Price = cursor.getInt(2)

                    Triple(cores, clock, Price)
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            // Handle database query error gracefully
            e.printStackTrace()
            null
        }
    }

    fun get_gpuInfo2(price: Int? = null, type: String? = null): Triple<Int, Int, Int>? {
        return try {
            val db = readableDatabase
            val cursor: Cursor?

            // Build the SQL query based on the provided parameters
            val query = when {
                price != null && type != "any" -> {
                    "SELECT $GPU_VRAM, $GPU_CLOCK_SPEED, $GPU_TIER FROM $TABLE_GPU WHERE $GPU_PRICE <= ? AND $GPU_NAME LIKE '%$type%'"
                }
                price != null -> {
                    "SELECT $GPU_VRAM, $GPU_CLOCK_SPEED, $GPU_TIER FROM $TABLE_GPU WHERE $GPU_PRICE <= ?"
                }
                else -> {
                    // No valid parameters provided
                    return null
                }
            }

            // Execute the query with appropriate arguments
            cursor = db.rawQuery(query, arrayOf(price.toString()))

            cursor.use {
                if (cursor.moveToFirst()) {
                    val gpuVram = cursor.getInt(0)
                    val gpuClockspeed = cursor.getInt(1)
                    val gpuTier = cursor.getInt(2)

                    Triple(gpuVram, gpuClockspeed, gpuTier)
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            // Handle database query error gracefully
            e.printStackTrace()
            null
        }
    }

    fun get_gpuInfo(price: Int? = null, type: String? = null): Pair<String, String>? {
        return try {
            val db = readableDatabase
            val cursor: Cursor?

            // Build the SQL query based on the provided parameters
            val query = when {
                price != null && type != "any" -> {
                    "SELECT $GPU_NAME, $GPU_PRICE FROM $TABLE_GPU WHERE $GPU_PRICE <= ? AND $GPU_NAME LIKE '%$type%'"
                }
                type == "any" -> {
                    "SELECT $GPU_NAME, $GPU_PRICE FROM $TABLE_GPU WHERE $GPU_PRICE <= ?"
                }
                else -> {
                    // No valid parameters provided
                    return null
                }
            }

            // Execute the query with appropriate arguments
            cursor = db.rawQuery(query, arrayOf(price.toString()))

            cursor.use {
                if (cursor.moveToFirst()) {
                    Pair(cursor.getString(0), cursor.getString(1))
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            // Handle database query error gracefully
            e.printStackTrace()
            null
        }
    }//gpu query



}