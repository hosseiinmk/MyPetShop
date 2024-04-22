package ir.hossein.mypetshop.ui.presentation.addProduct

import android.content.Context
import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ir.hossein.mypetshop.R
import ir.hossein.mypetshop.ui.theme.Green
import ir.hossein.mypetshop.ui.theme.White
import ir.hossein.mypetshop.ui.utils.RtlLayout
import ir.hossein.mypetshop.ui.utils.log
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduct(
    viewModel: AddProductViewModel = hiltViewModel(),
    goToHome: () -> Unit
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { imageUri ->
        imageUri?.let {
            log("${context.filesDir}/${imageUri.pathSegments.last()}.jpeg")
            viewModel.setImage(imageUri = imageUri, "${context.filesDir}/${imageUri.pathSegments.last()}.jpeg")
            val file = File(context.filesDir, "${imageUri.pathSegments.last()}.jpeg")
            context.openFileOutput(file.name, Context.MODE_PRIVATE).use { outputStream ->
                viewModel.getImageBitmap(imageUri = imageUri).compress(
                    Bitmap.CompressFormat.JPEG, 95, outputStream
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(20),
            onClick = {
                resultLauncher.launch(
                    PickVisualMediaRequest()
                )
            }
        ) {
            AsyncImage(
                model = state.imageUri ?: R.drawable.camera,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        RtlLayout {
            OutlinedTextField(
                value = state.name,
                onValueChange = { viewModel.setProductName(productName = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "نام محصول") },
                shape = RoundedCornerShape(20),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = state.price,
                onValueChange = { viewModel.setProductPrice(productPrice = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "قیمت محصول") },
                shape = RoundedCornerShape(20),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = state.amount,
                onValueChange = { viewModel.setProductAmount(productAmount = it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "تعداد محصول") },
                shape = RoundedCornerShape(20),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(3) {
                    Card(
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (state.category == it) Green else White
                        ),
                        onClick = { viewModel.setCategory(productCategory = it) }
                    ) {
                        Image(
                            painter = painterResource(id = viewModel.categoryIcons[it]),
                            contentDescription = null,
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { viewModel.addProduct() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = White
            )
        ) {
            Text(text = "افزودن محصول")
        }
    }
    BackHandler { goToHome() }
}