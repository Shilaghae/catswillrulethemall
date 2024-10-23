package cats.will.rule.them.all.features.breeds.api

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Headers
import de.jensklingenberg.ktorfit.http.Query

interface BreedService {

    @Headers("x-api-key: xxx")
    @GET("v1/breeds")
    suspend fun getBreeds(
        @Query("limit") limit: Int?,
        @Query("page") page: Int?
    ): List<BreedResponse>
}