package ir.hossein.mypetshop.ui.presentation.home

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hossein.mypetshop.core.BaseViewModel
import ir.hossein.mypetshop.domain.usecase.productUseCase.GetProductsUseCase
import ir.hossein.mypetshop.ui.utils.Response
import ir.hossein.mypetshop.ui.utils.log
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    init {
        getProducts()
    }

    private fun getProducts() {
        baseViewModelScope {
            getProductsUseCase().collect { response ->
                when (response) {
                    is Response.Success -> {
                        updateState {
                            copy(
                                products = response.data,
                                filteredProduct = response.data.filter { it.category == 0 }
                            )
                        }
                    }

                    is Response.Failure -> {
                        log("GET_PRODUCT_ERROR = ${response.message}")
                    }
                }
            }
        }
    }

    fun selectCategory(category: Int) {
        updateState {
            copy(
                selectedCategory = category,
                filteredProduct = products.filter { it.category == category }
            )
        }
    }
}