package org.pondar.pacmankotlin

class Pacman constructor(override var x: Int, override var y: Int, var game: Game): GameObject {

	fun move(dx: Int, dy: Int){
		this.x += dx
		this.y += dy
		this.x = this.x.coerceIn(0 until Game.gridHeight)
		this.y = this.y.coerceIn(0 until Game.gridWidth)
		game.nextTurn()
	}


}
