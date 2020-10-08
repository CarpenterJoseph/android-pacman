package org.pondar.pacmankotlin

import kotlin.random.Random

class Enemy (override var x: Int, override var y: Int, var pacman: Pacman): GameObject {

	fun move(){
		if(pacman.x <= this.x && pacman.y <= this.y){
			if(Random.nextInt(0,1) > 0){
				this.moveTo(this.x - 1, this.y - 2)
			} else {
				this.moveTo(this.x - 2, this.y - 1)
			}
		} else if(pacman.x >= this.x && pacman.y <= this.y){
			if(Random.nextInt(0,1) > 0){
				this.moveTo(this.x + 1, this.y - 2)
			} else {
				this.moveTo(this.x + 2, this.y - 1)
			}
		} else if(pacman.x >= this.x && pacman.y >= this.y){
			if(Random.nextInt(0,1) > 0){
				this.moveTo(this.x + 1, this.y + 2)
			} else {
				this.moveTo(this.x + 2, this.y + 1)
			}
		} else {
			if(Random.nextInt(0,1) > 0){
				this.moveTo(this.x - 1, this.y + 2)
			} else {
				this.moveTo(this.x - 2, this.y + 1)
			}
		}
	}
}
