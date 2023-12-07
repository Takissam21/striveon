package inf353;

public class testeurIndexation {
    

    public static void main(String[] args){

           
        MatriceIndexNaive m = new MatriceIndexNaive(3, 2);
        m.matrice[0][0] = 1;
        m.matrice[0][1] = 2;
        m.matrice[1][0] = 3;
        m.matrice[1][1] = 4;
        m.matrice[2][0] = 5;
        m.matrice[2][1] = 6;

        m.sauver("taki.txt");
        System.out.println(m.val(2, 1));
        m.affecte(2, 1,20);
        System.out.println(m.val(2, 1));

        
    
        
    }
}
