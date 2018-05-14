package mx.iteso.iot.appserviciosgenerales

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.iteso.iot.androidappiot.retrofit.clients.BackEndService
import mx.iteso.iot.androidappiot.retrofit.clients.IoTAPI.UserAPI
import mx.iteso.iot.androidappiot.retrofit.models.SuccessfulSlotResponse
import mx.iteso.iot.appserviciosgenerales.adapters.ParkingSlotAdapter
import mx.iteso.iot.retrofit.clients.IoTAPI.ServiceAPI
import mx.iteso.iot.retrofit.models.ParkingSlot
import kotlinx.android.synthetic.main.activity_cajones.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ActivityCajones : AppCompatActivity() {

    lateinit var slots: List<ParkingSlot>
    lateinit var adapter: ParkingSlotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cajones)
        createList()
    }

    fun createList(){
        slots = arrayListOf()// emptyList<ParkingSlot>()
// Create the adapter to convert the array to views
        adapter = ParkingSlotAdapter(this, slots)
// Attach the adapter to a ListView
        ActivityCajones_list.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
        requestSlots()
    }

    fun requestSlots() {
        val clientApi = BackEndService().client.create(ServiceAPI::class.java)
        val call = clientApi.getSlot();

        call.enqueue( object : Callback<List<ParkingSlot>> {
            override fun onFailure(call: Call<List<ParkingSlot>>?, t: Throwable?) {
                processCallFailure(call, t)
            }

            override fun onResponse(call: Call<List<ParkingSlot>>?, response: Response<List<ParkingSlot>>?) {
                processCallSuccess(call, response)
            }
        })

    }

    private fun processCallSuccess(call: Call<List<ParkingSlot>>?, response: Response<List<ParkingSlot>>?) {
        if(response != null && response.isSuccessful){
            updateLocalSlots(response.body())
        }else if( response != null ){
            processUnsuccesfulResponse(response)
        }else{
            processNoResponse()
        }
    }

    private fun processNoResponse() {
        Toast.makeText(this,"Comprueba tu internet",Toast.LENGTH_LONG).show()
    }

    private fun processUnsuccesfulResponse(response: Response<List<ParkingSlot>>) {
        Toast.makeText(this,"Error en el servidor", Toast.LENGTH_LONG).show()
    }

    private fun updateLocalSlots(body: List<ParkingSlot>?) {

        adapter.clear()
        adapter.addAll(body)
    }

    private fun processCallFailure(call: Call<List<ParkingSlot>>?, t: Throwable?) {
        Toast.makeText(this,"Comprueba tu internet",Toast.LENGTH_LONG).show()
    }
}
