package com.dicoding.asclepius.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class History(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_history")
    var idHistory: Int? = null,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "result")
    var result: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

): Parcelable
