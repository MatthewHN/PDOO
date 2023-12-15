module Irrgarten
    class TestP1
        def main
            require_relative 'Shield'
            require_relative 'Weapon'
            require_relative 'Dice'
            #Pruebas para la clase shield-
            shield = Shield.new(10, 3)
            shield1 = Shield.new(10,5)
            shield2 =Shield.new(10,1)
            puts shield.discard
            puts shield1.discard
            puts shield2.discard
            puts shield.to_s  # Debería mostrar "S[10, 3]"
            puts shield1.to_s
            puts shield2.to_s
             # Llamar a los métodos y probar la clase
            puts "Defence: #{shield.protect}"  # Debería mostrar "protect: 10"
            puts "Defence: #{shield2.protect}"
            puts shield.to_s  # Debería mostrar "S[10, 3]"
            puts shield1.to_s
            puts shield2.to_s
            puts "Defence: #{shield.protect}"  # Debería mostrar "protect: 10"
            puts "Defence: #{shield2.protect}"
            puts shield.discard
            puts shield1.discard
            puts shield2.discard
            #Funciona toda la clase shield
            puts "------------ \n"
            #Pruebas clase Weapon
            weapon = Weapon.new(10, 2)
            weapon1 = Weapon.new(10,5)
            weapon2 =Weapon.new(10,1)
            puts weapon.discard
            puts weapon1.discard
            puts weapon2.discard
            puts weapon.to_s  # Debería mostrar "S[10, 3]"
            puts weapon1.to_s
            puts weapon2.to_s
            # Llamar a los métodos y probar la clase
            puts "Attack: #{weapon.attack}"  # Debería mostrar "protect: 10"
            puts "Attack: #{weapon2.attack}"
            puts weapon.to_s  # Debería mostrar "S[10, 3]"
            puts weapon1.to_s
            puts weapon2.to_s
            puts "Attack: #{weapon.attack}"  # Debería mostrar "protect: 10"
            puts "Attack: #{weapon2.attack}"
            puts weapon.discard
            puts weapon1.discard
            puts weapon2.discard
            #Funciona todo en weapon
            puts "------------ \n"
            #Probar clase dice
            for i in 1..5
                puts Dice.intensity(4)
            end

        end
    end

    prueba = TestP1.new

    prueba.main
end


