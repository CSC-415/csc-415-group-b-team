package com.example.groupproject

class ChessGame {

    var board = Board()

    fun move(piece: Piece, x: Int, y: Int) {
        val validation = validateMove(piece, x, y)

        if (validation.move && validation.capture) { //move w/ capture
            piece.x = x
            piece.y = y
            val tempPiece = board.board[x][y].piece
            board.board[x][y].piece = piece
            if (tempPiece!!.isWhitePiece) { //we know this is not null because the validation function is handling it
                board.whiteCap.add(tempPiece) //if piece is white, add to list of captured white pieces
            } else {
                board.blackCap.add(tempPiece) //if piece is black, add to list of captured black pieces
            }
        } else if (validation.move) { //just move
            piece.x = x
            piece.y = y
            board.board[x][y].piece = piece
        }
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
                    if (piece.x - x == 1 || piece.x - x == 2) { //move forward 1 or 2 spaces
                        if (checkCollision(x,y) == null) { //nothing in the way
                            return Validation(true, false)
                        } else { //invalid move, something in the way
                            return Validation(false, false)
                        }
                    } else { //invalid move
                        return Validation(false, false)
                    }
                } else { //capture move
                    val targetPiece = checkCollision(x,y)
                    if (targetPiece == null) { //nothing in the way
                        return Validation(true, false)
                    } else { //invalid move, something in the way
                        if (!targetPiece.isWhitePiece) { //targetPiece is not on same side as piece, valid move w/ capture
                            return Validation(true,true)
                        } else { //targetPiece is on same side as piece, invalid move
                            return Validation(false,false)
                        }
                    }
                }
            } else { //non starting move
                if (y == piece.y) { //non-capture move
                    if (piece.x - x == 1) { //move forward 1 or 2 spaces
                        if (checkCollision(x,y) == null) { //nothing in the way
                            return Validation(true, false)
                        } else { //invalid move, something in the way
                            return Validation(false, false)
                        }
                    } else { //invalid move
                        return Validation(false, false)
                    }
                } else { //capture move
                    val targetPiece = checkCollision(x,y)
                    if (targetPiece == null) { //nothing in the way
                        return Validation(true, false)
                    } else { //invalid move, something in the way
                        if (!targetPiece.isWhitePiece) { //targetPiece is not on same side as piece, valid move w/ capture
                            return Validation(true,true)
                        } else { //targetPiece is on same side as piece, invalid move
                            return Validation(false,false)
                        }
                    }
                }
            }
        } else { //black piece logic
            if (piece.x == 1) { //starting move
                if (y == piece.y) { //non-capture move
                    if (x -piece.x == 1 || x - piece.x == 2) { //move forward 1 or 2 spaces
                        if (checkCollision(x,y) == null) { //nothing in the way
                            return Validation(true, false)
                        } else { //invalid move, something in the way
                            return Validation(false, false)
                        }
                    } else { //invalid move
                        return Validation(false, false)
                    }
                } else { //capture move
                    val targetPiece = checkCollision(x,y)
                    if (targetPiece == null) { //nothing in the way
                        return Validation(true, false)
                    } else { //invalid move, something in the way
                        if (targetPiece.isWhitePiece) { //targetPiece is not on same side as piece, valid move w/ capture
                            return Validation(true,true)
                        } else { //targetPiece is on same side as piece, invalid move
                            return Validation(false,false)
                        }
                    }
                }
            } else { //non starting move
                if (y == piece.y) { //non-capture move
                    if (x - piece.x == 1) { //move forward 1 or 2 spaces
                        if (checkCollision(x,y) == null) { //nothing in the way
                            return Validation(true, false)
                        } else { //invalid move, something in the way
                            return Validation(false, false)
                        }
                    } else { //invalid move
                        return Validation(false, false)
                    }
                } else { //capture move
                    val targetPiece = checkCollision(x,y)
                    if (targetPiece == null) { //nothing in the way
                        return Validation(true, false)
                    } else { //invalid move, something in the way
                        if (targetPiece.isWhitePiece) { //targetPiece is not on same side as piece, valid move w/ capture
                            return Validation(true,true)
                        } else { //targetPiece is on same side as piece, invalid move
                            return Validation(false,false)
                        }
                    }
                }
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
        while (i <= 7) { //going top left
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
        while (i <= 7) { //going bottom right
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
        while (i <= 7) { //going bottom left
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
    private fun validateRook (piece: Piece, x: Int, y: Int) : Validation {
        val hitPiece = false
        if (piece.isWhitePiece == true) { //white piece logic
            if (y == piece.y) { //rook is moving up/down
                if (x > piece.x) { //rook is moving towards white start
                    for (i in piece.x..x) {
                        val targetPiece = checkCollision(i, y)
                        if (targetPiece != null) { //piece was found
                            if (targetPiece.x == x && targetPiece.isWhitePiece) { //piece is where rook is going to and is on opposing side, valid move w/ capture
                                return Validation(true,true)
                            } else if (targetPiece.x == x && !targetPiece.isWhitePiece) { //piece is where rook is going to and is on same side, invalid move
                                return Validation(false,false)
                            }
                        }
                    }
                    return Validation(true, false) //nothing in the way, move is valid w/o capture
                } else  { //rook is moving towards black start
                    for (i in piece.x downTo x) {
                        val targetPiece = checkCollision(i, y)
                        if (targetPiece != null) { //piece was found
                            if (targetPiece.x == x && targetPiece.isWhitePiece) { //piece is where rook is going to and is on opposing side, valid move w/ capture
                                return Validation(true,true)
                            } else if (targetPiece.x == x && !targetPiece.isWhitePiece) { //piece is where rook is going to and is on same side, invalid move
                                return Validation(false,false)
                            }
                        }
                    }
                    return Validation(true, false) //nothing in the way, move is valid w/o capture
                }
            } else if (x == piece.x) { //rook is moving left/right
                if (y > piece.y) { //rook is moving to the right
                    for (i in piece.y..y) {
                        val targetPiece = checkCollision(x, y)
                        if (targetPiece != null) { //piece was found
                            if (targetPiece.y == y && !targetPiece.isWhitePiece) { //piece is where rook is going to and is on opposing side, valid move w/ capture
                                return Validation(true,true)
                            } else if (targetPiece.y == y && targetPiece.isWhitePiece) { //piece is where rook is going to and is on same side, invalid move
                                return Validation(false,false)
                            }
                        }
                    }
                    return Validation(true, false) //nothing in the way, move is valid w/o capture
                } else { //rook is moving to the left
                    for (i in piece.y downTo y) {
                        val targetPiece = checkCollision(x, y)
                        if (targetPiece != null) { //piece was found
                            if (targetPiece.y == y && !targetPiece.isWhitePiece) { //piece is where rook is going to and is on opposing side, valid move w/ capture
                                return Validation(true,true)
                            } else if (targetPiece.y == y && targetPiece.isWhitePiece) { //piece is where rook is going to and is on same side, invalid move
                                return Validation(false,false)
                            }
                        }
                    }
                    return Validation(true, false) //nothing in the way, move is valid w/o capture
                }
            } else { //invalid move, rook is trying to move diagonal
                return Validation(false,false)
            }
        } else { //black piece logic
            if (y == piece.y) { //rook is moving up/down
                if (x > piece.x) { //rook is moving towards white start
                    for (i in piece.x..x) {
                        val targetPiece = checkCollision(i,y)
                        if (targetPiece != null) { //piece was found
                            if (targetPiece.x == x && !targetPiece.isWhitePiece) { //piece is where rook is going to and is on opposing side, valid move w/ capture
                                return Validation(true,true)
                            } else if (targetPiece.x == x && targetPiece.isWhitePiece) { //piece is where rook is going to and is on same side, invalid move
                                return Validation(false,false)
                            }
                        }
                    }
                    return Validation(true, false) //nothing in the way, move is valid w/o capture
                } else  { //rook is moving towards black start
                    for (i in piece.x downTo x) {
                        val targetPiece = checkCollision(i,y)
                        if (targetPiece != null) { //piece was found
                            if (targetPiece.x == x && !targetPiece.isWhitePiece) { //piece is where rook is going to and is on opposing side, valid move w/ capture
                                return Validation(true,true)
                            } else if (targetPiece.x == x && targetPiece.isWhitePiece) { //piece is where rook is going to and is on same side, invalid move
                                return Validation(false,false)
                            }
                        }
                    }
                    return Validation(true, false) //nothing in the way, move is valid w/o capture
                }
            } else if (x == piece.x) { //rook is moving left/right
                if (y > piece.y) { //rook is moving to the right
                    for (i in piece.y..y) {
                        val targetPiece = checkCollision(x, y)
                        if (targetPiece != null) { //piece was found
                            if (targetPiece.y == y && targetPiece.isWhitePiece) { //piece is where rook is going to and is on opposing side, valid move w/ capture
                                return Validation(true,true)
                            } else if (targetPiece.y == y && !targetPiece.isWhitePiece) { //piece is where rook is going to and is on same side, invalid move
                                return Validation(false,false)
                            }
                        }
                    }
                } else { //rook is moving to the left
                    for (i in piece.y downTo y) {
                        val targetPiece = checkCollision(x, y)
                        if (targetPiece != null) { //piece was found
                            if (targetPiece.y == y && targetPiece.isWhitePiece) { //piece is where rook is going to and is on opposing side, valid move w/ capture
                                return Validation(true,true)
                            } else if (targetPiece.y == y && !targetPiece.isWhitePiece) { //piece is where rook is going to and is on same side, invalid move
                                return Validation(false,false)
                            }
                        }
                    }
                }
            } else { //invalid move, rook is trying to move diagonal
                return Validation(false,false)
            }
        }
        return Validation(false, false)
    }
    private fun validateQueen (piece: Piece, x: Int, y: Int) : Validation {
        val straightValidate = validateRook(piece, x, y)
        val diagValidate = validateBishop(piece, x, y)

        if ((straightValidate.move && straightValidate.capture) || (diagValidate.move && diagValidate.capture)) { //valid move w/ capture
            return Validation(true,true)
        } else if (straightValidate.move || diagValidate.move) { //valid move w/o capture
            return Validation(true,false)
        } else { //invalid move
            return Validation(false,false)
        }
    }
    private fun validateKing (piece: Piece, x: Int, y: Int) : Validation {
        if((x-1==piece.x || x+1==piece.x) && (y-1==piece.y || y+1==piece.y) ) {  //if move want to do is within 1 space of original king
            if (checkCollision(x, y) == null) {
                return Validation(true, false)
            } else {
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
            }
        }
        else{
            return Validation(false,false)
        }
        return Validation(false,false)
    }

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