package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.jmlb23.mediumclone.data.models.Comment
import com.github.jmlb23.mediumclone.data.models.Profile
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun CommentItem(comment: Comment) {
    Column {
        Text(text = comment.author.username ?: "")
        Text(text = comment.body)
    }
}


@Preview("example")
@Composable
fun Comment_Preview() {
    CommentItem(
        comment = Comment(
            5,
            Clock.System.now().toLocalDateTime(TimeZone.UTC).toString(),
            Clock.System.now().toLocalDateTime(TimeZone.UTC).toString(),
            "body",
            author = Profile("jmlb23", "my bio", "", false)
        )
    )
}