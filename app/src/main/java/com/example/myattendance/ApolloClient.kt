package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("http://127.0.0.1:8000/rust-graphql")
    .build()