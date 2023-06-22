package com.witzeal.pagination.presentation.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.witzeal.pagination.BR
import com.witzeal.pagination.R
import com.witzeal.pagination.common.observeOnce
import com.witzeal.pagination.databinding.ActivitySplashBinding
import com.witzeal.pagination.presentation.FragmentContainerActivity
import com.witzeal.pagination.presentation.core.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private lateinit var splashScreen: SplashScreen
    private val mViewModel: SplashViewModel by viewModels()

    private fun navigateToGridScreen() {
        val intent = Intent(this@SplashActivity, FragmentContainerActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun preSetContentViewInit() {
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