package com.aee.mokacam.activity

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.aee.mokacam.AeeApplication
import com.aee.mokacam.constants.AeeConstants
import com.nostra13.universalimageloader.core.ImageLoader

@OptIn(ExperimentalMaterial3Api::class)
class ComposeMediaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("filePath") ?: ""
        val isVideo = name.endsWith(".mp4", true) || name.endsWith(".mov", true)
        setContent {
            Scaffold(topBar = {
                TopAppBar(title = { Text(name) }, navigationIcon = {
                    IconButton(onClick = { finish() }) { Text("‹") }
                })
            }) { padding ->
                Box(Modifier.fillMaxSize().padding(padding)) {
                    if (isVideo) {
                        val player = remember {
                            ExoPlayer.Builder(this@ComposeMediaActivity).build().apply {
                                setMediaItem(MediaItem.fromUri(Uri.parse(mediaUrl(name))))
                                prepare()
                                playWhenReady = true
                            }
                        }
                        AndroidView(factory = { PlayerView(it).apply { this.player = player } }, modifier = Modifier.fillMaxSize())
                        DisposableEffect(Unit) { onDispose { player.release() } }
                    } else {
                        AndroidView(factory = { ImageView(it).apply {
                            scaleType = ImageView.ScaleType.FIT_CENTER
                            ImageLoader.getInstance().displayImage(mediaUrl(name), this)
                        } }, modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }

    private fun mediaUrl(name: String): String {
        if (name.startsWith("http://") || name.startsWith("https://") || name.startsWith("file://")) return name
        return AeeApplication.a()?.h.orEmpty() + name
    }
}
