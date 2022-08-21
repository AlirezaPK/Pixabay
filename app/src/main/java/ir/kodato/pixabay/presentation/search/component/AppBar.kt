package ir.kodato.pixabay.presentation.search.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import ir.kodato.pixabay.ui.theme.Black
import ir.kodato.pixabay.ui.theme.White

@Composable
fun AppBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        backgroundColor = Black,
        title = {
            Text(
                text = "Pixabay",
                color = White,
                fontStyle = MaterialTheme.typography.h6.fontStyle,
                fontWeight = FontWeight.Black
            )
        },
        actions = {
            IconButton(onClick = {
                onSearchClicked()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search Icon",
                    tint = White
                )
            }
        }
    )
}