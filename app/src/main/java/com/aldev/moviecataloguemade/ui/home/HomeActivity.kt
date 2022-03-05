package com.aldev.moviecataloguemade.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import com.aldev.moviecataloguemade.common.base.BaseActivity
import com.aldev.moviecataloguemade.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}