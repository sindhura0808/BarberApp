package  com.example.booksalonappointment.model.remote.response.service

data class ServiceResponse(
    val message: String,
    val services: List<Service>,
    val status: Int
)