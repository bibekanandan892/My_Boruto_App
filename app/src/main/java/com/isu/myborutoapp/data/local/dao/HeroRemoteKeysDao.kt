package com.isu.myborutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isu.myborutoapp.domain.model.HeroRemoteKey


@Dao
interface HeroRemoteKeysDao {

    @Query("SELECT * FROM hero_remote_keys_table WHERE id = :heroId")
    suspend fun getRemoteKey(heroId : Int): HeroRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys : List<HeroRemoteKey>)

    @Query("DELETE FROM hero_remote_keys_table")
    suspend fun deleteAllRemoteKey()
}