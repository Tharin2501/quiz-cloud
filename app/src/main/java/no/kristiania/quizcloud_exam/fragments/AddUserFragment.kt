package no.kristiania.quizcloud_exam.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.fragment.app.Fragment
import no.kristiania.quizcloud_exam.MainActivity
import no.kristiania.quizcloud_exam.R
import no.kristiania.quizcloud_exam.db.DatabaseHelper
import no.kristiania.quizcloud_exam.exoplayer.VideoPlayer
import no.kristiania.quizcloud_exam.game.GameActivity


class AddUserFragment : Fragment() {

    internal lateinit var myDb: DatabaseHelper
    private var UserName: EditText? = null
    private var btnSave: Button? = null
    private var btnBack: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)
        myDb = DatabaseHelper(context)

        UserName = view.findViewById<View>(R.id.txt_name) as EditText
        btnSave = view.findViewById<View>(R.id.btnSaveUser) as Button
        btnBack = view.findViewById<View>(R.id.btnBack) as Button
        AddData()


        btnBack!!.setOnClickListener { (activity as MainActivity).setViewPager(0) }

        return view
    }

    fun AddData() {
        btnSave!!.setOnClickListener {
            var isInserted = myDb.insertData(UserName!!.text.toString())
            val validName = UserName!!.text.toString().trim { it <= ' ' }

            if (validName.isEmpty() || validName.length == 0 || validName == "" || validName == null) {
                Toast.makeText(activity, " Please enter a valid username ", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(activity, GameActivity::class.java)
                startActivity(intent)
            }
        }
    }
}