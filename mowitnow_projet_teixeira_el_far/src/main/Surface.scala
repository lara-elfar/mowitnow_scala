package main
import scala.io.BufferedSource

object Surface {
  // On initialise les variables
  private var sup_x: Int = _
  private var sup_y: Int = _

  // On crée une fonction qui va lire et traiter les valeurs du fichier de données
  def lecture(fileName : String): Unit = {

    // On lit le fichier
    val fichierEntree: BufferedSource = io.Source.fromFile(getClass.getResource(s"/$fileName").getFile)

    // On récupére toutes les lignes du fichier
    val lignes = fichierEntree.getLines()

    // On récupére la première ligne du fichier qui correspond à la taille de la surface
    var ligne = lignes.next()

    // On split les deux coordonnées et on les assigne
    val coordSup = ligne.split(" ")

    sup_x = coordSup(0).toInt
    sup_y = coordSup(1).toInt

    var i = 1

    // On utilise une boucle while pour boucler jusqu'à qu'il n'y ait plus de lignes
    while (lignes.hasNext){

      ligne = lignes.next()

      // On récupére la position et le sens initial de la tondeuse qui correspond à la deuxieme ligne et on l'assigne
      val positionInitial = ligne.split(" ")

      val x = positionInitial(0).toInt
      val y = positionInitial(1).toInt

      val sensText = positionInitial(2)

      // On initialise la variable sens
      var sens = Sens.NORD

      sensText match {
        case "N" => sens = Sens.NORD
        case "E" => sens = Sens.EST
        case "S" => sens = Sens.SUD
        case "W" => sens = Sens.OUEST
      }

      val tondeuse = deplacement(coordonnees(x, y, sens), sup_x, sup_y)

      // On récupére la ligne suivante qui correspond à la liste d'instructions
      ligne = lignes.next()

      // On passe les instructions en format list
      val instruction = ligne.toList

      // On applique la fonction action sur chaque instruction
      instruction.foreach( tondeuse.action )

      // On affiche la position finale de la tondeuse
      println(s"Tondeuse $i : $tondeuse")
      i = i + 1

    }
    // On ferme le fichier
    fichierEntree.close()
  }
}
