package com.xxxxxxyk.game.model

import com.xxxxxxyk.game.business.Blockable
import com.xxxxxxyk.game.business.Moveable
import com.xxxxxxyk.game.engine.Painter
import com.xxxxxxyk.game.enum.Direction
import com.xxxxxxyk.game.utils.Config

class Tank(override var x: Int, override var y: Int) : Moveable {


    override val w: Int = Config.block - 3
    override val h: Int = Config.block - 3

    override var direction = Direction.UP

    var noDirection: Direction? = null

    fun move(direction: Direction) {

        if (direction == noDirection) {
            return
        }

        //防止位移过大
        if (this.direction != direction) {
            this.direction = direction
            return
        }

        when (direction) {
            Direction.UP -> y -= Config.speed
            Direction.DOWN -> y += Config.speed
            Direction.LEFT -> x -= Config.speed
            Direction.RIGHT -> x += Config.speed
        }

        //越界检查
        if (x <= 0) x = 0
        if (x >= Config.screenW - Config.block) x = Config.screenW - Config.block
        if (y <= 0) y = 0
        if (y >= Config.screenH - Config.block) y = Config.screenH - Config.block
    }

    override fun draw() {
        val s = when (direction) {
            Direction.UP -> "/img/tank_u.gif"
            Direction.DOWN -> "/img/tank_d.gif"
            Direction.LEFT -> "/img/tank_l.gif"
            Direction.RIGHT -> "/img/tank_r.gif"
        }
        Painter.drawImage(s, x, y)
    }

    fun bullets() : Bullet{
        var x = 0
        var y = 0

        when(direction){

        }
        var bullet : Bullet = Bullet(direction,x,y)
        return bullet
    }

    override fun willCollision(block: Blockable): Direction? {
        //去除不会发生碰撞的情况之后剩余都是碰撞
        var x = this.x
        var y = this.y

        when (direction) {
            Direction.UP -> y -= Config.speed
            Direction.DOWN -> y += Config.speed
            Direction.LEFT -> x -= Config.speed
            Direction.RIGHT -> x += Config.speed
        }

        var collision = when {
            block.y + block.h <= y -> false
            y + h <= block.y -> false
            block.x + block.w <= x -> false
            else -> x + w > block.x
        }
        return if (collision) direction else null
    }

    override fun notifyBlock(direction: Direction?, block: Blockable?) {
        noDirection = direction
    }
}