package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://informed-multiply-skunk.ngrok-free.app/rust-graphql")
    .build()