package com.xxxxxxyk.game.model

interface BaseElement {

    //位置
    val x : Int
    val y : Int

    //宽高
    val w : Int
    val h : Int

    fun draw()
}