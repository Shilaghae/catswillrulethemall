package cats.will.rule.them.all.features.breeds.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BreedResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("image") val image: BreedImageResponse? = null,
)

@Serializable
class BreedImageResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("width") val width: String? = null,
    @SerialName("height") val height: String? = null,
    @SerialName("url") val url: String? = null,
)