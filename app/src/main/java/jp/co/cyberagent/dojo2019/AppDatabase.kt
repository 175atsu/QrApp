package jp.co.cyberagent.dojo2019



import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1) // Kotlin 1.2からは arrayOf(User::class)の代わりに[User::class]と書ける
abstract class AppDatabase : RoomDatabase() {

    // DAOを取得する。
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "database.db").build()
    }
}