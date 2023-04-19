package com.example.groupproject

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.groupproject.data.Pieces
import com.example.groupproject.data.Space
import kotlin.math.min

// This is a class that represents a view for a Chess game.
class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val scaleFactor = 1.0f
    private var originX = 20f
    private var originY = 200f
    private var cellSide = 130f
    private val lightColor = Color.parseColor("#5A5A5A")
    private val darkColor = Color.parseColor("#BBBBBB")
    private val paint = Paint()
    var board: Board? = null
    private val bitmaps = mutableMapOf<Int, Bitmap>()
    private var fromCol: Int = -1
    private var fromRow: Int = -1
    private var movingPieceX = -1f
    private var movingPieceY = -1f
    private var movingPieceBitmap: Bitmap? = null
    private var movingPiece: Pieces? = null
    // This is a set of image resource IDs for the chess piece images.
    private val imgResIDs = setOf(
        R.drawable.bishop_black,
        R.drawable.king_black,
        R.drawable.queen_black,
        R.drawable.rook_black,
        R.drawable.knight_black,
        R.drawable.pawn_black,
        R.drawable.pawn_white,
        R.drawable.bishop_white,
        R.drawable.king_white,
        R.drawable.queen_white,
        R.drawable.rook_white,
        R.drawable.knight_white,
    )

    // This is the constructor for the ChessView class
    init {
        // This method loads the Bitmap objects for each piece image resource ID.
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        // Ensure that the canvas is not null
        canvas ?: return

        // Calculate the size of the chessboard based on the smaller dimension of the view
        val chessBoardSide = min(width, height) * scaleFactor
        // Calculate the size of each cell based on the size of the chessboard
        cellSide = chessBoardSide / 8f
        // Calculate the x and y coordinates of the top-left corner of the chessboard
        originX = (width - chessBoardSide) / 2f
        originY = (height - chessBoardSide) / 2f

        // Draw the chessboard on the canvas
        drawChessboard(canvas)
        // Draw the pieces on the chessboard
        drawPieces(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Ensure that the event is not null
        event ?: return false

        // Handle different types of touch events
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // When the user presses down on the screen, determine which cell they touched
                fromCol = ((event.x - originX) / cellSide).toInt()
                fromRow = 7 - ((event.y - originY) / cellSide).toInt()

                // Get the piece at the touched cell from the board, and set it as the moving piece
                board?.pieceAt(fromCol, fromRow)?.let {
                    movingPiece = it
                    movingPieceBitmap = bitmaps[it.resID]
                }
            }
            MotionEvent.ACTION_MOVE -> {
                // While the user is dragging their finger, update the x and y coordinates of the moving piece
                movingPieceX = event.x
                movingPieceY = event.y
                // Invalidate the view so that it is redrawn with the updated moving piece position
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                // When the user releases their finger, determine which cell they released it on
                val col = ((event.x - originX) / cellSide).toInt()
                val row = 7 - ((event.y - originY) / cellSide).toInt()
                // Move the piece from its starting cell to the released cell on the board
                board?.movePiece(fromCol, fromRow, col, row)
                // Reset the moving piece variables
                movingPiece = null
                movingPieceBitmap = null
            }
        }
        // Indicate that the touch event has been handled
        return true
    }

    // Draws the chessboard on the canvas
    private fun drawChessboard(canvas: Canvas) {
        for (row in 0 until 8) // Iterate through each row of the chessboard
            for (col in 0 until 8) // Iterate through each column of the chessboard
                drawSquareAt(canvas, col, row, (col + row) % 2 == 1) // Draw a square at this position
    }

    // Draws a square on the chessboard
    private fun drawSquareAt(canvas: Canvas, col: Int, row: Int, isDark: Boolean) {
        paint.color = if (isDark) darkColor else lightColor // Set the paint color to the appropriate color
        canvas.drawRect(
            originX + col * cellSide, // The x-coordinate of the top-left corner of the square
            originY + row * cellSide, // The y-coordinate of the top-left corner of the square
            originX + (col + 1) * cellSide, // The x-coordinate of the bottom-right corner of the square
            originY + (row + 1) * cellSide, // The y-coordinate of the bottom-right corner of the square
            paint // The paint to use for drawing the square
        )
    }


    // Draws pieces on a square if one is on it
    private fun drawPieces(canvas: Canvas) {
        // iterate over each square on the board
        for (row in 0..7) {
            for (col in 0..7) {
                // if there is a piece on the square and it's not the moving piece,
                // draw the piece bitmap at the square's position
                board?.pieceAt(col, row)?.let {
                    if (it != movingPiece) {
                        drawPieceAt(canvas, col, row, it.resID)
                    }
                }
            }
        }

        // if there is a piece being moved, draw its bitmap at the current touch position
        movingPieceBitmap?.let {
            canvas.drawBitmap(
                it,
                null,
                RectF(
                    movingPieceX - cellSide / 2,
                    movingPieceY - cellSide / 2,
                    movingPieceX + cellSide / 2,
                    movingPieceY + cellSide / 2
                ), paint
            )
        }
    }

    // draw the bitmap of the piece at the given col and row position on the chessboard
    private fun drawPieceAt(canvas: Canvas, col: Int, row: Int, resID: Int) =
        canvas.drawBitmap(
            bitmaps[resID]!!,
            null,
            RectF(
                originX + col * cellSide,
                originY + (7 - row) * cellSide,
                originX + (col + 1) * cellSide,
                originY + ((7 - row) + 1) * cellSide
            ),
            paint
        )


    private fun loadBitmaps() {
        // Iterate over each resource ID in the set of image resource IDs.
        imgResIDs.forEach {
            // Decode the resource into a bitmap and add it to the map with the resource ID as the key.
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }
}