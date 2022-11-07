package com.example.crudnative.ui.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animal_shelter.busines.Pet
import com.example.animal_shelter.busines.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class UpdateViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val PetRepository = Repo.getInstance()
    private var id: Int = checkNotNull(savedStateHandle["id"]).toString().toInt()

    private val petFlow: Flow<Pet> = flow {
        emit(checkNotNull(PetRepository.getPetwithId(id)))
    }

    var pet: Pet = Pet()

    init {
        viewModelScope.launch {
            petFlow.collect {
                pet = it
            }
        }
    }


    var NameInput by mutableStateOf(TextFieldValue(pet.name))
    var BehaviourInput by mutableStateOf(TextFieldValue(pet.behaviour))
    var MRInput by mutableStateOf(TextFieldValue(pet.medical_records))
    var SpeciesInput by mutableStateOf(TextFieldValue(pet.species))
    var AgeInput by mutableStateOf(TextFieldValue(pet.age.toString()))


    fun update() {
        val pet = Pet(
            1,
            NameInput.text,
            AgeInput.text.toInt(),
            MRInput.text,
            SpeciesInput.text,
            BehaviourInput.text
        )
        pet.id = id
        PetRepository.update(pet)
    }

}