package com.example.myattendance.gql

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.myattendance.LoginOrganizationQuery
import com.example.myattendance.apolloClient
import com.example.myattendance.type.OrganizationLogin

suspend fun loginFunction(
    email: String,
    password: String
): ApolloResponse<LoginOrganizationQuery.Data> {
    val response = try {
        apolloClient.query(LoginOrganizationQuery(OrganizationLogin(email, password))).execute()
    } catch (e: ApolloException) {
        throw e
    }
    return response
}