package cats.will.rule.them.all.network

import cats.will.rule.them.all.features.breeds.api.BreedService
import cats.will.rule.them.all.features.breeds.api.createBreedService
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorfitServiceCreator(url: String) {

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