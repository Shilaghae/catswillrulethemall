package cats.will.rule.them.all.features.landing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BreedResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
)