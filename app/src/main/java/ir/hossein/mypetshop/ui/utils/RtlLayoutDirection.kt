package ir.hossein.mypetshop.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun RtlLayout(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        value = LocalLayoutDirection provides LayoutDirection.Rtl,
        content = content
    )
}