package com.uti.coralsee.config

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uti.coralsee.config.Constant.Companion.DB_NAME
import com.uti.coralsee.config.Constant.Companion.DB_VERSION

class Lite(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    data class Pengguna(val namaLengkap: String, val email: String, val username: String, val password: String)

    override fun onCreate(db: SQLiteDatabase?) {
        // Create table query
        val table = """
            CREATE TABLE pengguna(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nama_lengkap VARCHAR(100),
                email VARCHAR(100),
                username CHAR(10),
                password VARCHAR(100)
            )
        """.trimIndent()
        // Execute query to create table
        db?.execSQL(table)
        // Insert initial data (with corrected values)
        val insert = "INSERT INTO pengguna(nama_lengkap, email, username, password) VALUES('ADMIN', 'admin@example.com', 'admin', 'root')"
        db?.execSQL(insert)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle database upgrade as needed
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS pengguna")
            onCreate(db)
        }
    }

    fun login(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM pengguna WHERE username=? AND password=?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val count = cursor.count
        cursor.close()
        db.close()
        return count > 0
    }

    fun insertPengguna(pengguna: Pengguna): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nama_lengkap", pengguna.namaLengkap)
            put("email", pengguna.email)
            put("username", pengguna.username)
            put("password", pengguna.password)
        }
        val result = db.insert("pengguna", null, values)
        db.close()
        return result
    }
    fun getFirstName(loggedInUser: String?): String? {
        val db = readableDatabase
        val query = "SELECT nama_lengkap FROM pengguna WHERE username = ?"
        val cursor = db.rawQuery(query, arrayOf(loggedInUser))
        var firstName: String? = null
        if (cursor != null && cursor.moveToFirst()) {
            val fullName = cursor.getString(cursor.getColumnIndexOrThrow("nama_lengkap"))
            firstName = fullName.split(" ").firstOrNull()
            cursor.close()
        }
        db.close()
        return firstName
    }
    fun getShortenedName(loggedInUser: String?): String? {
        val db = readableDatabase
        val query = "SELECT nama_lengkap FROM pengguna WHERE username = ?"
        val cursor = db.rawQuery(query, arrayOf(loggedInUser))
        var shortenedName: String? = null
        if (cursor != null && cursor.moveToFirst()) {
            val fullName = cursor.getString(cursor.getColumnIndexOrThrow("nama_lengkap"))
            val parts = fullName.split(" ")
            if (parts.size >= 3) {
                val firstName = parts[0]
                val middleInitials = parts.subList(1, parts.size - 1).joinToString(separator = " ") { "${it.firstOrNull()?.toUpperCase()}. " }
                val lastName = parts[parts.size - 1]
                shortenedName = "$firstName $middleInitials $lastName"
            }
            cursor.close()
        }
        db.close()
        return shortenedName
    }
    // Fungsi untuk mengecek apakah username sudah ada di tabel pengguna
    fun isUsernameExists(username: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM pengguna WHERE username = ?"
        val cursor = db.rawQuery(query, arrayOf(username))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }
    fun isEmailExists(email: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM pengguna WHERE email = ?"
        val cursor = db.rawQuery(query, arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }




}
