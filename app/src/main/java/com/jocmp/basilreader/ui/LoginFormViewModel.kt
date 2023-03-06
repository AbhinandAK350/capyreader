package com.jocmp.basilreader.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.jocmp.feedbin.FeedbinClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class LoginFormViewModel(
    val emailAddress: String = "",
    val password: String = "",
    val setEmailAddress: (String) -> Unit,
    val setPassword: (String) -> Unit,
    val login: () -> Unit,
)

@Composable
fun useLoginFormViewModel(): LoginFormViewModel {
    val feedbinClient = FeedbinClient()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val (emailAddress, setEmailAddress) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }

    fun loginUser() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                val isSuccess = feedbinClient.authentication(emailAddress, password)

                if (isSuccess) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Good stuff", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    return LoginFormViewModel(
        emailAddress = emailAddress,
        password = password,
        setPassword = setPassword,
        setEmailAddress = setEmailAddress,
        login = { loginUser() },
    )
}
