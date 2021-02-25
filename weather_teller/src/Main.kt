val API_URL = js("getApiUrl()") as String

fun main(args: Array<String>) {
    println("Hello world!")
    println(API_URL)
    val weatherTellerCitiesPresenter = WeatherTellerCitiesPresenter()
    val weatherTellerPage = WeatherTellerPage(weatherTellerCitiesPresenter)
    weatherTellerPage.show()
}
