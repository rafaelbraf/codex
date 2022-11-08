package com.example.codextesteapp

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.codextesteapp.Db.DatabaseHelper
import com.example.codextesteapp.Model.Task

class Question4Activity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question4)

        val listViewTasks: ListView = findViewById(R.id.listView_tasks)
        var listTasks = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, R.layout.layout_list_tasks, listTasks)
        listViewTasks.adapter = adapter

        val db = DatabaseHelper(this, null)
        val cursor = db.getTask()
        cursor.moveToFirst()
        addTaskInList(cursor, listTasks)
        while (cursor.moveToNext()) {
            addTaskInList(cursor, listTasks)
        }
        cursor.close()
        adapter.notifyDataSetChanged()

        val buttonAddTask: Button = findViewById(R.id.buttonAddTask)
        buttonAddTask.setOnClickListener {
            listTasks.clear()
            val editTextNewTask: EditText = findViewById(R.id.editTextNewTask)
            if (!editTextNewTask.text.isNullOrEmpty()) {
                val db = DatabaseHelper(this, null)
                var nameNewTask: String = editTextNewTask.text.toString()
                var task: Task = Task(null, nameNewTask)
//                listTasks.add(task.title)
//                adapter.notifyDataSetChanged()
//                editTextNewTask.text.clear()
                db.addTask(task)
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
                editTextNewTask.text.clear()
                val cursor = db.getTask()
                cursor.moveToFirst()
                addTaskInList(cursor, listTasks)
                while (cursor.moveToNext()) {
                    addTaskInList(cursor, listTasks)
                }
                cursor.close()
                adapter.notifyDataSetChanged()
            }
        }
        
        listViewTasks.setOnItemLongClickListener { parent, view, position, id ->
            val db = DatabaseHelper(this, null)
            db.deleteTask(id.toInt())
            listTasks.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }

    }

    @SuppressLint("Range")
    fun addTaskInList(cursor: Cursor, listTask: ArrayList<String>) {
        listTask.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TASK)))
    }

}