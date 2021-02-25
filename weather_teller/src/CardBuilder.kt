import org.w3c.dom.*
import kotlinx.browser.document
import kotlinx.dom.addClass

class CardBuilder {
    fun build(city: City): HTMLElement {
        val containerElement = document.createElement("div") as HTMLDivElement
        val imageElement = document.createElement("img") as HTMLImageElement
        val cityElement = document.createElement("div") as HTMLDivElement
        val tempElement = document.createElement("div") as HTMLDivElement
        val perceptibleTempElement = document.createElement("div") as HTMLDivElement
        val minTempElement = document.createElement("div") as HTMLDivElement
        val maxTempElement = document.createElement("div") as HTMLDivElement
        val pressureElement = document.createElement("div") as HTMLDivElement
        val humidityElement = document.createElement("div") as HTMLDivElement
        val cloudsElement = document.createElement("div") as HTMLDivElement
        val windElement = document.createElement("div") as HTMLDivElement

        bind(city = city,
                imageElement = imageElement,
                cityElement = cityElement,
                tempElement = tempElement,
                perceptibleTempElement = perceptibleTempElement,
                minTempElement = minTempElement,
                maxTempElement = maxTempElement,
                pressureElement = pressureElement,
                humidityElement = humidityElement,
                cloudsElement = cloudsElement,
                windElement = windElement)

        applyStyle(containerElement,
                cityElement = cityElement,
                imageElement = imageElement,
                tempElement = tempElement,
                perceptibleTempElement = perceptibleTempElement,
                minTempElement = minTempElement,
                maxTempElement = maxTempElement,
                pressureElement = pressureElement,
                humidityElement = humidityElement,
                cloudsElement = cloudsElement,
                windElement = windElement)

        containerElement
                .appendChild(
                        cityElement,
                        imageElement,
                        tempElement,
                        perceptibleTempElement,
                        minTempElement,
                        maxTempElement,
                        pressureElement,
                        humidityElement,
                        cloudsElement,
                        windElement
                )
        return containerElement
    }

    private fun bind(city: City,
                     cityElement: HTMLDivElement,
                     imageElement: HTMLImageElement,
                     tempElement: HTMLDivElement,
                     perceptibleTempElement: HTMLDivElement,
                     minTempElement: HTMLDivElement,
                     maxTempElement: HTMLDivElement,
                     pressureElement: HTMLDivElement,
                     humidityElement: HTMLDivElement,
                     cloudsElement: HTMLDivElement,
                     windElement: HTMLDivElement) {

        imageElement.src = "icons/" + city.weather[0].icon + ".png"
        imageElement.title = city.weather[0].main

        var title = city.name

        cityElement.innerHTML = title
        tempElement.innerHTML = "<strong>Temperature: </strong>" + city.main.temp + " &#8451;"
        perceptibleTempElement.innerHTML = "<strong>Perceptible temperature: </strong>" + city.main.feels_like + " &#8451;"
        minTempElement.innerHTML = "<strong>Minimal temperature: </strong>" + city.main.temp_min + " &#8451;"
        maxTempElement.innerHTML = "<strong>Maximal temperature: </strong>" + city.main.temp_max + " &#8451;"
        pressureElement.innerHTML = "<strong>Pressure: </strong>" + city.main.pressure + " hPa"
        humidityElement.innerHTML = "<strong>Humidity: </strong>" + city.main.humidity + "%"

        var cloudiness = city.clouds.all
        if (cloudiness !== undefined) {
            cloudiness += "%"
        } else {
            cloudiness = "N/A"
        }
        cloudsElement.innerHTML = "<strong>Cloudiness: </strong>$cloudiness"

        var windDegrees = city.wind.deg
        if (windDegrees !== undefined) {
            windDegrees += "&#176;"
        } else {
            windDegrees = "N/A"
        }
        windElement.innerHTML = "<strong>Wind: </strong>" + city.wind.speed + " m/s, " + windDegrees
    }

    private fun applyStyle(containerElement: HTMLDivElement,
                           cityElement: HTMLDivElement,
                           imageElement: HTMLImageElement,
                           tempElement: HTMLDivElement,
                           perceptibleTempElement: HTMLDivElement,
                           minTempElement: HTMLDivElement,
                           maxTempElement: HTMLDivElement,
                           pressureElement: HTMLDivElement,
                           humidityElement: HTMLDivElement,
                           cloudsElement: HTMLDivElement,
                           windElement: HTMLDivElement) {

        containerElement.addClass("card", "card-shadow")
        cityElement.addClass("text-title")
        imageElement.addClass("cover-image")
        tempElement.addClass("text-description")
        perceptibleTempElement.addClass("text-description")
        minTempElement.addClass("text-description", "hidden")
        maxTempElement.addClass("text-description", "hidden")
        pressureElement.addClass("text-description", "hidden")
        humidityElement.addClass("text-description", "hidden")
        cloudsElement.addClass("text-description", "hidden")
        windElement.addClass("text-description", "hidden")
    }

    private fun Element.appendChild(vararg elements: Element) {
        elements.forEach {
            this.appendChild(it)
        }
    }
}
