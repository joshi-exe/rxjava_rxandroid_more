package com.yash.rxandmore

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.yash.rxandmore.extensions.launchActivity
import com.yash.rxandmore.ui.PatternActivity
import com.yash.rxandmore.ui.game.TicTacToe
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    //  TAG for logs
    private val TAG: String = "HOME"

    //  array to use for description label.
    private val descriptionBannerArray = arrayListOf(
        "Explore around to find various reactive functionality",
        "Find the refresh icon on top-right corner to repeat all this"
    )

    private lateinit var bannerObservable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClicks()  // click events
        refreshDescriptionLabel()      // for refreshing description texts
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                bannerObservable.dispose()
                refreshDescriptionLabel()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onStop() {
        bannerObservable.dispose()
        super.onStop()
    }

    private fun onClicks() {

        /*btnFormChecks.setOnClickListener {
            launchActivity<FormChecksActivity> { }
        }*/

        btnPattern.setOnClickListener {
            launchActivity<PatternActivity> { }
        }

        btnGame.setOnClickListener {
            launchActivity<TicTacToe> { }
        }
    }

    private fun refreshDescriptionLabel() {

        // to set initial greetings every first instance
        tvDescriptionBanner.text = getString(R.string.greetings)

        // from iterable observable
        // taking single elements from array
        // emitting every 6 seconds with delay operator
        // changing textView for every value emitted; so it acts like banner
        bannerObservable = Observable.fromIterable(descriptionBannerArray)
            .subscribeOn(Schedulers.io())
            .concatMap { s -> Observable.just(s).delay(6, TimeUnit.SECONDS) }
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally{
                bannerObservable.dispose()
            }
            .subscribe {
                Log.i(TAG, it.toString())
                tvDescriptionBanner.text = it.toString()
            }
    }
}
