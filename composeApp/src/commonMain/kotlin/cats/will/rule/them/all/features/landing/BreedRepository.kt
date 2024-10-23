package cats.will.rule.them.all.features.landing

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

class BreedRepository(
    private val service: BreedService
) {

    suspend fun getBreeds(): List<BreedResponse> {
        return try {
            service.getBreeds()
        } catch (e: ClientRequestException) {
            // Client Error (4xx)
            throw e
        } catch (e: ServerResponseException) {
            // Server Error (5xx)
            throw e
        } catch (e: Exception) {
            // Others
            throw e
        }
    }
}