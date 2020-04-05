package com.jojartbence.utdijkalkulator

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
import com.jojartbence.helpers.LicensePlateNumberValidityChecker
import com.jojartbence.model.TruckModel
import kotlinx.android.synthetic.main.user_data_table_fragment.*


class UserDataTableFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[UserDataTableViewModel::class.java] }

    lateinit var navController: NavController


    private val truckListObserver = Observer<List<TruckModel>> {
        recyclerView.adapter = TruckAdapter(it, null)
        recyclerView.adapter?.notifyDataSetChanged()

        linearLayout.scrollTo(0,0)
        linearLayout.requestFocus()
        newTruckLicensePlateText.setText("")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_data_table_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        viewModel.truckList.observe(this, truckListObserver)

        recyclerView.adapter?.notifyDataSetChanged()

        addTruckButton.setOnClickListener { addTruck() }
    }


    private fun addTruck() {

        if (LicensePlateNumberValidityChecker.isValidLicensePlateNumber(newTruckLicensePlateText.text.toString())) {
            viewModel.addNewTruck(newTruckLicensePlateText.text.toString())
        } else {
            Toast.makeText(activity, "Format is wrong", Toast.LENGTH_SHORT).show()
        }

    }
}
