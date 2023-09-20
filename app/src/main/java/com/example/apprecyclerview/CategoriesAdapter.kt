package com.example.apprecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//esta clase tendra que recibir un parametro que en este caso sera una lista privada
//haremos que herede de RecylcerView.Adapter<....> y entre las llaves colocaremos el nombre de nuestro ViewHolder
//que para este caso nosotros lo llamamos CategoriesViewHolder
class CategoriesAdapter(private val LisCategories: List<TaskCategory>,private val onItemSelected:(Int) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>() {
    //debemos implementar los metodos de la clase
    //este metodo crea una vista visual y pasar y montar esa vista esto lo hacemos desde el layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        //nos crearemos una variable LAyout Inflater
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category,parent,false)
        //ahora retornaremos el view Holder
        return CategoriesViewHolder(viewHolder)
    }
    //este metodo es el que se encarga de mostrar el tamaño del listado por ejemplo si le coloco 5 siempre me mostrara el listado
    //de tamaño 5, si no hay tanta informacion va romperse y si hay informacion no se muestra
    override fun getItemCount(): Int {
        //En este caso le dire que me retorne el tamaño de nuestra lista
        return LisCategories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        //le pasamos la posicion de nuestro task categories
        //render es un metodo de la clase CategoriesViewHolder
        holder.render(LisCategories[position], onItemSelected)
    }
}