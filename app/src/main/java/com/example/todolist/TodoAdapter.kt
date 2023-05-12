package com.example.todolist

import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo1
import com.example.todolist.data.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(context: Context,mTodoViewModel:TodoViewModel

) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private var todo = emptyList<Todo1>()
    private  var  mTodoViewModel1 = mTodoViewModel
    private var context1 = context
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)

        return TodoViewHolder(view)


    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView,datatext: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
            datatext.paintFlags = datatext.paintFlags or STRIKE_THRU_TEXT_FLAG

        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            datatext.paintFlags = datatext.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todo[position]
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val currentDateAndTime = sdf.format(Date())


        val tvTodoTitle = holder.itemView.tvTodoTitle
        val datatext = holder.itemView.datatext
        val cbDone = holder.itemView.cbDone



        tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            datatext.text = currentDateAndTime
            toggleStrikeThrough(tvTodoTitle,datatext, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle,datatext, isChecked)
                curTodo.isChecked = !curTodo.isChecked



            tvTodoTitle.setOnClickListener{
                val builder = AlertDialog.Builder(context1)
                builder.setPositiveButton("Да"){ _, _ ->
                    mTodoViewModel1.deleteTodo(curTodo)
                    Toast.makeText(context1,"Запись успешно удалена", Toast.LENGTH_SHORT).show()

                }
                builder.setNegativeButton("Нет"){_,_ ->}
                builder.setTitle("Хотите удалить? :")
                builder.setMessage("Уверен?: ${todo[position].title}?")
                builder.create().show()
            }

        }
    }


    override fun getItemCount(): Int {
        return todo.size
    }
    fun setData(todo:List<Todo1>){
        this.todo = todo
        notifyDataSetChanged()}

    fun updateData(todo:Todo1){
        this.todo+=todo
        notifyDataSetChanged()}
}