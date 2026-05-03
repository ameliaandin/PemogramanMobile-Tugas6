package com.utama.aplikasiloginsederhana
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class EventDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "event_app.db"
        private const val DB_VERSION = 1
        const val TABLE_EVENTS = "events"
        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_DATE = "date"
        const val COL_LOCATION = "location"
        const val COL_PRICE = "price"
        const val COL_DESCRIPTION = "description"
        const val COL_REGISTERED = "is_registered"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
 CREATE TABLE $TABLE_EVENTS (
 $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
 $COL_NAME TEXT NOT NULL,
 $COL_DATE TEXT NOT NULL,
 $COL_LOCATION TEXT NOT NULL,
 $COL_PRICE INTEGER NOT NULL DEFAULT 0,
 $COL_DESCRIPTION TEXT,
 $COL_REGISTERED INTEGER NOT NULL DEFAULT 0
 )
 """.trimIndent()
        db.execSQL(createTable)
        insertSampleData(db)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EVENTS")
        onCreate(db)
    }
    private fun insertSampleData(db: SQLiteDatabase) {
        val sampleEvents = listOf<Array<String>>(
            arrayOf("Pemeriksaan Kesehatan Gratis", "15 Mei 2026", "Gedung Klinik Widyatama", "0", "Pemeriksaan gula darah dan tekanan darah gratis", "0"),
            arrayOf("Vaksinasi Booster", "20 Mei 2026", "Aula Widyatama", "0", "Pemberian vaksin booster untuk civitas akademika", "0"),
            arrayOf("Seminar Kesehatan Mental", "25 Mei 2026", "Ruang Seminar 1", "25000", "Seminar mengelola stres di dunia kerja", "0"),
            arrayOf("Donor Darah", "1 Juni 2026", "Lobi Utama Klinik", "0", "Kegiatan rutin donor darah bersama PMI", "1"),
            arrayOf("Konsultasi Gizi Online", "10 Juni 2026", "Google Meet", "50000", "Konsultasi gizi dengan dokter spesialis", "0")
        )
        sampleEvents.forEach { e ->
            val cv = ContentValues().apply {
                put(COL_NAME, e[0]); put(COL_DATE, e[1])
                put(COL_LOCATION, e[2]); put(COL_PRICE, e[3].toInt())
                put(COL_DESCRIPTION, e[4]); put(COL_REGISTERED, e[5].toInt())
            }
            db.insert(TABLE_EVENTS, null, cv)
        }
    }
}