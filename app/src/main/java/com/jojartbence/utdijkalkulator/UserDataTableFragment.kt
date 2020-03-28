package com.jojartbence.utdijkalkulator

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jojartbence.model.TruckDataModel
import kotlinx.android.synthetic.main.user_data_table_fragment.*


class UserDataTableFragment : Fragment(), TruckDataListener {

    private val viewModel by lazy { ViewModelProviders.of(this)[UserDataTableViewModel::class.java] }

    lateinit var navController: NavController


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

        val layoutManager = LinearLayoutManager(activity!!.applicationContext)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = TruckDataAdapter(viewModel.getTruckDataList(), this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onTruckDataClick(truckData: TruckDataModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
