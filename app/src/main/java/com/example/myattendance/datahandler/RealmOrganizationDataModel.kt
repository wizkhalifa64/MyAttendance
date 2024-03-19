package com.example.myattendance.datahandler

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.time.LocalDateTime

class RealmOrganizationDataModel() : RealmObject {
    @PrimaryKey
    var id: String = ""
    var orgName: String = ""
    var orgEmail: String = ""
    var isAdmin: Boolean = false
    var orgLocation: Int = 0
    var lastSubscribe: LocalDateTime? = null
}