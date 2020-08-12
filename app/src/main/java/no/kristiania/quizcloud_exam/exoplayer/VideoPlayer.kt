package no.kristiania.quizcloud_exam.exoplayer

import android.os.Bundle

import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util

import androidx.appcompat.app.AppCompatActivity
import no.kristiania.quizcloud_exam.R


class VideoPlayer : AppCompatActivity() {

    private var playerView: PlayerView? = null
    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.videoplayer_layout)

        playerView = findViewById(R.id.playerView)

        player = ExoPlayerFactory.newSimpleInstance(this)

        // Bind the player to the view.
        playerView!!.player = player

        // DataSource instances which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "QuizCloud"))

        // This is the MediaSource object representing the media to be played.
        val firstSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.pop_out))
        val secondSource = ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.praise_the_lord))


        //Creates a playlist by concatenating mediasources
        val playList = ConcatenatingMediaSource(firstSource, secondSource)

        // Autoplay video
        player!!.playWhenReady = true
        player!!.prepare(playList)


    }
}
