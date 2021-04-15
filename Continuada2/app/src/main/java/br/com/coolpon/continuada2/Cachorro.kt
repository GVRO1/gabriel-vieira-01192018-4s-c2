package br.com.coolpon.continuada2

import android.os.Parcel
import android.os.Parcelable

data class Cachorro(
    val id: Int,
    val raca: String?,
    val precoMedio: Int,
    val indicadoCriancas: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(raca)
        parcel.writeInt(precoMedio)
        parcel.writeByte(if (indicadoCriancas) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cachorro> {
        override fun createFromParcel(parcel: Parcel): Cachorro {
            return Cachorro(parcel)
        }

        override fun newArray(size: Int): Array<Cachorro?> {
            return arrayOfNulls(size)
        }
    }
}