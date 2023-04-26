package mx.itson.sol

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import mx.itson.sol.entidades.Ubicacion
import mx.itson.sol.utilerias.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), GoogleMap.OnMapLoadedCallback {

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

        } catch (ex: Exception) {
            Log.e("error al cargar el mapa", ex.toString())
        }
    }
}

private fun Fragment.getMapAsync(mainActivity: MainActivity) {

}
