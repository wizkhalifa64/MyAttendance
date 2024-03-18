package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://33d0-122-182-135-124.ngrok-free.app/rust-graphql")
    .build()