package com.modelo.todolistapp.Class

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocalList(
    var idList: String = "",
    var idUser: String = "",
    var userName: String = "",
    var title: String = "",
    var backgroundColor: String = "",
    var listIcon: Int = 0

) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocalList

        if (idList != other.idList) return false

        return true
    }

    override fun hashCode(): Int {
        return idList?.hashCode() ?: 0
    }
}