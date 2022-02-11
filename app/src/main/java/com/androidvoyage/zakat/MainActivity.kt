package com.androidvoyage.zakat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }


}

@Preview
@Composable
fun DefaultPreview() {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(RoundedCornerShape(50)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                shape = RoundedCornerShape(50),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,

        ) {

        LazyColumn {
            items(51) {
                Text(
                    text = "List item - $it",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )
                if (it == 50) {
                    Spacer(modifier = Modifier.height(56.dp))
                }
            }
        }
    }
}

@Composable
fun AppBottomBar(fabShape: RoundedCornerShape) {

    BottomAppBar(
        elevation = 10.dp,
        cutoutShape = fabShape
    ) {

        IconButton(
            onClick = {
                /* doSomething() */
            }
        ) {
            Icon(Icons.Filled.Home, "")
        }
        // The actions should be at the end of the BottomAppBar
        Spacer(Modifier.weight(1f, true))
        IconButton(
            onClick = {
                /* doSomething() */
            }
        ) {
            Icon(Icons.Filled.List, "")
        }

    }

}

@Composable
fun AppTopBar() {
    TopAppBar(
        elevation = 10.dp,
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (title, search) = createRefs()
            Text(
                text = "AppBarsSample",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(search.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(16.dp,0.dp)
            )
            IconButton(
                onClick = {
                    /* doSomething() */
                },
                modifier = Modifier
                    .constrainAs(search) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(Icons.Filled.MoreVert, "Search")
            }
        }
    }
}