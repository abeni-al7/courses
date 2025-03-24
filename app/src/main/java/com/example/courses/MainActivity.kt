package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.example.courses.data.DataSource
import com.example.courses.models.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                val layoutDirection = LocalLayoutDirection.current
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(
                            start = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateStartPadding(layoutDirection),
                            end = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateEndPadding(layoutDirection),
                        ),

                ) {
                    CoursesGrid(
                        DataSource.topics,
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesGrid(topicList: List<Topic>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(topicList.size) { topic ->
            TopicGrid(
                imageResourceId = topicList[topic].imageResourceId,
                name = getString(
                    LocalContext.current,
                    topicList[topic].stringResourceId
                ),
                numberOfCourses = topicList[topic].numberOfCourses,
            )
        }
    }
}

@Composable
fun TopicGrid(
    imageResourceId: Int,
    name: String,
    numberOfCourses: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.LightGray)
            .height(68.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(imageResourceId),
            modifier = Modifier
                .height(68.dp),
            contentScale = ContentScale.Crop,
            contentDescription = name
        )
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 11.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_grain),
                    colorFilter = ColorFilter.tint(Color.DarkGray),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Text(
                    text = numberOfCourses.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 8.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesTheme {
        TopicGrid(R.drawable.photography, "Photography", 321)
    }
}