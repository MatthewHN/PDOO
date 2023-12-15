module Irrgarten
  require_relative 'Dice'
  require_relative 'Monster'
  class Labyrinth
    @@BLOCK_CHAR='X'
    @@EMPTY_CHAR='-'
    @@MONSTER_CHAR='M'
    @@COMBAT_CHAR='C'
    @@EXIT_CHAR='E'
    @@ROW = 0
    @@COL= 1


    #Constructor
    def initialize(nRows,nCols,exitRow,exitCol)
      @nRows = nRows
      @nCols= nCols
      @exitRow = exitRow
      @exitCol = exitCol
      @monsters = Array.new(@nRows) { Array.new(@nCols){nil} }
      @labyrinth = Array.new(@nRows) { Array.new(@nCols){@@EMPTY_CHAR} }
      @labyrinth[exitRow][exitCol] = @@EXIT_CHAR
      @players = Array.new(@nRows) { Array.new(@nCols){nil} }
    end
    #Por implementar
    def spreadPlayers(players)
      for i in 0..(players.size-1)
        p=players[i]
        pos=self.randomEmptyPos
        self.putPlayer2D(-1,-1,pos[@@ROW],pos[@@COL],p)
      end
    end
    #Dice si hay un ganador
    def haveAWinner
      return @players[@exitRow][@exitCol]!=nil
    end
    #Imprime el laberinto
    def to_s
      resultado = ""

      @labyrinth.each_with_index do |r, r_index|
        r.each_with_index do |c, c_index|
          resultado += (@labyrinth[r_index][c_index]).to_s
          resultado +=" "
        end
        resultado+="\n"
      end
      return resultado
    end
    #Añade un monstruo
    def addMonster(row,col,monster)
      if posOK(row,col) && @labyrinth[row][col]==@@EMPTY_CHAR && monster!= nil   #no muy seguro de la última condición
        @labyrinth[row][col]=@@MONSTER_CHAR
        @monsters[row][col]=monster
        monster.setPos(row,col)
      end
    end
    #Por implementar
    def putPlayer(direction,player)
      oldRow=player.getRow
      oldCol=player.getCol
      newPos=dir2Pos(oldRow,oldCol,direction)
      monster=putPlayer2D(oldRow,oldCol,newPos[0],newPos[1],player)
      monster
    end
    #Por implementar
    def addBlock(orientation,startRow,startCol,length)
      incRow=0
      incCol=0
      if orientation==Orientation::VERTICAL
        incRow=1
        incCol=0
      else
        incRow=0
        incCol=1
      end
      while self.posOK(startRow,startCol) &&self.emptyPos(startRow,startCol)&&length>0
        @labyrinth[startRow][startCol]=@@BLOCK_CHAR
        length-=1
        startRow+=incRow
        startCol+=incCol
      end
    end
    #POr implementar
    def validMoves(row,col)
      output=[]
      if self.canStepOn(row+1,col)
        output<<Directions::DOWN
      end
      if self.canStepOn(row-1,col)
        output<<Directions::UP
      end
      if self.canStepOn(row,col+1)
        output<<Directions::RIGHT
      end
      if self.canStepOn(row,col-1)
        output<<Directions::LEFT
      end
      return output
    end
    private
    #Dice se la posición es correcta
    def posOK(row,col)
      if row < @nRows && row >=0 && col <@nCols && col>=0
        return true
      else
        return false
      end
    end
    #Dice si la posición está vacia
    def emptyPos(row,col)
      return @labyrinth[row][col]==@@EMPTY_CHAR
    end
    #Dice si hay un monstruo en la posicion
    def monsterPos(row,col)
      return @labyrinth[row][col]==@@MONSTER_CHAR
    end
    #DIce si es la casilla de salida.
    def exitPos(row,col)
      return @labyrinth[row][col]==@@EXIT_CHAR
    end
    #Dice si es una posicion de combate
    def combatPos(row,col)
      return (@labyrinth[row][col]==@@MONSTER_CHAR and @labyrinth[row][col]==@@COMBAT_CHAR)
    end
    #Dice si puedes avanzar a esa posicion
    def canStepOn(row,col)
      return (posOK(row,col) && (emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col)))
    end
    #Actualiza la posición
    def updateOldPos(row,col)
      if @labyrinth[row][col]==@@COMBAT_CHAR
        @labyrinth[row][col]=@@MONSTER_CHAR
      else
        @labyrinth[row][col]=@@EMPTY_CHAR
      end
    end
    #Dice a la posición a la que se avanzaría si se mueve
    def dir2Pos(row, col ,direction)
      pos = [0, 0]

      case direction
      when Directions::LEFT
        pos[@@ROW] = row
        pos[@@COL] = col -1
      when Directions::RIGHT
        pos[@@ROW] = row
        pos[@@COL] = col +1
      when Directions::UP
        pos[@@ROW] = row -1
        pos[@@COL] = col
      when Directions::DOWN
        pos[@@ROW] = row +1
        pos[@@COL] = col 
      end
      return pos
    end
    #Da una posicion aleatoria
    def randomEmptyPos()
      pos = [0, 0]
      found = false
      while !found
        pos[@@ROW] = Dice.randomPos(@nRows-1)
        pos[@@COL] = Dice.randomPos(@nCols-1)
        found = @labyrinth[pos[@@ROW]][pos[@@COL]] == @@EMPTY_CHAR
      end
      return pos
    end
    #Por implementar
    def putPlayer2D(oldRow,oldCol,row,col,player)
      output=nil
      if canStepOn(row,col)
        if posOK(oldRow,oldCol)
          p=@players[oldRow][oldCol]
          if p==player
            updateOldPos(oldRow,oldCol)
            @players[oldRow][oldCol]=nil
          end
        end
        monsterPos=monsterPos(row,col)
        if monsterPos
          @labyrinth[row][col]=@@COMBAT_CHAR
          output=@monsters[row][col]
        else
          number=player.getNumber
          #probablemte no salga bien el número en el tablero
          @labyrinth[row][col]=number
        end
        @players[row][col]=player
        player.setPos(row,col)
      end
      return output
    end
  end
end