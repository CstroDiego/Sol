package mx.itson.sol.entidades

import com.google.gson.annotations.SerializedName

/**
 * Variables que se obtienen del JSON de OpenMeteo
 *
 * @constructor Crea una clase para obtener las variables del JSON de OpenMeteo
 * @author Diego Castro Arce
 */
class Ubicacion {

    /**
     * Latitud de la ubicacion
     */
    var latitude: Float? = null

    /**
     * Longitud de la ubicacion
     */
    var longitude: Float? = null

    /**
     * Elevacion de la ubicacion
     */
    var elevation: Float? = null

    /**
     * Objeto clima para obtener las variables del clima actual
     */
    @SerializedName("current_weather")
    var clima: Clima? = null

}