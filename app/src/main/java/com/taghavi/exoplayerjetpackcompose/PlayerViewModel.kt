package com.taghavi.exoplayerjetpackcompose

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class PlayerViewModel : ViewModel() {

    companion object {
        const val Video_1 =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        const val Video_2 =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
        const val Video_3 =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        const val Video_4 =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
    }

    private val _playerState = MutableStateFlow<ExoPlayer?>(null)
    val playerState: StateFlow<ExoPlayer?> = _playerState

    fun createPlayerWithMediaItems(context: Context) {
        if (_playerState.value == null) {
            val mediaItem = MediaItem.Builder().setUri(Video_1).setMediaId("Video_1").build()

            _playerState.update {
                ExoPlayer.Builder(context).build().apply {
                    setMediaItem(mediaItem)
                    prepare()
                    playWhenReady = true
                }
            }

        }
    }
}