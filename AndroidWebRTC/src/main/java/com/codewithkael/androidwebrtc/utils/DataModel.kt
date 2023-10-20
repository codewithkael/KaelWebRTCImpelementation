package com.codewithkael.androidwebrtc.utils

enum class DataModelType {
    StartCall,Offer,Answer,IceCandidates,EndCall
}
data class DataModel(
    val type:DataModelType,
    val data:String?=null,
    val timeStamp:Long = System.currentTimeMillis()
)


fun DataModel.isValid(): Boolean {
    return System.currentTimeMillis() - this.timeStamp < 60000
}