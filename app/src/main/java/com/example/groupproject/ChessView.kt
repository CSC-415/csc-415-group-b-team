package com.example.groupproject

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.groupproject.data.Pieces
import com.example.groupproject.data.Space
import kotlin.math.min

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

    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val chessBoardSide = min(width, height) * scaleFactor
        cellSide = chessBoardSide / 8f
        originX = (width - chessBoardSide) / 2f
        originY = (height - chessBoardSide) / 2f

        drawChessboard(canvas)
        drawPieces(canvas)
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

    private fun drawPieces(canvas: Canvas) {
        for (row in 0..7) {
            for (col in 0..7) {
                board?.pieceAt(col, row)?.let {
                    if (it != movingPiece) {
                        drawPieceAt(canvas, col, row, it.resID)
                    }
                }
            }
        }

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
        imgResIDs.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }
}