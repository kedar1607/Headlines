package com.example.headlines.features.sources.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Model class for sources shown in the source fragment.
 */
@JsonClass(generateAdapter = true)
@Parcelize
@Entity(tableName = "sources")
data class Source(
    @Json(name = "category")
    @ColumnInfo(name = "category")
    val category: String? = null,
    @Json(name = "country")
    @ColumnInfo(name = "country")
    val country: String? = null,
    @Json(name = "description")
    @ColumnInfo(name = "source_description")
    val description: String? = null,
    @Json(name = "id")
    @ColumnInfo(name = "id")
    @PrimaryKey val id: String,
    @Json(name = "language")
    @ColumnInfo(name = "language")
    val language: String? = null,
    @Json(name ="name")
    @ColumnInfo(name = "name")
    val name: String,
    @Json(name = "url")
    @ColumnInfo(name = "source_url")
    val url: String? = null
) : Parcelable{

    override fun equals(other: Any?): Boolean {
       return if(other as? Source == null){
           false
       } else{
           other.id == id
       }
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}