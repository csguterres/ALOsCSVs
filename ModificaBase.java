import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cguterres
 */
public class ModificaBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		int refaturados = 0 ;
		int nao_refaturados = 0 ;
        File dir = new File(args[0] +"/");
        PrintStream std_output = System.out ;
        File base = new File("Base") ;
        base.mkdir() ;

        for (File file : dir.listFiles()){
        	System.setOut(std_output) ;
            try {
                Scanner scanner = new Scanner(file) ;
                String dataAnterior = "default";
                String rotuloInstalacaoAnterior = "default";
				String objetoLigacaoAnterior = "default" ;
                ArrayList<String> linha = new ArrayList<String>();
                File saida = new File(base.getAbsolutePath() + "/" + file.getName());
                FileOutputStream fos = new FileOutputStream(saida);
                PrintStream new_output = new PrintStream(fos);
                System.setOut(new_output);
				System.out.println("LATITUDE ; LONGITUDE ; UNIDADE DE LEITURA ; OBJETO LIGAÇAO ; CLASSE ; INSTALAÇAO ; MEDIDOR ; FASE ; MES ; CONSUMO; REFATURAMENTO (S/N); VALOR REFATURADO") ;
				boolean first = true ;
				if (scanner.hasNextLine()){
					scanner.nextLine() ;
				}
                while (scanner.hasNextLine()) {
                    String row = scanner.nextLine();
                    String campos[] = new String[9];
                    campos = row.split(";");
                    for (int i = 0; i < campos.length; i++) {
                        campos[i] = campos[i].trim();
                    }
                    String latitude = campos[0];
                    String longitude = campos[1];
                    String rotuloUnidadeLeitura = campos[2];
                    String rotuloObjetoLigacao = campos[3];
                    String classe = campos[4];
                    String rotuloInstalacao = campos[5];
                    String rotuloMedidor = campos[6];
                    String fase = campos[7];
                    String data = campos[8];
                    String consumo = campos[9];
					
                    if (!rotuloInstalacao.equals(rotuloInstalacaoAnterior) || !data.equals(dataAnterior) || !objetoLigacaoAnterior.equals(rotuloObjetoLigacao)) {
                        if (!(linha.size() ==0) ) {
                            System.out.print(linha.get(0));
                        }
                        if (linha.size() > 1) {
							int final_pos = linha.size()-1 ;
                            String new_row = linha.get(final_pos);
							String old_row = linha.get(0) ;
		                    int consumo_refaturado = Integer.parseInt((new_row.split(";"))[9]);
							int consumo_original = Integer.parseInt((old_row.split(";"))[9]) ;	
							if (consumo_original != consumo_refaturado && consumo_refaturado > 1){
	                            System.out.print("; SIM;" + consumo_refaturado+"\n");
								refaturados++ ;
							}else{
								System.out.print("; NAO;-1\n") ;
							}
                        }else{
							if (first == false){
								System.out.print("; NAO;-1\n") ;
								nao_refaturados++ ;
							}else{
								first = false ;
							}
						}
                        linha = new ArrayList<String>();
                    }
					linha.add(row);
      	            dataAnterior = data ;
  	                rotuloInstalacaoAnterior = rotuloInstalacao;
					objetoLigacaoAnterior = rotuloObjetoLigacao ;

                }
                if (!(linha.size() ==0) ) {
                	System.out.print(linha.get(0));
                }
                if (linha.size() > 1) {
					int final_pos = linha.size()-1 ;
                    String new_row = linha.get(final_pos);
					String old_row = linha.get(0) ;
                    int consumo_refaturado = Integer.parseInt((new_row.split(";"))[9]);
					int consumo_original = Integer.parseInt((old_row.split(";"))[9]) ;
					
					if (consumo_original != consumo_refaturado && consumo_refaturado > 1){
	                	System.out.print("; SIM;" + consumo_refaturado+"\n");
						refaturados++ ;
					}else{
						System.out.print("; NAO;-1\n") ;
					}
                }else{
					if (first == false){
						System.out.print("; NAO;-1\n") ;
						nao_refaturados++ ;
					}else{
						first = false ;
					}
				}
            } catch (FileNotFoundException e) {
				System.out.println("ERRO!") ;
            }
        }
       	System.setOut(std_output) ;
		System.out.println("Refaturados: " + refaturados + ", Nao Refaturados: " + nao_refaturados) ;
    }
    
}

