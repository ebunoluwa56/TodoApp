package com.iyanuoluwa.todoapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.iyanuoluwa.todoapp.R
import com.iyanuoluwa.todoapp.model.Task
import com.iyanuoluwa.todoapp.util.Utils
import javax.inject.Inject

class TaskAdapter
@Inject
constructor(
    val context: Context,
    private val tasks: List<Task>,
    var taskListener: OnTaskListener

) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Task = tasks[position]
        holder.task.text = task.task
        val formatted = Utils.formatDate(task.dueDate)
        holder.todayChip.text = formatted
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val radioButton: AppCompatRadioButton = itemView.findViewById(R.id.todo_radio_button)
        val task: AppCompatTextView = itemView.findViewById(R.id.todo_row_todo)
        val todayChip: Chip = itemView.findViewById(R.id.todo_row_chip)

        private var onTaskListener : OnTaskListener? = null
        init {
            this.onTaskListener = taskListener
            itemView.setOnClickListener(this)
            radioButton.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val curTask : Task = tasks[adapterPosition]
            when(view?.id) {
                R.id.task_row_layout -> {
                    onTaskListener?.onTaskClick(curTask)
                }
                R.id.todo_radio_button -> {
                    onTaskListener?.onTaskRadioButtonClick(curTask)
                }
            }
        }

    }
}