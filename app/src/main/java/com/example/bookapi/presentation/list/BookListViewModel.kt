package com.example.bookapi.presentation.list

import android.content.Context
import android.view.View
import android.widget.ImageButton
import com.example.bookapi.R
import com.example.bookapi.domain.model.Book
import com.example.bookapi.domain.model.NetworkResult
import com.example.bookapi.domain.usecase.UseCase
import com.example.bookapi.presentation.core.ViewState
import com.example.bookapi.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(private val useCase: UseCase, private val context: Context) :
    BaseViewModel() {

    private val uiStateFlow = MutableStateFlow<ViewState<List<Book>>>(ViewState.Loading(true))
    fun getviewStateFlow(): StateFlow<ViewState<List<Book>>> = uiStateFlow


    fun fetchList() {
        performCoroutineTask{
            useCase.getListUseCase().collect { result ->
                uiStateFlow.emit(when (result) {
                    is NetworkResult.Success -> ViewState.Success(result.data)
                    is NetworkResult.Failure -> ViewState.Failure(context.getString(R.string.faild_to_retrive))
                })
            }
        }
    }



    fun handleFavoriteBook(view: View, book: Book) {
        performCoroutineTask {
            val button: ImageButton = view as ImageButton
            val isCurrentlyFav = book.isFav
           if (isCurrentlyFav) {
                useCase.markFavUseCase.removeBookFromFavorites(book)
                button.setImageResource(R.drawable.ic_favorite_border)
            } else {
                useCase.markFavUseCase.setBookFavorite(book)
                button.setImageResource(R.drawable.ic_favorite)
            }
            book.isFav = !isCurrentlyFav
        }
    }

//    private suspend fun getAllFavouriteBooks(): List<Book> {
//        return suspendCancellableCoroutine { sc ->
//            dispatchOnIO {
//                bookListUseCase.getAllFavBooksUseCase().collect {
//                    if (sc.isActive) sc.resume(it)
//                }
//            }
//        }
//    }

    fun isFavoriteBook(book: Book, callback: (Boolean) -> Unit) {
        performCoroutineTask {
            var result = useCase.markFavUseCase.isFavoriteBook(book)
            println("handleFav VM " + result)
            callback(result)
        }
    }
}