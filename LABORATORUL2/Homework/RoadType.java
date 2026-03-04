/**
 * Enumerare care defineste tipurile posibile de drumuri ce pot exista intre doua locatii.
 * Fiecare constanta reprezinta o categorie distincta de drum.
 */

public enum RoadType {

    /**
     * Autostrada - drum de mare viteza.
     */
    HIGHWAY,

    /**
     * Drum expres - drum rapid cu acces limitat.
     */
    EXPRESS,

    /**
     * Drum national - leaga orase importante.
     */
    NATIONAL,

    /**
     * Drum judetean - drum local.
     */
    COUNTY
}