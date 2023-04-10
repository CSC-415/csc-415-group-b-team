package com.example.groupproject

class Space {
    var isBlackSpace : Boolean = false
    var piece : Piece? = null

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
        this.piece = piece

        if (x % 2 != 0 && y % 2 == 0) {
            isBlackSpace = true
        }
        if (x % 2 == 0 && y % 2 != 0) {
            isBlackSpace = true
        }
    }
}


