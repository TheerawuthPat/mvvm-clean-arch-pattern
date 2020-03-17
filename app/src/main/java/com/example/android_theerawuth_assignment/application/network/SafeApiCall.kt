package com.example.android_theerawuth_assignment.application.network

import com.example.android_theerawuth_assignment.application.exception.NoNetworkException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

suspend fun <T> safeApiCall(
		dispatcher: CoroutineDispatcher = Dispatchers.IO,
		apiCall: suspend () -> Response<T>
): Results<T> {

	return withContext(dispatcher) {
		try {
			val response = apiCall.invoke()
			if (response.isSuccessful) {
				if (response.body() != null) {
					Results.Success(response.body()!!)
				} else {
					Results.Empty
				}
			} else {
				when (response.code()) {
					HttpURLConnection.HTTP_UNAUTHORIZED -> {
						Results.Unauthorized
					}
					in HttpURLConnection.HTTP_BAD_REQUEST..499 -> {
						Results.ClientError(Throwable(response.errorBody()?.string()))
					}
					in HttpURLConnection.HTTP_INTERNAL_ERROR..599 -> {
						Results.ServerError(Throwable(response.message()))
					}
					else -> {
						Results.Error(Throwable(response.message()))
					}
				}
			}
		} catch (e: Exception) {
			if (e is TimeoutException || e is SocketTimeoutException) {
				Results.Timeout
			} else if (e is NoNetworkException) {
				Results.Network
			} else {
				Results.Error(e)
			}
		}
	}
}