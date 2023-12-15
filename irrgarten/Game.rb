module Irrgarten
  require_relative 'Dice'
  require_relative 'Labyrinth'
  require_relative 'Player'
  require_relative 'GameState'
  require_relative 'Monster'
  require_relative 'Orientation'
  require_relative 'GameCharacter'
  class Game
    @@MAX_ROUNDS=10

    #COnstructor
    def initialize(nplayers)
      @log = ""

      @monsters=Array.new
      @players=Array.new
      nplayers.times do |i|
        @players<< Player.new(i, Dice.randomIntelligence, Dice.randomStrength)
      end
      #Decides quien empieza y lo asignas a current player
      @currentPlayerIndex=Dice.whoStarts(nplayers)
      @currentPlayer=@players[@currentPlayerIndex]
      #Creas un nuevo laberinto
      # weapon = Weapon.new(Dice.weaponPower, Dice.usesLeft)
      @labyrinth = Labyrinth.new(10,10,9,9)
      configureLabyrinth
      #por implementar
      @labyrinth.spreadPlayers(@players)
    end
    #Si el juego ha terminado
    def finished
      return @labyrinth.haveAWinner
    end
    #POr implementar
    def nextStep(preferredDirection)
      @log=""
      dead=@currentPlayer.dead
      if !dead
        direction=self.actualDirection(preferredDirection)
        if direction != preferredDirection
          self.logPLayerNoOrders
        end
        monster=@labyrinth.putPlayer(direction,@currentPlayer)
        if monster==nil
          self.logNoMonster
        else
          winner=combat(monster)
          manageReward(winner)
        end
      else
        manageResurrection
      end
      endGame=finished
      if !endGame
        self.nextPlayer
      end
      return endGame
    end
    #Da el gamestate
    def getGameState
      p = ""
      for i in 0..@players.size-1
        p+=@players[i].to_s + "\n"
      end
      m= ""
      for i in 0..@monsters.size-1
        m+=@monsters[i].to_s + "\n"
      end
      laberinto = @labyrinth.to_s
      return GameState.new(laberinto,p,m,@currentPlayerIndex,finished,@log)
    end
    private
    def configureLabyrinth
      @labyrinth.addBlock(Orientation::HORIZONTAL, 0, 5, 4)
      @labyrinth.addBlock(Orientation::VERTICAL, 1, 1, 4)
      @labyrinth.addBlock(Orientation::VERTICAL, 2, 3, 4)
      @labyrinth.addBlock(Orientation::VERTICAL, 3, 8, 3)
      @labyrinth.addBlock(Orientation::VERTICAL, 7, 1, 2)
      @labyrinth.addBlock(Orientation::VERTICAL, 7, 3, 2)
      @labyrinth.addBlock(Orientation::VERTICAL, 8, 5, 3)
      #Cambiar intelligence y strengh
      for i in 1..6
        @monsters<< Monster.new("Monstruo #{i}",Dice.randomIntelligence,Dice.randomStrength)
      end
      @labyrinth.addMonster(0, 3, @monsters[0])
      @labyrinth.addMonster(0, 9, @monsters[1])
      @labyrinth.addMonster(2, 7, @monsters[2])
      @labyrinth.addMonster(4, 9, @monsters[3])
      @labyrinth.addMonster(7, 2, @monsters[4])
      @labyrinth.addMonster(7, 6, @monsters[5])
      @labyrinth.addMonster(9, 6, @monsters[6])
    end
    #Pasa al siguiente jugador
    def nextPlayer
      #puts @currentPlayerIndex
      c = @currentPlayerIndex+1
      @currentPlayerIndex = c % @players.size
      #puts @currentPlayerIndex
      @currentPlayer = @players[@currentPlayerIndex]
    end
    #Por implementar
    def actualDirection(preferredDirection)
      currentRow=@currentPlayer.getRow
      currentCol=@currentPlayer.getCol
      validmoves=@labyrinth.validMoves(currentRow,currentCol)
      output=@currentPlayer.move(preferredDirection,validmoves)
      return output
    end
    #Por implementar
    def combat(monster)
      rounds=0
      winner=GameCharacter::PLAYER
      playerAttack=@currentPlayer.attack
      lose=monster.defend(playerAttack)
      while (!lose && rounds<=@@MAX_ROUNDS)
        winner=GameCharacter::MONSTER
        rounds+=1
        monsterAttack=monster.attack
        lose=@currentPlayer.defend(monsterAttack)
        if !lose
          playerAttack=@currentPlayer.attack
          winner=GameCharacter::PLAYER
          lose=monster.defend(playerAttack)
        end
        self.logRounds(rounds,@@MAX_ROUNDS)
        return winner

      end
    end
    #Por implementar
    def manageReward(winner)
      if winner == GameCharacter::PLAYER
        @currentPlayer.receiveReward
        self.logPlayerWon
      else
        self.logPLayerSkipTurn
      end
    end
    #Por implementar
    def manageResurrection
      resurrect= Dice.resurrectPlayer
      if resurrect
        @currentPlayer.resurrect
        self.logResurrected
      else
        self.logPLayerSkipTurn
      end
    end
    #Por implementar
    def logPlayerWon
      @log += "El jugador ha ganado el combate.\n"
      puts @log
    end
    def logMonsterWon
      @log += "El monstruo ha ganado el combate.\n"
      #puts @log
    end
    def logResurrected
      @log += "El jugador #{@current_player_index} ha resucitado.\n"
      #puts @log
    end
    def logPLayerSkipTurn
      @log += "El jugador #{@current_player_index}  perdido el turno por estas muerto.\n"
      #puts @log
    end
    def logPLayerNoOrders
      @log += "El jugador #{@current_player_index}  no ha seguido las instrucciones indicadas.\n"
      #puts @log
    end
    def logNoMonster
      @log += "El jugador #{@current_player_index}  ha llegado a una casilla vacia o no ha podido moverse.\n"
    end
    def logRounds(rounds,max)
      @log += "Nos encontramos en la ronda #{rounds} de #{max} rondas. \n"
    end

  end
end