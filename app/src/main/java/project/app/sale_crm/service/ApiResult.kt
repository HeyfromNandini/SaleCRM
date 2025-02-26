package project.app.sale_crm.service


import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> HttpResponse,
    parseResponse: suspend (HttpResponse) -> T
): Result<T> {
    return try {
        val response = apiCall()
        when {
            response.status == HttpStatusCode.OK -> {
                val body = parseResponse(response)
                Result.success(body)
            }
            else -> {
                println("Responnse is $response")
                Result.failure(Throwable(response.bodyAsText()))
            }
        }
    } catch (e: Exception) {
        Result.failure(mapToThrowable(e))
    }
}



private fun mapToThrowable(exception: Exception): Throwable {
    return when (exception) {
        is IOException -> Throwable(message = "Network error occurred")
        is ClientRequestException -> Throwable(message = "Client request error occurred")
        is ServerResponseException -> Throwable(message = "Server error occurred")
        is RedirectResponseException -> Throwable(message = "Unexpected redirect occurred")
        else -> Throwable(message = exception.localizedMessage ?: "Unknown error occurred")
    }
}