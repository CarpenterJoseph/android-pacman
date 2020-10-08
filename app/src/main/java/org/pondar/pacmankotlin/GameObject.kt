package org.pondar.pacmankotlin

import android.graphics.Bitmap

interface GameObject {
	var x: Int
	var y: Int

	fun moveTo(x: Int, y: Int){
		this.x = x
		this.y = y
	}

	fun canvasPosX(canvasWidth: Int, bitmapWidth: Int) : Float{
		return ((canvasWidth - bitmapWidth) / (Game.gridWidth - 1) * x).toFloat()
	}

	fun canvasPosY(canvasHeight: Int, bitmapHeight: Int) : Float{
		return ((canvasHeight - bitmapHeight) / (Game.gridHeight - 1) * y).toFloat()
	}
}
