package com.example.myapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapp.model.Cronos
import kotlinx.coroutines.flow.Flow

@Dao
interface CronosDatabaseDao{
    @Query("SELECT * FROM cronos")
    fun getCronos(): Flow<List<Cronos>>

    @Query("SELECT * FROM cronos WHERE id = :id")
    fun getCronosById(id:Long): Flow<List<Cronos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cronos: Cronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update (cronos: Cronos)

    @Delete
    suspend fun delete(cronos: Cronos)
}

