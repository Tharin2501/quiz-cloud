package no.kristiania.quizcloud_exam.exoplayer

import android.content.Intent
import android.os.Bundle

import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util

import androidx.appcompat.app.AppCompatActivity
import no.kristiania.quizcloud_exam.MainActivity
import no.kristiania.quizcloud_exam.R
import no.kristiania.quizcloud_exam.fragments.WelcomeFragment

class IntroPlayer : AppCompatActivity() {

    companion object {

        private val TAG = "IntroPlayer"
    }

    private var playerView: PlayerView? = null
    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_layout)

        playerView = findViewById(R.id.playerView)

        player = ExoPlayerFactory.newSimpleInstance(this)

        // Bind the player to the view.
        playerView!!.player = player

        // DataSource instances which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "QuizCloud"))

        // This is the MediaSource object representing the media to be played.
        val video = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.intro))

        // Autoplay video
        player!!.playWhenReady = true
        player!!.prepare(video)

        /*
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent) */

    }


}