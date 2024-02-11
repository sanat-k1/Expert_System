package com.example.pchelper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import dbController

class DataFetcher(context: Context) {
    private val dbHelper: dbController = dbController(context)
    private val db: SQLiteDatabase = dbHelper.readableDatabase

    fun fetchCPUs(): List<CPU> {
        val cpuList = mutableListOf<CPU>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${dbController.TABLE_CPU}", null)

        if (cursor.moveToFirst()) {
            do {
                val cpu = CPU(
                    cursor.getInt(cursor.getColumnIndexOrThrow(dbController.CPU_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(dbController.CPU_NAME)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(dbController.CPU_PRICE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(dbController.CPU_TIER)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(dbController.CPU_BASE_CLOCK)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(dbController.CPU_MAX_CLOCK)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(dbController.CPU_CORES)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(dbController.CPU_THREADS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(dbController.CPU_IMG))
                )
                cpuList.add(cpu)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return cpuList
    }

    // Implement similar methods for fetching GPUs, SSDs, and RAMs
}
