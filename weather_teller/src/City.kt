data class City(
        val name: String,
        val sys: Country?,
        val weather: Array<WeatherEntity>,
        val main: Temperature,
        val clouds: Cloudiness,
        val wind: Wind
)
