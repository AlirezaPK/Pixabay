package ir.kodato.pixabay.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.kodato.pixabay.ui.theme.Black
import ir.kodato.pixabay.ui.theme.White

@Composable
fun TagItem(
    title: String
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Black.copy(alpha = ContentAlpha.disabled))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = MaterialTheme.typography.caption.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = White
        )
    }
}