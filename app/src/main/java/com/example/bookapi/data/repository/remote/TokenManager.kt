//package com.example.bookapi.data.repository.remote
//
//import android.content.Context
//import android.content.SharedPreferences
//import dagger.hilt.android.qualifiers.ApplicationContext
//import javax.inject.Inject
//
//class TokenManager @Inject constructor(@ApplicationContext context: Context) {
//    val USER_TOKEN = "user_token"
//    val PREFS_TOKEN_FILE = "prefs_token_file"
//    private var prefs: SharedPreferences =
//        context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)
//
//    fun saveToken(token: String) {
//        val editor = prefs.edit()
//        editor.putString(USER_TOKEN, token)
//        editor.apply()
//    }
//
//    fun getToken(): String? {
//        return prefs.getString(USER_TOKEN, null)
//    }
//}