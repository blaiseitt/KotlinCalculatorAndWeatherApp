interface CityStoreContract {
    interface View {
        fun showCities(cities: List<City>)
        fun showLoader()
        fun hideLoader()
        fun showCity(city: City)
    }

    interface Presenter {
        fun attach(view: View)
        fun loadCities()
    }
}