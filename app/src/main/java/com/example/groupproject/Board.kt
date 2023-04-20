package com.example.groupproject

import com.example.groupproject.data.Pieces

// Interface representing a chess board
interface Board {
    fun pieceAt(col: Int, row: Int): Pieces?
    fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int)
}


