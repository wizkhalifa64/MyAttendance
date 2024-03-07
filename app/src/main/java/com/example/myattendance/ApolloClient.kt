package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://65db-223-182-172-34.ngrok-free.app/rust-graphql")
    .build()