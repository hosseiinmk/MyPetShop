package ir.hossein.mypetshop.ui.presentation.profile

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ir.hossein.mypetshop.domain.model.User
import ir.hossein.mypetshop.ui.navigation.enterAndExitTransitionTogether
import ir.hossein.mypetshop.ui.presentation.loading.LoadingScreen
import ir.hossein.mypetshop.ui.theme.Black
import ir.hossein.mypetshop.ui.theme.Green
import ir.hossein.mypetshop.ui.theme.Red
import ir.hossein.mypetshop.ui.theme.SkyBlue
import ir.hossein.mypetshop.ui.utils.log

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    gotoHome: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.userExists, block = {
        log("is user exists: ${state.userExists}")
    })

    AnimatedContent(
        targetState = state.loading,
        modifier = Modifier.fillMaxSize(),
        label = "",
        transitionSpec = { enterAndExitTransitionTogether() }
    ) { loading ->
        when (loading) {
            true -> {
                LoadingScreen()
            }

            else -> {
                when (state.showDialog) {
                    true -> {
                        when (state.users.isNotEmpty()) {
                            true -> {
                                LoginDialog(
                                    state = state,
                                    onTypingEmail = { viewModel.updateState { copy(email = it) } },
                                    onConfirm = { viewModel.loginUser(email = it) },
                                    onDismiss = {
                                        viewModel.updateState { copy(showDialog = false) }
                                        gotoHome()
                                    }
                                )
                            }

                            else -> {
                                RegisterDialog(
                                    state = state,
                                    onTypingEmail = { viewModel.updateState { copy(email = it) } },
                                    onConfirm = { viewModel.registerUser(User.default.copy(email = it)) },
                                    onDismiss = {
                                        viewModel.updateState {
                                            copy(showDialog = false)
                                        }
                                        gotoHome()
                                    }
                                )
                            }
                        }
                    }

                    else -> {
                        state.activeUser?.let {
                            Profile(state = state, logout = {
                                viewModel.logoutUser()
                                gotoHome()
                            })
                        }
                    }
                }
            }
        }
    }

    BackHandler { gotoHome() }
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
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "جهت ثبت حساب کاربری ایمیل خود را وارد کنید")
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
                        focusedTextColor = Black
                    ),
                    keyboardActions = KeyboardActions(onDone = { onConfirm(state.email) })
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onConfirm(state.email) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(text = "ثبت", color = Color.White)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Red),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(text = "لغو", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun LoginDialog(
    state: ProfileUiState,
    onTypingEmail: (String) -> Unit,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier.height(300.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "جهت ورود به حساب کاربری ایمیل خود را وارد کنید", textAlign = TextAlign.Center)
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
                        focusedTextColor = Black
                    ),
                    keyboardActions = KeyboardActions(onDone = { onConfirm(state.email) })
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onConfirm(state.email) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(text = "ورود", color = Color.White)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Red),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(text = "لغو", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun Profile(state: ProfileUiState, logout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = state.activeUser?.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "${state.activeUser?.email}")
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(visible = state.activeUser?.username != "") {
            Text(text = "@${state.activeUser?.username}")
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(
            visible = state.activeUser?.name != "" && state.activeUser?.family != ""
        ) {
            Text(text = "${state.activeUser?.name}" + " " + "${state.activeUser?.family}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Button(
                onClick = { logout() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SkyBlue,
                    contentColor = Black
                )
            ) {
                Text(text = "خروج از حساب کاربری")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SkyBlue,
                    contentColor = Black
                )
            ) {
                Text(text = "بعدا اضافه میشود")
            }
        }
    }
}