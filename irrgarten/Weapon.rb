module Irrgarten
  require_relative 'Dice'
  class Weapon
      #Constructor con parámetros.
      def initialize(power, uses)
        @power = power
        @uses = uses
      end
      #Devuelve la intensidad del ataque del jugador y quita un uso.
      def attack
        if @uses > 0
          @uses -= 1
          return @power
        else
          return 0
        end
      end
      #Representa en forma de cadena de caracteres el estado interno del objeto.
      def to_s
          puts "W[#{@power}, #{@uses}]"
      end
      #Implementa si el arma se descarta o no.
      def discard
        Dice.discardElement(@uses)
      end
  end
end







