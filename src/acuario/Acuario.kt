package acuario

import kotlin.math.PI

// Clase base Acuario
open class Acuario(
    open var largo: Int = 100,
    open var ancho: Int = 20,
    open var alto: Int = 40
) {
    // Propiedad volumen con getter y setter
    open var volumen: Int
        get() = ancho * alto * largo / 1000  // 1000 cm³ = 1 litro
        set(valor) {
            alto = (valor * 1000) / (ancho * largo)
        }

    // Forma del acuario
    open val forma = "rectángulo"

    // Agua (90% del volumen)
    open var agua: Double = volumen * 0.9

    // Constructor secundario (por número de peces)
    constructor(numeroDePeces: Int) : this() {
        val tanque = numeroDePeces * 2000 * 1.1 // 2000 cm³ por pez + espacio extra
        alto = (tanque / (largo * ancho)).toInt()
    }

    // Método para imprimir dimensiones
    fun imprimirTamano() {
        println("Forma: $forma")
        println("Ancho: $ancho cm Largo: $largo cm Alto: $alto cm")
        println("Volumen: $volumen l Agua: $agua l (${agua / volumen * 100.0}% lleno)")
        println("-----------------------------------------")
    }
}

// Subclase TanqueTorre (cilíndrico)
class TanqueTorre(
    override var alto: Int,
    var diametro: Int
) : Acuario(alto = alto, ancho = diametro, largo = diametro) {

    override var volumen: Int
        // área elíptica = π * r1 * r2
        get() = (ancho / 2 * largo / 2 * alto / 1000 * PI).toInt()
        set(valor) {
            alto = ((valor * 1000 / PI) / (ancho / 2 * largo / 2)).toInt()
        }

    override var agua = volumen * 0.8
    override val forma = "cilindro"
}
