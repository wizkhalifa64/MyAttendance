package com.example.myattendance.ui.components.auth

import com.apollographql.apollo3.api.ApolloResponse
import com.example.myattendance.CreateOrganizationMutation
import com.example.myattendance.LoginEmployeeQuery
import com.example.myattendance.LoginOrganizationQuery
import com.example.myattendance.apolloClient
import com.example.myattendance.type.EmployeeLoginInput
import com.example.myattendance.type.OrganizationLogin
import com.example.myattendance.type.OrganizationRegister

class AuthService {
    suspend fun register(
        email: String, password: String, location: Map<String, String>, name: String
    ): ApolloResponse<CreateOrganizationMutation.Data> {
        val value: Int? = if (location["value"] == null) 0  else location["value"]?.toInt()
        val locationData = if (value !== null) value else 0
        return apolloClient.mutation(
            CreateOrganizationMutation(
                body = OrganizationRegister(
                    name, email, password, locationData
                )
            )
        ).execute()
    }

    suspend fun organizationLogin(
        email: String, password: String
    ): ApolloResponse<LoginOrganizationQuery.Data> {
        return apolloClient.query(
            LoginOrganizationQuery(
                body = OrganizationLogin(email, password)
            )
        ).execute()
    }

    suspend fun employeeLogin(
        email: String, password: String
    ): ApolloResponse<LoginEmployeeQuery.Data> {
        return apolloClient.query(
            LoginEmployeeQuery(
                body = EmployeeLoginInput(email, password)
            )
        ).execute()
    }
}