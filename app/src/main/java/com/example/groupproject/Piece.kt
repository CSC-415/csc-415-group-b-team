package com.example.groupproject

abstract class Piece(x: Int, y: Int, name: String, board: Board) {

    init {
        var x : Int = x
        var y : Int = y
        val name : String = name
        var board : Board
        var isWhitePiece : Boolean = false
        var moveSet : MutableList<String> = mutableListOf()
        if (name.first() == 'w') {
            isWhitePiece = true
        }
    }
}

