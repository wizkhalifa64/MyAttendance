package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl(" https://35f8-182-68-211-10.ngrok-free.app/rust-graphql")
    .build()