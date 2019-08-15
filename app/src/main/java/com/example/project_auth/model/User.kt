package com.example.project_auth.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
        @PrimaryKey
        @ColumnInfo(name = "user_name")
        val userName: String,
        @ColumnInfo(name = "first_name")
        val firstName: String,
        @ColumnInfo(name = "last_name")
        val lastName: String,
        @ColumnInfo(name = "_password")
        val password: String) : Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(userName)
                parcel.writeString(firstName)
                parcel.writeString(lastName)
                parcel.writeString(password)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<User> {
                override fun createFromParcel(parcel: Parcel): User {
                        return User(parcel)
                }

                override fun newArray(size: Int): Array<User?> {
                        return arrayOfNulls(size)
                }
        }
}