package com.example.childrenstherapy

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayerActivity : AppCompatActivity() { // Add : AppCompatActivity() here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_player)

        val videoView = findViewById<VideoView>(R.id.video_view)
        val videoUri = intent.getStringExtra("VIDEO_URI") ?: return

        videoView.setVideoURI(Uri.parse(videoUri))
        videoView.setOnPreparedListener { it.start() } // Auto-play the video
    }
}
