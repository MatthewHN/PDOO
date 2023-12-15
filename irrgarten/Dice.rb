module Irrgarten
  class Dice
    #Atributos de clase privados.
    @@MAX_USES=5
    @@MAX_INTELLIGENCE=10.0
    @@MAX_STRENGHT=10.0
    @@RESURRECT_PROB=0.3
    @@WEAPONS_REWARD=2
    @@SHIELD_REWARD=3
    @@HEALTH_REWARD=5
    @@MAX_ATTACK=3
    @@MAX_SHIELD=2  
    @@generator = Random.new 
    

    #Devuelve número de fila o columna aleatorio siendo el argumento el máximo posible.
    def self.randomPos(max)
      @@generator.rand(max).to_i
    end
    #Devuelve el índice del jugador que inicia la partida.
    def self.whoStarts(nplayers)
      @@generator.rand(nplayers).to_i
    end
    #Devuelve un valor aleatorio de inteligencia en [0,MAX_INTELLIGENCE].
    def self.randomIntelligence()
      @@generator.rand(@@MAX_INTELLIGENCE)
    end
    #Devuelve un valor aleatorio de fuerza en [0,MAX_STRENGHT].
    def self.randomStrength()
      @@generator.rand(@@MAX_STRENGHT)
    end
    #Indica si el jugador revive o no.
    def self.resurrectPlayer()
        if @@generator.rand() <= @@RESURRECT_PROB
          return true
        else
          return false
        end
    end
    #Indica la cantidad de armas que recibirá el jugador por ganar el combate.
    def self.weaponsReward()
      @@generator.rand(@@WEAPONS_REWARD+1).to_i
    end
    #Indica la cantidad de escudos que recibirá el jugador por ganar el combate.
    def self.shieldReward()
      @@generator.rand(@@SHIELD_REWARD+1).to_i
    end
    #Indica la cantidad de unidades de salud que recibirá el jugador por ganar el combate.
    def self.healthReward()
      @@generator.rand(@@HEALTH_REWARD+1).to_i
    end
    #Devuelve un valor aleatorio en el intervalo [0, MAX_ATTACK[.
    def self.weaponPower()
      @@generator.rand(@@MAX_ATTACK+1)
    end
    #Devuelve un valor aleatorio en el intervalo [0, MAX_SHIELD[.
    def self.shieldPower()
      @@generator.rand(@@MAX_SHIELD+1)
    end
    #Devuelve el número de usos que se asignará a un arma o escudo. 
    def self.usesLeft()
      @@generator.rand(@@MAX_USES+1).to_i
    end
    #Devuelve la cantidad de competencia aplicada.
    def self.intensity(competence)
      @@generator.rand(competence)
    end
    #Dice si un arma o un escudo es descartado o no.
    def self.discardElement(usesLeft)
      case usesLeft
      when @@MAX_USES
        return false
      when 0
        return true
      else
        probability = 1.0 - (usesLeft.to_f / @@MAX_USES)
        return @@generator.rand() <= probability
      end
    end    
  end
end
