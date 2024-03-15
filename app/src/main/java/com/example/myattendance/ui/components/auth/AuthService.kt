package com.example.myattendance.ui.components.auth

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.myattendance.CreateOrganizationMutation
import com.example.myattendance.apolloClient
import com.example.myattendance.type.OrganizationRegister

class AuthService {
    suspend fun register(
        email: String, password: String, location: Map<String, String>, name: String
    ): ApolloResponse<CreateOrganizationMutation.Data> {
        val value: Int? = location["value"]?.toInt()
        return apolloClient.mutation(
            CreateOrganizationMutation(
                body = OrganizationRegister(
                    name, email, password, value!!
                )
            )
        ).execute()
    }
}