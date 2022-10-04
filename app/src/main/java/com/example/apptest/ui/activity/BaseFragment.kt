package com.example.apptest.ui.activity

import androidx.fragment.app.Fragment
import androidx.work.WorkInfo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseFragment : Fragment(), KoinComponent {
    open fun setUpDynamicAdapter() { }
}