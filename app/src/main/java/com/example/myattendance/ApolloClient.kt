package com.example.myattendance

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://95cb-2402-3a80-4330-1cee-3fd8-cf92-2b54-437f.ngrok-free.app/rust-graphql")
    .build()