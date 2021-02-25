import org.w3c.dom.HTMLDivElement
import kotlinx.browser.document

class WeatherTellerPage(private val presenter: CityStoreContract.Presenter) : CityStoreContract.View {
    private val loader = document.getElementById("loader") as HTMLDivElement
    private val content = document.getElementById("content") as HTMLDivElement
    private val cardBuilder = CardBuilder()

    override fun showCities(cities: List<City>) {
        cities.forEach { city ->
            showCity(city)
        }
    }
    override fun showLoader() {
        loader.style.visibility = "visible"
    }

    override fun hideLoader() {
        loader.style.visibility = "hidden"
    }

    override fun showCity(city: City) {
        val card = cardBuilder.build(city)
        content.appendChild(card)
    }

    fun show(){
        presenter.attach(this)
        presenter.loadCities()
    }
}
