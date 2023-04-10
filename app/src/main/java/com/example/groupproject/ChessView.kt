package com.example.groupproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
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

    private fun validateMove(piece: Piece, x: Int, y: Int) : Validation {
        if (x > 7 || y > 7) { //move is out of bounds
            return Validation(false, false)
        } else { //move is in bounds
            return when (piece) {
                is Piece.Pawn -> validatePawn(piece, x, y)
                is Piece.Bishop -> validateBishop(piece, x, y)
                is Piece.Knight -> validateKnight(piece, x, y)
                is Piece.Rook -> validateRook(piece, x, y)
                is Piece.Queen -> validateQueen(piece, x ,y)
                is Piece.King -> validateKing(piece, x, y)
            }
        }
    }

    private fun validatePawn (piece: Piece, x: Int, y: Int) : Validation {
        if (piece.isWhitePiece) { //white piece logic
            if (piece.x == 6) { //starting move
                if (y == piece.y) { //non-capture move
                    if (x - piece.x == 1 || x - piece.x == 2) { //move forward 1 or 2 spaces
                        if (checkCollision(x,y) == null) { //nothing in the way
                            return Validation(true, false)
                        } else { //invalid move, something in the way
                            return Validation(false, false)
                        }
                    } else { //invalid move
                        return Validation(false, false)
                    }
                } else { //capture move
                    if (checkCollision(x,y) == null) { //nothing in the way
                        return Validation(true, false)
                    } else { //invalid move, something in the way
                        
                    }
                }
            } else { //non starting move

            }
        } else { //black piece logic
            if (piece.x == 1) { //starting move

            } else { //non starting move

            }
        }
    }
    private fun validateBishop(piece: Piece, x: Int, y: Int): Validation {
        var i = 1
        while (i <= 7) { //going top right
            var xHolder = (piece.x) + i
            var yHolder = (piece.y) + i
            if (xHolder == x && yHolder == y) {//if at where want to move
                if (checkCollision(x, y) == null) {
                    return Validation(true, false)
                }
                var targetPiece = checkCollision(x, y)
                if (targetPiece != null) {
                    if (piece.isWhitePiece && targetPiece.isWhitePiece) {
                        return Validation(false, false)
                    } else if (piece.isWhitePiece && targetPiece.isWhitePiece == false) {
                        return Validation(true, true)
                    } else if (piece.isWhitePiece == false && targetPiece.isWhitePiece == false) {
                        return Validation(false, false)
                    } else if (piece.isWhitePiece == false && targetPiece.isWhitePiece) {
                        return Validation(true, true)
                    }
                }
            } else if (checkCollision(xHolder, yHolder) != null) { //if collides before reaching where want to move
                break
            } else if (xHolder > 7 || xHolder < 0 || yHolder > 7 || yHolder < 0) {//out of bound
                break
            } else {
                i++
            }
        }

        i = 1
        while (i >= 7) { //going top left
            var xHolder = (piece.x) + i
            var yHolder = (piece.y) - i
            if (xHolder == x && yHolder == y) {//if at where want to move
                if (checkCollision(x, y) == null) {
                    return Validation(true, false)
                }
                var targetPiece = checkCollision(x, y)
                if (targetPiece != null) {
                    if (piece.isWhitePiece && targetPiece.isWhitePiece) {
                        return Validation(false, false)
                    } else if (piece.isWhitePiece && targetPiece.isWhitePiece == false) {
                        return Validation(true, true)
                    } else if (piece.isWhitePiece == false && targetPiece.isWhitePiece == false) {
                        return Validation(false, false)
                    } else if (piece.isWhitePiece == false && targetPiece.isWhitePiece) {
                        return Validation(true, true)
                    }
                }
            } else if (checkCollision(xHolder, yHolder) != null) { //if collides before reaching where want to move
                break
            } else if (xHolder > 7 || xHolder < 0 || yHolder > 7 || yHolder < 0) {//out of bound
                break
            } else {
                i++
            }
        }

        i = 1
        while (i >= 7) { //going bottom right
            var xHolder = (piece.x) - i
            var yHolder = (piece.y) + i
            if (xHolder == x && yHolder == y) {//if at where want to move
                if (checkCollision(x, y) == null) {
                    return Validation(true, false)
                }
                var targetPiece = checkCollision(x, y)
                if (targetPiece != null) {
                    if (piece.isWhitePiece && targetPiece.isWhitePiece) {
                        return Validation(false, false)
                    } else if (piece.isWhitePiece && targetPiece.isWhitePiece == false) {
                        return Validation(true, true)
                    } else if (piece.isWhitePiece == false && targetPiece.isWhitePiece == false) {
                        return Validation(false, false)
                    } else if (piece.isWhitePiece == false && targetPiece.isWhitePiece) {
                        return Validation(true, true)
                    }
                }
            } else if (checkCollision(xHolder, yHolder) != null) { //if collides before reaching where want to move
                break
            } else if (xHolder > 7 || xHolder < 0 || yHolder > 7 || yHolder < 0) {//out of bound
                break
            } else {
                i++
            }
        }

        i = 1
        while (i >= 7) { //going bottom left
            var xHolder = (piece.x) - i
            var yHolder = (piece.y) - i
            if (xHolder == x && yHolder == y) {//if at where want to move
                if (checkCollision(x, y) == null) {
                    return Validation(true, false)
                }
                var targetPiece = checkCollision(x, y)
                if (targetPiece != null) {
                    if (piece.isWhitePiece && targetPiece.isWhitePiece) {
                        return Validation(false, false)
                    } else if (piece.isWhitePiece && targetPiece.isWhitePiece == false) {
                        return Validation(true, true)
                    } else if (piece.isWhitePiece == false && targetPiece.isWhitePiece == false) {
                        return Validation(false, false)
                    } else if (piece.isWhitePiece == false && targetPiece.isWhitePiece) {
                        return Validation(true, true)
                    }
                }
            } else if (checkCollision(xHolder, yHolder) != null) { //if collides before reaching where want to move
                break
            } else if (xHolder > 7 || xHolder < 0 || yHolder > 7 || yHolder < 0) {//out of bound
                break
            } else {
                i++
            }
        }

        return Validation(false, false)
    }
    private fun validateKnight (piece: Piece, x: Int, y: Int) : Validation {  //piece.x is starting x= moving to
        if((piece.x+1 == x && piece.y+2 ==y)||(piece.x+2 == x && piece.y+1 ==y)||(piece.x-1 == x && piece.y+2 ==y)||(piece.x-2 == x && piece.y+1 ==y)||(piece.x+1 == x && piece.y-2 ==y)||(piece.x+2 == x && piece.y-1 ==y)||(piece.x-1 == x && piece.y-2 ==y)||(piece.x-2 == x && piece.y-1 ==y)) {  //is the move a possiible valid move, dont have to look at out of bounds as done above
            if (checkCollision(x,y) === null){
                return Validation(true, false)
            }
            else{
                var targetPiece=checkCollision(x,y)
                if (targetPiece != null) {
                    if(piece.isWhitePiece&& targetPiece.isWhitePiece) {
                        return Validation(false,false)
                    }
                    else if (piece.isWhitePiece&&targetPiece.isWhitePiece==false){
                        return Validation(true,true)
                    }
                    else if (piece.isWhitePiece==false&&targetPiece.isWhitePiece==false){
                        return Validation(false,false)
                    }
                    else if (piece.isWhitePiece==false&&targetPiece.isWhitePiece){
                        return Validation(true,true)
                    }
                }
            }
        }
        else{   //if not one of 8 possible moves of the knight
            return Validation(false, false)
        }
        return Validation(false, false)
    }
    private fun validateRook (piece: Piece, x: Int, y: Int) : Validation {}
    private fun validateQueen (piece: Piece, x: Int, y: Int) : Validation {}
    private fun validateKing (piece: Piece, x: Int, y: Int) : Validation {}

    private fun checkCollision (x: Int, y: Int) : Piece? {
        var targetSpace = board.board[x][y]
        if (targetSpace.piece == null) {
            return null
        } else {
            return targetSpace.piece
        }
    }

    data class Validation(val move: Boolean, val capture: Boolean)
}