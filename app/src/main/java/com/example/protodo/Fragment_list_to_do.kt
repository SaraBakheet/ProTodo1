package com.example.protodo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//import com.example.projecttodo.FragmentToDo
//import com.example.projecttodo.R


const val MY_ID = "myId"
class FragmentListToDo : Fragment() {


    private lateinit var toDoRecycleView: RecyclerView // 1 dec


    val toDoListViewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toDoListViewModel.liveDataTask.observe(
            viewLifecycleOwner, Observer {
                updateAdapter(it)
            }
        )

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_list_to_do, container, false)
        toDoRecycleView = view.findViewById(R.id.recycler_view_list)
        val linearLayoutManager = LinearLayoutManager(context)
        toDoRecycleView.layoutManager = linearLayoutManager
        return view

    }
    fun updateAdapter(task: List<ToDoData>) {
        //الادابتر يشغل الداتا ويحفظها
        val taskAdapter = ToDoAdapter(task)
        toDoRecycleView.adapter = taskAdapter
    }
    private inner class TaskHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var task: ToDoData
        private val titleTextView: TextView = itemView.findViewById(R.id.title_item)
        private val dateTextView: TextView = itemView.findViewById(R.id.date_item)
        val checkBoxisCompleted : CheckBox =itemView.findViewById(R.id.isCompleted)

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(task: ToDoData) {
            this.task = task
            titleTextView.text = task.title
            dateTextView.text = task.date.toString()
            checkBoxisCompleted.isChecked=task.isCompleted
        }



        override fun onClick(v: View?) {
            Toast.makeText(context,"tap to edit",Toast.LENGTH_SHORT).show()
            if (v == itemView){
                val fragment = FragmentListToDo()
                val args = Bundle ()
                args.putSerializable(MY_ID , task.id)
                fragment.arguments = args
                activity?.let {
                    it.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView,fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }

        }

    }
    private inner class ToDoAdapter (var tasks :List <ToDoData> ):RecyclerView.Adapter<TaskHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
            val view = layoutInflater.inflate(R.layout.list_item_to_do,parent,false)
            return TaskHolder(view)
        }

        override fun onBindViewHolder(holder: TaskHolder, position: Int) {
            val task = tasks[position]

            holder.bind(task)

        }

        override fun getItemCount(): Int = tasks.size


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tasklist,menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
                R.id.itemTask -> {
                val task = ToDoData()
                toDoListViewModel.addTask(task)
                val args = Bundle()
                args.putSerializable(MY_ID,task.id)
                val fragment = fragment_to_do()
                fragment.arguments = args
                activity?.let {
                         it.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView,fragment)
                        .addToBackStack(null)
                        .commit()
                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}