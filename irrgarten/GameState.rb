module Irrgarten
    class GameState
        #Constructor  de la clase con parámetros.
        def initialize(labytinthv, players, monsters, currentplayer, winner, log )
          # Set the instance variables within the constructor
          @labyrinthv = labytinthv
          @players = players
          @monsters = monsters
          @currentPlayer = currentplayer
          @winner = winner
          @log = log
        end
        #private
        #Get para Labyrinthv.
        def getLabyrinthv
            @labyrinthv
        end
        #Get para players.
        def getplayers
            @players
        end
        #Get para monsters.
        def getmonsters
            @monsters
        end
        #Get para currentPlayer.
        def getcurrentPlayer
            @currentPlayer
        end
        #Get para winner.
        def getWinner
            @winner
        end
        #Get para Log.
        def getLog
            @log
        end
        
    end
end

