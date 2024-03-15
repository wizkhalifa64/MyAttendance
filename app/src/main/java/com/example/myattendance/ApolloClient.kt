package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://7193-2402-3a80-4330-4939-e2c8-53a2-21a9-6fd5.ngrok-free.app")
    .build()