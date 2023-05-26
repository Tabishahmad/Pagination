package com.example.bookapi.presentation.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.bookapi.BR
import com.example.bookapi.R
import com.example.bookapi.common.observeOnce
import com.example.bookapi.databinding.ActivitySplashBinding
import com.example.bookapi.presentation.FragmentContainerActivity
import com.example.bookapi.presentation.core.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private lateinit var splashScreen: SplashScreen
    private val mViewModel: SplashViewModel by viewModels()

    private fun navigateToGridScreen() {
        val intent = Intent(this@SplashActivity, FragmentContainerActivity::class.java)
        startActivity(intent)
        overridePendingTransition(
            R.anim.modal_activity_open_enter,
            R.anim.modal_activity_close_exit
        )
        finish()
    }

    override fun preSetViewInit() {
        splashScreen = installSplashScreen()
    }
    override fun init() {
        binding.setVariable(BR.viewModel,mViewModel)
    }
    override fun observeViewModel() {
        mViewModel.onStartEvent.observeOnce(this) {
            navigateToGridScreen()
        }
    }
}