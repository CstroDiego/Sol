package mx.itson.sol

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.util.Locale

/**
 * Activity para ver el clima
 *
 * @constructor Crea un activity para ver el clima
 * @author Diego Castro Arce
 */
class ViewWeatherActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * Latitud de la ubicacion
     */
    var latitud: Float = 0.0f

    /**
     * Longitud de la ubicacion
     */
    var longitud: Float = 0.0f

    /**
     * Elevacion de la ubicacion
     */
    var elevacion: Float = 0.0f

    /**
     * Temperatura de la ubicacion
     */
    var temperatura: Float = 0.0f

    /**
     * Velocidad viento de la ubicacion
     */
    var velocidadViento: Float = 0.0f

    /**
     * Direccion viento de la ubicacion
     */
    var direccionViento: Float = 0.0f

    /**
     * Codigo del clima de la ubicacion
     */
    var codigoClima: Int = 0

    /**
     * Es dia de la ubicacion
     */
    var esDia: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = ""
        setContentView(R.layout.activity_view_weather)

        latitud = intent.getFloatExtra("latitud", 0.0f)
        longitud = intent.getFloatExtra("longitud", 0.0f)
        elevacion = intent.getFloatExtra("elevacion", 0.0f)
        temperatura = intent.getFloatExtra("temperatura", 0.0f)
        velocidadViento = intent.getFloatExtra("velocidadViento", 0.0f)
        direccionViento = intent.getFloatExtra("direccionViento", 0.0f)
        codigoClima = intent.getIntExtra("codigoClima", 0)
        esDia = intent.getIntExtra("esDia", 0)

        findViewById<View>(R.id.btnCerrar).setOnClickListener(this)

        cargar(
            latitud,
            longitud,
            elevacion,
            temperatura,
            velocidadViento,
            direccionViento,
            codigoClima,
            esDia
        )
    }

    /**
     * Carga todos los datos de la ubicacion en la vista
     *
     * @param latitud Latitud de la ubicacion
     * @param longitud Longitud de la ubicacion
     * @param elevacion Elevacion de la ubicacion
     * @param temperatura Temperatura de la ubicacion
     * @param velocidadViento Velocidad del viento de la ubicacion
     * @param direccionViento Direccion del viento de la ubicacion
     * @param codigoClima Codigo del clima de la ubicacion
     * @param esDia Es dia de la ubicacion
     */
    @SuppressLint("DiscouragedApi")
    private fun cargar(
        latitud: Float,
        longitud: Float,
        elevacion: Float,
        temperatura: Float,
        velocidadViento: Float,
        direccionViento: Float,
        codigoClima: Int,
        esDia: Int
    ) {
        val tvLongitud = findViewById<View>(R.id.tvLongitud) as TextView
        val tvLatitud = findViewById<View>(R.id.tvLatitud) as TextView
        val tvTemperatura = findViewById<View>(R.id.tvTemperatura) as TextView
        val tvElevacion = findViewById<View>(R.id.tvElevacion) as TextView
        val tvVelocidadViento = findViewById<View>(R.id.tvVelocidadViento) as TextView
        val tvDireccionViento = findViewById<View>(R.id.tvDireccionViento) as TextView
        val txtWeatherCode = findViewById<View>(R.id.txtWeatherCode) as TextView
        val language = Locale.getDefault().language
        val df = DecimalFormat("#.##")

        val prefix = if (esDia == 1) "day" else "night"
        findViewById<View>(R.id.main).setBackgroundResource(
            resources.getIdentifier(
                "gradient_${prefix}",
                "drawable",
                packageName
            )
        )

        when (codigoClima) {
            0 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_clear",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_despejado)
            }

            1, 2, 3 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_cloudy",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_nublado)
            }

            45, 48 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_fog",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_neblina)
            }

            51, 53, 55 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_drizzle",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_llovizna)
            }

            56, 57 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_freezing_drizzle",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_llovizna_helada)
            }

            61, 63, 65, 80, 81, 82 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_rain",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_lluvia)
            }

            66, 67 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_freezing_rain",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_lluvia_helada)
            }

            71, 73, 75, 84, 85 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_snow",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_nieve)
            }

            77 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_snow_grains",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_granizo)
            }

            95 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_thunderstorm",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_tormenta)
            }

            96, 99 -> {
                findViewById<View>(R.id.imgWeatherCode).setBackgroundResource(
                    resources.getIdentifier(
                        "${prefix}_thunderstorm_heavy",
                        "drawable",
                        packageName
                    )
                )
                txtWeatherCode.text = getString(R.string.texto_tormenta_fuerte)
            }
        }

        tvLongitud.text = "$longitud"
        tvLatitud.text = "$latitud"
        val elevacionFormat = df.format(elevacion).toFloat()
        tvElevacion.text = "$elevacionFormat"
        tvDireccionViento.text = "$direccionViento"
        if (language == "en") {
            //convervitoms la temperatura a grados fahrenheit
            // reducir a 2 decimales

            val temperatura = df.format((temperatura * 1.8f) + 32).toFloat()
            tvTemperatura.text = "$temperatura"

            val velocidadViento = df.format(velocidadViento / 1.609f).toFloat()
            tvVelocidadViento.text = "$velocidadViento"
        } else {
            val tempFormat = df.format(temperatura).toFloat()
            val velocidadFormat = df.format(velocidadViento).toFloat()
            tvTemperatura.text = "$tempFormat"
            tvVelocidadViento.text = "$velocidadFormat"
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnCerrar -> finish()
        }
    }

}