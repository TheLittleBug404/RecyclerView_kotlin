package com.example.apprecyclerview

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

//esta clase recibira una View
//esta clase heredara un view Holder
//y el viewHolder recibira el view que nos creamos en la clase
class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    //agarraremos nuestro Tv del layout item_task_category
    private var tvNombreCategorias: TextView = view.findViewById(R.id.tvCategorieName)
    private var divider: View = view.findViewById(R.id.divider)
    private var viewConteiner: CardView = view.findViewById(R.id.viewConteiner)
    fun render(tc: TaskCategory, onItemSelected: (Int) -> Unit) {
        val color = if (tc.isSelected) {
            R.color.background_card
        } else {
            R.color.background_disabled
        }
        viewConteiner.setCardBackgroundColor(ContextCompat.getColor(viewConteiner.context,color))
        // con el layoutPosition obtendremos la posicion del lista
        itemView.setOnClickListener { onItemSelected(layoutPosition) }
        //tvNombreCategorias.text = "Ejemplo"
        when (tc) {
            TaskCategory.Business -> {
                tvNombreCategorias.text = "Negocios"
                //colocaremos un color para el divider la linea que divide el card view
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.business_category)
                )
            }

            TaskCategory.Other -> {
                tvNombreCategorias.text = "Otros"
                //colocaremos un color para el divider la linea que divide el card view
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.other_category)
                )
            }

            TaskCategory.Personal -> {
                tvNombreCategorias.text = "Personal"
                //colocaremos un color para el divider la linea que divide el card view
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }

            TaskCategory.Ricardo -> {
                tvNombreCategorias.text = "RicardoF"
                //colocaremos un color para el divider la linea que divide el card view
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }
        }
    }
}