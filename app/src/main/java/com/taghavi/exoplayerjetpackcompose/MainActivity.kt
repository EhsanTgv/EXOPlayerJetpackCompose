package com.taghavi.exoplayerjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.PlayerSurface
import com.taghavi.exoplayerjetpackcompose.ui.theme.EXOPlayerJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EXOPlayerJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlayerRoute(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PlayerRoute(
    modifier: Modifier = Modifier,
    playerViewModel: PlayerViewModel = viewModel()
) {
    val exoPlayer = playerViewModel.playerState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        exoPlayer.value?.let {
            PlayerScreen(exoPlayer = it)
        }
    }

    LaunchedEffect(Unit) {
        playerViewModel.createPlayerWithMediaItems(context)
    }
}

@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    exoPlayer: ExoPlayer,
) {
    Box(
        modifier = modifier,
    ) {
        PlayerSurface(
            player = exoPlayer,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .align(Alignment.Center)
        )
    }
}