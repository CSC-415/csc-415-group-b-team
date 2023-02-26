package com.example.groupproject

abstract class Piece(val x: Int,val y: Int,val name: String,val board: Board) {

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
class Pawn(x: Int, y: Int, name: String, board: Board) : Piece(x, y, name, board) {
    init {
        var x : Int = x
        var y : Int = y
        val name : String = "pawn"
        var board : Board
        var isWhitePiece : Boolean = false
        var moveSet : MutableList<String> = mutableListOf()
        if (name.first() == 'w') {
            isWhitePiece = true
        }
    }
}
class Rook(x: Int, y: Int, name: String, board: Board) : Piece(x, y, name, board) {

}
class King(x: Int, y: Int, name: String, board: Board) : Piece(x, y, name, board) {

}
class Queen(x: Int, y: Int, name: String, board: Board) : Piece(x, y, name, board) {

}
class Bishop(x: Int, y: Int, name: String, board: Board) : Piece(x, y, name, board) {

}
class Knight(x: Int, y: Int, name: String, board: Board) : Piece(x, y, name, board) {

}
class CheckerPeice(x: Int, y: Int, name: String, board: Board) : Piece(x, y, name, board) {

}
