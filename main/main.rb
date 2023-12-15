require_relative '../irrgarten/Game'
require_relative '../controller/controller'
require_relative '../UI/textUI'
module Main
  class Testp3
    def main
      j=Irrgarten::Game.new(1)
      v=UI::TextUI.new
      c=Controller::Controller.new(j,v)
      c.play

    end
  end

  prueba = Testp3.new

  prueba.main
end