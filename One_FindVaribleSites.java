//Program finds variable sites in multiple sequences and stores 3 files varsite positions, varsequence sites and sequence names

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class One_FindVaribleSites {

    public static void main(String[] args) throws IOException {

        One_FindVaribleSites fvs = new One_FindVaribleSites();
        // TODO code application logic here
    }

        ArrayList<String> myList;
	ArrayList<String> fileNameMyList;
	ArrayList<Integer> variableList;
        FileWriter wVSeqList, wVList, fileList;

	One_FindVaribleSites() throws FileNotFoundException, IOException
	{
		String line = "";
		String seq = "";
		int flagForArrayListSize = 0;
		Boolean IsVarSite = false;

                myList = new ArrayList<String>();
		fileNameMyList = new ArrayList<String>();

                int flag = 0;
                char[] data;
				String path = "/Users/complexsystemslab/Dropbox/Ancient_mtDNA/02_Meso";//input
				path = "";

                wVList = new FileWriter(path + "VList_Meso.dat", true) ;
                wVSeqList = new FileWriter(path + "VSeqList_Meso.dat", true) ;
                fileList = new FileWriter(path + "FileList_Meso.dat", true) ;

                BufferedReader br = new BufferedReader(new FileReader(path + "02_Meso.fasta"));//input
				String pattern = "(>)(.*)";
				Pattern r = Pattern.compile(pattern);


                while((line= br.readLine()) !=null) {

                   int starArray=1;

                   if(line.charAt(0)=='>'){

                        flagForArrayListSize++;

                        if(flagForArrayListSize!=1){

                            myList.add(seq);

                            //wCList.write(seq + "\n");}
                              seq = "" ;
                        }

                        Matcher m = r.matcher(line);
                        if(m.find()) {
                            fileNameMyList.add(m.group(2));
                            //System.out.println(m.group(2));
                            fileList.write(m.group(2) + "\n");
                        }
                   }
		   else	{
                            seq +=  line;
		   }
		}

		myList.add(seq);
                //wCList.write(seq + "\n");
		variableList = new ArrayList<Integer>();

		int flagCount=0;
		for(int i=0; i<seq.length(); i++)
		{
			IsVarSite = false;
			IsVarSite = findVariableSites(myList, i);

			if(IsVarSite==true)
			{
				flagCount++;
                                String vl = Integer.toString(i+1);
				variableList.add(i+1);
                                wVList.write(vl + "\n");
			}
		}

                int size = seq.length();
                int numOfSeq = myList.size();

                char[][] tempSeq = new char[size-1][variableList.size()];

		for(int i=0; i<numOfSeq; i++)
		{
                    for(int j=0; j < variableList.size(); j++)
                    {
			tempSeq[i][j] = myList.get(i).charAt(variableList.get(j)-1); // get() takes value from 1,2..n;hence -1 to make it array postion
                        wVSeqList.write(tempSeq[i][j]);

                    }
                    wVSeqList.write("\n");

		}

                //String filePath = path + "vList.dat";
		//writeArrayListInFile(variableList, "hi");

		System.out.println("Variable sites count: " + flagCount);

                 wVSeqList.close();
                 wVList.close();
                 fileList.close();
	}


        public static boolean findVariableSites(ArrayList<String> list, int i) {
		//Hashtable<Integer,Character> hm=new Hashtable<Integer,Character>();

		char varChar;
		int countA, countT, countG, countC;
		countA = countT = countG = countC = 0;

		double freqA, freqT, freqG, freqC, freqN;
		freqA = freqT = freqG = freqC = freqN = 0;
		boolean IsVaribleSite = false;
		boolean IsNPresent = false;

		char[] var = new char[list.size()];

		int countN=0;

		for(int j = 0; j < list.size(); j++){

                    var[j] = list.get(j).charAt(i);

                    if(var[j] == 'a' || var[j] == 'A') {

			countA++;
                    }
                    if(var[j] == 't' || var[j] == 'T') {

			countT++;
                    }
                    if(var[j] == 'g' || var[j] == 'G') {

			countG++;
                    }
                    if(var[j] == 'c' || var[j] == 'C') {

			countC++;
                    }
                    if(var[j] == 'N' || var[j] == '-' || var[j] == 'Y' || var[j] == 'R' || var[j] == 'M' || var[j] == 'W'){

			countN++;
			IsNPresent = true;
                    }
		}

		double listSize = (double) (list.size() - countN);

                //System.out.println(countN + "\t" + listSize);

		freqA =  ((double)countA / listSize);
		freqT =  ((double)countT / listSize);
		freqG =  ((double)countG / listSize);
		freqC =  ((double)countC / listSize);
		freqN =  ((double)countN / listSize);

		double thres = 1;

		if(IsNPresent == true)
		{
                    if((listSize == 0.0) || (listSize == 0)) {

                        IsVaribleSite = false;
                    }
                    else if(freqN == thres || freqA == thres || freqT == thres || freqG == thres|| freqC == thres){

                        IsVaribleSite = false;
                    }
                    else {

                        IsVaribleSite = true;
                        //System.out.println("Site with N:" +(i+1));
                    }
		}
		else{

                    if( freqA == thres || freqT == thres || freqG == thres|| freqC == thres) {

                        IsVaribleSite = false;
                    }
                    else {

                        IsVaribleSite = true;
                        //System.out.println("l:" +(i+1));
                    }
		}

		return IsVaribleSite;

	}// End of findVariableSites()
}
