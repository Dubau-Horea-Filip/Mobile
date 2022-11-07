package com.example.animal_shelter.busines



class Pet() {
    var id: Int = 0
    var name: String = ""
    var age: Int = 0
    var  medical_records: String = ""
    var  species: String = ""
    var behaviour: String = ""
    constructor(id: Int, name: String, age: Int,  medical_records: String,  species: String, behaviour: String) : this() {
        this.id = id
        this.name = name
        this.age = age
        this.medical_records= medical_records
        this.species=species
        this.behaviour=behaviour
    }


}