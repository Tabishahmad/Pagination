package com.witzeal.pagination.data.repository.remote

import android.content.Context
import com.witzeal.pagination.R
import com.witzeal.pagination.domain.model.User
import com.witzeal.pagination.domain.model.NetworkResult
import com.witzeal.pagination.domain.repository.BookListRepository
import javax.inject.Inject

class BookListRepositoryImpl @Inject
        constructor(private val context: Context): BookListRepository {

    override suspend fun getBookList(offset : Int): NetworkResult<User> {
        println("getBookList(): NetworkResult<Book>")
        return NetworkResult.Success(createMockUserList(offset) ?: emptyList())
    }
    fun createMockUserList(offset : Int): List<User> {
        val userList = mutableListOf<User>()
        val start = offset
        val end = offset + 50
        for (i in start..end) {
            val user = User(
                rank = i,
                profile_pic_URL = "https://st4.depositphotos.com/20523356/22445/v/450/depositphotos_224458104-stock-illustration-flat-user-icon-website-face.jpg",
                user_name = "User$i",
                user_point = i + 98000,
                price_money = i * 200
            )
            userList.add(user)
        }

        return userList
    }


}