package com.kishorramani.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kishorramani.noteapp.Converters
import com.kishorramani.noteapp.dao.NoteDao
import com.kishorramani.noteapp.models.Note

@Database(entities = [Note::class], exportSchema = true, version = 3)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE note_table ADD COLUMN isCompleted INTEGER NOT NULL DEFAULT(0)")
            }
        }

        val migration_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE note_table ADD COLUMN isImportant INTEGER NOT NULL DEFAULT(0)")
            }
        }

        @Volatile  //Whenever we write any thing in INSTANCE variable then all thread will be aware about it's latest value
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // if instance of database is null then create the instance of database
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database.db",
                    ).addMigrations(migration_1_2, migration_2_3)
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
