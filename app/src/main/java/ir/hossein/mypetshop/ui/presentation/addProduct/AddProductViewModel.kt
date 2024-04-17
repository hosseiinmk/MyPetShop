package ir.hossein.mypetshop.ui.presentation.addProduct

import android.app.Application
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hossein.mypetshop.core.BaseViewModel
import ir.hossein.mypetshop.domain.model.Product
import ir.hossein.mypetshop.domain.usecase.productUseCase.AddProductUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : BaseViewModel<AddProductUiState>(AddProductUiState()) {

    fun addProduct() {
        if (!isProductValid()) return
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            addProductUseCase(
                product = Product(
                    name = stateValue().name,
                    price = stateValue().price.toInt(),
                    category = stateValue().category,
                    amount = stateValue().amount.toInt(),
                    image = stateValue().image.toString()
                )
            )
            clearProduct()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun setImage(uri: Uri) {
//        val imageBitmap = ImageDecoder.createSource(application.contentResolver, uri).let {
//            ImageDecoder.decodeBitmap(it)
//        }
        updateState { copy(image = uri) }
    }

    fun setProductName(productName: String) {
        updateState { copy(name = productName) }
    }

    fun setProductPrice(productPrice: String) {
        updateState { copy(price = productPrice) }
    }

    fun setCategory(productCategory: Int) {
        updateState { copy(category = productCategory) }
    }

    fun setProductAmount(productAmount: String) {
        updateState { copy(amount = productAmount) }
    }

    private fun isProductValid(): Boolean =
        stateValue().image.toString() != Product.default.image &&
                stateValue().name != Product.default.name &&
                stateValue().price != Product.default.price.toString() &&
                stateValue().category != Product.default.category &&
                stateValue().amount != Product.default.amount.toString()

    private fun clearProduct() {
        updateState {
            copy(
                name = "NewProduct",
                price = "",
                category = -1,
                amount = "",
                image = null
            )
        }
    }
}