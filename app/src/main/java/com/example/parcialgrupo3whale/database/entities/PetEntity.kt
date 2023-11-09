package com.example.parcialgrupo3whale.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.parcialgrupo3whale.enums.Location

@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val petName: String = "",
    val petAge: String = "",
    val petWeigh: String = "",
    val petDescription: String = "",
    val gender: Boolean,
    val location: Location,
    val owner: String = "",
    val images: String = "",
    val breed: String = "",
    val subBreed: String = "",

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
) : Parcelable {

    constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",s
    parcel.readString() ?: "",
    parcel.readByte() != 0.toByte(),
    Location.valueOf(parcel.readString() ?: ""),
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readByte() != 0.toByte()
    )

    // Método para escribir el objeto en un Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(petName)
        parcel.writeString(petAge)
        parcel.writeString(petWeigh)
        parcel.writeString(petDescription)
        parcel.writeByte(if (gender) 1 else 0)
        parcel.writeString(location.name)
        parcel.writeString(owner)
        parcel.writeString(images)
        parcel.writeString(breed)
        parcel.writeString(subBreed)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    // Método para describir los tipos de objetos especiales contenidos en el objeto Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Objeto companion para implementar Parcelable
    companion object CREATOR : Parcelable.Creator<PetEntity> {
        // Método para crear una instancia del objeto desde un Parcel
        override fun createFromParcel(parcel: Parcel): PetEntity {
            return PetEntity(parcel)
        }

        // Método para crear un array de objetos del tipo Parcelable
        override fun newArray(size: Int): Array<PetEntity?> {
            return arrayOfNulls(size)
        }
    }
}