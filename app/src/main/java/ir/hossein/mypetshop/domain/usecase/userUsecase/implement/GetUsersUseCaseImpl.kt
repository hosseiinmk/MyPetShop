package ir.hossein.mypetshop.domain.usecase.userUsecase.implement

import ir.hossein.mypetshop.domain.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.domain.usecase.userUsecase.GetUsersUseCase
import ir.hossein.mypetshop.domain.utils.mapToDomain
import ir.hossein.mypetshop.ui.utils.Response
import ir.hossein.mypetshop.ui.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetUsersUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUsersUseCase {

    override suspend operator fun invoke(): Flow<Response<List<User>>> = flow {
        userRepository.getUsers().collect { responseData ->
            try {
                val response = when (responseData) {
                    is Response.Success -> Response.Success(data = responseData.data.map { user ->
                        user.mapToDomain()
                    })
                    is Response.Failure -> Response.Failure(message = "")
                }
                emit(response)
            } catch (e: Exception) {
                log(e.stackTraceToString())
            }
        }
    }
}