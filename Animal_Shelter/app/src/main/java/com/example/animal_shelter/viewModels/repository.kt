package com.example.animal_shelter.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.animal_shelter.busines.Pet
import com.example.animal_shelter.busines.Repo

class repository : ViewModel() {

    var IdInput by mutableStateOf(TextFieldValue(""))
    var NameInput by mutableStateOf(TextFieldValue(""))
    var AgeInput by mutableStateOf(TextFieldValue(""))
    var SpeciesInput by mutableStateOf(TextFieldValue(""))
    var BehaviourInput by mutableStateOf(TextFieldValue(""))
    var MRInput by mutableStateOf(TextFieldValue(""))
    var Behaviour by mutableStateOf(TextFieldValue(""))

    var repo= Repo.getInstance()
    var id = 11
    fun add()
    {
        var new = Pet(id.inc(), NameInput.text, AgeInput.text.toInt(), MRInput.text, SpeciesInput.text,BehaviourInput.text)
        repo.add(new)

    }

    fun getAtIndex(index: Int?): Pet {

        return repo.pets.get(index!!);
    }

    fun getPetWithId(id:Int?): Pet
    {
        return this.repo.getPetwithId(id!!)
    }

    fun deletePet(id: Int) {
        this.repo.deletePet(id);
    }

    fun size(): Int{return this.repo.getSize()}

    fun getPets(): MutableList<Pet> {
        return repo.pets
    }


}