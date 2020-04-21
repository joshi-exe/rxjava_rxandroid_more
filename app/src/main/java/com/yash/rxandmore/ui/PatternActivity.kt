package com.yash.rxandmore.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.yash.rxandmore.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_pattern.*
import java.util.concurrent.TimeUnit

/**
 * Created by Joshi on 07-04-2020.
 */
class PatternActivity : AppCompatActivity() {

    private lateinit var patternObservable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pattern)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        createPattern()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        patternObservable.dispose()
        super.onStop()
    }

    private fun createPattern() {
        patternObservable = Observable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Observable.just(it) }
            .takeWhile { t -> t < 15 }
            .map { "*".repeat(it.toInt()) }
            .subscribe { tvPattern.text = it }
    }
}