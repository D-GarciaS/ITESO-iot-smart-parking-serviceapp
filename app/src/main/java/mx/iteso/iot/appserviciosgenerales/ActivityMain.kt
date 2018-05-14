package mx.iteso.iot.appserviciosgenerales

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*


class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setActivityViews()
    }

    private fun setActivityViews() {
        setRegistrarButton();
        setCajonesButton();
    }

    private fun setCajonesButton() {
        ActivityMain_Cajones_btn.setOnClickListener {
            val launchIntent = Intent(this, ActivityCajones::class.java)
            startActivity(launchIntent)
        }
    }

    private fun setRegistrarButton() {
        ActivityMain_Registrar_btn.setOnClickListener {
            val launchIntent = Intent(this, ActivityRegistrar::class.java)
            startActivity(launchIntent)
        }
    }
}
