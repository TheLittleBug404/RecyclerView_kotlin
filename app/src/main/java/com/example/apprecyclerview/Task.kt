package com.example.apprecyclerview
//data class es una clase que debe tener muchos atributos
//isSelected estara en falso por que cuando iniciemos la APP no estara seleccionado ninguno de nuestros atributos
data class Task(val name:String, val category:TaskCategory, var isSelected:Boolean=false) {
}