package com.example.bookapi.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookapi.R
import com.example.bookapi.common.LIST_INDEX
import com.example.bookapi.common.hide
import com.example.bookapi.common.show
import com.example.bookapi.common.showCustomToast
import com.example.bookapi.databinding.FragmentBookListBinding
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.core.ViewState
import com.example.bookapi.presentation.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BookListFragment : BaseFragment<BookListViewModel,FragmentBookListBinding>(
    R.layout.fragment_book_list
),ImageListAdapter.ItemClickListener {
    override val viewModel: BookListViewModel by activityViewModels()

    override fun init() {
        viewModel.fetchList()
    }
    private fun hideProgressBar(){
        binding.progressBar.hide()
    }
    private fun showProgressBar(){
        binding.progressBar.show()
    }
    private fun setupRecyclersView(list:List<Book>){
        hideProgressBar()
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rv.setData(list)
        binding.rv.setItemClickListener(this)
    }

    override fun onItemClick(view: View, any: Any, index: Int) {
        navigateToDetail(index)
    }
    private fun navigateToDetail(index: Int){
        val b = Bundle()
        b.putInt(LIST_INDEX,index)
        findNavController().navigate(R.id.bookDetailFragment,b)
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.getviewStateFlow().collect{ viewState->
                when(viewState){
                    is ViewState.Loading ->
                        showProgressBar()
                    is ViewState.Success -> {
                        setupRecyclersView(viewState.result)
                    }
                    is ViewState.Failure -> {
                        hideProgressBar()
                        viewState.failMessage.let {
                            it.showCustomToast(requireContext())
                        }
                    }
                }
            }
        }
    }
}