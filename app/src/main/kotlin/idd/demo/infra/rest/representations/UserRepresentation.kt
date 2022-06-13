package idd.demo.infra.rest.representations

@kotlinx.serialization.Serializable
data class UserRepresentation(
    val username: String,
    val password: String,
    val about: String
)
