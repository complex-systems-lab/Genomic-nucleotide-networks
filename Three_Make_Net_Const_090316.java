import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.*;
import java.io.IOException;

/**
 *
 * @author pra
 */

public class Three_Make_Net_Const_090316 extends Thread {
    
    ArrayList<Integer> vList;
    ArrayList<String> cList, fileList;
    BufferedReader brvList, brvSeqList, brfileList;
    Date dNow;
    String path;
    
    String fileName;
    File file;
    FileWriter writer;
    String writeFile;
    
    int or_numOfSeq;
    int size;
    
    Thread t;
    int i;
    
    //takes input from three files
    Three_Make_Net_Const_090316(int start) throws IOException 
    {
        i = start;
        
        vList = new ArrayList<Integer>();
        cList = new ArrayList<String>();
        fileList = new ArrayList<String>();
        
        path = "\home\project\";
        path = "";
        
        brvList = new BufferedReader(new FileReader(path + "VList.dat"));
        
        String line;
        while (((line = brvList.readLine()) != null)) {
            
            vList.add(Integer.parseInt(line));
        }
        
        brvSeqList = new BufferedReader(new FileReader(path + "VSeqList.dat"));
        while (((line = brvSeqList.readLine()) != null)) {
            
            cList.add(line);
        }
        
        brfileList = new BufferedReader(new FileReader(path + "FileList.dat"));
        while (((line = brfileList.readLine()) != null)) {
            
            fileList.add(line);
        }
        
        size = fileList.size();
        or_numOfSeq = cList.size();
        
        //System.out.println("cList \t vList \t fileList : " +  cList.size() + "\t" +  vList.size() + "\t" + fileList.size());
        
        fileName = fileList.get(i) + ".dat";
        fileList = null;
        file = new File(path + fileName);
        file.createNewFile();
        writer = new FileWriter(file);
        writeFile = "";
        
        t = new Thread();
        t.start();
        
        
    }
    
    
    
    public void run()
    {
        try{
            
            int numOfSeq = or_numOfSeq;
            
            System.out.println(fileName+" ");
            
            for(int j = 0; j < vList.size(); j++)
            {
                // System.out.println(j);
                
                for(int k = j+1; k < vList.size(); k++){
                    
                    int count12 = 0;
                    int countFirstPos = 0, countSecondPos = 0;
                    
                    char charIJ = (cList.get(i).charAt(j));
                    char charIK = (cList.get(i).charAt(k));
                    
                    int flag1 = 0;
                    
                    if(charIJ == '-') flag1++;
                    if(charIK == '-') flag1++;
                    if(charIJ == 'N') flag1++;
                    if(charIK == 'N') flag1++;
                    
                    if(flag1 == 0)
                    {
                        
                        for(int i1 = 0; i1 < size; i1++) {
                            
                            char charI1J = (cList.get(i1).charAt(j));
                            char charI1K = (cList.get(i1).charAt(k));
                            
                            int flag2 = 0;
                            
                            if(charI1J == '-') flag2++;
                            if(charI1K == '-') flag2++;
                            if(charI1J == 'N') flag2++;
                            if(charI1K == 'N') flag2++;
                            
                            if(flag2 == 0) {
                                
                                if((charIJ == charI1J) && (charIK == charI1K)) {
                                    
                                    count12++;
                                }
                                
                                if(charIJ == charI1J) {
                                    
                                    countFirstPos++;
                                }
                                
                                if(charIK == charI1K) {
                                    
                                    countSecondPos++;
                                }
                            }
                            else{
                                
                                numOfSeq--;
                            }
                            
                        }//i1 ends
                        // Calculate the co-occurrence frequency
                        double connectFreq = (((double)count12 / (double)(numOfSeq)) * ((double)count12 / (double)(numOfSeq))) /
                        (((double)countFirstPos / (double)(numOfSeq)) * ((double)countSecondPos / (double)(numOfSeq)));
                        
                        //define the threshold for network construction
                        if(connectFreq >= 0.0)
                        {
                            
                            //writer.write(vList.get(j)+"\t"+vList.get(k) +"\t" + connectFreq +"\n");
                            writer.write(vList.get(j)+"\t"+vList.get(k) +"\t" +charIJ+"\t"+charIK
                                         +"\t"+countFirstPos+"\t"+countSecondPos +"\t" + connectFreq +"\n");
                            
                        }
                    }
                    
                }//k ends
                
            }//j ends
            
            writer.flush();
            writer.close();
            
        }//end try
        catch(NullPointerException e){System.out.println(e.getMessage());
        }
        catch(Exception e){System.out.println();
        }
    }
    
}
