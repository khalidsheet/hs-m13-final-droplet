package com.khalidmsheet.droplet2.presentation.timeline

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.khalidmsheet.droplet2.R
import java.util.Calendar

@Composable
fun ThoughtCard(data: ThoughtData) {
    val db = FirebaseFirestore.getInstance()
    var hasLiked by remember { mutableStateOf(false) }

    fun updateThoughtLikes() {
        val likes = if (hasLiked) data.totalLikes - 1 else data.totalLikes + 1
        db.document("thoughts/" + data.id).update("totalLikes", likes)
        hasLiked = !hasLiked
        data.totalLikes = likes
    }


    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row {
                    AsyncImage(
                        model = data.photo,
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = data.username.toString(), fontSize = 16.sp)
                        Text(
                            text = "@" + data.username.toString(),
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                }
                Text(
                    text = data.timestamp?.seconds?.toTimeAgo().toString(),
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = data.content, fontSize = 13.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            Modifier.width(160.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { updateThoughtLikes() },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Transparent, containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(40.dp)
                    .padding(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_heart_icon),
                    contentDescription = null
                )

            }
            Text(text = data.totalLikes.toString())

            Image(
                painter = painterResource(id = R.drawable.ic_comment_icon),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = R.drawable.ic_more_icon), contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0XFFCCCCCC))
        )
    }
}


private const val SECOND = 1
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR
private const val MONTH = 30 * DAY
private const val YEAR = 12 * MONTH

private fun currentDate(): Long {
    val calendar = Calendar.getInstance()
    return calendar.timeInMillis
}

// Long: time in millisecond
fun Long.toTimeAgo(): String {
    val time = this
    val now = currentDate()

    // convert back to second
    val diff = (now - time * 1000) / 1000

    return when {
        diff < MINUTE -> "Just now"
        diff < 2 * MINUTE -> "a minute ago"
        diff < 60 * MINUTE -> "${diff / MINUTE} minutes ago"
        diff < 2 * HOUR -> "an hour ago"
        diff < 24 * HOUR -> "${diff / HOUR} hours ago"
        diff < 2 * DAY -> "yesterday"
        diff < 30 * DAY -> "${diff / DAY} days ago"
        diff < 2 * MONTH -> "a month ago"
        diff < 12 * MONTH -> "${diff / MONTH} months ago"
        diff < 2 * YEAR -> "a year ago"
        else -> "${diff / YEAR} years ago"
    }
}