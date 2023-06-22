//package com.witzeal.pagination.data.repository.model
//
//import com.witzeal.pagination.domain.model.User
//
//
//data class UserDTO(
//    val rank: Int,
//    val profile_pic_URL: String,
//    val user_name : String,
//    val user_point:Int,
//    val price_money: Int){
//
//    fun toBook():List<User>{
//        val bookArray : ArrayList<User> = ArrayList(items.size)
//        for (item in items){
//            item.volumeInfo?.title.let {
//                val thumbNail = item.volumeInfo?.imageLinks?.thumbnail ?: ""
//                bookArray.add(User(item.id,item.volumeInfo?.title!!,thumbNail))
//            }
//        }
//        return bookArray
//    }
//}
