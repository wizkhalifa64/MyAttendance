package com.example.myattendance.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.example.myattendance.CreateOrganizationMutation
import com.example.myattendance.apolloClient
import com.example.myattendance.type.OrganizationRegister

class AuthRepository {
    suspend fun OrganizationRegisterService(
        email: String, password: String, location: Map<String, String>, name: String
    ): ApolloResponse<CreateOrganizationMutation.Data> {
        val value: Int? = if (location["value"] == null) 0 else location["value"]?.toInt()
        val locationData = if (value !== null) value else 0
        return apolloClient.mutation(
            CreateOrganizationMutation(
                body = OrganizationRegister(
                    name, email, password, locationData
                )
            )
        ).execute()
    }
}