module Irrgarten
  require_relative 'Dice'
  class Monster
    #Atributos de la clase
    @@INITIAL_HEALTH=5

    #Constructor
    def initialize(name, intelligence,strength)
      @name = name
      @intelligence = intelligence
      @strength =strength
      @health = @@INITIAL_HEALTH
      @row = -1
      @col = -1
    end
    #Dice si el monstruo esta muerto
    def dead()
      if @health !=0
        return false
      else
        return true
      end
    end
    #Intensidad del ataque del monstruo
    def attack()
      return Dice.intensity(@strength)
    end
    #Por implementar
    def defend(recievedAttack)
      isDead=self.dead
      if !isDead
        defensiveEnergy=Dice.intensity(@intelligence)
        if defensiveEnergy<recievedAttack
          self.gotWounded
          isDead=self.dead
        end
      end
      return isDead
    end
    #Setter de la posicion del monstruo
    def setPos(row,col)
      @row=row
      @col=col
    end
    #Print del estado del monstruo
    def to_s
      "El monstruo #{@name} tiene una inteligencia de #{@intelligence}, una fuerza de #{@strength}, una salud de #{@health} y se encuentra en [#{@row},#{@col}]"
    end
    #Metod que reduce la salud de un monstruo cuando es atacado.
    private
    def gotWounded()
      if @health > 0
        @health-= 1
      end
    end

  end
end