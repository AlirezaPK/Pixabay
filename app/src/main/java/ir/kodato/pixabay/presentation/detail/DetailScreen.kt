package ir.kodato.pixabay.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kodato.pixabay.R
import ir.kodato.pixabay.domain.model.PixabayImage
import ir.kodato.pixabay.presentation.common.TagItem
import ir.kodato.pixabay.ui.theme.Black
import ir.kodato.pixabay.ui.theme.White

@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    detail: PixabayImage
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(detail.largeImageURL)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )

        TopAppBar(
            backgroundColor = Color.Transparent,
            title = {},
            elevation = 0.dp,
            navigationIcon = {
                IconButton(onClick = {
                    navigator.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back Icon",
                        tint = White
                    )
                }
            }
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Black.copy(alpha = ContentAlpha.medium))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(detail.userImageURL)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )

                    Text(
                        text = detail.user,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        color = White
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CountItem(
                        icon = R.drawable.ic_download,
                        amount = detail.downloads
                    )

                    CountItem(
                        icon = R.drawable.ic_like,
                        amount = detail.likes
                    )

                    CountItem(
                        icon = R.drawable.ic_comment,
                        amount = detail.comments
                    )
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(detail.tags.split(",")) {
                        TagItem(title = it)
                    }
                }
            }
        }
    }
}

@Composable
fun CountItem(
    icon: Int,
    amount: Int
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Black.copy(alpha = ContentAlpha.disabled))
            .padding(8.dp)
    ) {
        Row {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = amount.toString(),
                fontSize = MaterialTheme.typography.body2.fontSize,
                fontWeight = FontWeight.Medium,
                color = White
            )
        }
    }
}