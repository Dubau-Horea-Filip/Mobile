package com.example.animal_shelter.busines

import com.example.animal_shelter.viewModels.repository

class Repo {

    companion object {
        private val repoInstance = Repo()

        fun getInstance(): Repo {
            return repoInstance
        }
    }

    fun update(pet:Pet)
    {
        for (item in pets)
        {
            if(item.id == pet.id)
            {
                item.behaviour=pet.behaviour
                item.age=pet.age
                item.medical_records=pet.medical_records
                item.species=pet.species
                item.name=pet.name

            }
        }
    }

    var pets = mutableListOf<Pet>(


        Pet(1, "Rio", 2, "all vaccines done", "Orange Tabby cat","neede"),
        Pet(2, "Fio", 3, "almost all vaccines done", "cat","nice"),
        Pet(3, "Fio", 2, "all vaccines done", "Orange Tabby cat","scared"),
        Pet(4, "Fio", 2, "all vaccines done", "Orange Tabby cat","vicious"),
        Pet(5, "Fio", 2, "all vaccines done", "Orange Tabby cat","good"),
        Pet(6, "Fio", 2, "all vaccines done", "Orange Tabby cat","good"),
        Pet(7, "Fio", 2, "all vaccines done", "Orange Tabby cat","good"),
        Pet(8, "Fio", 2, "all vaccines done", "Orange Tabby cat","good"),
        Pet(9, "Fio", 2, "all vaccines done", "Orange Tabby cat","good"),
        Pet(10, "Fio", 2, "all vaccines done", "Orange Tabby cat","good")
    )



    fun add(p: Pet)
    {

        this.pets.add(p)
    }

    fun getPetwithId(id: Int):Pet{
        for (item in pets)
        {
            if(item.id == id)
            {

                return item
            }
        }
        return pets[0]
    }

    fun deletePet(id:Int)
    {
        for (item in pets)
        {
            if(item.id == id)
            {
                pets.remove(item)
                return
            }
        }
    }

    fun  getSize():Int
    {
        return pets.size-1
    }
}