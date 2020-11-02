package com.creativedrewy.sample.pixabaybrowser.activities

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.creativedrewy.sample.R
import com.creativedrewy.sample.pixabaybrowser.datamodels.VideoPreview
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.android.synthetic.main.content_video_player.*

class VideoPlayerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VIDEO_DATA = "EXTRA_VIDEO_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        setSupportActionBar(toolbar)

        (intent.getSerializableExtra(EXTRA_VIDEO_DATA) as VideoPreview?)?.let { video ->
            val exoplayer = ExoPlayerFactory.newSimpleInstance(
                    DefaultRenderersFactory(this),
                    DefaultTrackSelector(), DefaultLoadControl()
            )

            val mediaSource = ExtractorMediaSource.Factory(DefaultHttpDataSourceFactory("pixabay-app"))
                    .createMediaSource(Uri.parse(video.videoUrl))

            video_view.player = exoplayer
            exoplayer.playWhenReady = true
            exoplayer.prepare(mediaSource)

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            toolbar.visibility = View.GONE
            video_view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }

}
