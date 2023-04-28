package mx.itson.sol

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import mx.itson.sol.entidades.Ubicacion
import mx.itson.sol.utilerias.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), GoogleMap.OnMapLoadedCallback, LocationListener {

    private var mapa: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        obtenerUbicacion()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun obtenerUbicacion() {
        val llamada: Call<Ubicacion> =
            RetrofitUtil.getApi().getClima("27.9676629", "-110.9188319", true)

        llamada.enqueue(object : Callback<Ubicacion> {
            override fun onResponse(call: Call<Ubicacion>, response: Response<Ubicacion>) {
                val ubicacion: Ubicacion? = response.body()

                var a: Int = 1
            }

            override fun onFailure(call: Call<Ubicacion>, t: Throwable) {
                var a: Int = 1
            }
        })
    }

    override fun onMapLoaded() {
        try {
            mapa!!.mapType = GoogleMap.MAP_TYPE_HYBRID

            val estaPermitido = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (estaPermitido) {
                mapa!!.isMyLocationEnabled = true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
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

        val latLng = com.google.android.gms.maps.model.LatLng(latitud, longitud)
        mapa?.clear()
        mapa?.addMarker(
            com.google.android.gms.maps.model.MarkerOptions().position(latLng).title("Mi ubicación")
        )
        mapa?.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(latLng))
        mapa?.animateCamera(com.google.android.gms.maps.CameraUpdateFactory.zoomTo(15f))

        mapa?.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDrag(p0: Marker) {
                TODO("Not yet implemented")
            }

            override fun onMarkerDragEnd(p0: Marker) {
                Toast.makeText(
                    this@MainActivity,
                    "Latitud: ${p0.position.latitude} Longitud: ${p0.position.longitude}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onMarkerDragStart(p0: Marker) {
                TODO("Not yet implemented")
            }


        })
    }
}

private fun Fragment.getMapAsync(mainActivity: MainActivity) {

}
