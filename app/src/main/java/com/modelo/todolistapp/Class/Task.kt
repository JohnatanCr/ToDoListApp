package com.modelo.todolistapp.Class

data class Task( var idList : String, var title : String, var creatorName : String , var Importance : Int, var endDate : String, var isComplete : String ) {
    var idTask : String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (idTask != other.idTask) return false

        return true
    }

    override fun hashCode(): Int {
        return idTask?.hashCode() ?: 0
    }
}
