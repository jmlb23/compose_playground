package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.jmlb23.mediumclone.R
import com.github.jmlb23.mediumclone.data.models.Article
import com.github.jmlb23.mediumclone.data.models.Profile
import dev.chrisbanes.accompanist.coil.CoilImage
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FeedItem(art: Article) {
    Box(modifier = Modifier.wrapContentHeight().padding(5.dp).fillMaxWidth()) {
        Row {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CoilImage(
                    data = art.author.image,
                    modifier = Modifier.border(
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color.Transparent)
                    ).size(100.dp, 100.dp)
                )
                Column(modifier = Modifier.align(Alignment.Top)) {
                    Text(text = art.author.username, fontSize = 12.sp)
                    Text(text = art.updatedAt, fontSize = 10.sp)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Favorite, tint = Color.Black,
                modifier = Modifier
                    .background(Color.White, shape = RectangleShape)
                    .padding(horizontal = 5.dp)
                    .size(24.dp)
            )
        }
    }

}

@Preview("FeedItem")
@Composable
fun FeedItemPreview() {
    FeedItem(
        art = Article(
            "",
            "",
            "",
            "",
            emptyList(),
            "",
            SimpleDateFormat("dd MM yyyy").format(Date()),
            true,
            0,
            author = Profile("jmlb23", "a bio", "", false)
        )
    )
}