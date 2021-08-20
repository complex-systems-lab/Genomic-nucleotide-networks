# Genomic-nucleotide-networks
Nucleotide co-occurrence network construction codes.
<h2>Co-occurrence Network</h2>
<li>Open the One_find_variable_sites.java file in any editor and edit the path and name of fasta file and save the file in a directory.
<li>Open terminal and go into the directory where file is saved and run this file using command javac.
<li>It will generate three files named as "FileList.dat", "VList.dat", "VSeqList.dat".
<li>"FileList.dat" contains the names of each sequence in the fasta file.
<li>"VList.dat" contains all the variable sites.
<li>"VSeqList.dat" contains the sequences with only varibale sites.
<li>Next, run Second_Co-occ_net.java file using javac command.
<li>Last, open the Third_make_net_const.java file in any editor and edit the number of samples and save.
<li>Run this file using javac.
<li>This will generate co-occurrence network for each sequence in the fasta file.
  <h2>Co-mutation Network</h2>
<li> Run One_find_variable_sites.java as in step 1.
<li> Run Co-mutation_network_const.java to generate file with node pairs, minor allele freq, cf and p values.

