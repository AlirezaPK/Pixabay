package ir.kodato.pixabay.presentation.search.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.kodato.pixabay.R
import ir.kodato.pixabay.presentation.common.TagItem
import ir.kodato.pixabay.ui.theme.Black
import ir.kodato.pixabay.ui.theme.White

@Composable
fun ImageItem(
    context: Context,
    image: String,
    username: String,
    tags: List<String>,
    onImageClicked: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .clickable { onImageClicked() }) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black.copy(alpha = ContentAlpha.medium))
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Text(
                    text = username,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = White
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(tags) {
                        TagItem(title = it)
                    }
                }
            }
        }
    }
}