package com.example.bookapi.data.datamapper

import com.example.bookapi.domain.model.Book

class RoomNRemoteMerger {
    fun checkListNMarkFavorite(roomList: List<Book>,remoteList:List<Book>):List<Book>{
        var hashSet : HashSet<String> = HashSet(0)
        for (savedBook in roomList){
            hashSet.add(savedBook.bookHashId)
        }
        for (remoteBook in remoteList){
            if(hashSet.contains(remoteBook.bookHashId)){
                remoteBook.isFav = true
            }
        }
        return remoteList
    }
}