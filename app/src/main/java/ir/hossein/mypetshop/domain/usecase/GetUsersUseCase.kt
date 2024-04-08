package ir.hossein.mypetshop.domain.usecase

import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {

    suspend operator fun invoke(): Response<Flow<List<User>>>
}