package mx.itson.sol

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewWeatherActivity : AppCompatActivity(), View.OnClickListener {

    var latitud: Double = 0.0
    var longitud: Double = 0.0
    var elevacion: Float = 0.0f
    var temperatura: Float = 0.0f
    var velocidadViento: Float = 0.0f
    var direccionViento: Float = 0.0f
    var codigoClima: Int = 0
    var esDia: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_weather)

        latitud = intent.getDoubleExtra("latitud", 0.0)
        longitud = intent.getDoubleExtra("longitud", 0.0)
        elevacion = intent.getFloatExtra("elevacion", 0.0f)
        temperatura = intent.getFloatExtra("temperatura", 0.0f)
        velocidadViento = intent.getFloatExtra("velocidadViento", 0.0f)
        direccionViento = intent.getFloatExtra("direccionViento", 0.0f)
        codigoClima = intent.getIntExtra("codigoClima", 0)
        esDia = intent.getIntExtra("esDia", 0)

        Log.i("elevacion", elevacion.toString())
        Log.i("temperatura", temperatura.toString())
        Log.i("velocidadViento", velocidadViento.toString())
        Log.i("direccionViento", direccionViento.toString())
        Log.i("codigoClima", codigoClima.toString())
        Log.i("esDia", esDia.toString())

        findViewById<View>(R.id.btnCerrar).setOnClickListener(this)
        cargar(elevacion, temperatura, velocidadViento, direccionViento, codigoClima, esDia)
    }

    private fun cargar(
        elevacion: Float,
        temperatura: Float,
        velocidadViento: Float,
        direccionViento: Float,
        codigoClima: Int,
        esDia: Int
    ) {

        if (esDia == 1) {
            findViewById<View>(R.id.main).setBackgroundResource(R.drawable.gradient_day)
        } else {
            findViewById<View>(R.id.main).setBackgroundResource(R.drawable.gradient_night)
        }
        // Asignar temperatura a txtTemperatura
        val txtTemperatura = findViewById<View>(R.id.txtTemperatura) as TextView
        txtTemperatura.text = temperatura.toString() + "°C"

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnCerrar -> finish()
        }
    }

}