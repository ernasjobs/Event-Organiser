package com.sid1818416.eventorganiser.database.models
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData (
        @PrimaryKey(autoGenerate = true)   var id: Int,
        @ColumnInfo(name = "title")
        var title: String,
        @ColumnInfo(name = "priority")
        var priority: Priority,
        @ColumnInfo(name = "description")
        var description: String
        ):Parcelable


