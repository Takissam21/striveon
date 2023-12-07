// Importez les classes nécessaires
package inf353;

class MainProgram {
    public static void main(String[] args) {
        // Création d'une matrice index naïve de dimensions 5x3
        MatriceIndexNaive matriceIndex = new MatriceIndexNaive(5, 3);

        // Utilisation des méthodes de la classe
        matriceIndex.incremente(1, 2); // Incrémente le nombre d'occurrences du terme 2 dans le document 1
        int valeur = matriceIndex.val(1, 2); // Récupère le nombre d'occurrences du terme 2 dans le document 1
        System.out.println("Nombre d'occurrences : " + valeur);

        // Sauvegarde de la matrice dans un fichier
        matriceIndex.sauver("matrice_index.txt");

        // Création d'une matrice index naïve à partir d'un fichier
        MatriceIndexNaive matriceIndexFromFile = new MatriceIndexNaive("matrice_index.txt");

        // Utilisation des méthodes de la matrice chargée depuis le fichier
        int valeurFromFile = matriceIndexFromFile.val(1, 2);
        System.out.println("Nombre d'occurrences (chargé depuis le fichier) : " + valeurFromFile);
    }
}
