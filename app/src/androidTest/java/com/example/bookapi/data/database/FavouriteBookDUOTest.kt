package com.example.bookapi.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookapi.domain.model.Book
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class FavouriteBookDUOTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    private lateinit var favouriteBookDUO: FavouriteBookDUO
    @Inject
    private lateinit var favouriteBookDatabase: FavouriteBookDatabase

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        favouriteBookDUO = favouriteBookDatabase.favouriteBookDUO()
    }

    @Test
    fun insertBook() = runBlocking{
        val book = Book("A1B2","Test Book","http://www.example.com")
        favouriteBookDUO.markFavouriteBook(book)
        val result = favouriteBookDUO.getAllFavouriteBook()
        result.collect{
            Assert.assertEquals(1, it.size)
        }
    }

    @After
    fun closeDatabase() {
        favouriteBookDatabase.close()
    }
}