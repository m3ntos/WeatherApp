package com.example.rafal.weatherapp.framework

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<T> : Fragment() {

    protected abstract val viewModel: BaseViewModel<T>

    @LayoutRes protected open val layoutResId: Int = 0

    protected abstract fun updateUi(viewData: T)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getViewData().observe(this, ::updateUi)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (layoutResId == 0) throw Error(
            """LayoutResId is not set. Either set layout id by overriding layoutResId property
            or override onCreateView method if you want to create layout programmatically"""
        )
        return inflater.inflate(layoutResId, container, false)
    }

}