package com.iyanuoluwa.todoapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.iyanuoluwa.todoapp.model.Task
import com.iyanuoluwa.todoapp.ui.SharedViewModel
import com.iyanuoluwa.todoapp.ui.TaskViewModel
import com.iyanuoluwa.todoapp.util.Priority
import com.iyanuoluwa.todoapp.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var enterTask: EditText? = null
    private var calenderButton: ImageButton? = null
    private var priorityButton: ImageButton? = null
    private var priorityRadioGroup: RadioGroup? = null
    private val selectedRadioButton: RadioButton? = null
    private var selectedButtonId: Int? = null
    private lateinit var saveButton: ImageButton
    private var calenderView: CalendarView? = null
    private var calenderGroup: Group? = null
    private val taskViewModel: TaskViewModel by viewModels()
    private var sharedViewModel: SharedViewModel? = null
    private var dueDate: Date? = null
    private val calender: Calendar = Calendar.getInstance()

    private var isEdit : Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.bottom_sheet, container, false)
        calenderGroup = view.findViewById(R.id.calendar_group)
        calenderView = view.findViewById(R.id.calendar_view)
        calenderButton = view.findViewById(R.id.today_calendar_button)
        enterTask = view.findViewById(R.id.enter_task_et)
        saveButton = view.findViewById(R.id.save_task_button)
        priorityButton = view.findViewById(R.id.priority_task_button)
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority)

        val todayChip: Chip = view.findViewById(R.id.today_chip)
        todayChip.setOnClickListener(this)
        val tomorrowChip: Chip = view.findViewById(R.id.tomorrow_chip)
        tomorrowChip.setOnClickListener(this)
        val nextWeekChip: Chip = view.findViewById(R.id.next_week_chip)
        nextWeekChip.setOnClickListener(this)

        return view
    }

    override fun onResume() {
        super.onResume()

        if (sharedViewModel?.getSelectedItem()?.value != null) {
            isEdit = sharedViewModel?.isEdit
            val task : Task = sharedViewModel?.getSelectedItem()?.value!!
            enterTask?.setText(task.task)
            Log.d("MY", "onViewCreated ${task.task}")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        calenderButton?.setOnClickListener {
            calenderGroup?.visibility =
                if (calenderGroup?.visibility == View.GONE) View.VISIBLE else View.GONE
            Utils.hideSoftKeyboard(it)
        }

        calenderView?.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            //Log.d("Cal", "onViewCreated ==> month ${month + 1}, dayOfMonth $dayOfMonth")
            calender.clear()
            calender.set(year, month, dayOfMonth)
            dueDate = calender.time
        }

        saveButton.setOnClickListener {
            val task = enterTask?.text.toString().trim()
            if (!TextUtils.isEmpty(task) && dueDate != null) {
                val myTask =
                    Task(
                        0, task, Priority.MEDIUM, dueDate!!,
                        Calendar.getInstance().time, false
                    )
                if (isEdit == true) {
                    val updateTask : Task = sharedViewModel?.getSelectedItem()?.value!!
                    updateTask.task = task
                    updateTask.dateCreated = Calendar.getInstance().time
                    updateTask.dueDate = dueDate!!
                    updateTask.priority = Priority.MEDIUM
                    taskViewModel.update(updateTask)
                    sharedViewModel?.isEdit = false
                } else {
                    taskViewModel.insertTask(myTask)
                }
                enterTask?.setText("")
                if (this.isVisible) {
                    this.dismiss()
                }
            } else {
                Snackbar.make(saveButton, R.string.empty_field, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.today_chip -> {
                calender.add(Calendar.DAY_OF_YEAR, 0)
                dueDate = calender.time
                Log.d("Time", "onCreate ${dueDate.toString()}")
            }
            R.id.tomorrow_chip -> {
                calender.add(Calendar.DAY_OF_YEAR, 1)
                dueDate = calender.time
                Log.d("Time", "onCreate ${dueDate.toString()}")
            }
            R.id.next_week_chip -> {
                calender.add(Calendar.DAY_OF_YEAR, 7)
                dueDate = calender.time
                Log.d("Time", "onCreate ${dueDate.toString()}")
            }
        }
    }

}