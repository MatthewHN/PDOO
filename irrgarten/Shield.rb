
module Irrgarten
  require_relative 'Dice'

  class Shield
      #Constructor con parámetros.
      def initialize(protection, uses)
        @protection = protection
        @uses = uses
      end
      #Devuelve la intensidad de la defensa del jugador y reduce uno el uso.
      def protect
        if @uses > 0
          @uses -= 1
          return @protection
        else
          return 0
        end
      end
      #Devuelve el estado interno del objeto.
      def to_s()
          puts "S[#{@protection}, #{@uses}]"
      end
      ##Dice si un escudo se devuelve o no.
      def discard()
        Dice.discardElement(@uses)
      end
  end
end