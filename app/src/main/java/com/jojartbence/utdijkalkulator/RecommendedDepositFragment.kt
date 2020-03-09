package com.jojartbence.utdijkalkulator

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class RecommendedDepositFragment : Fragment() {

    companion object {
        fun newInstance() = RecommendedDepositFragment()
    }

    private lateinit var viewModel: RecommendedDepositViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recommended_deposit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecommendedDepositViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
