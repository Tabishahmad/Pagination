//package com.example.bookapi.presentation
//
//import androidx.lifecycle.ViewModel
//import com.example.bookapi.domain.model.NetworkResult
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.flow.*
//
//open class BaseViewModel(
//    private val dispatchers: CoroutineDispatcher
//) : ViewModel() {
//    suspend fun <T : Any> getViewStateFlowFromResponse(
//        response: Flow<NetworkResult<T>>,
//    ) : Flow<ViewState<T>> {
//        return flow<ViewState<T>> {
//            emit(ViewState.Loading(true))
//            response.map {
//                when(it){
//                    is NetworkResult.Success ->
//                        ViewState.Success(it.data)
//                    is NetworkResult.Failure ->
//                        ViewState.Failure(it.throwable)
//                }
//            }.collect{
//
//            }
//            emit(ViewState.Loading(false))
//        }.flowOn(dispatchers)
//    }
//}
//
//
