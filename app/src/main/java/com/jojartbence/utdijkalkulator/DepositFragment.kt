package com.jojartbence.utdijkalkulator

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.deposit_fragment.*


class DepositFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[DepositViewModel::class.java] }
    lateinit var navController: NavController


    private val recommendedDepositObserver = Observer<Double> {
        recommendedDeposit.setText("Ajanlott feltoltes: $it Ft")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.deposit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewModel.recommendedDeposit.observe(viewLifecycleOwner, recommendedDepositObserver)

        depositButton.setOnClickListener { onDepositButtonClick() }
    }

    private fun onDepositButtonClick() {
        val value = depositValue.text.toString().toDouble()
        viewModel.deposit(value)
    }
}
