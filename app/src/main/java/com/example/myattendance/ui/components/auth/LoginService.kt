package com.example.myattendance.ui.components.auth

import com.example.myattendance.CreateOrganizationMutation
import com.example.myattendance.apolloClient
import com.example.myattendance.type.OrganizationRegister

class LoginService {
    fun register(email:String,password:String,location:Map<String,String>,name:String){
//        val response = try {
//            val location =if (location["value"]!== null) location["value"]?.toInt() else 0;
//            apolloClient.mutation(CreateOrganizationMutation(body = OrganizationRegister(name,email,password,location)))
//        }catch ()
    }
}