package com.witzeal.pagination.domain.usecase

data class UseCase(val getListUseCase: GetRemoteListUseCase,
                   val manageBookUseCase: ManageBookFavoriteUseCase) {
}