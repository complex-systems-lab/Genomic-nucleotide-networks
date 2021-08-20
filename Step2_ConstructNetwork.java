//package comutation2019;


import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Step2_ConstructNetwork implements Runnable {
    
    private volatile double p_val;
    int MAF_1; int MAF_2;
    int NUMBER_RANGE;
    double cooccure;
      
    Step2_ConstructNetwork(int MAF_1, int MAF_2, int NUMBER_RANGE, double cooccure) throws FileNotFoundException, IOException{
        
        this.MAF_1 = MAF_1;
        this.MAF_2 = MAF_2;
        this.NUMBER_RANGE = NUMBER_RANGE;
        this.cooccure = cooccure;
        this.p_val = 0.0;
        
        //int MAF_1 = countFirstPos; int MAF_2 = countSecondPos;
        //int NUMBER_RANGE = fileList.size();   
    }
    
    Step2_ConstructNetwork(){}
    
    @Override
    public void run(){
    
        int flag = 0;          
        int ITERATION = 10000; //INPUT
                    
        for(int m = 0; m < ITERATION; m++){
            
            if((permutation(this.MAF_1, this.MAF_2, this.NUMBER_RANGE)) >= this.cooccure)
                flag++;
        }
                    
        this.p_val = (double)(flag) / (double) ITERATION;
              
       //System.out.println("\t" + this.p_val + "\t" + this.getValue());
        
       /* try {
            Thread.sleep(1000);
            System.out.println("\t" + this.p_val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Step2_ConstructNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
               
    char[] allele_count(ArrayList str_alleles)
    {
        char[] minMax = new char[2];
    
        try{
                Set<String> st = new HashSet<String>(str_alleles); 
                int[] allele_int = new int[2];
                String[] allele_char = new String[2];
                
                
                int flag1 = 0;
                                
                for (String s : st) {
                    
                    allele_int[flag1] = Collections.frequency(str_alleles, s);
                    allele_char[flag1] = s;
                    flag1++;
                }
                
                //System.out.println(st);
                
                char min_allele; char max_allele;
                if(allele_int[0] > allele_int[1]) {
                
                    min_allele = allele_char[1].charAt(0);
                    
                    max_allele = allele_char[0].charAt(0);
                }
                else{
                        min_allele = allele_char[0].charAt(0);
                        max_allele = allele_char[1].charAt(0);
                }
        
                minMax[0] = min_allele;
                minMax[1] = max_allele;
                
                //System.out.println(minMax.length + ": ");
        }
        catch(NullPointerException e){}
        catch(ArrayIndexOutOfBoundsException ae){}
                
        return minMax;
    }
    
    /* permutation() function creats polymorphic positions with desired MAF and then 
    calculate co-occurrence frequency between them.*/
    
        double permutation(int MAF_1, int MAF_2, int NUMBER_RANGE){
           
        ArrayList al_maf1 = new ArrayList();
        ArrayList al_maf2 = new ArrayList();
                
        al_maf1 = random_MAF(MAF_1, NUMBER_RANGE);
        al_maf2 = random_MAF(MAF_2, NUMBER_RANGE);
        
        int rand_count_12 = 0;
        double rand_freq = 0;
        
       for(int i = 0; i < al_maf1.size(); i++){
            
            int i_val = (int) al_maf1.get(i);
            
            for(int j = 0; j < al_maf2.size(); j++)
            {
                int j_val = (int) al_maf2.get(j);
                if(i_val == j_val) {
                    
                    rand_count_12++;
                }
            }
        }
        
            
        if(rand_count_12 > 0){
            
            rand_freq = (double) (rand_count_12 * rand_count_12) / (double) (MAF_1 * MAF_2);
            //System.out.println(rand_count_12 + "\t" + rand_freq);
        }
        
        return rand_freq;
    }// permutation() ends
            
    /* random_MAF() creats a random polymorphic sites having a desired value of MAF. */
    ArrayList random_MAF(int SET_SIZE_REQUIRED, int NUMBER_RANGE) {
        
        Random random = new Random();
        Set set = new HashSet<Integer>(SET_SIZE_REQUIRED);
 
        while(set.size()< SET_SIZE_REQUIRED) {
            
            while(set.add(random.nextInt(NUMBER_RANGE)) != true);
        }
            assert set.size() == SET_SIZE_REQUIRED;
            //System.out.println(set);
            
        ArrayList aList = new ArrayList(); 
        for (Object x : set) 
            aList.add(x); 
        
        //System.out.println(aList);

        return aList;
    } // random_MAF() ends
    
    public static void main(String[] args) throws IOException {
        
        /* Declarations*/
        
          ArrayList<Integer> vList;
          ArrayList<String> cList, fileList;
          BufferedReader brvList, brvSeqList, brfileList;
          Date dNow;
          String path;
          
          String fileName;
          File file, file1;
          FileWriter writer;
          String writeFile;

          int or_numOfSeq;
          int size;
          
        /* Assignments */
        
        vList = new ArrayList<Integer>();
        cList = new ArrayList<String>();
        fileList = new ArrayList<String>();
        
        String folder = "tib";
        path = "/Users/thirdeye/Dropbox/High_alt_TEA/Tibetan/Co-mutation/";
        
        writer = new FileWriter(path + "Network/" + folder + "_net.dat");
	    
        brvList = new BufferedReader(new FileReader(path + "VList_" + folder +".dat"));
        String line;
        while (((line = brvList.readLine()) != null)) {
            
            vList.add(Integer.parseInt(line));     
        }
        
        brvSeqList = new BufferedReader(new FileReader(path + "VSeqList_" + folder +".dat"));
        while (((line = brvSeqList.readLine()) != null)) {
            
            cList.add(line);        
        }
    
        brfileList = new BufferedReader(new FileReader(path + "FileList_" + folder +".dat"));
        while (((line = brfileList.readLine()) != null)) {
            
            fileList.add(line);        
        }
      
        size = fileList.size();
        or_numOfSeq = cList.size();
                        
        //System.out.println("cList \t vList \t fileList : " +  cList.size() + "\t" +  vList.size() + "\t" + fileList.size());
        try{
            for(int i = 0; i < (vList.size()); i++)
            {
               for(int j = i + 1; j < vList.size(); j++)
               {
                   //System.out.println(cList.get(0));
                    int count12 = 0; 
                    int countFirstPos = 0, countSecondPos = 0;
                    double cooccure = 0;
                    
                    ArrayList<String> strI = new ArrayList<String>();
                    ArrayList<String> strJ = new ArrayList<String>();

                    char min_allele1, max_allele1;
                    char min_allele2, max_allele2;
                    char[] temp1 = new char[2];
                    char[] temp2 = new char[2];
                    int N_sites = 0;
                    
                    Step2_ConstructNetwork CLASS_CALL = new Step2_ConstructNetwork();
                                        
                    for(int k = 0; k < fileList.size(); k++){

                        String charI = Character.toString(cList.get(k).charAt(i));
                        String charJ = Character.toString(cList.get(k).charAt(j));

                        if((charI.charAt(0) == 'A' || charI.charAt(0) == 'T' || charI.charAt(0) != 'G' || charI.charAt(0) != 'C') && 
                                (charJ.charAt(0) == 'A' || charJ.charAt(0) == 'T' || charJ.charAt(0) == 'G' || charJ.charAt(0) == 'C')){
                            
                            strI.add(charI);
                            strJ.add(charJ);
                        }
                        else{
                            
                            N_sites++;
                        }

                        //System.out.println(charI + "\t" + charJ);
                    }//k ends

                    //System.out.println(strI);
                    temp1 = CLASS_CALL.allele_count(strI);
                    temp2 = CLASS_CALL.allele_count(strJ);
                    
                    //System.out.println(temp1.toString() + ": " + temp2.toString());
                    
                    min_allele1 = temp1[0]; max_allele1 = temp1[1];
                    min_allele2 = temp2[0]; max_allele2 = temp2[1];


                    for(int l = 0; l < strI.size(); l++)
                    {
                        if(strI.get(l).charAt(0) == min_allele1) countFirstPos++;
                        if(strJ.get(l).charAt(0) == min_allele2) countSecondPos++;

                        if((strI.get(l).charAt(0) == min_allele1) && (strJ.get(l).charAt(0) == min_allele2)) count12++;
                    }

                    cooccure = (double) (count12 * count12) / (double) (countFirstPos * countSecondPos);
                    //System.out.println(flag + "\t" + p_val);
                    
                    if(count12 >= 1 && cooccure >= 0.00) { //INPUT
                         
                             /* p-value statistics of nucleotide co-occuring pairs.*/
                             Step2_ConstructNetwork sc = new Step2_ConstructNetwork(countFirstPos, countSecondPos, fileList.size(), cooccure);
                             Thread myThread = new Thread(sc);
                             myThread.start();
                             myThread.join(100);
                             
                             //System.out.println(vList.get(i) + "\t" + vList.get(j) + "\t" + countFirstPos + "\t" + 
                             //countSecondPos + "\t" + count12 + "\t" + String.format("%.5f", cooccure) + "\t" + flag + "\t" + String.format("%.5f", p_val));
                                                      
                            writer.write(vList.get(i) + "\t" + vList.get(j) + "\t" + countFirstPos + "\t" + 
                                     countSecondPos + "\t" +min_allele1+ "\t" +min_allele2 + "\t" + count12 + "\t" + String.format("%.8f", cooccure) + "\t" + String.format("%.8f", sc.p_val) +"\n");
                             
                            // System.out.println(vList.get(i) + "\t" + vList.get(j) + "\t" + countFirstPos + "\t" + 
                            //         countSecondPos + "\t" + count12 + "\t" + String.format("%.5f", cooccure) + "\t" + sc.p_val +"");
                             
                           
                     }
                     
                    /**********************************************************/
             }//j ends

            }//i ends
        }
        catch(NullPointerException e){System.out.println(e.getMessage());
            }
        catch(Exception e){System.out.println();}
        
        writer.close();
    }   
    
}
