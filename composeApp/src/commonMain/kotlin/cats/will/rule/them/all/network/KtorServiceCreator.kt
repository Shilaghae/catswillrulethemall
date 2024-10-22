package cats.will.rule.them.all.network

import cats.will.rule.them.all.features.landing.BreedService
import cats.will.rule.them.all.features.landing.createBreedService
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorfitServiceCreator(val url: String) {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
    private val ktorfit = Ktorfit.Builder()
        .baseUrl(url)
        .httpClient(httpClient)
        .build()

    fun createBreedServices(): BreedService {
        return ktorfit.createBreedService()
    }
}