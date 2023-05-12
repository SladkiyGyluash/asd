package com.example.todolist

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.Todo1
import com.example.todolist.data.TodoViewModel
import com.example.todolist.databinding.FragmentListfragmentBinding

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date


class Listfragment : Fragment() {

    private lateinit var bind: FragmentListfragmentBinding
    private lateinit var mTodoViewModel: TodoViewModel

    private lateinit var adapter:TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentListfragmentBinding.inflate(
            inflater,
            container,
            false
        )//все что тут это же было снизу если что

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
         adapter = TodoAdapter(requireContext(),mTodoViewModel)
        bind.rvTodoItems.layoutManager = LinearLayoutManager(requireContext())

        bind.rvTodoItems.adapter = adapter


        mTodoViewModel.readAllData.observe(viewLifecycleOwner, Observer { todolist ->
            adapter.setData(todolist)
        })
        bind.btnAddTodo.setOnClickListener {
            insertDataToDB()

            findNavController().navigate(R.id.action_listfragment_to_fg)
        }
        return bind.root
    }

    private fun insertDataToDB() {
        val title = bind.vvod.text
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val currentDateAndTime = sdf.format(Date())
        val date = currentDateAndTime


        if (inputCheck(title, date)) {
            val todo = Todo1(0, title.toString(), date.toString())
            mTodoViewModel.addTodo(todo)
            adapter.updateData(todo)

            Toast.makeText(requireContext(), "Запись добавлена в БД", Toast.LENGTH_SHORT).show()

        } else Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()

    }

    private fun inputCheck(title: Editable, date: String): Boolean {
        return !(title.isEmpty() || date.isEmpty())
    }




}
