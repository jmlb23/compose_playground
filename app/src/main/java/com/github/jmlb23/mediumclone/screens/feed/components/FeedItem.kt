package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.github.jmlb23.mediumclone.Ambients.LocalStore
import com.github.jmlb23.mediumclone.data.models.Article
import com.github.jmlb23.mediumclone.data.models.Profile
import dev.chrisbanes.accompanist.coil.CoilImage
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FeedItem(art: Article, controller: NavHostController) {
    val store = LocalStore.current

    Row(modifier = Modifier.padding(5.dp)) {
        CoilImage(
            data = art.author.image,
            "Avatar",
            modifier = Modifier
                .border(
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Transparent)
                )
                .padding(10.dp)
                .size(40.dp, 40.dp)
                .clip(CircleShape)
                .border(BorderStroke(2.dp, Color.Black), shape = CircleShape),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Row {
                Row {
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(text = art.author.username, style = MaterialTheme.typography.h5)
                        Text(text = art.updatedAt, style = MaterialTheme.typography.subtitle1)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                FavButton{
                    store.state().token?.let {

                    } ?: run {
                        controller.navigate("/profile")
                    }
                }
            }
            Column {
                Text(text = art.title, style = MaterialTheme.typography.body1)
                Text(text = art.description, style = MaterialTheme.typography.body2)
            }
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
        ),
        controller = rememberNavController()
    )
}