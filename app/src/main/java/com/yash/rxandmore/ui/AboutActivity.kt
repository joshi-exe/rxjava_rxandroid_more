package com.yash.rxandmore.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.yash.rxandmore.R
import kotlinx.android.synthetic.main.activity_about.*


/**
 * Created by Joshi on 21-04-2020.
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        onClick()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun onClick() {

        ivLinkedIn.setOnClickListener {
            val url = "https://www.linkedin.com/in/69yash"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        ivGitHub.setOnClickListener {
            val url = "https://github.com/joshi-exe?tab=repositories"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        ivStackOverFlow.setOnClickListener {
            val url = "https://stackoverflow.com/users/story/11598941?view=Timeline"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        ivMailMe.setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "69yash@gmail.com", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Yash,\n")
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_email)))
        }
    }
}