package fr.com.example.proj_allart_florentin.telephone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.com.example.proj_allart_florentin.telephone.model.MyTelephoneForRecyclerView
import fr.com.example.proj_allart_florentin.telephone.model.TelephoneDataSample
import fr.com.example.proj_allart_florentin.telephone.repository.TelephoneRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.map
import fr.com.example.proj_allart_florentin.telephone.model.TelephoneDataFooterSample
import fr.com.example.proj_allart_florentin.telephone.model.TelephoneDataHeaderSample
import kotlinx.coroutines.launch

class TelephoneViewModel : ViewModel() {

    private val telephoneRepository: TelephoneRepository by lazy { TelephoneRepository() }
//    private val _telephoneList = MutableLiveData<List<MyTelephoneForRecyclerView>>()
//    val _telephoneList: LiveData<List<MyTelephoneForRecyclerView>> get() = _telephoneList
//    init {
//        _telephoneList.postValue(telephoneRepository.generateFakeData())
//    }


    val telephoneList: LiveData<List<MyTelephoneForRecyclerView>> =
        telephoneRepository.selectAllTelephone().map { list ->
            list.toMyTelephoneForRecyclerView()
        }


     fun insertTelephone(nom: String, marque: String, img: String) {
        viewModelScope.launch(Dispatchers.IO) {
            telephoneRepository.insertTelephone(
                TelephoneDataSample(nom, marque, img)
            )
        }
    }

     fun deleteAllTelephone() {
        viewModelScope.launch(Dispatchers.IO) {
            telephoneRepository.deleteAllTelephone()
        }
    }

    private fun List<TelephoneDataSample>.toMyTelephoneForRecyclerView(): List<MyTelephoneForRecyclerView> {
        val result = mutableListOf<MyTelephoneForRecyclerView>()

        groupBy {

            it.marque
        }.forEach { (marque, items) ->
            // For each mean for each list split
            // Here we have a map (key = isModulo) and each key have a list of it's items
            result.add(TelephoneDataHeaderSample(marque))
            result.addAll(items)
            result.add(TelephoneDataFooterSample(items.size.toString()))
        }
        return result
    }

}
