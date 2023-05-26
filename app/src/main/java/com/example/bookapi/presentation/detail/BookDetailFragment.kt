package com.example.bookapi.presentation.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.bookapi.R
import com.example.bookapi.common.LIST_INDEX
import com.example.bookapi.databinding.FragmentBookDetailBinding
import com.example.bookapi.domain.model.Book
import com.example.bookapi.presentation.core.base.BaseFragment
import com.example.bookapi.presentation.core.ViewState
import com.example.bookapi.presentation.list.BookListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<BookListViewModel, FragmentBookDetailBinding>(
    R.layout.fragment_book_detail
) {
    override val viewModel: BookListViewModel by viewModels()

    private var currentIndex: Int = 0
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentBookDetailBinding.inflate(inflater, container, false)
////        binding : FragmentBookDetailBinding  = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_book_detail)
//        binding.viewModel = viewModel
//        arguments?.getInt(LIST_INDEX)?.let {
//            currentIndex = it
//            viewModel.fetchList()
//            println("handleFav observeViewModel")
//            observeViewModel()
//        }
//        return binding.root
//    }

    override fun init() {
        arguments?.getInt(LIST_INDEX)?.let {
            currentIndex = it
            viewModel.fetchList()
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.getviewStateFlow().collect { viewState ->
                when (viewState) {
                    is ViewState.Success -> {
                        println("handleFav ViewState.Success")
                        handleResponseSuccess(viewState.result.get(currentIndex))
                    }
                    is ViewState.Failure -> {
                        println("handleFav Failure")
                    }
                    else -> {}
                }
            }
        }
    }

    private fun handleResponseSuccess(book: Book) {
        binding.book = book
        println("handleFav called")
        setFavoriteBookResource(book)
        setText(book.thumbnailUrl)
    }

    private fun setText(string: String) {
        binding.textView.setText(string)
    }

    private fun setFavoriteBookResource(book: Book) {
        viewModel.isFavoriteBook(book) { boolean ->
            println("handleFav " + boolean)
            val imageRes = if (boolean) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            binding.favoriteButton.setImageResource(imageRes)
        }
    }

}