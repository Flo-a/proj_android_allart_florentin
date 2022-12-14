package fr.com.example.proj_allart_florentin.telephone.repository;

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fr.com.example.proj_allart_florentin.telephone.architecture.CustomApplication
import fr.com.example.proj_allart_florentin.telephone.model.LocalDataSourceSample
import fr.com.example.proj_allart_florentin.telephone.model.TelephoneDataSample


class TelephoneRepository {

//    fun generateFakeData(): MutableList<MyTelephoneForRecyclerView> {
//        val result = mutableListOf<MyTelephoneForRecyclerView>()
//        // Create data raw
//        mutableListOf(
//            TelephoneDataSample("Google pixel 6", "Google", "https://picsum.photos/200"),
//            TelephoneDataSample("Google pixel 5", "Google", "https://picsum.photos/200"),
//            TelephoneDataSample("Google pixel 6 Pro", "Google", "https://picsum.photos/200"),
//            TelephoneDataSample("Google pixel 4", "Google", "https://picsum.photos/200"),
//            TelephoneDataSample("Google pixel 3", "Google", "https://picsum.photos/200"),
//            TelephoneDataSample("Google pixel 2", "Google", "https://picsum.photos/200"),
//            TelephoneDataSample("Google pixel", "Google", "https://picsum.photos/200"),
//            TelephoneDataSample("Google pixel 6a", "Google", "https://picsum.photos/200"),
//            TelephoneDataSample("Samsung Galaxy S21", "Samsung", "https://picsum.photos/200"),
//            TelephoneDataSample("Samsung Galaxy S22", "Samsung", "https://picsum.photos/200"),
//            TelephoneDataSample("Xiaomi 4", "Xiaomi", "https://picsum.photos/200"),
//            TelephoneDataSample("Xiaomi 5", "Xiaomi", "https://picsum.photos/200"),
//        ).groupBy {
//            it.marque
//        }.forEach { (marque, items) ->
//            result.add(TelephoneDataHeaderSample(" $marque"))
//            result.addAll(items)
//            result.add(TelephoneDataFooterSample(items.size.toString()))
//        }
//        return result
//    }


    private val mTelephoneDao =
        CustomApplication.instance.mApplicationDatabase.telephoneDao()

    fun selectAllTelephone(): LiveData<List<TelephoneDataSample>> {
        return mTelephoneDao.selectAll().map { list ->
            list.toTelephoneDataSample()
        }
    }

    fun insertTelephone(telephoneDataSample: TelephoneDataSample) {
        mTelephoneDao.insert(telephoneDataSample.toRoomObject())
    }

    fun deleteAllTelephone() {
        mTelephoneDao.deleteAll()
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
}


