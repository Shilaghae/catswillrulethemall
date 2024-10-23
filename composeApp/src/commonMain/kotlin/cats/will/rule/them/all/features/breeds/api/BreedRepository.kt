package cats.will.rule.them.all.features.breeds.api

import cats.will.rule.them.all.features.breeds.data.Breed
import cats.will.rule.them.all.features.breeds.data.Breeds
import cats.will.rule.them.all.features.breeds.data.IBreedRepository
import cats.will.rule.them.all.features.breeds.data.Pagination
import io.github.aakira.napier.Napier
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

private val TAG = BreedRepository::class.simpleName

class BreedRepository(
    private val service: BreedService
) : IBreedRepository {

    override suspend fun getBreeds(limit: Int?, page: Int?): Breeds {
        return try {
            val breeds = service.getBreeds(limit, page)
            if (breeds.isEmpty()) {
                return Breeds(breeds = emptyList(), pagination = Pagination(nextPage = null))
            }
            Breeds(breeds = breeds
                .filter { it.id != null && it.name != null }
                .map {
                    Breed(
                        id = it.id!!,
                        name = it.name!!,
                        imageUrl = it.image?.url,
                    )
                }, pagination = Pagination(nextPage = page?.plus(1) ?: 0)
            )
        } catch (e: ClientRequestException) {
            // Client Error (4xx)
            Napier.e(
                "Client error status = ${e.response.status.value} message = ${e.message}",
                e,
                TAG
            )
            throw e
        } catch (e: ServerResponseException) {
            // Server Error (5xx)
            Napier.e(
                "Server error status = ${e.response.status.value} message = ${e.message}",
                e,
                TAG
            )
            throw e
        } catch (e: Exception) {
            // Others
            Napier.e("Other error message = ${e.message}", e, TAG)
            throw e
        }
    }
}