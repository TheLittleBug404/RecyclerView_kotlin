package com.example.apprecyclerview

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecyclerview.TaskCategory.Business
import com.example.apprecyclerview.TaskCategory.Other
import com.example.apprecyclerview.TaskCategory.Personal
import com.example.apprecyclerview.TaskCategory.Ricardo
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    //Nos creamos una referencia al Recyler View
    private lateinit var rvCategories: RecyclerView
    //crearemos una variable para el adapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    //creamos una referencia al recycler view de tareas
    private lateinit var rvTask: RecyclerView
    //nos creamos un adapter para el task
    private lateinit var taskAdapter: TaskAdapter
    //creamos una varaiable para el Floating action button
    private lateinit var fabAddTasks:FloatingActionButton

    //nos creamos un listado de categorias para el categoriesAdapter
    private val categories = listOf(
        //le pasamos los parametros para que pueda llenar el recycler view
        //todo lo que le pasemos aca nos saldra en el recycler view
        //debemos importar el task category
        Business,
        Personal,
        Other,
        Ricardo
    )

    //aca crearemos un listado para que reciba el task Category
    //esta vez es una lista mutable por que iremos añadiendo datos a este listado
    private val tasks = mutableListOf(
        Task("Prueba Negocio", Business, false),
        Task("Prueba Personal", Personal, false),
        Task("Prueba Otros", Other, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //nos creamos los metodos que utilizamos para las aplicaciones
        initComponents()
        initUI()
        initListeners()
    }

    private fun initUI() {
        //en aca inicializamos el categoriesAdapter
        categoriesAdapter = CategoriesAdapter(categories){ position -> updateCategories(position)}
        //ahora le colocamos el layoutamanager al recyler View
        //el layout manager es el que se encarga deque la vista del Recycler sea horizontal o vertical
        //en este caso el colocaremos de forma horizontal
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
        //_________________________________________________________
        //aca inicializamos el adapter de Tareas (Task)
        //esta es una funcion lamdba
        taskAdapter = TaskAdapter(tasks){position -> onItemSelected(position)}
        rvTask.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTask.adapter = taskAdapter
    }

    private fun initComponents() {
        //iniciamos nuestro Recyler View Categorias
        rvCategories = findViewById(R.id.rvCategorias)
        //iniciamos el recycler view Tareas
        rvTask = findViewById(R.id.rvTasks)
        //le asignamos la referencia del XML
        fabAddTasks =  findViewById(R.id.fabAddTask)
    }
    //aca creaaremos una funcion para todos los listener o sea para todos los botones que se van a presionar
    private fun initListeners(){
        //funcion para cuando clicleemos el Floating action button
        fabAddTasks.setOnClickListener {
            showDialog()
        }
    }
    //funcion para crear el dialogo
    private fun showDialog(){
        val dialog:Dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val btnAddTask:AppCompatButton = dialog.findViewById(R.id.buttonCreaTarea)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val rgCategories:RadioGroup = dialog.findViewById(R.id.rgCategories)
        btnAddTask.setOnClickListener{
            val currentTask = etTask.text.toString()
            if(currentTask.isNotEmpty()){
                val selectedID = rgCategories.checkedRadioButtonId
                val selectedRadioButton:RadioButton = rgCategories.findViewById(selectedID)
                val currentCategory:TaskCategory = when(selectedRadioButton.text){
                    getString(R.string.negocio) -> Business
                    getString(R.string.personal) -> Personal
                    else -> Other
                }
                tasks.add(Task(currentTask,currentCategory))
                updateTasks()
                dialog.hide()
            }
        }
        dialog.show()
    }
    private fun updateTasks(){
        //solo mostramos las categorias seleccionadas
        //con el filter seleccionamos las categorias seleccionadas
        val selectedCategories:List<TaskCategory> = categories.filter { it.isSelected }
        //con esta linea filtramos entre todas las tareas que tengan el it.category
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.tasks = newTasks
        taskAdapter.notifyDataSetChanged()
    }
    private fun onItemSelected(position:Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }
    private fun updateCategories(position:Int){
        categories[position].isSelected = !categories[position].isSelected
        //el notifyItemCahnged es para decirle a nuestro programa que hubi un objeto modificado
        categoriesAdapter.notifyItemChanged(position)
        //debemos actualizar el task
        updateTasks()
    }
}