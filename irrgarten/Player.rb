module Irrgarten
  require_relative 'Dice'
  require_relative 'Weapon'
  require_relative 'Shield'
  class Player
    @@MAX_WEAPONS=2
    @@MAX_SHIELDS=3
    @@INITIAL_HEALTH=10
    @@HITS2LOSE=3

    #Constructor
    def initialize(number,intelligence,strength)
      @number = number
      @intelligence = intelligence
      @strength = strength
      @health = @@INITIAL_HEALTH
      @name = "Player #{@number}"
      @row = -1
      @col = -1
      @Weapons = Array.new
      @Shields = Array.new
      @consecutiveHits=0
    end
    #El jugador resucita
    def resurrect
      @health=@@INITIAL_HEALTH
      @consecutiveHits=0
      @Weapons=Array.new
      @Shields=Array.new
    end
    #Getter de row
    def getRow
      return @row
    end
    #Getter de col
    def getCol
      return @col
    end
    #Getter de number
    def getNumber
      @number
    end
    #Setter de posicion
    def setPos(row,col)
      @row=row
      @col=col
    end
    #Dice si esta muerto o no
    def dead
      if @health==0
        return true
      else
        return false
      end
    end
    #Por determinar
    def move(direction,valid_moves)
      size=valid_moves.size
      contained = valid_moves.include?(direction)
      if (size>0 && !contained)
        firstElement =valid_moves[0]
        return firstElement
      else
        return direction
      end
    end
    #Suma la fuerza de todas las armas y la del jugador
    def attack
      return @strength + sumWeapons
    end
    #Hay que implementar manageHIt
    def defend(recievedAttack)
      return manageHit(recievedAttack)
    end
    #Por determinar
    def receiveReward
      wReward=Dice.weaponsReward
      sReward=Dice.shieldReward
      for i in 1..wReward
        wnew=Weapon.new(Dice.weaponPower,Dice.usesLeft)
        self.recieveWeapon(wnew)
      end
      for i in 1..sReward
        snew = Shield.new(Dice.shieldPower,Dice.usesLeft)
        self.recieveShield(snew)
      end
      extraHealth=Dice.healthReward
      @health+=extraHealth
    end
    #Imprime el estado del jugador
    def to_s
      "El jugador #{@name}, con una inteligencia #{@intelligence}, una fuerza #{@strength}, una salud #{@health}, está en la posición [#{@row},#{@col}], y ha recibido #{@consecutiveHits} golpes consecutivos."
    end
    #Por determinar
    private
    def recieveWeapon(w)
      for i in 0..@Weapons.size-1
        wi=@Weapons[i]
        discard = wi.discard
        if discard
          @Weapons.delete_at(i)
        end
      end
      size=@Weapons.size
      if size < @@MAX_WEAPONS
        @Weapons.push(w)
      end
    end
    #Por determinar
    def recieveShield(s)
      for i in 0..@Shields.size-1
        si=@Shields[i]
        discard = si.discard
        if discard
          @Shields.delete_at(i)
        end
      end
      size=@Shields.size
      if size < @@MAX_SHIELDS
        @Shields.push(s)
      end
    end
    #Crea un nuevo arma
    def newWeapon
      weapon = Weapon.new(Dice.weaponPower, Dice.usesLeft)
      return weapon
    end
    #Crea un nuevo escudo
    def newShield
      shield = Shield.new(Dice.shieldPower,Dice.usesLeft)
      return shield
    end
    #Suma la fuerza de todas las armas
    def sumWeapons
      result = 0
      @Weapons.each do |weapon|
        result += weapon.attack
      end
      return result
    end
    #Suma la defensa de todos los escudos
    def sumShields
      result = 0
      @Shields.each do |shield|
        result +=shield.protect
      end
      return result
    end
    #Por determinar
    def defensiveEnergy
      return @intelligence + sumShields
    end
    #Por determinar
    def manageHit(recievedAttack)
      defense = defensiveEnergy
      if defense<recievedAttack
        gotWounded
        inConsecutiveHits
      else
        resetHits
      end
      if (@consecutiveHits==@@HITS2LOSE || self.dead)
        resetHits
        lose=true
      else
        lose=false
      end
      return lose
    end
    #Resetea los golpes consecutivos
    def resetHits
      @consecutiveHits=0
    end
    #Quita uno de daño en salud por ataque
    def gotWounded
      @health-=1
    end
    #Añade uno a consecutive hits
    def inConsecutiveHits
      @consecutiveHits+=1
    end
  end
end