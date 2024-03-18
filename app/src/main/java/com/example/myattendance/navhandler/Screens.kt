package com.example.myattendance.navhandler

sealed class Screens(val route: String){
    data object Auth: Screens("auth_screen/")
    data object Home: Screens("home_screen")
    data object Index: Screens("index")
}
