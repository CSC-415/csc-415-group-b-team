package com.example.groupproject

import com.example.groupproject.data.Pieces

interface Board {
    fun pieceAt(col: Int, row: Int): Pieces?
    fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int)
}

//class Board {
//
//    var board = arrayOf<Array<Space>>()
//    var blackCap = mutableListOf<Piece>()
//    var whiteCap = mutableListOf<Piece>()
//    val blackKingX = 0
//    val blackKingY = 4
//    val whiteKingX = 7
//    val whiteKingY = 4
//
//    init {
//
//        //init board with empty space objects for now
//        for (i in 0..board.size) {
//            for (j in 0..board[i].size) {
//                board[i][j] = Space(i,j)
//            }
//        }
//
//        //put pieces in starting spaces
//
//        //black pieces
//        board[0][0] = Space(0, 0, Piece.Rook(0, 0, false, R.drawable.rook_black))
//        board[0][1] = Space(0, 1, Piece.Knight(0, 1, false, R.drawable.knight_black))
//        board[0][2] = Space(0, 2, Piece.Bishop(0, 2, false, R.drawable.bishop_black))
//        board[0][3] = Space(0, 3, Piece.Queen(0, 3, false, R.drawable.queen_black))
//        board[0][4] = Space(0, 4, Piece.King(0, 4, false, R.drawable.king_black))
//        board[0][5] = Space(0, 5, Piece.Bishop(0, 5, false,R.drawable.bishop_black))
//        board[0][6] = Space(0, 6, Piece.Knight(0, 6, false,R.drawable.knight_black))
//        board[0][7] = Space(0, 7, Piece.Rook(0, 7, false, R.drawable.rook_black))
//        board[1][0] = Space(1, 0, Piece.Pawn(1, 0, false, R.drawable.pawn_black))
//        board[1][1] = Space(1, 1, Piece.Pawn(1, 1, false, R.drawable.pawn_black))
//        board[1][2] = Space(1, 2, Piece.Pawn(1, 2, false, R.drawable.pawn_black))
//        board[1][3] = Space(1, 3, Piece.Pawn(1, 3, false, R.drawable.pawn_black))
//        board[1][4] = Space(1, 4, Piece.Pawn(1, 4, false, R.drawable.pawn_black))
//        board[1][5] = Space(1, 5, Piece.Pawn(1, 5, false, R.drawable.pawn_black))
//        board[1][6] = Space(1, 6, Piece.Pawn(1, 6, false, R.drawable.pawn_black))
//        board[1][7] = Space(1, 7, Piece.Pawn(1, 7, false, R.drawable.pawn_black))
//
//        //white pieces
//        board[7][0] = Space(7, 0, Piece.Rook(7, 0, true, R.drawable.rook_white))
//        board[7][1] = Space(7, 1, Piece.Knight(7, 1, true, R.drawable.knight_white))
//        board[7][2] = Space(7, 2, Piece.Bishop(7, 2, true, R.drawable.bishop_white))
//        board[7][3] = Space(7, 3, Piece.Queen(7, 3, true, R.drawable.queen_white))
//        board[7][4] = Space(7, 4, Piece.King(7, 4, true, R.drawable.king_white))
//        board[7][5] = Space(7, 5, Piece.Bishop(7, 5, true, R.drawable.bishop_white))
//        board[7][6] = Space(7, 6, Piece.Knight(7, 6, true, R.drawable.knight_white))
//        board[7][7] = Space(7, 7, Piece.Rook(7, 7, true, R.drawable.rook_white))
//        board[6][0] = Space(6, 0, Piece.Pawn(6, 0, true, R.drawable.pawn_white))
//        board[6][1] = Space(6, 1, Piece.Pawn(6, 1, true, R.drawable.pawn_white))
//        board[6][2] = Space(6, 2, Piece.Pawn(6, 2, true, R.drawable.pawn_white))
//        board[6][3] = Space(6, 3, Piece.Pawn(6, 3, true, R.drawable.pawn_white))
//        board[6][4] = Space(6, 4, Piece.Pawn(6, 4, true, R.drawable.pawn_white))
//        board[6][5] = Space(6, 5, Piece.Pawn(6, 5, true, R.drawable.pawn_white))
//        board[6][6] = Space(6, 6, Piece.Pawn(6, 6, true, R.drawable.pawn_white))
//        board[6][7] = Space(6, 7, Piece.Pawn(6, 7, true, R.drawable.pawn_white))
//    }
//}

