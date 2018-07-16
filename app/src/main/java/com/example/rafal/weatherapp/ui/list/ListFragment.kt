package com.example.rafal.weatherapp.ui.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rafal.weatherapp.R
import com.example.rafal.weatherapp.framework.observe
import com.example.rafal.weatherapp.framework.viewModelProvider
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    private val viewModel by viewModelProvider { ListViewModel() }
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getViewData().observe(this, ::updateUi)

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
