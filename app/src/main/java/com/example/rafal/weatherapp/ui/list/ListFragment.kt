package com.example.rafal.weatherapp.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rafal.weatherapp.R
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.viewData.observe(this, Observer { data ->
            if (data != null) updateUi(data)
        })
        forecastAdapter = ForecastAdapter(activity!!, viewModel)
        recyclerview.adapter = forecastAdapter
        recyclerview.layoutManager = LinearLayoutManager(activity!!)
    }


    private fun updateUi(viewData: ListViewData) {

        with(viewData) {
            forecastAdapter.swapForecast(items)
            goToDetails?.handle { navigateToDetails(it.weatherEntryId) }
        }
    }

    private fun navigateToDetails(weatherEntryId: Int) {
        val action = ListFragmentDirections.showDetails(weatherEntryId)
        findNavController().navigate(action)
    }
}
