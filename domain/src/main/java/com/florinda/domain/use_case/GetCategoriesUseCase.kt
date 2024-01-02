package com.florinda.domain.use_case

import android.util.Log
import com.florinda.domain.model.Resource
import com.florinda.domain.model.CategoryModel
import com.florinda.domain.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCategoriesUseCase(
    private val orderRepository: OrderRepository
) {

    operator fun invoke(): Flow<Resource<List<CategoryModel>>> = flow {
        emit(Resource.Loading)
        val category = orderRepository.getCategories()
        Log.i("GetCategoriesUseCase", category.size.toString())

        emit(Resource.Success(category))
    }.catch {
        emit(Resource.Error(it.message ?: "un known error"))
    }.flowOn(Dispatchers.IO)


}