package inf353;

/**
 * Un Dictionnaire est un ensemble de L mot (tous différents). Chaque mot est associé à un entier
 * compris entre 0 et L-1.
 *
 * @author serasset
 *
 */
public class DictionnaireNaif implements Dictionnaire {

     private char[] d;
     private int L;
     private  int N ;
     private int taillemax;
     
     public DictionnaireNaif (int n){
         this.N=n;
         L=0;
         this.taillemax=n*40;
         this.d=new char[taillemax];
}
    /**
     * é.i: qcq
     * é.f.: le dictionnaire est vide.
     */
    public void vider(){
       int i=0;
       while (i!=taillemax){
             d[i]='\0';
             i=i+40;
        }
        L=0;		
    }    		

    /**
     * é.i.: le dictionnaire contient D0 (un ensemble de N mots).
     * é.f.: si m appartient à D0; le dictionnaire est inchangé
     *       sinon, le dictionnaire contient D0 U {m}
     *
     * @param m
     */
    public void ajouterMot(String m){
          if (!contient(m) && L< N){
		     int i =0;
		     int p=this.L*40; // Début de l'emplacement pour le nouveau mot
		     while (i<m.length()){
		       d[p+i]=m.charAt(i);
			   i=i+1;
			}
                        
			 d[p+m.length()] ='\0';//pour marquer la fin du mot ajouté 
			 L=L+1;
		  }
		  
	}
	/**
     * renvoie l'entier associé à m;
     * @param m
     * @return
     */
    public int indiceMot(String m){
	  int i=0;
	  boolean trouve=false;
	  while ((i!=L)&&(!trouve)){
	     int debmot=i*40;
		 int j=0;
		 while (j!=m.length() && d[debmot+j]==m.charAt(j)){
		       j++;
		 }
		 if (j==m.length()){
		      trouve =true ;
		 }else {
		      i=i+1;
		}
	  }
	  if (trouve){
	      return i;
	  }else {
	      return -1;
	}
   }
    /**
     * renvoie le mot associé à l'entier i;
     * @param i l'indice du mot à renvoyer
     * @return
     */
    public String motIndice(int i){
	    String ch="";
	    if (i >= 0 && i < L) {
			int debmot=i*40;
				while (d[debmot]!='\0'){
					 ch=ch+d[debmot];
					 debmot=debmot+1;
				}
		}
		if (ch==""){
			return null;
		}else{
			return ch;
		}
    }
    /**
     * renvoie vrai ssi m est dans le dictionnaire.
     * @param m
     * @return
     */
    public boolean contient(String m){
	    return indiceMot(m) != -1;
	}
    /**
     * renvoie le nombre de mots de m.
     * @return
     */
    public int nbMots(){
	     return L;
	}
    /**
     * vrai ssi il existe m dans D0 tel que il exist u tq m = p.u
     *
     * (vari si un mot de D0 commence par p)
     * @param p le préfixe recherché
     * @return
     */
    public boolean contientPrefixe(String p){
		  int i=0;
		  boolean trouve=false;
		  while ((i!=L)&&(!trouve)){
			 int debmot=i*40;
			 int j=0;
			   if (p.length() > motIndice(i).length()){
			         i=i+1;
			   }else {
					 while (j!=p.length() && d[debmot+j]==p.charAt(j)){
						   j++;
					 }
					 if  (j==p.length()){
						  trouve =true ;
					 }else {
						  i=i+1;
			   }
			}
	    }
		return trouve ;
	}  
    /**
     * renvoie la chaîne de caractères s telle que
     *  s est dans D0
     *  et m commence par s
     *  et il n'existe pas de chaîne s' ds D plus longue que s tq m commence par s.
     *
     * @param mot
     * @return
     */
    public String plusLongPrefixeDe(String mot){
			int i=0;
			String plusLongPrefixe =""; 
				 while (i!=L){
					int debmot=i*40;
					if ( motIndice(i).length()>mot.length()){
					      i=i+1;
					}else {
					    int j=0; 
						while(d[debmot+j]!='\0' && j<mot.length() && d[debmot+j]==mot.charAt(j)){
						   j++;
						}
						if (j>plusLongPrefixe.length()){
								plusLongPrefixe="";
								while (j>0){
								   plusLongPrefixe=plusLongPrefixe+d[debmot];
								   debmot=debmot+1;
								   j--;
								}
							}
							i=i+1;
					}
					
				 }
			 if (plusLongPrefixe==""){
			       return null;
		     }else{
			      return plusLongPrefixe;
		}
	        }
}
