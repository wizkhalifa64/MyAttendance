package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://cbc7-2402-3a80-413c-a04-5a54-ae79-5752-f0ce.ngrok-free.app/rust-graphql")
    .build()