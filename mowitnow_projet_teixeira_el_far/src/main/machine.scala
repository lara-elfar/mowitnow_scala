package main

trait machine {
  def className: String = asInstanceOf[machine].getClass.getSimpleName.toLowerCase
}
  // La classe position caractérise les coordonnées de la tondeuse
  case class coordonnees(_x : Int, _y : Int, _sens : Sens.Value) extends machine {
    var x: Int = _x
    var y: Int = _y
    var sens: Sens.Value = _sens
    override def toString = s"$x $y ${sens.toString.charAt(0)}"}

  // La classe tondeuse définit et actualise les mouvements et déplacements de la tondeuse
  case class deplacement(_position: coordonnees, _sup_x : Int, _sup_y : Int) extends machine {

  // On initialise une var car la variable position change
  private val position : coordonnees = _position

  // On initialise une constante car les coordonnées maximales de la surface ne changent pas
  private val sup_x: Int =  _sup_x
  private val sup_y: Int =  _sup_y

  // On crée une fonction action qui définit les changements d'état de la tondeuse
  def action(command: Char): Unit = {
    command match {
      // Dans le cas où il y a la lettre "A" la tondeuse doit avancer. On fait donc les 4 cas possibles en fonction
      // du sens de la tondeuse.
      case 'A' =>
        position.sens match {
          case Sens.NORD =>
            if (position.y < sup_y) position.y = position.y + 1
          case Sens.EST =>
            if (position.x < sup_x) position.x = position.x + 1
          case Sens.SUD =>
            if (position.y > 0) position.y = position.y - 1
          case Sens.OUEST =>
            if (position.x > 0) position.x = position.x - 1
        }
      // Dans le cas où il y a la lettre "D" la tondeuse doit tourner à droite. On adapte le sens de la tondeuse
      // en fonction des 4 sens initiaux possibles.
      case 'D' =>
        position.sens match {
          case Sens.NORD => position.sens = Sens.EST
          case Sens.EST => position.sens = Sens.SUD
          case Sens.SUD => position.sens = Sens.OUEST
          case Sens.OUEST => position.sens = Sens.NORD
        }
      // Dans le cas où il y a la lettre "G" la tondeuse doit touner à gauche. On adapte le sens de la tondeuse
      // en fonction des 4 sens initiaux possibles.
      case 'G' =>
        position.sens match {
          case Sens.NORD => position.sens = Sens.OUEST
          case Sens.OUEST => position.sens = Sens.SUD
          case Sens.SUD => position.sens = Sens.EST
          case Sens.EST => position.sens = Sens.NORD
        }
    }
  }
  override def toString = s"$position"
}