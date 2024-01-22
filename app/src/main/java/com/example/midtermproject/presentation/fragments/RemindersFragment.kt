package com.example.midtermproject.presentation.fragments

import android.app.AlertDialog
import android.content.Context
import android.util.Log.d
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtermproject.R
import com.example.midtermproject.databinding.FragmentRemindersBinding
import com.example.midtermproject.presentation.base.BaseFragment
import com.example.midtermproject.presentation.adapters.CalendarRecyclerAdapter
import com.example.midtermproject.presentation.adapters.RemindersRecyclerAdapter
import com.example.midtermproject.presentation.model.CalendarDay
import com.example.midtermproject.presentation.viewModels.RemindersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Integer.max
import java.lang.Integer.min
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class RemindersFragment :
    BaseFragment<FragmentRemindersBinding>(FragmentRemindersBinding::inflate) {

    private val viewModel: RemindersViewModel by viewModels()
    private lateinit var remindersRecyclerAdapter: RemindersRecyclerAdapter
    private lateinit var calendarRecyclerAdapter: CalendarRecyclerAdapter

    override fun setUp() {
//        viewModel.insertUser()
        recyclerViewInit()
        bindObserves()
        d("calendar days",createCalendarDays().toString())
    }

    private fun recyclerViewInit() {
        val calendarDays = createCalendarDays()
        calendarRecyclerAdapter = CalendarRecyclerAdapter(calendarDays)
        binding.apply{
            recyclerViewCalendarDays.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerViewCalendarDays.adapter = calendarRecyclerAdapter
        }

        val middlePosition = calendarDays.indexOfFirst { it.isCurrentDay }
        if (middlePosition != -1) {
            binding.recyclerViewCalendarDays.scrollToPosition(middlePosition)
        }


        remindersRecyclerAdapter = RemindersRecyclerAdapter()
        binding.apply {
            recyclerViewReminders.layoutManager = LinearLayoutManager(requireContext())

            recyclerViewReminders.adapter = remindersRecyclerAdapter
        }





    }

    override fun listeners() {

        binding.btnAddReminder.setOnClickListener {
            showInputDialog(requireContext())
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reminders.collect {
                    remindersRecyclerAdapter.submitList(it)
                }
            }
        }
    }

    private fun createCalendarDays(): List<CalendarDay> {
        val calendar = Calendar.getInstance()
        val currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH)
        val maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Calculate the start and end positions to show seven days
        val startPosition = max(1, currentDayOfMonth - 3)
        val endPosition = min(maxDays, currentDayOfMonth + 3)
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val calendarDays = (startPosition..endPosition).map { day ->
            calendar.set(Calendar.DAY_OF_MONTH, day)
            val dayOfWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time)
            val isCurrentDay = day == currentDayOfMonth
            val isCurrentMonth = calendar.get(Calendar.MONTH) == currentMonth
            CalendarDay(day, dayOfWeek, isCurrentMonth, isCurrentDay, monthFormat.format(calendar.time))
        }

        return calendarDays
    }


    private fun showInputDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_input, null)
        builder.setView(dialogView)

        val editTextMedicine = dialogView.findViewById<EditText>(R.id.editTextMedicine)
        val editTextTime = dialogView.findViewById<EditText>(R.id.editTextTime)

        builder.setPositiveButton("Submit") { _, _ ->
            val medicineName = editTextMedicine.text.toString()
            val timeToTake = editTextTime.text.toString()

            viewModel.insertReminder(medicineName, timeToTake)
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }




}