module Irrgarten
    class TestP2
        def main
            require_relative 'Shield'
            require_relative 'Weapon'
            require_relative 'Dice'
            require_relative 'Monster'
            require_relative 'Player'
            require_relative 'Labyrinth'
            require_relative 'Directions'
            require_relative 'Game'
            require_relative 'GameState'

            #Pruebas para la clase Monster
            monster1 =Monster.new("Manolo",Dice.randomIntelligence,Dice.randomStrength)
            monster2 =Monster.new("Pepe",Dice.randomIntelligence,Dice.randomStrength)
            monster3 =Monster.new("Kani",Dice.randomIntelligence,Dice.randomStrength)
            puts monster1.to_s
            puts monster2.to_s
            puts monster3.to_s
            #monster1.gotWounded
            ##monster1.gotWounded
            #monster1.gotWounded
            #monster1.gotWounded
            puts monster1.dead
            puts monster1.to_s
            #monster1.gotWounded
            puts monster1.dead
            puts monster1.to_s
            puts monster1.attack
            #monster1.setPos(1,4)
            puts monster1.to_s
            #monster1.gotWounded
            puts monster1.to_s
            puts "-------------"
            #Prueba para la clase player
            player1 =Player.new(1,Dice.randomIntelligence,Dice.randomStrength)
            player2 =Player.new(2,Dice.randomIntelligence,Dice.randomStrength)
            player3 =Player.new(3,Dice.randomIntelligence,Dice.randomStrength)
            puts player1.to_s
            puts player2.to_s
            puts player3.to_s
            puts player1.getCol
            puts player1.getRow
            puts player1.getNumber
            player1.setPos(1,2)
            puts player1.to_s
            puts player1.dead
            player1.resurrect
            puts player1.to_s

            #arma = player1.newWeapon
            #puts arma.to_s
            #escudo = player1.newShield
            #puts escudo.to_s
            #player1.resetHits
            #puts player1.to_s
            #player1.gotWounded
            #puts player1.to_s
            #player1.inConsecutiveHits
            #puts player1.to_s
            #puts player1.attack
            #puts player1.sumShields
            player1.resurrect
            puts player1.to_s
            puts "-------------\n"
            laberinto = Labyrinth.new(10,10,9,9)
            puts laberinto.to_s
            puts laberinto.haveAWinner

            laberinto.addMonster(1,2,monster1)
            puts laberinto.to_s
            #puts laberinto.posOK(10,10)
            #puts laberinto.emptyPos(1,1)
            #puts laberinto.monsterPos(1,3)
            #puts laberinto.exitPos(9,8)
            #puts laberinto.canStepOn(1,3)
            puts player1.getRow
            puts player1.getCol
            #laberinto.updateOldPos(1,2)
            pos = [0,0]
            #pos=laberinto.dir2Pos(1,2,Directions::RIGHT)
            puts pos[0]
            puts pos[1]
            #pos=laberinto.randomEmptyPos
            puts pos[0]
            puts pos[1]
            puts "-------------\n"
            juego = Game.new(4)
            puts juego.finished
            #juego.nextPlayer
            #juego.logMonsterWon
            #juego.logResurrected
            #juego.logPLayerSkipTurn
            #juego.logPLayerNoOrders
            #juego.logNoMonster
            #juego.logRounds(3,10)
            estado = juego.getGameState
            #puts estado.getlabyrinthv
            #puts estado.getplayers
            #puts estado.getmonsters
            #puts estado.getcurrentPlayer
            #puts estado.getWinner
            #puts estado.getLog



            #Probar getgamestate no funciona
        end


    end

    prueba = TestP2.new

    prueba.main
end