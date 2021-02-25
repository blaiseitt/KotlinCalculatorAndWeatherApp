import org.w3c.xhr.XMLHttpRequest

class WeatherTellerCitiesPresenter : CityStoreContract.Presenter {

    private lateinit var view: CityStoreContract.View

    override fun attach(view: CityStoreContract.View) {
        this.view = view
    }

    override fun loadCities() {
        view.showLoader()

        getAsync(API_URL) { response ->
            val cities = JSON.parse<ResponseObject>(response)
            view.showCities(cities.list.toList())
            cities.list.forEach { city -> println(city.name) }
        }
        view.hideLoader()
    }

    private fun getAsync(url: String, callback: (String) -> Unit) {
        val xmlHttp = XMLHttpRequest()
        xmlHttp.open("GET", url)

        xmlHttp.onload = {
            if (xmlHttp.readyState == 4.toShort() && xmlHttp.status == 200.toShort()) {
                callback.invoke(xmlHttp.responseText)
            }
        }
        xmlHttp.send()
    }
}

data class ResponseObject(val list: Array<City>)
