package com.example.myattendance.gql

import android.util.Log
import com.apollographql.apollo3.exception.ApolloException
import com.example.myattendance.RootQuery
import com.example.myattendance.apolloClient
import com.example.myattendance.type.OrganizationLogin

suspend fun LoginFunction(email: String, password: String): Boolean {
    try {
        Log.w("Error", "starting")
       val res = apolloClient.query(RootQuery(OrganizationLogin(email, password))).execute()
        res.data?.loginOrganization?.let { Log.e("data", it.token) }
    } catch (e: ApolloException) {
        Log.w("Error", e)
        return false
    }
    return true
}