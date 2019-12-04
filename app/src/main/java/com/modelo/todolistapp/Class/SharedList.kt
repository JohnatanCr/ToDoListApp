package com.modelo.todolistapp.Class

data class SharedList(var idUser: String,
                      var userName : String,
                      var title: String,
                      var Description: String,
                      var backgroundColor: String,
                      var listIcon: Int,
                      var listOfUsers : MutableList<String>){
    var idList: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SharedList

        if (idList != other.idList) return false

        return true
    }

    override fun hashCode(): Int {
        return idList?.hashCode() ?: 0
    }

}