package mx.itson.sol.entidades

import com.google.gson.annotations.SerializedName

class Clima {

    @SerializedName("temperature")
    var temperatura: Float? = null

    @SerializedName("windspeed")
    var velocidadViento: Float? = null

    @SerializedName("winddirection")
    var direccionViento: Float? = null

    @SerializedName("weathercode")
    var codigoClima: Int? = null

    @SerializedName("is_day")
    var esDia: Int? = null

}