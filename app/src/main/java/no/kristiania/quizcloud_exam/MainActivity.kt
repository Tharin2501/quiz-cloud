package no.kristiania.quizcloud_exam

import android.content.Intent
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import no.kristiania.quizcloud_exam.adapter.SectionsStatePagerAdapter
import no.kristiania.quizcloud_exam.db.DatabaseHelper
import no.kristiania.quizcloud_exam.fragments.AddUserFragment
import no.kristiania.quizcloud_exam.fragments.WelcomeFragment
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import no.kristiania.quizcloud_exam.exoplayer.IntroPlayer
import no.kristiania.quizcloud_exam.game.GameActivity


class MainActivity : AppCompatActivity() {

    private var mSectionsStatePagerAdapter: SectionsStatePagerAdapter? = null
    private var mViewPager: ViewPager? = null
    internal lateinit var myDb: DatabaseHelper





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: Started.")



        mSectionsStatePagerAdapter = SectionsStatePagerAdapter(supportFragmentManager)

        mViewPager = findViewById<View>(R.id.containter) as ViewPager
        //setup the pager
        setupViewPager(mViewPager!!)
        //create db
        myDb = DatabaseHelper(this)

    }


    // Add the fragments, remember the order added
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = SectionsStatePagerAdapter(supportFragmentManager)

        adapter.addUserFragment(WelcomeFragment(), "WelcomeFragment")
        adapter.addUserFragment(AddUserFragment(), "AddUserFragment")

        viewPager.adapter = adapter
    }

    fun setViewPager(fragmentNumber: Int) {
        mViewPager!!.currentItem = fragmentNumber
    }

    override fun onBackPressed() {

        val builder = androidx.appcompat.app.AlertDialog.Builder(this@MainActivity)
        builder.setMessage("Are you sure you want to exit?")
        builder.setCancelable(true)
        builder.setNegativeButton("Yes") { _, _ -> finish() }
        builder.setPositiveButton("No") { dialog, _ -> dialog.cancel() }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    // Exit button exits the app
    fun clickToExit() {
        onBackPressed()
    }

    companion object {

        private val TAG = "MainActivity"
    }

}

