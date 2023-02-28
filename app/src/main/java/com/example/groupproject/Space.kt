package com.example.groupproject

import java.lang.reflect.Constructor

class Space {
    var isBlackSpace : Boolean = false

    constructor(x : Int, y : Int) {
        val x = x
        val y = y
        val piece = null

        if (x % 2 != 0 && y % 2 == 0) {
            isBlackSpace = true
        }
        if (x % 2 == 0 && y % 2 != 0) {
            isBlackSpace = true
        }
    }

    constructor(x : Int, y : Int, piece : Piece?) {
        val x = x
        val y = y
        val piece = piece

        if (x % 2 != 0 && y % 2 == 0) {
            isBlackSpace = true
        }
        if (x % 2 == 0 && y % 2 != 0) {
            isBlackSpace = true
        }
    }
}


