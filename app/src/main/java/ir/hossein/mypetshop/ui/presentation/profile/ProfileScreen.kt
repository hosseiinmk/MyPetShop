package ir.hossein.mypetshop.ui.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.ui.theme.Green
import ir.hossein.mypetshop.ui.theme.Red

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state().collectAsState()

    AnimatedVisibility(visible = state.registerDialog) {
        RegisterDialog(
            state = state,
            onTypingEmail = {
                viewModel.updateState { copy(email = it) }
            },
            onConfirm = { email ->
                viewModel.registerUser(
                    User(
                        username = "",
                        name = "",
                        family = "",
                        email = email
                    )
                )
            },
            onDismiss = {
                viewModel.updateState { copy(registerDialog = false) }
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            viewModel.updateState { copy(registerDialog = true) }
        }) {
            Text(text = "ثبت نام")
        }
    }
}

@Composable
fun RegisterDialog(
    state: ProfileUiState,
    onTypingEmail: (String) -> Unit,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier.height(300.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "جهت ثبت حساب کاربری ایمیل خود را وارد کنید",
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.email,
                    onValueChange = { onTypingEmail(it) },
                    placeholder = {
                        Text(
                            text = "ایمیل",
                            color = Color.Gray,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        focusedTextColor = Color.Black
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        onConfirm(state.email)

                    })
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onConfirm(state.email) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(
                        text = "ثبت",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(
                        text = "لغو",
                        color = Color.White
                    )
                }
            }
        }
    }
}