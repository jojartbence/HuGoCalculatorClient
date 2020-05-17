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
import kotlinx.android.synthetic.main.server_url_set_fragment.*


class ServerUrlSetFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[ServerUrlSetViewModel::class.java] }
    lateinit var navController: NavController

    private val serverUrlObserver = Observer<String> {
        serverUrlValue.setText(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.server_url_set_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewModel.serverUrl.observe(viewLifecycleOwner, serverUrlObserver)
        viewModel.getServerUrl()

        setUrlButton.setOnClickListener { onSetUrlButtonClick() }
    }

    private fun onSetUrlButtonClick() {
        val value = serverUrlValue.text.toString()
        viewModel.setServerUrl(value)
    }

}
