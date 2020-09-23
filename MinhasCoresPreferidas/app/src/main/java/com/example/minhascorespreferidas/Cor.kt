package com.example.minhascorespreferidas

import android.os.Parcel
import android.os.Parcelable

class Cor (var nome: String, var codigo: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return "Cor: ${nome}\nCódigo Hexadecimal: ${codigo}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeString(codigo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cor> {
        override fun createFromParcel(parcel: Parcel): Cor {
            return Cor(parcel)
        }

        override fun newArray(size: Int): Array<Cor?> {
            return arrayOfNulls(size)
        }
    }
}