package com.example.bookapi.data.database


import org.junit.*
import org.mockito.Mock
import javax.inject.Inject
import javax.inject.Named


class MarkFavouriteBookTest {

    @Inject
    @Named("test_db")
    private lateinit var favouriteBookDUO: FavouriteBookDUO

    @Mock
    private lateinit var favouriteBookDatabase: FavouriteBookDatabase

    @Before
    fun setUp(){
        favouriteBookDUO = favouriteBookDatabase.favouriteBookDUO()
    }
//    @Test
//    fun insertBook() = runBlocking{
//        val book = Book("A1B2","Test Book","http://www.example.com")
//        favouriteBookDUO.markFavouriteBook(book)
//        val result = favouriteBookDUO.getAllFavouriteBook()
//        result.collect{
//            Assert.assertEquals(1, it.size)
//        }
//    }

    @After
    fun closeDatabase() {
        favouriteBookDatabase.close()
    }
}