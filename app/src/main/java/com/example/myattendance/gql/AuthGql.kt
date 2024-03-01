package com.example.myattendance.gql

import android.util.Log
import com.apollographql.apollo3.exception.ApolloException
import com.example.myattendance.LoginOrganizationQuery
import com.example.myattendance.apolloClient
import com.example.myattendance.type.OrganizationLogin

private suspend fun Login(email: String): Boolean {
    val response = try {
        apolloClient.query(
            LoginOrganizationQuery(
                body = OrganizationLogin(
                    email = email, password = "Ujjwal1031997*"
                )
            )
        )
    } catch (e: ApolloException) {


        return false

    }

    return true
}