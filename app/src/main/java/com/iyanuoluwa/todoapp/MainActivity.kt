package com.iyanuoluwa.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.iyanuoluwa.todoapp.model.Task
import com.iyanuoluwa.todoapp.ui.OnTaskListener
import com.iyanuoluwa.todoapp.ui.SharedViewModel
import com.iyanuoluwa.todoapp.ui.TaskAdapter
import com.iyanuoluwa.todoapp.ui.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnTaskListener {

    private val taskViewModel: TaskViewModel by viewModels()
    private var recyclerView: RecyclerView? = null
    private var taskAdapter: TaskAdapter? = null
    private var bottomSheetFragment: BottomSheetFragment? = null
    private var sharedViewModel: SharedViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        bottomSheetFragment = BottomSheetFragment()
        val constraintLayout = findViewById<ConstraintLayout>(R.id.bottomSheet)
        val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> =
            BottomSheetBehavior.from(constraintLayout)
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.STATE_HIDDEN

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        lifecycleScope.launch {
            getAllTasks()
        }

        fab.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment?.tag)
    }

    private fun getAllTasks() {
        taskViewModel.getAllTasks().observe(this) {
            taskAdapter = TaskAdapter(this, it, this)
            recyclerView?.adapter = taskAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) return true
        return super.onOptionsItemSelected(item)
    }

    override fun onTaskClick(task: Task) {
        //Log.d("Click", "onTaskClick $adapterPosition")
        sharedViewModel?.selectItem(task)
        sharedViewModel?.isEdit = true
        showBottomSheetDialog()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onTaskRadioButtonClick(task: Task) {
        //Log.d("Click", "onRadioButton ${task.task}")
        lifecycleScope.launch {
            taskViewModel.delete(task)
            taskAdapter?.notifyDataSetChanged()
        }
    }
}