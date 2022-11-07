package com.example.animal_shelter.Screens

sealed class Screen(val route:String)
{


    object MainScreen: Screen("main_screen")
    object DetailScreen: Screen("detail_screen")
    object AddScreen: Screen("add_screen")

    fun withArgs(vararg args: String): String{
        val a = buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
        return a
    }
}
