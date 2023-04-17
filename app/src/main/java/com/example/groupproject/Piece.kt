package com.example.groupproject

import com.example.groupproject.Board

sealed interface PieceInterface {
    var x: Int
    var y: Int
    val isWhitePiece: Boolean
}
sealed class Piece(
    override var x: Int,
    override var y: Int,
    override val isWhitePiece: Boolean = false
) : PieceInterface {
    /*
     * originally the pieces were full classes, instead being moved to data classes and
     * simplifying their fields, move logic will be in ChessView instead of within
     * each type of piece class
     */
    data class Pawn(override var x: Int, override var y: Int, override val isWhitePiece: Boolean) : Piece(x, y, isWhitePiece)
    data class Rook(override var x: Int, override var y: Int, override val isWhitePiece: Boolean) : Piece(x, y, isWhitePiece)
    data class Bishop(override var x: Int, override var y: Int, override val isWhitePiece: Boolean) : Piece(x, y, isWhitePiece)
    data class Knight(override var x: Int, override var y: Int, override val isWhitePiece: Boolean) : Piece(x, y, isWhitePiece)
    data class King(override var x: Int, override var y: Int, override val isWhitePiece: Boolean) : Piece(x, y, isWhitePiece)
    data class Queen(override var x: Int, override var y: Int, override val isWhitePiece: Boolean) : Piece(x, y, isWhitePiece)
    //data class CheckerPiece(override var x: Int, override var y: Int, override val isWhitePiece: Boolean) : Piece(x, y, isWhitePiece)
}