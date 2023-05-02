package mx.itson.sol

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import mx.itson.sol.entidades.Ubicacion
import mx.itson.sol.utilerias.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private var mapa: GoogleMap? = null
    val codigoResultado = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        try {
            mapa = googleMap
            mapa!!.mapType = GoogleMap.MAP_TYPE_HYBRID

            val estaPermitido = ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (estaPermitido) {
                mapa!!.isMyLocationEnabled = true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(ACCESS_FINE_LOCATION),
                    200
                )
            }

            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                onLocationChanged(location)
            }

        } catch (ex: Exception) {
            Log.e("error al cargar el mapa", ex.toString())
        }
    }


    override fun onLocationChanged(location: Location) {
        val latitud = location.latitude
        val longitud = location.longitude

        val latLng = LatLng(latitud, longitud)
        mapa?.clear()

        // Guardar referencia al marcador
        mapa?.addMarker(MarkerOptions().position(latLng).draggable(true).title("Mi ubicación"))

        mapa?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mapa?.animateCamera(CameraUpdateFactory.zoomTo(15f))

        // Agregar listener al marcador
        mapa?.setOnMarkerDragListener(object : OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {
                val a = 1 + 1
            }

            override fun onMarkerDrag(marker: Marker) {
                val a = 1 + 1
            }

            override fun onMarkerDragEnd(marker: Marker) {
                obtenerUbicacion(marker.position.latitude, marker.position.longitude)

            }
        })
    }

    private fun obtenerUbicacion(lat: Double, lon: Double) {
        val llamada: Call<Ubicacion> =
            RetrofitUtil.getApi().getClima(lat, lon, true)

        llamada.enqueue(object : Callback<Ubicacion> {
            override fun onResponse(call: Call<Ubicacion>, response: Response<Ubicacion>) {
                val ubicacion: Ubicacion? = response.body()
                if (ubicacion != null) {

                    val latitud = ubicacion.latitude
                    val longitud = ubicacion.longitude
                    val elevacion = ubicacion.elevation
                    val temperatura = ubicacion.clima?.temperatura
                    val velocidadViento = ubicacion.clima?.velocidadViento
                    val direccionViento = ubicacion.clima?.direccionViento
                    val codigoClima = ubicacion.clima?.codigoClima
                    val esDia = ubicacion.clima?.esDia

                    val intent = Intent(this@MainActivity, ViewWeatherActivity::class.java)
                    intent.putExtra("latitud", latitud)
                    intent.putExtra("longitud", longitud)
                    intent.putExtra("elevacion", elevacion)
                    intent.putExtra("temperatura", temperatura)
                    intent.putExtra("velocidadViento", velocidadViento)
                    intent.putExtra("direccionViento", direccionViento)
                    intent.putExtra("codigoClima", codigoClima)
                    intent.putExtra("esDia", esDia)

                    startActivityForResult(intent, codigoResultado)
                }
            }

            override fun onFailure(call: Call<Ubicacion>, t: Throwable) {
                Log.e("Error", t.toString())
            }
        })


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == codigoResultado && resultCode == RESULT_OK) {
            // realizar alguna acción en consecuencia
        }
    }
}