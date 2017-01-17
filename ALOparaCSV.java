/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosfaixa;

import cad.OracleCRUD.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
/**
 *
 * @author cguterres
 */
public class ALOparaCSV {
    
    
    ObjetoLigacao ultimoOL ;
    UnidadeLeitura ultimaUL ;
    DadoInstalacao ultimaInstalacao ;
    Medidor ultimoMedidor ;
    DadoOSB60 ultimoDado ;
    public ALOparaCSV(String path, String name) {
        String data = name.substring(4,10) ;
        //try{
            Dados auxiliar;
        //    HashMap<String,Dados> coordenadas = getCoordenadas(); // LER COORDENADAS 2014 DO BANCO DE DADOS
        //    HashMap<String,Dados> coordenadas = readCoordenadas("coordenadas.txt");

        //    writeCoordenadas("coordenadas.txt",coordenadas); // ESCREVER COORDENADAS2014 EM SAIDA.TXT PARA EVITAR CONEXÃO COM O BANCO!
            PrintStream standard_output = System.out;
            String arquivo = path ;
            File f = new File(arquivo);
            Alo alo = new Alo(f) ;
            String fase = "0";

            ArrayList<String> dataconsumo = new ArrayList<String>() ;
            for (Registro r : alo.getRegistros()){
                if (r instanceof UnidadeLeitura ){
                    FileOutputStream fos = null ;
                    ultimaUL = (UnidadeLeitura) r ;
                    File ULcsv = new File("CSVs/" +ultimaUL.getCodigoUnidadeLeitura() + "_" + name + ".csv") ;
                    try {
                        fos = new FileOutputStream(ULcsv);
                        PrintStream new_output = new PrintStream(fos);
                        System.setOut(new_output);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ALOparaCSV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (r instanceof ObjetoLigacao){
                    ultimoOL = (ObjetoLigacao) r ;
                }
                if (r instanceof DadoInstalacao){
                    dataconsumo = new ArrayList<String>() ;
                    ultimaInstalacao = (DadoInstalacao) r ; 
                }
                if (r instanceof Medidor){
                    ultimoMedidor = (Medidor) r ;
                }

                if (r instanceof DadoOSB60){
                    ultimoDado = (DadoOSB60) r ;
                    if (ultimaInstalacao.getFases().contains("3F")){
                        fase = "3" ;
                    }else{
                        if (ultimaInstalacao.getFases().contains("2F")){
                            fase = "2" ;
                        }else{
                            fase = "1" ;
                        }
                    }
                    if (!ultimoDado.getMesReferencia1().equals("      ")){
                        /*
                        // VERIFICANDO SE UNIDADE DE INSTALAÇÃO ESTÁ EM COORDENADAS 2014
                        if(coordenadas.containsKey(ultimaInstalacao.getInstalacao()))
                        {    
                            auxiliar = coordenadas.get(ultimaInstalacao.getInstalacao());
                            System.out.println(auxiliar.getLat() +";"+auxiliar.getLongitude()+";"+ ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor()+ ";" + fase + ";" + ultimoDado.getMesReferencia1() + ";" + ultimoDado.getConsumo1()) ;
                        }else
                        */
                            dataconsumo.add(ultimoDado.getMesReferencia1() + "-" + ultimoDado.getConsumo1()) ;
                            System.out.println("0.0;0.0"+";"+ ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor()+ ";" + fase + ";" + ultimoDado.getMesReferencia1() + ";" + ultimoDado.getConsumo1()) ;

                        
                    }
                    if (!ultimoDado.getMesReferencia2().equals("      ")){
                        /*
                        // VERIFICANDO SE UNIDADE DE INSTALAÇÃO ESTÁ EM COORDENADAS 2014
                        if(coordenadas.containsKey(ultimaInstalacao.getInstalacao()))
                        {
                            auxiliar = coordenadas.get(ultimaInstalacao.getInstalacao());
                            System.out.println(auxiliar.getLat() +";"+auxiliar.getLongitude()+";"+ ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" + ultimoDado.getMesReferencia2() + ";" + ultimoDado.getConsumo2()) ;
                        }else
                        */
                            dataconsumo.add(ultimoDado.getMesReferencia2() + "-" + ultimoDado.getConsumo2()) ;
                            System.out.println("0.0;0.0;"+ ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" + ultimoDado.getMesReferencia2() + ";" + ultimoDado.getConsumo2()) ;

                    }
                    if (!ultimoDado.getMesReferencia3().equals("      ")){
                        /*
                        // VERIFICANDO SE UNIDADE DE INSTALAÇÃO ESTÁ EM COORDENADAS 2014
                       if(coordenadas.containsKey(ultimaInstalacao.getInstalacao()))
                       { 
                         auxiliar = coordenadas.get(ultimaInstalacao.getInstalacao());
                         System.out.println(auxiliar.getLat() +";"+auxiliar.getLongitude()+";"+ ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" + ultimoDado.getMesReferencia3() + ";" + ultimoDado.getConsumo3()) ;
                       }else
                        */
                        dataconsumo.add(ultimoDado.getMesReferencia3() + "-" + ultimoDado.getConsumo3()) ;
                        System.out.println("0.0;0.0;" + ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" + ultimoDado.getMesReferencia3() + ";" + ultimoDado.getConsumo3()) ;
                    }
                    if (!ultimoDado.getMesReferencia4().equals("      ")){
                        /*
                        // VERIFICANDO SE UNIDADE DE INSTALAÇÃO ESTÁ EM COORDENADAS 2014
                       if(coordenadas.containsKey(ultimaInstalacao.getInstalacao()))
                       {
                            auxiliar = coordenadas.get(ultimaInstalacao.getInstalacao());
                            System.out.println(auxiliar.getLat() +";"+auxiliar.getLongitude() +";" +ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" + ultimoDado.getMesReferencia4() + ";" + ultimoDado.getConsumo4()) ;
                       }else
                        */
                        dataconsumo.add(ultimoDado.getMesReferencia4() + "-" + ultimoDado.getConsumo4()) ;

                        System.out.println("0.0;0.0;" + ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" + ultimoDado.getMesReferencia3() + ";" + ultimoDado.getConsumo3()) ;



                    }
                    if (!ultimoDado.getMesReferencia5().equals("      ")){
                        /*
                        // VERIFICANDO SE UNIDADE DE INSTALAÇÃO ESTÁ EM COORDENADAS 2014
                       if(coordenadas.containsKey(ultimaInstalacao.getInstalacao())) 
                       {
                         auxiliar = coordenadas.get(ultimaInstalacao.getInstalacao());
                         System.out.println(auxiliar.getLat() +";"+auxiliar.getLongitude()+";"+ ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" + ultimoDado.getMesReferencia5() + ";" + ultimoDado.getConsumo5()) ;
                       }else
                        */
                        dataconsumo.add(ultimoDado.getMesReferencia5() + "-" + ultimoDado.getConsumo5()) ;

                        System.out.println("0.0;0.0"+";"+ ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" + ultimoDado.getMesReferencia5() + ";" + ultimoDado.getConsumo5()) ;

                    }
                    // LATITUDE ; LONGITUDE ; UNIDADE DE LEITURA ; OBJETO DE LIGACAO ; CLASSE ; MEDIDOR ; FASE ; DATA ; CONSUMO
                }
                /*
                if (r instanceof DescricaoParceiro){
                    System.out.print("0.0;0.0"+";"+ ultimaUL.getCodigoUnidadeLeitura() + ";" + ultimoOL.getCodigoObjetoLigacao() + ";" + ultimaInstalacao.getClasse() + ";" + ultimaInstalacao.getInstalacao() + ";" + ultimoMedidor.getNumeroMedidor() + ";" + fase + ";" ) ;
                    for (int i = 0; i < dataconsumo.size(); i++){
                        if (i == dataconsumo.size()-1){
                            System.out.print(dataconsumo.get(i) + "\n") ;
                        }else{
                            System.out.print(dataconsumo.get(i) + ";") ;
                        }
                    }
                }
                */
                
            }
            
        //}catch(FileNotFoundException fnfe){
            
        //}
    }
    
    /**
     * Escreve coordenadas lidas do banco em saida.txt
     * @param name Endereço do arquivo saida.txt a ser lido
     * @param catalogo HashMap de catalogo com as coordenadas 2014 
     */
    public void writeCoordenadas(String name,HashMap<String,Dados> catalogo)
    {
        File arquivo = new File(name);
        try(FileWriter fw = new FileWriter(arquivo))
        {
            System.out.println("ESCREVENDO COORDENADAS EM SAIDA.TXT ...");

            for (Map.Entry<String,Dados> pair : catalogo.entrySet()) {
                  fw.write(pair.getValue().getRotulo()+"\n");
                  fw.write(pair.getValue().getLat()+"\n");
                  fw.write(pair.getValue().getLongitude()+"\n");
                  fw.flush();
            }
            fw.close();
            System.out.println("COORDENADAS ESCRITAS EM SAIDA.TXT !!!");

        } catch (IOException ex) {
            Logger.getLogger(ALOparaCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Ler Coordenadas de saida.txt
     * @param name Endereço do arquivo saida.txt a ser lido
     * @return HashMap de Dados com as coordenadas 2014
     */
    public static HashMap<String,Dados> readCoordenadas(String name)
    {
        HashMap<String,Dados> listCoordenadas = new HashMap<>();        
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(name));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ALOparaCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dados o;
        System.out.println("LENDO COORDENADAS DE SAIDA.TXT ...");
        while(scanner.hasNext())
        {
            String rotulo = scanner.nextLine();
            double lat = Double.parseDouble(scanner.nextLine());
            double longitude = Double.parseDouble(scanner.nextLine());
//            System.out.println(rotulo +";"+lat+";"+longitude);
            o = new Dados(rotulo, lat, longitude);
//            listCoordenadas.put(rotulo, new Dados(rotulo,lat,longitude));
            listCoordenadas.put(rotulo, o);
        }
        scanner.close();
        System.out.println("COORDENADAS LIDAS DE SAIDA.TXT !!!");

        return listCoordenadas;
    }
    
    /**
     * PEGA COORDENADAS DO BANCO DE DADOS
     * @return HashMap de Dados com as coordenadas 2014
     */
    public HashMap<String,Dados> getCoordenadas()
    {
        DBConnectionOracle.makeConnection();
        Coordenadas2014 n = new Coordenadas2014();
        HashMap<String,Dados> result = n.getList();
        DBConnectionOracle.closeConnection();
        return result;
    }
       
    public static void main(String[] args) {
        // TODO code application logic here
        String path = "alos/" ;
        File folder = new File(path) ;
        PrintStream std_output = new PrintStream(System.out);

        for (File dir : folder.listFiles()){
            /*
            if (!dir.isDirectory()){
                String name = dir.getName() ;
                path = dir.getAbsolutePath() ;
                System.out.println(path) ;
                ALOparaCSV a = new ALOparaCSV(path, name) ;
            }
            */
            for (File file : dir.listFiles()){
                System.setOut(std_output);
                String name = file.getName() ;
                path = file.getAbsolutePath() ;
                System.out.println(path) ;
                ALOparaCSV a = new ALOparaCSV(path, name) ;
            }
                    
        }
    }
}
    

