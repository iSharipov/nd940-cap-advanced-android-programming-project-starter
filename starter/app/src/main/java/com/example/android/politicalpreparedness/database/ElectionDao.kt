package com.example.android.politicalpreparedness.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android.politicalpreparedness.network.models.Election

@Dao
interface ElectionDao {

    //TODO: Add insert query
    @Insert
    fun save(election: Election)
    //TODO: Add select all election query

    @Query("SELECT * FROM election_table")
    fun getAll(): List<Election>
    //TODO: Add select single election query

    @Query("SELECT * FROM election_table WHERE id = :id ")
    fun getById(id: Int): Election?
    //TODO: Add delete query
    @Delete
    fun delete(election: Election)
    //TODO: Add clear query

}