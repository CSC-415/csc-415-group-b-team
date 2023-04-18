package com.example.groupproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groupproject.data.Pieces
import com.example.groupproject.data.Space

class MainActivity : AppCompatActivity(), Board {
    var chessModel = ChessModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun pieceAt(col: Int, row: Int): Pieces? {
        return chessModel.pieceAt(col, row)
    }

    override fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        chessModel.movePiece(fromCol, fromRow, toCol, toRow)
        findViewById<ChessView>(R.id.chess_view).invalidate()
    }
}
