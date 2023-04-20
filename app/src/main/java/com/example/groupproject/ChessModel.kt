package com.example.groupproject

import com.example.groupproject.data.Piece
import com.example.groupproject.data.Pieces
import com.example.groupproject.data.Players
import java.lang.Math.abs

class ChessModel {
    var piecesBox =
        mutableSetOf<Pieces>() // A mutable set of Pieces to store the current state of the game

    init {
        reset() // Initializes the game by calling the reset function
    }

    // Function to move a piece from one position to another
    fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        if (fromCol == toCol && fromRow == toRow) return // If the source and destination positions are the same, do nothing
        val movingPiece = pieceAt(fromCol, fromRow)
            ?: return // Get the piece at the source position, if there's none, do nothing and return

        if (!validateMove(piecesBox, movingPiece.player, fromCol, fromRow, toCol, toRow)) { //validate move
            return
        }

        // If there's a piece at the destination position, remove it from the set of pieces
        pieceAt(toCol, toRow)?.let {
            if (it.player == movingPiece.player) {
                return // If the piece at the destination position belongs to the same player, do nothing and return
            }
            piecesBox.remove(it)
        }

        // Remove the moving piece from the set of pieces, and add it to the set with the new position
        piecesBox.remove(movingPiece)
        piecesBox.add(
            Pieces(
                toCol, toRow, movingPiece.player, movingPiece.piece, movingPiece.resID
            )
        )
    }

    private fun reset() {
        // Remove all pieces from the set
        piecesBox.removeAll(piecesBox)
        // Add the rooks for both players
        for (i in 0..1) {
            piecesBox.add(
                Pieces(
                    0 + i * 7, 0, Players.WHITE, Piece.ROOK, R.drawable.rook_white
                )
            )
            piecesBox.add(
                Pieces(
                    0 + i * 7, 7, Players.BLACK, Piece.ROOK, R.drawable.rook_black
                )
            )

            // Add the knights for both players
            piecesBox.add(
                Pieces(
                    1 + i * 5, 0, Players.WHITE, Piece.KNIGHT, R.drawable.knight_white
                )
            )
            piecesBox.add(
                Pieces(
                    1 + i * 5, 7, Players.BLACK, Piece.KNIGHT, R.drawable.knight_black
                )
            )

            // Add the bishops for both players
            piecesBox.add(
                Pieces(
                    2 + i * 3, 0, Players.WHITE, Piece.BISHOP, R.drawable.bishop_white
                )
            )
            piecesBox.add(
                Pieces(
                    2 + i * 3, 7, Players.BLACK, Piece.BISHOP, R.drawable.bishop_black
                )
            )
        }

        // Add the pawns for both players
        for (i in 0..7) {
            piecesBox.add(
                Pieces(
                    i, 1, Players.WHITE, Piece.PAWN, R.drawable.pawn_white
                )
            )
            piecesBox.add(
                Pieces(
                    i, 6, Players.BLACK, Piece.PAWN, R.drawable.pawn_black
                )
            )
        }

        // Add the queens and kings for both players
        piecesBox.add(
            Pieces(
                3, 0, Players.WHITE, Piece.QUEEN, R.drawable.queen_white
            )
        )
        piecesBox.add(
            Pieces(
                3, 7, Players.BLACK, Piece.QUEEN, R.drawable.queen_black
            )
        )
        piecesBox.add(
            Pieces(
                4, 0, Players.WHITE, Piece.KING, R.drawable.king_white
            )
        )
        piecesBox.add(
            Pieces(
                4, 7, Players.BLACK, Piece.KING, R.drawable.king_black
            )
        )
    }

    // Function to find the piece at a given position
    fun pieceAt(col: Int, row: Int): Pieces? {
        // Iterate through all pieces in the piecesBox set
        for (piece in piecesBox) {
            // If the piece's position matches the given position, return the piece
            if (col == piece.col && row == piece.row) {
                return piece
            }
        }
        // If no piece is found, return null
        return null
    }

    fun validateMove(piecesBox: Set<Pieces>, player: Players, fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) : Boolean {
        val movingPiece = piecesBox.find { it.col == fromCol && it.row == fromRow } ?: return false

        //player cannot move the opponents piece
        if (movingPiece.player != player) return false

        //player cant move to a space with one of their own pieces already in it
        if (piecesBox.any { it.col == toCol && it.row == toRow && it.player == player }) return false

        return when (movingPiece.piece) {
            Piece.PAWN -> validatePawn(piecesBox, player, fromCol, fromRow, toCol, toRow)
            Piece.KING -> validateKing(fromCol, fromRow, toCol, toRow)
            Piece.QUEEN -> validateQueen(piecesBox, fromCol, fromRow, toCol, toRow)
            Piece.BISHOP -> validateBishop(piecesBox, fromCol, fromRow, toCol, toRow)
            Piece.ROOK -> validateRook(piecesBox, fromCol, fromRow, toCol, toRow)
            Piece.KNIGHT -> validateKnight(fromCol, fromRow, toCol, toRow)
        }
    }

    private fun validatePawn(piecesBox: Set<Pieces>, player: Players, fromCol : Int, fromRow: Int, toCol : Int, toRow: Int) : Boolean {
        val distanceX = toCol - fromCol
        val distanceY = toRow - fromRow

        if (distanceX == 0) { //moving forward
            if (player == Players.WHITE) { //logic for white pieces
                if (distanceY == 1 && !piecesBox.any { it.col == fromCol && it.row == fromRow + 1}) { //normal move
                    return true
                } else if (distanceY == 2 && fromRow == 1 && !piecesBox.any { it.col == fromCol && it.row in 2..3 }) { //starting move
                    return true
                }
            } else { //logic for black pieces
                if (distanceY == -1 && !piecesBox.any { it.col == fromCol && it.row == fromRow - 1 }) { //normal move
                    return true
                } else if (distanceY == -2 && fromRow == 6 && !piecesBox.any { it.col == fromCol && it.row in 4..5 }) { //starting move
                    return true
                }
            }
        } else if (abs(distanceX) == 1 && abs(distanceY) == 1) { //diagonal move -> capture
            val capturedPiece = piecesBox.find { it.col == toCol && it.row == toRow } //get piece at destination space
            if (capturedPiece != null) { //if piece is there to capture
                return true
            }
        }
        return false
    }

    private fun validateKnight(fromCol : Int, fromRow: Int, toCol : Int, toRow: Int) : Boolean {
        val distanceX = toCol - fromCol
        val distanceY = toRow - fromRow

        if ((abs(distanceX) == 2 && abs(distanceY) == 1) || (abs(distanceX) == 1 && abs(distanceY) == 2)) {
            return true
        }

        return false
    }

    private fun validateBishop(piecesBox: Set<Pieces>, fromCol: Int, fromRow: Int, toCol : Int, toRow: Int) : Boolean {
        val distanceX = toCol - fromCol
        val distanceY = toRow - fromRow

        if (abs(distanceX) == abs(distanceY) && !isPieceBetween(fromCol, fromRow, toCol, toRow, piecesBox)) {
            return true
        }

        return false
    }

    private fun validateRook(piecesBox: Set<Pieces>, fromCol: Int, fromRow: Int, toCol : Int, toRow: Int) : Boolean {
        val distanceX = toCol - fromCol
        val distanceY = toRow - fromRow

        if ((distanceX == 0 || distanceY == 0) && !isPieceBetween(fromCol, fromRow, toCol, toRow, piecesBox)) {
            return true
        }

        return false
    }

    private fun validateQueen(piecesBox: Set<Pieces>, fromCol: Int, fromRow: Int, toCol : Int, toRow: Int) : Boolean {
        if (validateBishop(piecesBox, fromCol, fromRow, toCol, toRow) || validateRook(piecesBox, fromCol, fromRow, toCol, toRow)) {
            return true
        }

        return false
    }

    private fun validateKing(fromCol : Int, fromRow: Int, toCol : Int, toRow: Int) : Boolean {
        val distanceX = toCol - fromCol
        val distanceY = toRow - fromRow

        if (abs(distanceX) <= 1 && abs(distanceY) <= 1) {
            return true
        }

        return false
    }

    private fun isPieceBetween(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int, piecesBox: Set<Pieces>) : Boolean {
        var distanceX = 0
        if (toCol > fromCol) {
            distanceX = 1
        } else if (toCol < fromCol) {
            distanceX = -1
        }

        var distanceY = 0
        if (toRow > fromRow) {
            distanceY = 1
        } else if (toRow < fromRow) {
            distanceY = -1
        }

        var x = fromCol + distanceX
        var y = fromRow + distanceY

        while (x != toCol || y != toRow) {
            if (piecesBox.any { it.col == x && it.row == y }) {
                return true //piece was found in between start and end locations
            }

            x += distanceX
            y += distanceY
        }

        return false //no pieces found in between start and end locations
    }
}
