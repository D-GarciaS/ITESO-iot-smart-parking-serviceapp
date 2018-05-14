package mx.iteso.iot.retrofit.models

import android.os.Parcel
import android.os.Parcelable

data class ParkingSlot(
    val id: String,
    val number: String,
    val section: String,
    val occupied: Boolean,
    val waiting: Boolean,
    val invaded: Boolean,
    val preferential: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(number)
        parcel.writeString(section)
        parcel.writeByte(if (occupied) 1 else 0)
        parcel.writeByte(if (waiting) 1 else 0)
        parcel.writeByte(if (invaded) 1 else 0)
        parcel.writeByte(if (preferential) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParkingSlot> {
        override fun createFromParcel(parcel: Parcel): ParkingSlot {
            return ParkingSlot(parcel)
        }

        override fun newArray(size: Int): Array<ParkingSlot?> {
            return arrayOfNulls(size)
        }
    }
}