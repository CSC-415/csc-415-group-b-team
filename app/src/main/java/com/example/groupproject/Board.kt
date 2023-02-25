package com.example.groupproject

class Board {

    var board = Array(8) { Array(8) { Any() } }

    val blackKingX = 0
    val blackKingY = 4
    val whiteKingX = 7
    val whiteKingY = 4

    init {

        //init board with empty space objects for now
        for (i in 0..board.size) {
            for (j in 0..board[i].size) {
                board[i][j] = Space(i,j)
            }
        }

        //put pieces in starting spaces
        board[0][0] = Space(0, 0, Rook(0, 0, "bR", this))
        board[0][1] = Space(0, 1, Knight(0, 1, "bN", this))
        board[0][2] = Space(0, 2, Bishop(0, 2, "bB", this))
        board[0][3] = Space(0, 3, Queen(0, 3, "bQ", this))
        board[0][4] = Space(0, 4, King(0, 4, "bK", this))
        board[0][5] = Space(0, 5, Bishop(0, 5, "bB", this))
        board[0][6] = Space(0, 6, Knight(0, 6, "bN", this))
        board[0][7] = Space(0, 7, Rook(0, 7, "bR", this))
        board[1][0] = Space(1, 0, Pawn(1, 0, "bp", this))
        board[1][1] = Space(1, 1, Pawn(1, 1, "bp", this))
        board[1][2] = Space(1, 2, Pawn(1, 2, "bp", this))
        board[1][3] = Space(1, 3, Pawn(1, 3, "bp", this))
        board[1][4] = Space(1, 4, Pawn(1, 4, "bp", this))
        board[1][5] = Space(1, 5, Pawn(1, 5, "bp", this))
        board[1][6] = Space(1, 6, Pawn(1, 6, "bp", this))
        board[1][7] = Space(1, 7, Pawn(1, 7, "bp", this))

        board[7][0] = Space(7, 0, Rook(7, 0, "wR", this))
        board[7][1] = Space(7, 1, Knight(7, 1, "wN", this))
        board[7][2] = Space(7, 2, Bishop(7, 2, "wB", this))
        board[7][3] = Space(7, 3, Queen(7, 3, "wQ", this))
        board[7][4] = Space(7, 4, King(7, 4, "wK", this))
        board[7][5] = Space(7, 5, Bishop(7, 5, "wB", this))
        board[7][6] = Space(7, 6, Knight(7, 6, "wN", this))
        board[7][7] = Space(7, 7, Rook(7, 7, "wR", this))
        board[6][0] = Space(6, 0, Pawn(6, 0, "wp", this))
        board[6][1] = Space(6, 1, Pawn(6, 1, "wp", this))
        board[6][2] = Space(6, 2, Pawn(6, 2, "wp", this))
        board[6][3] = Space(6, 3, Pawn(6, 3, "wp", this))
        board[6][4] = Space(6, 4, Pawn(6, 4, "wp", this))
        board[6][5] = Space(6, 5, Pawn(6, 5, "wp", this))
        board[6][6] = Space(6, 6, Pawn(6, 6, "wp", this))
        board[6][7] = Space(6, 7, Pawn(6, 7, "wp", this))
    }
}