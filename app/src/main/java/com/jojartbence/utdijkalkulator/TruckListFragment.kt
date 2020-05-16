package com.jojartbence.utdijkalkulator

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jojartbence.adapter.TruckAdapter
import com.jojartbence.model.TruckModel
import kotlinx.android.synthetic.main.truck_list_fragment.*


class TruckListFragment : Fragment(), TruckAdapter.OnClickListener {

    private val viewModel by lazy { ViewModelProviders.of(this)[TruckListViewModel::class.java] }

    lateinit var navController: NavController


    private val truckListObserver = Observer<List<TruckModel>> {
        recyclerView.adapter = TruckAdapter(it, this)
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
        return inflater.inflate(R.layout.truck_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        viewModel.truckList.observe(viewLifecycleOwner, truckListObserver)
        viewModel.getAllTrucks()

        recyclerView.adapter?.notifyDataSetChanged()

        addTruckButton.setOnClickListener { addTruck() }
    }


    private fun addTruck() {

        try {
            val licensePlateNumber = newTruckLicensePlateText.text.toString()
            val emissionCategory = newTruckEmissionCategory.text.toString()

            viewModel.addNewTruck(licensePlateNumber, emissionCategory)

        } catch (e: Exception) {
            Toast.makeText(activity, "Format is wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTruckClick(truck: TruckModel) {
        val bundle = bundleOf("truck" to truck.copy())
        navController.navigate(R.id.action_truckListFragment_to_movementListFragment, bundle)
    }
}
