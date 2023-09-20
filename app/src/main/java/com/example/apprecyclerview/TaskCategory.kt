package com.example.apprecyclerview

sealed class TaskCategory(var isSelected:Boolean = true) {
    //Crearemos objetos dentro de esta clase
    //estos objetos son los que colocaremos en nuestra Recycler View
    object Personal:TaskCategory()
    object Business:TaskCategory()
    object Other:TaskCategory()
    object Ricardo:TaskCategory()
}