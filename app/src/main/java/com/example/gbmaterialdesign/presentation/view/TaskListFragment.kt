package com.example.gbmaterialdesign.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbmaterialdesign.data.model.Task
import com.example.gbmaterialdesign.databinding.FragmentTaskListBinding
import com.example.gbmaterialdesign.presentation.adapter.OnStartDragListener
import com.example.gbmaterialdesign.presentation.adapter.TasksAdapter
import com.example.gbmaterialdesign.presentation.adapter.TasksTouchHelperCallback

class TaskListFragment : Fragment() {
    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksAdapter = TasksAdapter(object : OnStartDragListener {
            override fun onStartDrag(viewHolder: TasksAdapter.TaskViewHolder) {
                itemTouchHelper.startDrag(viewHolder)
            }
        }
        )

        itemTouchHelper = ItemTouchHelper(TasksTouchHelperCallback(tasksAdapter))

        binding.tasksContainer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tasksAdapter
        }

        binding.inputLayout.apply {
            setEndIconOnClickListener {
                if ((editText?.text?.length ?: 0) > 0) {
                    val newTask = Task(
                        id = (System.currentTimeMillis() / 1000).toString(),
                        text = editText?.text?.toString() ?: "",
                    )
                    tasksAdapter.addTask(newTask)
                }
            }
        }

        itemTouchHelper.attachToRecyclerView(binding.tasksContainer)
    }
}
