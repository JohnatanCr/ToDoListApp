package com.modelo.todolistapp.Class

data class User(
    var idUser: String ,
    var email: String,
    var name: String,
    var password: String,
    var icon: Int,
    var isVerified: Boolean
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (idUser != other.idUser) return false

        return true
    }

    override fun hashCode(): Int {
        return idUser?.hashCode() ?: 0
    }

}