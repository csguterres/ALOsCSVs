import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author cguterres
 */
public class Fix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


        File base = new File("Corrigidos");
        PrintStream std_output = System.out ;
        File dir = new File(args[0] +"/");
        base.mkdir() ;        
        
        for (File file : dir.listFiles()){
            System.setOut(std_output) ;
            try{
                boolean precisa_corrigir = false ;
  
                Scanner scanner = new Scanner(file) ;
                if (scanner.hasNextLine()){
                    scanner.nextLine() ;
		}
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int count = line.length() - line.replace(";", "").length();
                    if (count == 10) {
                        if (precisa_corrigir == false){
                            File saida = new File(base.getAbsolutePath() + "/" + file.getName());
                            FileOutputStream fos = new FileOutputStream(saida);
                            PrintStream new_output = new PrintStream(fos);
                            System.setOut(new_output);
                            System.out.println("LATITUDE ; LONGITUDE ; UNIDADE DE LEITURA ; OBJETO LIGAÇAO ; CLASSE ; INSTALAÇAO ; MEDIDOR ; FASE ; MES ; CONSUMO");
                            precisa_corrigir = true ;
                        }
                        String row[] = line.split(";");
                        System.out.print(row[0]);
                        for (int i = 1; i < 10; i++) {
                            System.out.print(";" + row[i]);
                        }
                        System.out.print("\n");
                    }
                }
            }catch(FileNotFoundException e) {
		System.out.println("ERRO!") ;
            }
        
        }
    }
}

