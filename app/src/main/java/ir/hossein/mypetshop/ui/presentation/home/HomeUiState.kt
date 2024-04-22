package ir.hossein.mypetshop.ui.presentation.home

import ir.hossein.mypetshop.domain.model.Product

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val filteredProduct: List<Product> = emptyList(),
    val selectedCategory: Int = 0
)
