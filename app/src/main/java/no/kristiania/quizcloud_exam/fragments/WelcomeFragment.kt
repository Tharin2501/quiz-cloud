package no.kristiania.quizcloud_exam.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import no.kristiania.quizcloud_exam.MainActivity
import no.kristiania.quizcloud_exam.R


class WelcomeFragment : Fragment() {
    private var btnNavFrag2: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment1_layout, container, false)

        btnNavFrag2 = view.findViewById<View>(R.id.btnNameScreen) as Button
        Log.d(TAG, "onCreateView: started.")

        btnNavFrag2!!.setOnClickListener { (activity as MainActivity).setViewPager(1) }

        return view
    }

    companion object {

        private val TAG = "WelcomeFragment"
    }
}
