package ir.hossein.mypetshop.domain.usecase

import ir.hossein.mypetshop.domain.model.User
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {

    suspend operator fun invoke(): Flow<Response<List<User>>>
}