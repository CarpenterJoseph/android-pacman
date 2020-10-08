package org.pondar.pacmankotlin

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.util.*


/**
 *
 * This class should contain all your game logic
 */

class Game(private var context: Context, pointsView: TextView, highscoreView: TextView) {

    val pref: SharedPreferences = context.getSharedPreferences("Prefs", 0)

    private var pointsView: TextView = pointsView
    private var highscoreView: TextView = highscoreView
    private var points : Int = 0
    //bitmap of the pacman
    var pacBitmap: Bitmap
    var enemyBitmap: Bitmap
    var coinBitmap: Bitmap



    //the list of goldcoins - initially empty
    var coins = ArrayList<GoldCoin>()
    //a reference to the gameview
    private var gameView: GameView? = null
    private var h: Int = 0
    private var w: Int = 0 //height and width of screen

    //game vars


    var pacman = Pacman(4, 4, this)
    var enemy = Enemy(6,6, pacman)
    var coin = GoldCoin(2,3)

    companion object {
        var gridHeight: Int = 8
        var gridWidth: Int = 8
    }
    var gameBoard: Array<Array<GameObject>> = arrayOf()


    init {
        pacBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pacman)
        enemyBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy)
        coinBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.coin)
    }

    fun setGameView(view: GameView) {
        this.gameView = view
    }

    fun setSize(h: Int, w: Int) {
        this.h = h
        this.w = w
    }

    fun newGame() {
        pacman.x = 4
        pacman.y = 4
        enemy.x = 6
        enemy.y = 6
        coin.toRandomPos()
        //reset the points
        points = 0
        highscoreView.text = "${context.resources.getString(R.string.high_score)} ${pref.getInt("HighScore", 0)}"
        pointsView.text = "${context.resources.getString(R.string.points)} $points"
        gameView?.invalidate() //redraw screen
    }

    fun nextTurn(){
        Log.d("GAME", "${pacman.x} ${pacman.y}")
        enemy.move()
        doCollisionCheck()
        gameView?.invalidate() //redraw screen
    }

    //TODO check if the pacman touches a gold coin
    //and if yes, then update the neccesseary data
    //for the gold coins and the points
    //so you need to go through the arraylist of goldcoins and
    //check each of them for a collision with the pacman
    fun doCollisionCheck() {
        if(enemy.x == pacman.x && enemy.y == pacman.y)
            gameOver()
        if(coin.x == pacman.x && coin.x == pacman.y){
            points++
            coin.toRandomPos()
            gameView?.invalidate() //redraw screen
        }
    }

    fun gameOver(){
        // 0 - for private mode
        val editor = pref.edit()
        if(pref.getInt("HighScore", -1) < points){
            editor.putInt("HighScore", points)
            editor.commit()
        }

        Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show()
        newGame()
    }
}