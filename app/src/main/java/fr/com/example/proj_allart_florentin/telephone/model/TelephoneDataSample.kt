package fr.com.example.proj_allart_florentin.telephone.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class MyTelephoneForRecyclerView()

data class TelephoneDataHeaderSample(
    val header: String
) : MyTelephoneForRecyclerView()

data class TelephoneDataSample(
    val nom: String,
    val marque: String,
    val img: String


) : MyTelephoneForRecyclerView()

@Entity(tableName = "telephone_object_table")
data class LocalDataSourceSample(
    @ColumnInfo(name = "nom")
    val nom: String,
    @ColumnInfo(name = "marque")
    val marque: String,
    @ColumnInfo(name = "img")
    val img: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


private fun TelephoneDataSample.toRoomObject(): LocalDataSourceSample {
    return LocalDataSourceSample(
        nom = nom,
        marque = marque,
        img = img
    )
}

private fun List<LocalDataSourceSample>.toTelephoneDataSample(): List<TelephoneDataSample> {
    return map { eachItem ->
        TelephoneDataSample(
            nom = eachItem.nom,
            marque = eachItem.marque,
            img = eachItem.img
        )
    }
}


    data class TelephoneDataFooterSample(
        val footer: String
    ) : MyTelephoneForRecyclerView()