package com.yash.rxandmore.ui.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.yash.rxandmore.R
import com.yash.rxandmore.extensions.launchActivity
import com.yash.rxandmore.ui.AboutActivity
import com.yash.rxandmore.utils.AppUtils
import kotlinx.android.synthetic.main.layout_tic_tac_toe.*

/**
 * Created by Joshi on 21-04-2020.
 */
class TicTacToe : AppCompatActivity() {

    private var turnCounter: Int = 1
    private var alertCounter: Int = 1

    private var player1: String = ""
    private var player2: String = ""
    private var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tic_tac_toe)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initGameLogic()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_info -> launchActivity<AboutActivity> { }
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_info, menu)
        return true
    }

    private fun initGameLogic() {

        initPlayerNames()

        a1.setOnClickListener {
            if (turnCounter % 2 == 0) {
                a1.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                a1.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            a1.isClickable = false
        }

        a2.setOnClickListener {
            if (turnCounter % 2 == 0) {
                a2.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                a2.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            a2.isClickable = false
        }

        a3.setOnClickListener {
            if (turnCounter % 2 == 0) {
                a3.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                a3.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            a3.isClickable = false
        }

        b1.setOnClickListener {
            if (turnCounter % 2 == 0) {
                b1.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                b1.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            b1.isClickable = false
        }

        b2.setOnClickListener {
            if (turnCounter % 2 == 0) {
                b2.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                b2.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            b2.isClickable = false
        }

        b3.setOnClickListener {
            if (turnCounter % 2 == 0) {
                b3.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                b3.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            b3.isClickable = false
        }

        c1.setOnClickListener {
            if (turnCounter % 2 == 0) {
                c1.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                c1.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            c1.isClickable = false
        }

        c2.setOnClickListener {
            if (turnCounter % 2 == 0) {
                c2.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                c2.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            c2.isClickable = false
        }

        c3.setOnClickListener {
            if (turnCounter % 2 == 0) {
                c3.text = getString(R.string.o)
                turnCounter++
                checkWinner()
                checkTurn()
            } else {
                c3.text = getString(R.string.x)
                turnCounter++
                checkWinner()
                checkTurn()
            }
            c3.isClickable = false
        }
    }

    private fun doOnFinish() {
        if (alertCounter <= 2) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.game_over)
            builder.setMessage(message)

            builder.setPositiveButton(getString(R.string.play_again)) { _, _ ->
                resetGame()
            }

            builder.setNegativeButton(getString(R.string.quit)) { _, _ ->
                finishAndRemoveTask()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.setCanceledOnTouchOutside(false)
            alertDialog.show()
        }
    }

    @SuppressLint("InflateParams")
    private fun initPlayerNames() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_player_names, null)
        builder.setView(dialogView)
        val dialog = builder.create()

        val p1 = dialogView.findViewById(R.id.etPlayer1) as EditText
        val p2 = dialogView.findViewById(R.id.etPlayer2) as EditText
        val startGame = dialogView.findViewById(R.id.tvStartGame) as TextView
        val quitGame = dialogView.findViewById(R.id.tvQuitGame) as TextView

        if (player1.isNotEmpty() && player2.isNotEmpty()) {
            p1.setText(player1)
            p2.setText(player2)
        }

        startGame.setOnClickListener {

            val name1 = p1.text.toString().trim()
            val name2 = p2.text.toString().trim()

            if (name1.isEmpty() || name2.isEmpty()) {
                AppUtils.longToast(this, getString(R.string.enter_valid_name))
                return@setOnClickListener
            }

            player1 = name1
            player2 = name2
            tvPlayer1.text = name1
            tvPlayer2.text = name2
            tvTurnIndicator.text = "$name1's turn.."
            dialog.cancel()
        }

        quitGame.setOnClickListener {
            finishAndRemoveTask()
        }

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun resetGame() {
        a1.text = ""
        a2.text = ""
        a3.text = ""
        b1.text = ""
        b2.text = ""
        b3.text = ""
        c1.text = ""
        c2.text = ""
        c3.text = ""

        turnCounter = 1
        alertCounter = 1
        message = ""

        initGameLogic()
    }

    private fun checkWinner() {

        val a1 = a1.text.toString().trim()
        val a2 = a2.text.toString().trim()
        val a3 = a3.text.toString().trim()
        val b1 = b1.text.toString().trim()
        val b2 = b2.text.toString().trim()
        val b3 = b3.text.toString().trim()
        val c1 = c1.text.toString().trim()
        val c2 = c2.text.toString().trim()
        val c3 = c3.text.toString().trim()

        if (
            (
                    a1 == getString(R.string.x) &&
                            a2 == getString(R.string.x) &&
                            a3 == getString(R.string.x)) ||
            (
                    b1 == getString(R.string.x) &&
                            b2 == getString(R.string.x) &&
                            b3 == getString(R.string.x)) ||
            (
                    c1 == getString(R.string.x) &&
                            c2 == getString(R.string.x) &&
                            c3 == getString(R.string.x)) ||
            (
                    a1 == getString(R.string.x) &&
                            b1 == getString(R.string.x) &&
                            c1 == getString(R.string.x)) ||
            (
                    a2 == getString(R.string.x) &&
                            b2 == getString(R.string.x) &&
                            c2 == getString(R.string.x)) ||
            (
                    a3 == getString(R.string.x) &&
                            b3 == getString(R.string.x) &&
                            c3 == getString(R.string.x)) ||
            (
                    a1 == getString(R.string.x) &&
                            b2 == getString(R.string.x) &&
                            c3 == getString(R.string.x)) ||
            (
                    a3 == getString(R.string.x) &&
                            b2 == getString(R.string.x) &&
                            c1 == getString(R.string.x))
        ) {
            if (alertCounter <= 1) {
                message = "$player1 won the game!"
                alertCounter++
                doOnFinish()
            }
        } else if (
            (
                    a1 == getString(R.string.o) &&
                            a2 == getString(R.string.o) &&
                            a3 == getString(R.string.o)) ||
            (
                    b1 == getString(R.string.o) &&
                            b2 == getString(R.string.o) &&
                            b3 == getString(R.string.o)) ||
            (
                    c1 == getString(R.string.o) &&
                            c2 == getString(R.string.o) &&
                            c3 == getString(R.string.o)) ||
            (
                    a1 == getString(R.string.o) &&
                            b1 == getString(R.string.o) &&
                            c1 == getString(R.string.o)) ||
            (
                    a2 == getString(R.string.o) &&
                            b2 == getString(R.string.o) &&
                            c2 == getString(R.string.o)) ||
            (
                    a3 == getString(R.string.o) &&
                            b3 == getString(R.string.o) &&
                            c3 == getString(R.string.o)) ||
            (
                    a1 == getString(R.string.o) &&
                            b2 == getString(R.string.o) &&
                            c3 == getString(R.string.o)) ||
            (
                    a3 == getString(R.string.o) &&
                            b2 == getString(R.string.o) &&
                            c1 == getString(R.string.o))
        ) {
            if (alertCounter <= 1) {
                message = "$player2 won the game!"
                alertCounter++
                doOnFinish()
            }
        } else {
            message = getString(R.string.tie)
            checkIfGameIsFinished()
        }
    }

    private fun checkIfGameIsFinished() {
        if (
            !a1.text.isNullOrEmpty() && !a2.text.isNullOrEmpty() &&
            !a3.text.isNullOrEmpty() && !b1.text.isNullOrEmpty() &&
            !b2.text.isNullOrEmpty() && !b3.text.isNullOrEmpty() &&
            !c1.text.isNullOrEmpty() && !c2.text.isNullOrEmpty() && !c3.text.isNullOrEmpty()
        ) {
            if (alertCounter <= 1) {
                doOnFinish()
            }
        }
    }

    private fun checkTurn() {
        if (turnCounter % 2 != 0) {
            tvTurnIndicator.text = "$player1's turn.."
        } else {
            tvTurnIndicator.text = "$player2's turn.."
        }
    }
}