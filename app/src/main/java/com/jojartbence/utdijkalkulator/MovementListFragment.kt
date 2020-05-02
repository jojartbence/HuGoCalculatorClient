package com.jojartbence.utdijkalkulator

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jojartbence.adapter.MovementAdapter
import com.jojartbence.model.MovementModel
import kotlinx.android.synthetic.main.movement_list_fragment.*
import kotlinx.android.synthetic.main.truck_list_fragment.*
import kotlinx.android.synthetic.main.truck_list_fragment.recyclerView
import java.text.SimpleDateFormat
import java.util.*


class MovementListFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[MovementListViewModel::class.java] }

    lateinit var navController: NavController

    private val movementListObserver = Observer<List<MovementModel>> {
        recyclerView.adapter = MovementAdapter(it, null)
        recyclerView.adapter?.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.attachArguments(arguments!!.getParcelable("truck")!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movement_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        viewModel.movementList.observe(viewLifecycleOwner, movementListObserver)

        recyclerView.adapter?.notifyDataSetChanged()

        addMovementButton.setOnClickListener { addMovement() }
    }

    private fun addMovement() {
        try {
            val from = stringToDate(newMovementFrom.text.toString())
            val to = stringToDate(newMovementTo.text.toString())
            val distance = newMovementDistance.text.toString().toInt()
            val onMotorway = newMovementIsOnMotorway.isChecked

            if (from != null && to != null) {
                viewModel.addMovement(from, to, distance, onMotorway)
            }
            showFormatError()
        } catch(e: Exception) {
            showFormatError()
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun stringToDate(dateAsString: String): Date? {
        return SimpleDateFormat("yyyy.MM.dd. HH:mm:ss").parse(dateAsString)
    }


    private fun showFormatError() {
        Toast.makeText(activity, "Rossz formatum", Toast.LENGTH_SHORT).show()
    }

}
