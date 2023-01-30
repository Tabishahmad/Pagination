package com.example.bookapi.hilt

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BookListMappingAnnotation

//@Retention(AnnotationRetention.BINARY)
//@Qualifier
//annotation class CoroutineScopeIO
//
//@Retention(AnnotationRetention.BINARY)
//@Qualifier
//annotation class CoroutineScopeDefault