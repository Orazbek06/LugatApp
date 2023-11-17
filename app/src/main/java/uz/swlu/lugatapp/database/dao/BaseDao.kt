package uz.swlu.lugatapp.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<Entity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: Entity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg args: Entity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entities: List<Entity>)

    @Update
    suspend fun update(entity: Entity?)

    @Update
    suspend fun update(vararg args: Entity)

    @Update
    suspend fun update(entities: List<Entity>)

    @Delete
    suspend fun delete(entity: Entity)

}