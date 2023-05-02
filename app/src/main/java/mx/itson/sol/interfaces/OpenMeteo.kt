package mx.itson.sol.interfaces

import mx.itson.sol.entidades.Ubicacion
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface para obtener el clima con OpenMeteo
 *
 * @constructor Crea una interface para configurar los parametros de la peticion
 * @author Diego Castro Arce
 */
interface OpenMeteo {

    /**
     * Obtiene el clima de una ubicacion
     *
     * @param lat Latitud de la ubicacion
     * @param lon Longitud de la ubicacion
     * @param current Si es el clima actual
     * @return Call<Ubicacion> Llamada para obtener el clima
     */
    @GET("forecast")
    fun getClima(@Query("latitude") lat:Double,
                 @Query("longitude") lon:Double,
                 @Query("current_weather") current: Boolean): Call<Ubicacion>
}