package com.xxxxxxyk.game.view

import com.xxxxxxyk.game.business.Blockable
import com.xxxxxxyk.game.business.Moveable
import com.xxxxxxyk.game.engine.Window
import com.xxxxxxyk.game.enum.Direction
import com.xxxxxxyk.game.model.*
import com.xxxxxxyk.game.utils.Config
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import java.io.File

class Launcher : Window(title = "游戏主界面", width = Config.screenW, height = Config.screenH) {

    private var map = ArrayList<BaseElement>()
    private lateinit var tank: Tank

    override fun onCreate() {
        map = createMap()
    }

    /**
     *  初始化地图
     * */
    private fun createMap(): ArrayList<BaseElement> {
        var listMap = ArrayList<BaseElement>()
        //读取地图配置文件,得到地图每一行
        var file = File(javaClass.getResource("/map/1.map").path)
        val lines = file.readLines()

        var row = 0
        lines.forEach {
            //得到地图一行中每个元素
            var column = 0
            it.toCharArray().forEach {
                when (it) {
                    '1' -> listMap.add(Wall(column * Config.block, row * Config.block))
                    '2' -> listMap.add(Steel(column * Config.block, row * Config.block))
                }
                column++
            }
            row++
        }

        //添加一个坦克
        tank = Tank(Config.block * 4, Config.block * 12)
        listMap.add(tank)
        return listMap
    }

    override fun onDisplay() {
        map.forEach {
            it.draw()
        }
    }

    override fun onRefresh() {
        //遍历界面所有元素,找到所有可移动的和不可移动的元素
        map.filter { it is Moveable }.forEach { move ->

            move as Moveable
            var noDirection : Direction? = null
            var noBlock : Blockable? = null

            map.filter { it is Blockable }.forEach block@ { block ->
                //判断移动和阻塞是否将要发生碰撞
                block as Blockable

                //得到将要碰撞的方向
                var direction = move.willCollision(block)

                //let函数只有不为空的时候才会走
                direction?.let {
                    //找到了将要碰撞的方向,就不需要往后继续循环了
                    noDirection = direction
                    noBlock = block
                    return@block
                }
            }

            //找到了将要碰撞的方向,通知移动物体
            move.notifyBlock(noDirection,noBlock)
        }
    }

    override fun onKeyPressed(event: KeyEvent) {
        //判断用户按键
        when (event.code) {
            KeyCode.UP -> tank.move(Direction.UP)
            KeyCode.DOWN -> tank.move(Direction.DOWN)
            KeyCode.LEFT -> tank.move(Direction.LEFT)
            KeyCode.RIGHT -> tank.move(Direction.RIGHT)
            KeyCode.X ->    map.add(tank.bullets())
        }
    }

}

fun main(args: Array<String>) {
    Application.launch(Launcher::class.java)
}