package com.example.apprecyclerview

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(ventana: View) : RecyclerView.ViewHolder(ventana) {
    private val tvTask: TextView = ventana.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = ventana.findViewById(R.id.cbTask)
    fun render(objetoTask: Task) {
        if (objetoTask.isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        //por ejemplo le colocaremos un texto del Objeto task
        tvTask.text = objetoTask.name
        cbTask.isChecked = objetoTask.isSelected
        val color = when (objetoTask.category) {
            //dependiendo de la categoria se le asignara la referencia del color
            TaskCategory.Business -> R.color.business_category//asi se consigue el color por codigo
            TaskCategory.Other -> R.color.other_category//asi se consigue el color por codigo
            TaskCategory.Personal -> R.color.personal_category//asi se consigue el color por codigo
            TaskCategory.Ricardo -> R.color.business_category
        }
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)//asi se consigue el color por codigo
        )
    }
}