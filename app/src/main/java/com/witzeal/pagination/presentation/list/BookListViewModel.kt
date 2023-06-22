package com.witzeal.pagination.presentation.list

import android.content.Context
import android.view.View
import android.widget.ImageButton
import com.witzeal.pagination.R
import com.witzeal.pagination.domain.model.User
import com.witzeal.pagination.domain.model.NetworkResult
import com.witzeal.pagination.domain.usecase.UseCase
import com.witzeal.pagination.presentation.core.ViewState
import com.witzeal.pagination.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val useCase: UseCase,
    private val context: Context
) :
    BaseViewModel() {
    private var isFetchingPreviousData = false
    private val uiStateFlow = MutableStateFlow<ViewState<List<User>>>(ViewState.Loading(true))
    fun getviewStateFlow(): StateFlow<ViewState<List<User>>> = uiStateFlow


    fun fetchList() {
        performCoroutineTask {
            useCase.getListUseCase(0).collect { result ->
                uiStateFlow.emit(
                    when (result) {
                        is NetworkResult.Success -> ViewState.Success(result.data)
                        is NetworkResult.Failure -> ViewState.Failure(context.getString(R.string.faild_to_retrive))
                    }
                )
            }
        }
    }


    //    fun handleFavoriteBook(view: View, book: User) {
//        performCoroutineTask {
//            val button: ImageButton = view as ImageButton
//            val isCurrentlyFav = book.isFav
//            book.isFav = !isCurrentlyFav
//            if (isCurrentlyFav) {
//                useCase.manageBookUseCase.removeBookFromFavorites(book)
//                button.setImageResource(R.drawable.ic_favorite_border)
//            } else {
//                useCase.manageBookUseCase.setBookFavorite(book)
//                button.setImageResource(R.drawable.ic_favorite)
//            }
//        }
//    }
    fun fetchPreviousUsersData(offset : Int) {
        if (isFetchingPreviousData) {
            // Already fetching data, return or show an error message
            return
        }
       isFetchingPreviousData = true
        performCoroutineTask {
            useCase.getListUseCase(offset).collect { result ->
                isFetchingPreviousData = false
                uiStateFlow.emit(
                    when (result) {
                        is NetworkResult.Success -> ViewState.Success(result.data)
                        is NetworkResult.Failure -> ViewState.Failure(context.getString(R.string.faild_to_retrive))
                    }
                )
            }
        }
    }

    fun fetchNextUsersData(offset : Int) {
        if (isFetchingPreviousData) {
            // Already fetching data, return or show an error message
            return
        }
        isFetchingPreviousData = true
        performCoroutineTask {
            useCase.getListUseCase(offset).collect { result ->
                isFetchingPreviousData = false
                uiStateFlow.emit(
                    when (result) {
                        is NetworkResult.Success -> ViewState.Success(result.data)
                        is NetworkResult.Failure -> ViewState.Failure(context.getString(R.string.faild_to_retrive))
                    }
                )
            }
        }
    }
}