package uz.swlu.lugatapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    indices = [Index(value = ["english", "uzbek", "russian"], unique = true)]
)
data class WordsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "english")
    val english: String,

    @ColumnInfo(name = "uzbek")
    val uzbek: String,

    @ColumnInfo(name = "russian")
    val russian: String

)