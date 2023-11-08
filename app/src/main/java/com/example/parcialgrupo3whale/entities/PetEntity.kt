package com.example.parcialgrupo3whale.entities
import android.os.Parcel
import android.os.Parcelable

class PetEntity(
    val namePet: String,
    val agePet: String,
    val weightPet: String,
    val descriptionPet: String,
    val gender: Boolean,
    val location: String,
    val owner: String,
    val race: String,
    val subrace: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(namePet)
        parcel.writeString(agePet)
        parcel.writeString(weightPet)
        parcel.writeString(descriptionPet)
        parcel.writeByte(if (gender) 1 else 0)
        parcel.writeString(location)
        parcel.writeString(owner)
        parcel.writeString(race)
        parcel.writeString(subrace)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PetEntity> {
        override fun createFromParcel(parcel: Parcel): PetEntity {
            return PetEntity(parcel)
        }

        override fun newArray(size: Int): Array<PetEntity?> {
            return arrayOfNulls(size)
        }
    }
}
