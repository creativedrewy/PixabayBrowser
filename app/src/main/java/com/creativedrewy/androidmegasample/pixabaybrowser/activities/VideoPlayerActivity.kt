package com.creativedrewy.androidmegasample.pixabaybrowser.activities

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.creativedrewy.androidmegasample.R
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.VideoPreviewVO
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

        (intent.getSerializableExtra(EXTRA_VIDEO_DATA) as VideoPreviewVO?)?.let { video ->
            val exoplayer = ExoPlayerFactory.newSimpleInstance(
                    DefaultRenderersFactory(this),
                    DefaultTrackSelector(), DefaultLoadControl())

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
