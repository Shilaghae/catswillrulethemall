package cats.will.rule.them.all.features.landing

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Headers

interface BreedService {

    @Headers("x-api-key: xxx")
    @GET("v1/breeds?limit=10&page=0")
    suspend fun getBreeds(): List<BreedResponse>
}