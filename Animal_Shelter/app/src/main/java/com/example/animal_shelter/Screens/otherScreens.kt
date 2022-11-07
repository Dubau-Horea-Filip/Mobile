package com.example.animal_shelter.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.animal_shelter.ui.theme.Purple700
import com.example.animal_shelter.viewModels.LoginViewModel
import com.example.animal_shelter.viewModels.repository
import com.example.crudnative.ui.viewmodels.UpdateViewModel


@Composable
fun add(
    repository: repository = viewModel(),
    nav: NavController,
) {

    var nameHasError by remember { mutableStateOf(false) }
    var nameLabel by remember { mutableStateOf("name") }

    var ageHasError by remember { mutableStateOf(false) }
    var ageLabel by remember { mutableStateOf("age") }

    var speciesHasError by remember { mutableStateOf(false) }
    var speciesLabel by remember { mutableStateOf("species") }

    var behaviourHasError by remember { mutableStateOf(false) }
    var behaviourLabel by remember { mutableStateOf("behaviour") }

    var mrHasError by remember { mutableStateOf(false) }
    var mrLabel by remember { mutableStateOf("medical records") }

    Box(
        modifier = Modifier.fillMaxWidth()//, horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            OutlinedTextField(
//                value = repository.IdInput,
//                label = { Text(text = "id") },
//                placeholder = { Text(text = "id") },
//                onValueChange = { newText: TextFieldValue ->
//                    repository.IdInput = newText
//                }, //textStyle = TextStyle(Purple200, background = Purple700)
//            )

            OutlinedTextField(
                value = repository.NameInput,
                isError = nameHasError,
                label = { Text(text = nameLabel) },
                placeholder = { Text(text = "Name") },
                onValueChange = { newText: TextFieldValue ->
                    repository.NameInput = newText
                }, //textStyle = TextStyle(Purple200, background = Purple700)
            )
            OutlinedTextField(
                value = repository.AgeInput,
                isError = ageHasError,
                label = { Text(text = ageLabel) },
                placeholder = { Text(text = "age") },
                onValueChange = { newText: TextFieldValue ->
                    repository.AgeInput = newText
                }, //textStyle = TextStyle(Purple200, background = Purple700)
            )
            OutlinedTextField(
                value = repository.SpeciesInput,
                isError = speciesHasError,
                label = { Text(text = speciesLabel) },
                //placeholder = { Text(text = "age") },
                onValueChange = { newText: TextFieldValue ->
                    repository.SpeciesInput = newText
                }, //textStyle = TextStyle(Purple200, background = Purple700)
            )
            OutlinedTextField(
                value = repository.BehaviourInput,
                isError = behaviourHasError,
                label = { Text(text = behaviourLabel) },
                //placeholder = { Text(text = "age") },
                onValueChange = { newText: TextFieldValue ->
                    repository.BehaviourInput = newText
                }, //textStyle = TextStyle(Purple200, background = Purple700)
            )
            OutlinedTextField(
                value = repository.MRInput,
                isError = mrHasError,
                label = { Text(text = mrLabel) },
                //placeholder = { Text(text = "age") },
                onValueChange = { newText: TextFieldValue ->
                    repository.MRInput = newText
                }, //textStyle = TextStyle(Purple200, background = Purple700)
            )
        }

        FloatingActionButton(
            onClick = {// validation
                if (!repository.AgeInput.text.isDigitsOnly()) {
                    ageHasError = true
                    ageLabel = "age should be a number"
                } else
                    when {
                        repository.NameInput.text.isEmpty() -> {
                            nameHasError = true
                            nameLabel = "Name cannot be empty"
                        }
                        repository.AgeInput.text.isEmpty() -> {
                            ageHasError = true
                            ageLabel = "age cannot be empty"
                        }


                        repository.SpeciesInput.text.isEmpty() -> {
                            speciesHasError = true
                            speciesLabel = "species cannot be empty"
                        }
                        repository.BehaviourInput.text.isEmpty() -> {
                            behaviourHasError = true
                            behaviourLabel = "behaviour cannot be empty"
                        }

                        repository.MRInput.text.isEmpty() -> {
                            mrHasError = true
                            mrLabel = "medical records cannot be empty"
                        }
                        else -> {
                            repository.add()
                            nav.navigate(Screen.MainScreen.route)
                        }
                    }
            },
            modifier = Modifier //add
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomCenter),
            //backgroundColor = Purple700
        )
        {
            Icon(imageVector = Icons.Default.Add, contentDescription = "icon")
        }
    }


}

@Composable
fun logginscreen(
    loginViewModel: LoginViewModel = viewModel(),
    onLoginClicked: () -> Unit
) {

    var username: String = "";
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        TextField(value = loginViewModel.UsernameInput,
            placeholder = { Text(text = "Username") },
            onValueChange = { newText: TextFieldValue ->
                loginViewModel.UsernameInput = newText
            }
            //modifier = Modifier.padding(4.dp)
        )

        TextField(value = loginViewModel.PasswordInput,
            placeholder = { Text(text = "Password") },
            onValueChange = { newText ->
                loginViewModel.PasswordInput = newText
            }
            // modifier = Modifier.padding(4.dp)
        )

        Button(onClick = { loginViewModel.testCredentials(onLoginClicked) }) {
            Text(text = "login")

        }
    }
}


@Composable
fun SimpleAlertDialog(
    onClicked: () -> Unit,
    nav: NavController
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = {
                nav.navigate(Screen.MainScreen.route)
            })
            { Text(text = "OK") }
        },
        dismissButton = {
            TextButton(onClick = onClicked)
            { Text(text = "Cancel") }
        },
        title = { Text(text = "Please confirm") },
        text = { Text(text = "Should I continue with the requested action?") }
    )
}


@Composable
fun Update(
    id: String?,
    nav: NavController,
    repository: repository = viewModel(),
    update: UpdateViewModel = viewModel()

) {
    var expanded by remember { mutableStateOf(false) }
    var delete by remember { mutableStateOf(false) }
    val pet = repository.getPetWithId(id?.toInt())

    //validATION

    var nameHasError by remember { mutableStateOf(false) }
    var nameLabel by remember { mutableStateOf("name") }
    var ageHasError by remember { mutableStateOf(false) }
    var ageLabel by remember { mutableStateOf("age") }
    var speciesHasError by remember { mutableStateOf(false) }
    var speciesLabel by remember { mutableStateOf("species") }
    var behaviourHasError by remember { mutableStateOf(false) }
    var behaviourLabel by remember { mutableStateOf("behaviour") }
    var mrHasError by remember { mutableStateOf(false) }
    var mrLabel by remember { mutableStateOf("medical records") }



    Column(
        modifier = Modifier
            // .background(Purple700)
            .fillMaxSize()

    ) {
        OutlinedTextField(
            value = update.NameInput,
            //value = repository.NameInput,
            isError = nameHasError,
            label = { Text(text = nameLabel) },
            placeholder = { Text(text = pet.name) },
            onValueChange = { newText: TextFieldValue ->
                update.NameInput = newText
                //repository.NameInput = newText
            }, //textStyle = TextStyle(Purple200, background = Purple700)
        )
        OutlinedTextField(
            value = update.AgeInput,
            isError = ageHasError,
            label = { Text(text = ageLabel) },
            placeholder = { Text(text = pet.age.toString()) },
            onValueChange = { newText: TextFieldValue ->
                update.AgeInput = newText
                //repository.NameInput = newText
            }, //textStyle = TextStyle(Purple200, background = Purple700)
        )
        OutlinedTextField(
            value = update.MRInput,
            isError = mrHasError,
            label = { Text(text = mrLabel) },
            placeholder = { Text(text = pet.medical_records) },
            onValueChange = { newText: TextFieldValue ->
                update.MRInput = newText
            },
        )
        OutlinedTextField(
            value = update.SpeciesInput,
            isError = speciesHasError,
            label = { Text(text = speciesLabel) },
            placeholder = { Text(text = pet.species) },
            onValueChange = { newText: TextFieldValue ->
                update.SpeciesInput = newText
            },
        )
        OutlinedTextField(
            value = update.BehaviourInput,
            isError = behaviourHasError,
            label = { Text(text = behaviourLabel) },
            placeholder = { Text(text = pet.behaviour) },
            onValueChange = { newText: TextFieldValue ->
                update.BehaviourInput = newText
            },
        )


        Button(  //update
            onClick = {
                if (!update.AgeInput.text.isDigitsOnly()) {
                    ageHasError = true
                    ageLabel = "age should be a number"
                } else
                    when {
                        update.NameInput.text.isEmpty() -> {
                            nameHasError = true
                            nameLabel = "Name cannot be empty"
                        }
                        update.AgeInput.text.isEmpty() -> {
                            ageHasError = true
                            ageLabel = "age cannot be empty"
                        }


                        update.SpeciesInput.text.isEmpty() -> {
                            speciesHasError = true
                            speciesLabel = "species cannot be empty"
                        }
                        update.BehaviourInput.text.isEmpty() -> {
                            behaviourHasError = true
                            behaviourLabel = "behaviour cannot be empty"
                        }

                        update.MRInput.text.isEmpty() -> {
                            mrHasError = true
                            mrLabel = "medical records cannot be empty"
                        }
                        else -> {
                            update.update()
                            nav.navigate(Screen.MainScreen.route)
                        }
                    }

            }
        ) {
            Text("update")

        }


        Button(  //delete
            onClick = {
                repository.deletePet(pet.id)
                delete = !delete
            }
        ) {
            Text("remove")

        }
        if (delete) {
            SimpleAlertDialog({ delete = !delete }, nav = nav)
        }

    }

}