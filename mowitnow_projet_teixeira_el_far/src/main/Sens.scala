package main

/*Nous choisissons d'utiliser le type énumération car il permet de spécifier l'ensemble des valeurs prises
par une variable/constante (ici constante) */

object Sens extends Enumeration {
  val NORD, EST, SUD, OUEST = Value
}
