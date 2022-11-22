package com.isu.myborutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.isu.myborutoapp.data.local.dao.HeroDao
import com.isu.myborutoapp.data.local.dao.HeroRemoteKeysDao
import com.isu.myborutoapp.domain.model.Hero
import com.isu.myborutoapp.domain.model.HeroRemoteKey

@TypeConverters(DatabaseConverter::class)
@Database(entities = [Hero::class,HeroRemoteKey::class], version = 1)
abstract class BorutoDatabase : RoomDatabase() {
    abstract fun heroDao():HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}