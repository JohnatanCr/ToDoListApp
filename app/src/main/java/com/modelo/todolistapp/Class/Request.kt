package com.modelo.todolistapp.Class

data class Request (var idUserTo : String, var idList: String, var idUserCreator : String) {
    var idRequest : String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Request

        if (idRequest != other.idRequest) return false

        return true
    }

    override fun hashCode(): Int {
        return idRequest?.hashCode() ?: 0
    }
}