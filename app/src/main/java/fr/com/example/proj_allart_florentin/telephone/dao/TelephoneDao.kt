package fr.com.example.proj_allart_florentin.telephone.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.com.example.proj_allart_florentin.telephone.model.LocalDataSourceSample

@Dao
interface TelephoneDao {

    @Query("SELECT * FROM telephone_object_table ORDER BY nom ASC")
    fun selectAll(): LiveData<List<LocalDataSourceSample>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(telephone: LocalDataSourceSample)

    @Query("DELETE FROM telephone_object_table")
     fun deleteAll()
}
