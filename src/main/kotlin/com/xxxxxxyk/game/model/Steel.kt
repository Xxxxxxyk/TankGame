package com.xxxxxxyk.game.model

import com.xxxxxxyk.game.business.Blockable
import com.xxxxxxyk.game.engine.Painter
import com.xxxxxxyk.game.utils.Config

class Steel(override val x: Int, override val y: Int) : Blockable {

    override val w: Int = Config.block
    override val h: Int = Config.block

    override fun draw() {
        Painter.drawImage("/img/steel.gif",x,y)
    }
}