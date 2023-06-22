package com.witzeal.pagination.presentation.favourite

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.witzeal.pagination.R
import com.witzeal.pagination.common.hide
import com.witzeal.pagination.common.show
import com.witzeal.pagination.common.showCustomToast
import com.witzeal.pagination.databinding.FragmentBookFavouriteBinding
import com.witzeal.pagination.domain.model.User
import com.witzeal.pagination.presentation.core.base.BaseFragment
import com.witzeal.pagination.presentation.list.BookListViewModel
import com.witzeal.pagination.presentation.list.ImageListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavouriteBooksFragment : BaseFragment<BookListViewModel,FragmentBookFavouriteBinding>(
    R.layout.fragment_book_favourite
),ImageListAdapter.ItemClickListener {
    override val viewModel: BookListViewModel by activityViewModels()

    override fun init() {
        viewModel.fetchList()
    }

    private fun hideProgressBar(){
        binding.progressBar.hide()
    }

    private fun setupRecyclersView(list:List<User>){
        hideProgressBar()
        if(list.isEmpty()){
            binding.rv.hide()
            binding.nodata.show()
        }else {
            binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.rv.setData(list)
            binding.rv.setItemClickListener(this)
        }
    }

    override fun onItemClick(view: View, any: Any, index: Int) {
        requireContext().getString(R.string.not_implemented).showCustomToast(requireContext(),Toast.LENGTH_LONG)
    }


    override fun observeViewModel() {
//        lifecycleScope.launch {
//            viewModel.getAllFavouriteBooks().collect{
//                setupRecyclersView(it)
//            }
//        }
    }
}