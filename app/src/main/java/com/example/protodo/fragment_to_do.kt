package com.example.protodo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.util.*
const val TASK_DATE_KEY = "TaskDate"
    class fragment_to_do:Fragment(),DateDialogs.DatePickerCallback {
    private val viewModelFragmentToDo by lazy { ViewModelProvider(this).get(ViewModelFragmentToDo::class.java) }

    private val REQUEST_CONTACT = 1

    //    private lateinit var task
    private lateinit var titleText: EditText
    private lateinit var dateView: Button
    private lateinit var checkisCompleted: CheckBox
    private lateinit var detaiEditText: EditText
    private lateinit var task: ToDoData
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_to_do, container, false)

        titleText = view.findViewById<EditText>(R.id.task_title)
        detaiEditText = view.findViewById(R.id.task_details)
        checkisCompleted = view.findViewById<CheckBox>(R.id.Completed_check_box)
        dateView = view.findViewById(R.id.date_Btn)
        return view
    }

    override fun onStart() {
        super.onStart()

        dateView.setOnClickListener {

            val args = Bundle()
            args.putSerializable(TASK_DATE_KEY, task.date)

            val datePicker = DateDialogs()

            datePicker.arguments = args
            datePicker.setTargetFragment(this, 0)
            datePicker.show(this.parentFragmentManager, "date picker")

        }
        checkisCompleted.visibility = if (task.isCompleted) {
            View.VISIBLE
        } else {
            View.GONE
        }
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                task.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }


        }
        titleText.addTextChangedListener(textWatcher)

        val detilWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                task.detail = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        detaiEditText.addTextChangedListener(detilWatcher)
    }



    override fun onDateSelected(date: Date) {


            task.duoDate = date
        dateView.text = date.toString()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFragmentToDo.toDoDataLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                task = it
                titleText.setText(it.title)
                detaiEditText.setText(it.detail)
                dateView.text = it.duoDate.toString()

            }

        )

    }

    override fun onStop() {
        super.onStop()
        viewModelFragmentToDo.saveUpdate(task)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        task = ToDoData()

        val toDoId = arguments?.getSerializable(MY_ID) as UUID

        viewModelFragmentToDo.loadToDo(toDoId)


    }



}
