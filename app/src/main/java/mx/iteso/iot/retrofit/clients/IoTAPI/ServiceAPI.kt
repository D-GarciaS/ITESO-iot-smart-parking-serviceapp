package mx.iteso.iot.retrofit.clients.IoTAPI

import mx.iteso.iot.androidappiot.retrofit.models.SlotRequest
import mx.iteso.iot.androidappiot.retrofit.models.SuccessfulSlotResponse
import mx.iteso.iot.retrofit.models.ParkingSlot
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {
    @GET("/parkingslots/get")
    fun getSlot() : Call<List<ParkingSlot>>
}