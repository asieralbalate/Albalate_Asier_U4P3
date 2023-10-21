package com.example.albalate_asier_u4p3

import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.albalate_asier_u4p3.ui.theme.Pink40
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Portada(navController: NavHostController) {
    var badgeCount by remember { mutableStateOf(0) }
    var selectedCardName by remember { mutableStateOf("") }
    var showSnackbar by remember { mutableStateOf(false) }
    var showDrawer by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    modifier = Modifier.fillMaxWidth().zIndex(1f)
                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = {
                                    showDrawer = !showDrawer
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                            BadgedBox(badge = { Text(text = badgeCount.toString()) }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    modifier = Modifier.clickable { badgeCount++ },
                                    tint = Color.White
                                )
                            }
                        }
                        Row {
                            FloatingActionButton(onClick = { /*TODO*/ }, containerColor = Pink40) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }

            }
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding())
            ) {
                val cardDataList = getCardData()
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    content = {
                        items(cardDataList) { cardData ->
                            ItemCard(cardData) {
                            }
                        }
                    }
                )
                if (showSnackbar) {
                    LaunchedEffect(true) {
                        delay(2000)
                        showSnackbar = false
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp), contentAlignment = Alignment.BottomCenter
                    ) {
                        Snackbar(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ) {
                            Text(text = "selectedCardName")
                        }
                    }
                }
            }
            if (showDrawer) {
                ModalDrawerSheet {
                    Image(
                        painter = painterResource(id = R.drawable.manchasolar),
                        contentDescription = "Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        contentScale = ContentScale.Crop
                    )
                    Row (modifier = Modifier.fillMaxWidth()){
                        Icon(imageVector = Icons.Default.Build, contentDescription = null)
                        Text(text = "Build")
                    }
                    Row {
                        Icon(imageVector = Icons.Default.Info, contentDescription = null)
                        Text(text = "Info")
                    }
                    Row {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                        Text(text = "Email")
                    }
                }
            }
        }
}

data class CardData(
    var name: String,
    @DrawableRes var photo: Int
)

fun getCardData(): List<CardData> {
    return listOf(
        CardData(
            "Corona Solar",
            R.drawable.corona_solar,
        ),
        CardData(
            "Erupción solar",
            R.drawable.erupcionsolar,
        ),
        CardData(
            "Espículas",
            R.drawable.espiculas,
        ),
        CardData(
            "Filamentos",

            R.drawable.filamentos,
        ),
        CardData(
            "Magnetosfera",
            R.drawable.magnetosfera,
        ),
        CardData(
            "Mancha solar",
            R.drawable.manchasolar,
        ),
    )
}

@Composable
fun ItemCard(cardData: CardData, onCardClick: (String) -> Unit) {
    var isImageMenuVisible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = cardData.photo),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp).clickable { onCardClick(cardData.name)},
                contentScale = ContentScale.Crop
            )
            BottomAppBar(modifier = Modifier.height(55.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = cardData.name, modifier = Modifier.padding(start = 10.dp))
                    IconButton(
                        onClick = {
                            isImageMenuVisible = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Abrir menú"
                        )
                    }
                }

                DropdownMenu(
                    expanded = isImageMenuVisible,
                    onDismissRequest = { isImageMenuVisible = false },
                    offset = DpOffset(0.dp, ((-40).dp))
                )
                {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Copiar",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        },
                        onClick = { isImageMenuVisible = false },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Eliminar",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        },
                        onClick = { isImageMenuVisible = false },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        },
                    )
                }
            }
        }
    }
}