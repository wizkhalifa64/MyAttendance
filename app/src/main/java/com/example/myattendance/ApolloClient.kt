package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://8683-2402-3a80-4330-6da8-ecec-16c9-e358-50dd.ngrok-free.app/rust-graphql")
    .build()