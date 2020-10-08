package org.pondar.pacmankotlin

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View


//note we now create our own view class that extends the built-in View class
class GameView : View {

    private var game: Game? = null
    private var h: Int = 0
    private var w: Int = 0 //used for storing our height and width of the view

    fun setGame(game: Game?) {
        this.game = game
    }


    /* The next 3 constructors are needed for the Android view system,
	when we have a custom view.
	 */
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //In the onDraw we put all our code that should be
    //drawn whenever we update the screen.
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        //Here we get the height and weight
        h = canvas.width
        w = canvas.width
        //update the size for the canvas to the game.
        game?.setSize(h, w)
        Log.d("GAMEVIEW", "h = $h, w = $w")

//        //are the coins initiazlied?
//        if (!(game!!.coinsInitialized))
//            game?.initializeGoldcoins()


        //Making a new paint object
        val paint = Paint()
        canvas.drawColor(Color.WHITE) //clear entire canvas to white color

        //draw pacman
        canvas.drawBitmap(
                game!!.coinBitmap,
                null,
                RectF(
                        game?.coin?.canvasPosX(w, game?.coinBitmap!!.width)!!,
                        game?.coin?.canvasPosY(h, game?.coinBitmap!!.height)!!,
                        game?.coin?.canvasPosX(w, game?.coinBitmap!!.width)!! + w / Game.gridWidth,
                        game?.coin?.canvasPosY(h, game?.coinBitmap!!.height)!! + w / Game.gridHeight
                ),
                paint)

        //draw pacman
        canvas.drawBitmap(
                game!!.pacBitmap,
                null,
                RectF(
                        game?.pacman?.canvasPosX(w, game?.pacBitmap!!.width)!!,
                        game?.pacman?.canvasPosY(h, game?.pacBitmap!!.height)!!,
                        game?.pacman?.canvasPosX(w, game?.pacBitmap!!.width)!! + w / Game.gridWidth,
                        game?.pacman?.canvasPosY(h, game?.pacBitmap!!.height)!! + w / Game.gridHeight
                ),
                paint)

        //draw enemy
        canvas.drawBitmap(
                game!!.enemyBitmap,
                null,
                RectF(
                        game?.enemy?.canvasPosX(w, game?.enemyBitmap!!.width)!!,
                        game?.enemy?.canvasPosY(h, game?.enemyBitmap!!.height)!!,
                        game?.enemy?.canvasPosX(w, game?.enemyBitmap!!.width)!! + w / Game.gridWidth,
                        game?.enemy?.canvasPosY(h, game?.enemyBitmap!!.height)!! + w / Game.gridHeight
                ),
                paint)

        //TODO loop through the list of goldcoins and draw them.

        game?.doCollisionCheck()
        super.onDraw(canvas)
    }

}
