package com.example.animal_shelter

import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animal_shelter.Screens.*
import com.example.animal_shelter.busines.Pet
import com.example.animal_shelter.ui.theme.Animal_ShelterTheme
import com.example.animal_shelter.ui.theme.Purple200
import com.example.animal_shelter.ui.theme.Purple700
import com.example.animal_shelter.viewModels.LoginViewModel
import com.example.animal_shelter.viewModels.repository

// modifier = Modifier.fillMaxWidth()
//        .padding(16.dp)
//        .border(2.dp, MaterialTheme.colors.secondary, shape)
//        .background(MaterialTheme.colors.primary, shape)
//        .padding(16.dp)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            MyApp(modifier = Modifier.fillMaxSize())

        }
    }

}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    Animal_ShelterTheme {
        NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
            composable(route = Screen.MainScreen.route)
            {
                var IsLogged by rememberSaveable { mutableStateOf(true) }
                Surface(modifier) {
                    if (!IsLogged) {
                        logginscreen(onLoginClicked = { IsLogged = true })
                    } else {

                        Greetings(
                            onLoginClicked = { IsLogged = false },
                            nav = navController
                        )

                    }
                }
            }
            composable(
                route = Screen.DetailScreen.route + "/{id}",
                arguments = listOf(
                    navArgument("id")
                    {
                        type = NavType.StringType
                        defaultValue = "1"

                    }
                )
            ) { entry ->
                Surface(modifier) {
                    Update(id = entry.arguments?.getString("id"),nav=navController)
                }


            }

            composable(
                route = Screen.AddScreen.route
            )
            {
                Surface(modifier) {
                    add(nav= navController)
                }
            }

        }
    }

}


@Composable
private fun Greetings(
    repository: repository = viewModel(),
    modifier: Modifier = Modifier,
    onLoginClicked: () -> Unit,
    nav: NavController
) {


    Box() {


        LazyColumn(modifier = modifier.padding(vertical = 4.dp)
        ) {
            items(repository.getPets()) { pet ->
                Greeting(pet=pet, nav)
            }

        }

        FloatingActionButton(
            onClick = { nav.navigate(Screen.AddScreen.route) },
            modifier = modifier //add
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomCenter),
            //backgroundColor = Purple700
        )
        {
            Icon(imageVector = Icons.Default.Add, contentDescription = "icon")
        }
        FloatingActionButton( //back
            onClick = onLoginClicked,
            modifier = modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd),
            //backgroundColor = Purple700
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "icon")
        }
    }
}


@Composable
private fun Greeting(pet: Pet, nav: NavController) {
    Card(

        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {

        Details(pet, nav)
        // CardContent(name)
    }
}

@Composable
fun Details(
    pet: Pet, nav: NavController,
    repository: repository = viewModel()
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text("name:" + pet.name)
            Text("age:" + pet.age)
            Text("species:" + pet.species)
            if (expanded) {
                Text(
                    "medical_records: " + pet.medical_records
                )
            }
        }
        Button(
            onClick = {
                expanded = !expanded
                nav.navigate(Screen.DetailScreen.withArgs(pet.id.toString()))
            }
        ) {
            Text(if (expanded) "Show less" else "Show more")
        }
    }


}

@Composable
private fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name, //style = MaterialTheme.typography.headlineMedium.copy(
                // fontWeight = FontWeight.ExtraBold
                // )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(1),
                )
            }
        }
        Button(
            onClick = { expanded = !expanded }
        ) {
            Text(if (expanded) "Show less" else "Show more")
        }
    }
}





