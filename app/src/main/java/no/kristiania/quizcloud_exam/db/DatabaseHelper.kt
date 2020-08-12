package no.kristiania.quizcloud_exam.db


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // insert data
    fun insertData(name: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, name)
        val result = db.insert(TABLE_NAME, null, contentValues)
       /* return if (result == -1) {
            false
        } else {
            true
        } */
        return true

    }


    companion object {

        val DATABASE_NAME = "User.db"
        val TABLE_NAME = "user_table"
        val COL_1 = "ID"
        val COL_2 = "NAME"
    }
}
