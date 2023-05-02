package mx.itson.sol.entidades

import com.google.gson.annotations.SerializedName

/**
 * Variables que contiene el objeto current_weather del JSON de OpenMeteo
 *
 * @constructor Crea una clase para obtener las variables del clima actual del JSON de OpenMeteo
 */
class Clima {

    /**
     * Temperatura actual
     */
    @SerializedName("temperature")
    var temperatura: Float? = null

    /**
     * Velocidad del viento
     */
    @SerializedName("windspeed")
    var velocidadViento: Float? = null

    /**
     * Direccion del viento
     */
    @SerializedName("winddirection")
    var direccionViento: Float? = null

    /**
     * Codigo del clima
     */
    @SerializedName("weathercode")
    var codigoClima: Int? = null

    /**
     * Es dia o no
     */
    @SerializedName("is_day")
    var esDia: Int? = null

}