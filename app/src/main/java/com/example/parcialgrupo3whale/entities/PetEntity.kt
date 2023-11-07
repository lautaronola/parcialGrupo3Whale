package com.example.parcialgrupo3whale.entities
class PetEntity {
    private val namePet: String;
    private val agePet: String;
    private val weightPet: String;
    private val descriptionPet: String;
    private val gender: Boolean;
    private val location: String;
    private val owner: String;
    private val race: String;
    private val subrace: String;

    constructor(namePet:String,agePet:String,weightPet:String,descriptionPet:String,
                gender:Boolean,location:String,owner:String,race:String,subrace:String){
        this.namePet = namePet;
        this.agePet= agePet;
        this.weightPet = weightPet;
        this.descriptionPet = descriptionPet;
        this.gender = gender;
        this.location= location;
        this.owner= owner;
        this.race = race;
        this.subrace = subrace;
    }
    override fun toString(): String {
        return "Mascota en adoption(" +
                "nombre='$namePet', " +
                "edad='$agePet', " +
                "peso=$weightPet, " +
                "descripción='$descriptionPet', " +
                "genero='$gender', " +
                "ubicación='$location', " +
                "cuidador='$owner', " +
                "raza='$race', " +
                "subraza='$subrace')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PetEntity

        if (namePet != other.namePet) return false
        if (agePet != other.agePet) return false
        if (weightPet != other.weightPet) return false
        if (descriptionPet != other.descriptionPet) return false
        if (gender != other.gender) return false
        if (location != other.location) return false
        if (owner != other.owner) return false
        if (race != other.race) return false
        if (subrace != other.subrace) return false

        return true
    }

    override fun hashCode(): Int {
        var result = namePet.hashCode()
        result = 31 * result + agePet.hashCode()
        result = 31 * result + weightPet.hashCode()
        result = 31 * result + descriptionPet.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + owner.hashCode()
        result = 31 * result + race.hashCode()
        result = 31 * result + subrace.hashCode()
        return result
    }


}