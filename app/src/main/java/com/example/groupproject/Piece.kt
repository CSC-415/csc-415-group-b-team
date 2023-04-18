package com.example.groupproject

import com.example.groupproject.Board

sealed interface PieceInterface {
    var x: Int
    var y: Int
    val isWhitePiece: Boolean
    val asset: Int
}
sealed class Piece(
    override var x: Int,
    override var y: Int,
    override val isWhitePiece: Boolean = false,
    override val asset: Int
) : PieceInterface {
    /*
     * originally the pieces were full classes, instead being moved to data classes and
     * simplifying their fields, move logic will be in ChessView instead of within
     * each type of piece class
     */
    data class Pawn(override var x: Int, override var y: Int, override val isWhitePiece: Boolean, override val asset: Int) : Piece(x, y, isWhitePiece, asset)
    data class Rook(override var x: Int, override var y: Int, override val isWhitePiece: Boolean, override val asset: Int) : Piece(x, y, isWhitePiece, asset)
    data class Bishop(override var x: Int, override var y: Int, override val isWhitePiece: Boolean, override val asset: Int) : Piece(x, y, isWhitePiece, asset)
    data class Knight(override var x: Int, override var y: Int, override val isWhitePiece: Boolean, override val asset: Int) : Piece(x, y, isWhitePiece, asset)
    data class King(override var x: Int, override var y: Int, override val isWhitePiece: Boolean, override val asset: Int) : Piece(x, y, isWhitePiece, asset)
    data class Queen(override var x: Int, override var y: Int, override val isWhitePiece: Boolean, override val asset: Int) : Piece(x, y, isWhitePiece, asset)
}