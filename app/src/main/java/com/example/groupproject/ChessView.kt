package com.example.groupproject

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.min

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val scaleFactor = 1.0f
    private var originX = 20f
    private var originY = 200f
    private var cellSide = 130f
    private val lightColor = Color.parseColor("#5A5A5A")
    private val darkColor = Color.parseColor("#BBBBBB")
    private val paint = Paint()
    private var fromCol: Int = -1
    private var fromRow: Int = -1
    private var movingPieceX = -1f
    private var movingPieceY = -1f

    private var board = Board()

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val chessBoardSide = min(width, height) * scaleFactor
        cellSide = chessBoardSide / 8f
        originX = (width - chessBoardSide) / 2f
        originY = (height - chessBoardSide) / 2f

        drawChessboard(canvas)
    }

    private fun drawChessboard(canvas: Canvas) {
        for (row in 0 until 8)
            for (col in 0 until 8)
                drawSquareAt(canvas, col, row, (col + row) % 2 == 1)
    }

    private fun drawSquareAt(canvas: Canvas, col: Int, row: Int, isDark: Boolean) {
        paint.color = if (isDark) darkColor else lightColor
        canvas.drawRect(
            originX + col * cellSide,
            originY + row * cellSide,
            originX + (col + 1) * cellSide,
            originY + (row + 1) * cellSide,
            paint
        )
    }

    private fun validateMove(piece: Piece, x: Int, y: Int) : Boolean {
        when (piece) {
            is Piece.Pawn -> validatePawn(piece, x, y)
            is Piece.Bishop -> validateBishop(piece, x, y)
            is Piece.Knight -> validateKnight(piece, x, y)
            is Piece.Rook -> validateRook(piece, x, y)
            is Piece.Queen -> validateQueen(piece, x ,y)
            is Piece.King -> validateKing(piece, x, y)
        }
    }

    private fun validatePawn (piece: Piece, x: Int, y: Int) : Boolean {
        if (piece.isWhitePiece) { //white piece logic
            if (piece.x == 6) { //starting move
                if (y == piece.y) { //non-capture move
                    if (x - piece.x == 1 || x - piece.x == 2) { //move forward 1 or 2 spaces

                    } else { //invalid move
                        return false
                    }
                } else { //capture move

                }
            } else { //non starting move

            }
        } else { //black piece logic
            if (piece.x == 1) { //starting move

            } else { //non starting move

            }
        }
    }
    private fun validateBishop (piece: Piece, x: Int, y: Int) : Boolean {}
    private fun validateKnight (piece: Piece, x: Int, y: Int) : Boolean {}
    private fun validateRook (piece: Piece, x: Int, y: Int) : Boolean {}
    private fun validateQueen (piece: Piece, x: Int, y: Int) : Boolean {}
    private fun validateKing (piece: Piece, x: Int, y: Int) : Boolean {}

    private fun checkCollision (piece: Piece, x: Int, y: Int) : Piece? {
        var targetSpace = board.board[x][y]
        if (targetSpace.piece == null) {
            return null
        } else {
            return targetSpace.piece
        }
    }

}