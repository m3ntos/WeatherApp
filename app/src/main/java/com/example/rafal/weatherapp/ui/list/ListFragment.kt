package com.example.rafal.weatherapp.ui.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.rafal.weatherapp.R
import com.example.rafal.weatherapp.framework.BaseFragment
import com.example.rafal.weatherapp.framework.viewModelProvider
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : BaseFragment<ListViewData>() {

    override val layoutResId = R.layout.list_fragment
    override val viewModel by viewModelProvider { ListViewModel() }

    private lateinit var forecastAdapter: ForecastAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun updateUi(viewData: ListViewData) {
        with(viewData) {
            forecastAdapter.swapForecast(items)
            goToDetails?.handle { navigateToDetails(it.weatherEntryId) }
        }
    }

    private fun setupRecyclerView() {
        forecastAdapter = ForecastAdapter(activity!!, viewModel)
        recyclerview.adapter = forecastAdapter
        recyclerview.layoutManager = LinearLayoutManager(activity!!)
    }

    private fun navigateToDetails(weatherEntryId: Int) {
        val action = ListFragmentDirections.showDetails(weatherEntryId)
        findNavController().navigate(action)
    }
}
