//package com.witzeal.pagination.presentation.detail
//
//import androidx.fragment.app.viewModels
//import com.witzeal.pagination.R
//import com.witzeal.pagination.common.LIST_INDEX
//import com.witzeal.pagination.common.hide
//import com.witzeal.pagination.common.show
//import com.witzeal.pagination.databinding.FragmentBookDetailBinding
//import com.witzeal.pagination.domain.model.User
//import com.witzeal.pagination.presentation.core.ViewState
//import com.witzeal.pagination.presentation.core.base.BaseFragment
//import com.witzeal.pagination.presentation.list.BookListViewModel
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class BookDetailFragment : BaseFragment<BookListViewModel, FragmentBookDetailBinding>(
//    R.layout.fragment_book_detail
//) {
//    override val viewModel: BookListViewModel by viewModels()
//    private var currentIndex: Int = 0
//
//    override fun init() {
//        arguments?.getInt(LIST_INDEX)?.let {
//            currentIndex = it
//            viewModel.fetchList()
//        }
//    }
//    private fun hideProgressBar(){
//        binding.progressBar.hide()
//    }
//    private fun showProgressBar(){
//        binding.progressBar.show()
//    }
//    override fun observeViewModel() {
//        performCoroutineTask {
//            viewModel.getviewStateFlow().collect { viewState ->
//                when (viewState) {
//                    is ViewState.Success -> {
//                        hideProgressBar()
//                        println("handleFav ViewState.Success")
//                        handleResponseSuccess(viewState.result.get(currentIndex))
//                    }
//                    is ViewState.Failure -> {
//                        hideProgressBar()
//                        println("handleFav Failure")
//                    }
//                    else -> {}
//                }
//            }
//        }
//    }
//
//    private fun handleResponseSuccess(book: User) {
//        binding.book = book
//        setFavoriteBookResource(book)
////        setText(book.thumbnailUrl)
//    }
//
//    private fun setText(string: String) {
//        binding.textView.setText(string)
//    }
//
//    private fun setFavoriteBookResource(book: User) {
////        viewModel.isFavoriteBook(book) { boolean ->
////            println("handleFav " + boolean)
////            val imageRes = if (boolean) R.drawable.ic_favorite else R.drawable.ic_favorite_border
////            binding.favoriteButton.setImageResource(imageRes)
////        }
//    }
//
//}