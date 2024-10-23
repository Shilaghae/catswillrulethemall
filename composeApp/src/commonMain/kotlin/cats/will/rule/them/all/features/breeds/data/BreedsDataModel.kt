package cats.will.rule.them.all.features.breeds.data

data class Breeds(
    val breeds: List<Breed>,
    val pagination: Pagination
)

data class Breed(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
)

data class Pagination(val nextPage: Int?)