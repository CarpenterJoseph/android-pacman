package org.pondar.pacmankotlin

import android.graphics.Bitmap
import kotlin.random.Random

//Here you need to fill out what should be in a GoldCoin and what should the constructor be
class GoldCoin(override var x: Int, override var y: Int): GameObject {
	fun toRandomPos(){
		this.x = Random.nextInt(0, Game.gridWidth - 1)
		this.y = Random.nextInt(0, Game.gridHeight - 1)
	}
}