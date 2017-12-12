package com.xxxxxxyk.game.model

import com.xxxxxxyk.game.engine.Painter
import com.xxxxxxyk.game.enum.Direction
import com.xxxxxxyk.game.utils.Config

class Bullet(var direction: Direction , override val x: Int, override val y: Int) : BaseElement{
    override val w: Int = Config.block

    override val h: Int = Config.block

    override fun draw() {
        var img = when(direction){
            Direction.UP -> "/img/bullet_u.gif"
            Direction.DOWN -> "/img/bullet_d.gif"
            Direction.LEFT -> "/img/bullet_l.gif"
            Direction.RIGHT -> "/img/bullet_r.gif"
        }
        Painter.drawImage(img,x,y)
    }
}