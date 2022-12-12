package fr.com.example.proj_allart_florentin.model

sealed class MyTelephoneForRecyclerView()

data class TelephoneDataHeaderSample(
    val header: String
) : MyTelephoneForRecyclerView()

data class TelephoneDataSample(
    val nom: String,
    val marque: String,
    val img: String
) : MyTelephoneForRecyclerView()

data class TelephoneDataFooterSample(
    val footer: String
) : MyTelephoneForRecyclerView()