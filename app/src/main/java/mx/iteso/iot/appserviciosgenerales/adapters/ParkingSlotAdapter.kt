package mx.iteso.iot.appserviciosgenerales.adapters

import android.widget.ArrayAdapter
import mx.iteso.iot.retrofit.models.ParkingSlot
import android.R.attr.name
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.R.attr.name
import android.content.Context
import android.R.attr.name
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_parking_slot.view.*
import mx.iteso.iot.androidappiot.retrofit.models.User
import mx.iteso.iot.appserviciosgenerales.R


public class ParkingSlotAdapter(
        context: Context,
        slots: List<ParkingSlot>
) : ArrayAdapter<ParkingSlot>(context, 0, slots) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var parkingSlot = getItem(position)

        val mView:View

        if (view == null) {
            mView = LayoutInflater.from(getContext()).inflate(R.layout.item_parking_slot, parent, false);
        }else{
            mView = view
        }

        val layout = mView.findViewById(R.id.item_parking_slot_layout) as LinearLayout
        val number = mView.findViewById(R.id.item_parking_slot_number) as TextView
        val section = mView.findViewById(R.id.item_parking_slot_section) as TextView
        val status = mView.findViewById(R.id.item_parking_slot_status) as TextView

        val stat : String;
        val col : Int;

        if(parkingSlot.invaded){
            stat = "Invadido"
            col = Color.RED
        }else if (parkingSlot.waiting){
            stat = "Esperando"
            col = Color.YELLOW
        }else if (parkingSlot.occupied){
            stat = ""
            col = Color.BLUE
        }else if (parkingSlot.preferential){
            stat = "Vacio"
            col = Color.GREEN
        }else {
            stat = "Estacionamiento General"
            col = Color.GRAY
        }

        section.text = "Sección: " + parkingSlot.section
        number.text = "Numero: " + parkingSlot.number
        status.text = "Estatus: " + stat
        layout.setBackgroundColor(col)

        return mView
    }
}