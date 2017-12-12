package com.xxxxxxyk.game.business

import com.xxxxxxyk.game.enum.Direction
import com.xxxxxxyk.game.model.BaseElement

/**
 * 标记物体可运动
 */
interface Moveable : BaseElement {

    //运动方向
    val direction : Direction

    /**
     *  返回将要碰撞的方向
     */
    fun willCollision(block : Blockable) : Direction?

    /**
     *  得到将要碰撞的方向和阻塞块
     */
    fun notifyBlock(direction: Direction? , block: Blockable?)

}