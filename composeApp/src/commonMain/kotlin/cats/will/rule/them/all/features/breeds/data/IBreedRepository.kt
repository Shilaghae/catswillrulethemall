package cats.will.rule.them.all.features.breeds.data

interface IBreedRepository {
    suspend fun getBreeds(limit: Int?, page: Int?): Breeds
}