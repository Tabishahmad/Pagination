package com.example.bookapi.presentation.introduction.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookapi.common.gone
import com.example.bookapi.common.showToast
import com.example.bookapi.common.visible
import com.example.bookapi.databinding.FragmentListBinding
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.BaseFragment
import com.example.bookapi.presentation.ViewState
import com.example.bookapi.presentation.introduction.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : BaseFragment(),ImageListAdapter.ImageClickListener {
    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchList()
        lifecycleScope.launch {
            viewModel.getViewStateFlow().collect{viewState->
                when(viewState){
                    is ViewState.Loading ->
                        showProgressBar()
                    is ViewState.Success -> {
                        setupRecyclersView(viewState.result)
                    }
                    is ViewState.Failure -> {
                        hideProgressBar()
                        viewState.throwable.message?.let {
                            it.showToast(requireContext())
                        }
                    }
                }
            }
        }
    }
    private fun hideProgressBar(){
        binding.progressBar.gone()
    }
    private fun showProgressBar(){
        binding.progressBar.visible()
    }
    private fun setupRecyclersView(list:List<Book>){
        hideProgressBar()
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rv.setData(list)
        binding.rv.setItemClickListener(this)
    }

    override fun onImageClick(view: View, any: Any, index: Int) {
        viewModel.handleBookFav(any as Book)
    }
}