package com.example.bookapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.bookapi.R
import com.example.bookapi.databinding.ActivityFragmentContainerBinding
import com.example.bookapi.presentation.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentContainerActivity : BaseActivity<ActivityFragmentContainerBinding>(R.layout.activity_fragment_container) {
}