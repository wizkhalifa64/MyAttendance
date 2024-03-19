package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://aeaa-122-173-183-164.ngrok-free.app/rust-graphql")
    .build()