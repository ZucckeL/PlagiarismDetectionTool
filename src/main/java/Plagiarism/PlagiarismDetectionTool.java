package Plagiarism;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PlagiarismDetectionTool extends JFrame {
	
	static ArrayList<String> dosyaList = new ArrayList<String>();
	static ArrayList<String> dosyaList2 = new ArrayList<String>();
	static PlagResult resultDetail = new PlagResult(0, 0);
	static PlagResult resultDetail2 = new PlagResult(0, 0);
	static int SimilarityFiles[];
	static int SimilarityFiles2[];
	static Float[] Similarity;
	static Float[] Similarity2;
	static int count  =0;
	public static void main(String args[]) throws Exception {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		final PathClass path = new PathClass();
		GSTiling start = new GSTiling();
		GSTiling start2 = new GSTiling();
		/////////////////////////////////////////////////////////////////////
		JFrame frame = new JFrame();
		frame.setTitle("Plagiarism Detection Tool For Java Class File");
		//////////////////////////////////////////////////////////////////////
		JPanel GUI = new JPanel();
		GUI.setLayout(null);
		///////////////////////////////////////////////////////////////////////
		final JPanel dugme = new JPanel();
		dugme.setLayout(null);
		dugme.setLocation(10,10);
		dugme.setSize(750,100);
		GUI.add(dugme);
		/////////////////////////////////////////////////////////////////////
		JPanel middle = new JPanel();
		middle.setLayout(null);
		middle.setLocation(40,40);
		middle.setSize(750,400);
		GUI.add(middle);
		/////////////////////////////////////////////////////////////////////
		
		final JTextField FFile = new JTextField(path.getPath());
		FFile.setSize(500,20);
		FFile.setLocation(200,20);
		FFile.setEditable(false);
		dugme.add(FFile);
		
		JTextArea File1 = new JTextArea();
		File1.setBackground(Color.white);
		File1.setBounds(10, 90,200, 250);
		File1.setEditable(false);
		middle.add(File1);
		
		JLabel File1SelectL = new JLabel("Project 1 Directories");
		File1SelectL.setForeground(Color.BLUE);
		File1SelectL.setBounds(50,50,130,75);
		dugme.add(File1SelectL);
		
		JScrollPane pane = new JScrollPane(File1);
		pane.setBounds(10,90,200,250);
		middle.add(pane);
		
		JLabel secim1L = new JLabel("1-Choose Class");
		secim1L.setForeground(Color.BLUE);
		secim1L.setBounds(50,300,100,100);
		middle.add(secim1L);
		
		JTextField secim1 = new JTextField("1");
		secim1.setHorizontalAlignment(JTextField.CENTER);
		secim1.setBackground(Color.white);
		secim1.setBounds(75, 365,45, 20);
		secim1.setEditable(true);
		middle.add(secim1);
		
		final JButton FStudent = new JButton("Choose Project 1");
		FStudent.setToolTipText("Choose First Student Files");
		FStudent.setSize(140,20);
		FStudent.setLocation(30,20);
		FStudent.setHorizontalAlignment(0);
		FStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("C:/Users/"));
				fc.setDialogTitle("Select Directory For Plagiarism");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(File1.getText() != null) {
					File1.setText(null);
				}
				if(fc.showOpenDialog(FStudent) == JFileChooser.APPROVE_OPTION) {
					path.setPath(fc.getSelectedFile().getAbsolutePath());
					FFile.setText(path.getPath());
					}
				dosyaPathDondur(new File(path.getPath()),dosyaList);
				String[] c = null;
				for(int i=0;i<dosyaList.size();i++) {
					c = dosyaList.get(i).split("\\\\");
		            System.out.println("Dosya Yolu : " + dosyaList.get(i));
		            File1.append(i+" - " + c[c.length-1].toString() + "\n");
		            
		        }
				
			}
		});
		dugme.add(FStudent);
		
		/////////////////////////////////////////////////////////////////
		
		final JTextField SFile = new JTextField(path.getPath2());
		SFile.setSize(500,20);
		SFile.setLocation(200,45);
		SFile.setEditable(false);
		dugme.add(SFile);
		
		JTextArea File2 = new JTextArea();
		File2.setBackground(Color.white);
		File2.setBounds(230, 90, 200, 250);
		File2.setEditable(false);
		middle.add(File2);
		
		JLabel File2SelectL = new JLabel("Project 2 Directories");
		File2SelectL.setForeground(Color.BLUE);
		File2SelectL.setBounds(270,50,130,75);
		dugme.add(File2SelectL);
		
		JScrollPane pane2 = new JScrollPane(File2);
		pane2.setBounds(230,90,200,250);
		middle.add(pane2);
		
		JLabel secim2L = new JLabel("2-Choose Class");
		secim2L.setForeground(Color.BLUE);
		secim2L.setBounds(270,300,100,100);
		middle.add(secim2L);
		
		JTextField secim2 = new JTextField("1");
		secim2.setHorizontalAlignment(JTextField.CENTER);
		secim2.setBackground(Color.white);
		secim2.setBounds(295, 365,45, 20);
		secim2.setEditable(true);
		middle.add(secim2);
		
		final JButton SStudent = new JButton("Choose Project 2");
		SStudent.setToolTipText("Choose Second Student Files");
		SStudent.setSize(140,20);
		SStudent.setLocation(30,45);
		SStudent.setHorizontalAlignment(0);
		SStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc2 = new JFileChooser();
				fc2.setCurrentDirectory(new java.io.File("C:/Users/"));
				fc2.setDialogTitle("Select Directory For Plagiarism");
				fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(File2.getText() != null) {
					File2.setText(null);
				}
				if(fc2.showOpenDialog(SStudent) == JFileChooser.APPROVE_OPTION) {
					path.setPath2(fc2.getSelectedFile().getAbsolutePath());
					SFile.setText(path.getPath2());
				}
				dosyaPathDondur(new File(path.getPath2()),dosyaList2);
				String[] d = null;
				for(int i=0;i<dosyaList2.size();i++) {
					d = dosyaList2.get(i).split("\\\\");
		            System.out.println("Dosya Yolu : " + dosyaList2.get(i));
		            File2.append(i+" - " + d[d.length-1].toString() + "\n");
		            
		        }
				
			}
		});
		dugme.add(SStudent);
		
		////////////////////////////////////////////////////////////////////////////
		JLabel thresholdL = new JLabel("Threshold");
		thresholdL.setForeground(Color.BLUE);
		thresholdL.setBounds(640,300,110,100);
		middle.add(thresholdL);
		
		JTextField threshold = new JTextField("0.8");
		threshold.setHorizontalAlignment(JTextField.CENTER);
		threshold.setBackground(Color.white);
		threshold.setBounds(645, 365,45, 20);
		threshold.setEditable(true);
		
		middle.add(threshold);
		/////////////////////////////////////////////////////////////////////////////
		JLabel minSimL = new JLabel("Minimum Similarity Point");
		minSimL.setForeground(Color.BLUE);
		minSimL.setBounds(460,300,150,100);
		middle.add(minSimL);
		
		JTextField minSimCount = new JTextField("1");
		minSimCount.setHorizontalAlignment(JTextField.CENTER);
		minSimCount.setBackground(Color.white);
		minSimCount.setBounds(495, 365,45, 20);
		minSimCount.setEditable(true);
		middle.add(minSimCount);
		////////////////////////////////////////////////////////////////////////////
		
		
		
		
		JTextArea result = new JTextArea("\n        Plagiarism\n        Project 1 -> Project 2\n        "+ resultDetail.suspectedPlagiarism + "\n" + "        Similarity : %" + resultDetail.similarity*100);
		result.setBackground(Color.GREEN);
		result.setForeground(Color.white);
		result.setFont(new Font("Times Roman",Font.BOLD,15));
		result.setBounds(460, 90, 200, 120);
		result.setEditable(false);
		middle.add(result);
		
		JTextArea results = new JTextArea("\n        Plagiarism\n        Project 2 -> Project 1\n        "+ resultDetail.suspectedPlagiarism + "\n" + "        Similarity : %" + resultDetail.similarity*100);
		results.setBackground(Color.GREEN);
		results.setForeground(Color.white);
		results.setFont(new Font("Times Roman",Font.BOLD,15));
		results.setBounds(460, 220, 200, 120);
		results.setEditable(false);
		middle.add(results);
		
		
		JLabel qResultSelectL = new JLabel("Quick Result");
		qResultSelectL.setForeground(Color.RED);
		qResultSelectL.setBounds(490,50,100,75);
		dugme.add(qResultSelectL);
		
		JButton RunButton = new JButton("RUN");
		RunButton.setToolTipText("RUN!");
		RunButton.setSize(120,20);
		RunButton.setLocation(50,445);
		RunButton.setHorizontalAlignment(0);
		RunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				Date startTime = new Date();
				long startTimeS = System.currentTimeMillis();
				FPD fControl = new FPD();
				float totalSimilarity = 0f;
				float total = 0f;
				float sim =0f;
				String className = dosyaList.get(Integer.parseInt(secim1.getText()));
				String className2 = dosyaList2.get(Integer.parseInt(secim2.getText()));
				System.out.println("\n---------------------------------------------------------\n");
				System.out.println("File 1 : "+className + "\nFile 2 : "+className2.toString());
				//1->2
				try {
					totalSimilarity = fControl.TotalSimilarity(className, className2,Integer.parseInt(minSimCount.getText()),Float.parseFloat(threshold.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Result Similarity(Project A -> Project B) : " + totalSimilarity);
				
				resultDetail.setSimilarity(totalSimilarity);
				if(totalSimilarity > Float.parseFloat(threshold.getText())) {
					resultDetail.suspectedPlagiarism = true;
					result.setText("\n        Plagiarism\n        Project 1 -> Project 2\n        " +resultDetail.suspectedPlagiarism+ "\n" + "        Similarity : %" + resultDetail.getSimilarity()*100);
					result.setBackground(Color.red);
				}else {
					resultDetail.suspectedPlagiarism = false;
					result.setText("\n        Plagiarism\n        Project 1 -> Project 2\n        " +resultDetail.suspectedPlagiarism + "\n" + "        Similarity : %" + resultDetail.getSimilarity()*100);
					result.setBackground(Color.green);
				}
				//1->2
				System.out.println("\n---------------------------------------------------------\n");
				//2->1
				System.out.println("File 1 : "+className2+ "\nFile 2 : "+className);
				totalSimilarity = 0f;
			    total = 0f;
				sim =0f;
				
				try {
					totalSimilarity = fControl.TotalSimilarity(className2, className,Integer.parseInt(minSimCount.getText()),Float.parseFloat(threshold.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Result Similarity(Project B -> Project A) : " + totalSimilarity);
				resultDetail2.setSimilarity(totalSimilarity);
				if(totalSimilarity > Float.parseFloat(threshold.getText())) {
					resultDetail2.suspectedPlagiarism = true;
					results.setText("\n        Plagiarism\n        Project 1 -> Project 2\n        " +resultDetail2.suspectedPlagiarism+ "\n" + "        Similarity : %" + resultDetail2.getSimilarity()*100);
					results.setBackground(Color.red);
				}else {
					resultDetail2.suspectedPlagiarism = false;
					results.setText("\n        Plagiarism\n        Project 1 -> Project 2\n        " +resultDetail2.suspectedPlagiarism+ "\n" + "        Similarity : %" + resultDetail2.getSimilarity()*100);
					results.setBackground(Color.green);
				}
				//2->1
				System.out.println("\n---------------------------------------------------------\n");
				
				 Date endTime = new Date();
				 long endTimeS = System.currentTimeMillis();
				 long estimatedTime = endTimeS - startTimeS;
				 float seconds = (float)estimatedTime/1000;
				 System.out.println("Start Time: "+ startTime + "\nEnd Time: "+ endTime + "\nEstimated Time: " + seconds);
			}
		});
		GUI.add(RunButton);
		
		////RUN ALL
		JButton RunALLButton = new JButton("RUN ALL");
		RunALLButton.setToolTipText("RUN ALL PROJECTS!");
		RunALLButton.setSize(120,20);
		RunALLButton.setLocation(635,445);
		RunALLButton.setHorizontalAlignment(0);
		RunALLButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)  {
			Date startTime = new Date();
			
			float totalSimilarity = 0f;
			float total = 0f;
			float sim =0f;
			int j;
			int k = 0,t = 0;
			
			List<Float> fileSimilarity = new ArrayList<Float>();
			long startTimeS = System.currentTimeMillis();
			FPD fControl = new FPD();
			//1->2
			SimilarityFiles = new int[dosyaList.size()];
			Similarity = new Float[dosyaList.size()];
			for(int i =0;i<dosyaList.size();i++) {
				
				sim =0f;
				j=0;
				while(j<dosyaList2.size() && sim != 1.0f) {
					String s1 = dosyaList.get(i);
					String s2 = dosyaList2.get(j);
					System.out.println("File 1 : "+dosyaList.get(i)+ "\nFile 2 : "+dosyaList2.get(j));
					try {
						totalSimilarity = fControl.TotalSimilarity(s1, s2,Integer.parseInt(minSimCount.getText()),Float.parseFloat(threshold.getText()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(totalSimilarity>sim) {
						sim=totalSimilarity;
						k=i;
						t=j;
					}
					j++;
				}
					fileSimilarity.add(sim);
					SimilarityFiles[k] = t;
					Similarity[k] = sim;
			}
			
			for(int i = 0;i<fileSimilarity.size();i++) {
				System.out.println("\nSimilarity "+ i +" : "+ fileSimilarity.get(i));
				total = total + fileSimilarity.get(i);
			}
			total = total / fileSimilarity.size();
			System.out.println("Result Similarity(Project A -> Project B) : " + total);
			resultDetail.setSimilarity(total);
			if(total > Float.parseFloat(threshold.getText())) {
				resultDetail.suspectedPlagiarism = true;
				result.setText("\n        Plagiarism\n        Project 1 -> Project 2\n        " +resultDetail.suspectedPlagiarism+ "\n" + "        Similarity : %" + resultDetail.getSimilarity()*100);
				result.setBackground(Color.red);
			}else {
				resultDetail.suspectedPlagiarism = false;
				result.setText("\n        Plagiarism\n        Project 1 -> Project 2\n        " +resultDetail.suspectedPlagiarism+ "\n" + "        Similarity : %" + resultDetail.getSimilarity()*100);
				result.setBackground(Color.green);
			}
			
			//1->2
			System.out.println("\n---------------------------------------------------------\n");
			fileSimilarity.clear();
			totalSimilarity = 0f;
			total = 0f;
			sim =0f;
			k=0;
			t=0;
			//2->1
			SimilarityFiles2 = new int[dosyaList2.size()];
			Similarity2 = new Float[dosyaList2.size()];
			for(int i =0;i<dosyaList2.size();i++) {
				
				sim =0f;
				j=0;
					while(j<dosyaList.size() && sim != 1.0f) {
					String s1 = dosyaList2.get(i);
					String s2 = dosyaList.get(j);
					System.out.println("File 1 : "+dosyaList2.get(i)+ "\nFile 2 : "+dosyaList.get(j));
					try {
						totalSimilarity = fControl.TotalSimilarity(s1, s2,Integer.parseInt(minSimCount.getText()),Float.parseFloat(threshold.getText()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(totalSimilarity>sim) {
						sim=totalSimilarity;
						k=i;
						t=j;
					}
					j++;
				}
					fileSimilarity.add(sim);
					SimilarityFiles2[k]=t;
					Similarity2[k] = sim;
			}
			
			for(int i = 0;i<fileSimilarity.size();i++) {
				System.out.println("\nSimilarity "+ i +" : "+ fileSimilarity.get(i));
				total = total + fileSimilarity.get(i);
			}
			
			total = total / fileSimilarity.size();
			System.out.println("Result Similarity(Project B -> Project A) : " + total);
			resultDetail2.setSimilarity(total);
			if(total > Float.parseFloat(threshold.getText())) {
				resultDetail2.suspectedPlagiarism= true;
				results.setText("\n        Plagiarism\n        Project 2 -> Project 1\n        " +resultDetail2.suspectedPlagiarism+ "\n" + "        Similarity : %" + resultDetail2.getSimilarity()*100);
				results.setBackground(Color.red);
			}else {
				resultDetail2.suspectedPlagiarism = false;
				results.setText("\n        Plagiarism\n        Project 2 -> Project 1\n        " +resultDetail2.suspectedPlagiarism+ "\n" + "        Similarity : %" + resultDetail2.getSimilarity()*100);
				results.setBackground(Color.green);
			}
			//2->1
			
				 Date endTime = new Date();
				 long endTimeS = System.currentTimeMillis();
				 long estimatedTime = endTimeS - startTimeS;
				 float seconds = (float)estimatedTime/1000;
				 System.out.println("Start Time: "+ startTime + "\nEnd Time: "+ endTime + "\nEstimated Time: " + seconds);
				 
			}
		});
		GUI.add(RunALLButton);
		////RUN ALL
		
		//Reset ALL Button & Function
		JButton resetALL = new JButton("Reset");
		resetALL.setToolTipText("Reset All!");
		resetALL.setSize(120,20);
		resetALL.setLocation(450,445);
		resetALL.setHorizontalAlignment(0);
		resetALL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				ResetAll(path,minSimCount,threshold,FFile,SFile,File1,File2,result,resultDetail,dosyaList,dosyaList2,resultDetail2,results);
			}
		});
		GUI.add(resetALL);
		//Reset ALL Button & Function
		
		JButton ResultFinal = new JButton("RESULT");
		ResultFinal.setToolTipText("Result Details");
		ResultFinal.setSize(120,20);
		ResultFinal.setLocation(250,445);
		ResultFinal.setHorizontalAlignment(0);
		ResultFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame resultFrame = new JFrame();
				resultFrame.setTitle("Result Details");
				
				JPanel secondGUI = new JPanel();
				secondGUI.setLayout(null);
				//Table 1
				String[] columns = {"First Files","Second Files","Similarity"};
				DefaultTableModel dtm = new DefaultTableModel();
				JTable table = new JTable();
				JScrollPane tablePane = new JScrollPane(table);
				tablePane.setBounds(425,60,350,225);
				table.setEnabled(false);
				table.setBounds(425,60,350,225);
				table.setModel(dtm);
				dtm.setColumnIdentifiers(columns);
				tablePane.getViewport().add(table);
				secondGUI.add(tablePane);
				String[] dosya1=null;
				String[] dosya2=null;
				for(int i=0;i<dosyaList.size();i++) {
					dosya1 = dosyaList.get(i).split("\\\\");
					dosya2 = dosyaList2.get(SimilarityFiles[i]).split("\\\\");
					dtm.addRow(new String[] {dosya1[dosya1.length-1].toString(),dosya2[dosya2.length-1].toString(),Float.toString(Similarity[i]*100)});
				}
				//Table 1
				
				//Table 2
				String[] columns2 = {"First Files","Second Files","Similarity"};
				DefaultTableModel dtm2 = new DefaultTableModel();
				JTable table2 = new JTable();
				JScrollPane table2Pane = new JScrollPane(table2);
				table2Pane.setBounds(425,300,350,225);
				table2.setEnabled(false);
				table2.setBounds(425,300,350,225);
				table2.setModel(dtm2);
				dtm2.setColumnIdentifiers(columns2);
				secondGUI.add(table2Pane);
				String[] dosya3=null;
				String[] dosya4=null;
				for(int i=0;i<dosyaList2.size();i++) {
					dosya3 = dosyaList2.get(i).split("\\\\");
					dosya4 = dosyaList.get(SimilarityFiles2[i]).split("\\\\");
					dtm2.addRow(new String[] {dosya3[dosya3.length-1].toString(),dosya4[dosya4.length-1].toString(),Float.toString(Similarity2[i]*100)});
				}
				//Table 2
				
				JLabel firstText = new JLabel("First Files : ");
				firstText.setBounds(62,40,100,100);
				secondGUI.add(firstText);
				JLabel secondText = new JLabel("Second Files : ");
				secondText.setBounds(45,290,110,100);
				secondGUI.add(secondText);
				
				
				///Result First Methods///
				JTextArea resultFDetails =new JTextArea();
				resultFDetails.setBackground(Color.WHITE);
				resultFDetails.setBounds(150, 75, 250, 200);
				resultFDetails.setDisabledTextColor(Color.BLACK);
				resultFDetails.setEnabled(false);
				resultFDetails.setVisible(true);
				secondGUI.add(resultFDetails);
				
				
				JScrollPane pane3 = new JScrollPane(resultFDetails);
				pane3.setBounds(150,75,250,200);
				pane3.setEnabled(false);
				pane3.setVisible(true);
				secondGUI.add(pane3);
				
				String[] a = null;
				for(int i = 0;i<dosyaList.size();i++) {
					 a = dosyaList.get(i).split("\\\\");
					 resultFDetails.append(i+1 + "-->" + a[a.length-1].toString() +"\n");
				}
				
				///Result First Methods///
				
				///Result Second Methods///
				JTextArea resultSDetails =new JTextArea();
				resultSDetails.setBackground(Color.WHITE);
				resultSDetails.setBounds(150, 325, 250, 200);
				resultSDetails.setDisabledTextColor(Color.BLACK);
				resultSDetails.setEnabled(false);
				resultSDetails.setVisible(true);
				secondGUI.add(resultSDetails);
				
				
				JScrollPane pane4 = new JScrollPane(resultSDetails);
				pane4.setBounds(150,325,250,200);
				pane4.setEnabled(false);
				pane4.setVisible(true);
				secondGUI.add(pane4);
				
				
				String[] b = null;
				for(int i = 0;i<dosyaList2.size();i++) {
					 b = dosyaList2.get(i).split("\\\\");
					 resultSDetails.append(i+1 +"-->" + b[b.length-1].toString() +"\n");
				}
				///Result Second Methods///
				
				
				
				///Similarity Result Label 1
				JLabel similarityResultL = new JLabel("Similarity(A->B) : %" + resultDetail.getSimilarity()*100);
				similarityResultL.setBounds(850,50,150,100);
				secondGUI.add(similarityResultL);
				
				///Similarity Result Label 2
				JLabel similarityResultL2 = new JLabel("Similarity(B->A) : %" + resultDetail2.getSimilarity()*100);
				similarityResultL2.setBounds(850,275,150,100);
				secondGUI.add(similarityResultL2);
				
				//Suspected Similarity 1
				JLabel plagiarismL = new JLabel("Plagiarism : ");
				plagiarismL.setBounds(850,75,150,100);
				secondGUI.add(plagiarismL);
				
				
				JLabel susSimilarity = new JLabel();
				susSimilarity.setForeground(Color.BLACK);
				susSimilarity.setBounds(925,75,150,100);
				secondGUI.add(susSimilarity);
				
				if(resultDetail.suspectedPlagiarism == true) {
					susSimilarity.setForeground(Color.RED);
					susSimilarity.setText("TRUE");
				}else{
					susSimilarity.setForeground(Color.GREEN);
					susSimilarity.setText("FALSE");
				}
				//Suspected Similarity 1
				
				//Suspected Similarity 2
				JLabel plagiarismL2 = new JLabel("Plagiarism : ");
				plagiarismL2.setBounds(850,315,150,100);
				secondGUI.add(plagiarismL2);
				
				
				JLabel susSimilarity2 = new JLabel();
				susSimilarity2.setForeground(Color.BLACK);
				susSimilarity2.setBounds(925,315,150,100);
				secondGUI.add(susSimilarity2);
				
				if(resultDetail2.suspectedPlagiarism == true) {
					susSimilarity2.setForeground(Color.RED);
					susSimilarity2.setText("TRUE");
				}else{
					susSimilarity2.setForeground(Color.GREEN);
					susSimilarity2.setText("FALSE");
				}
				//Suspected Similarity 2
				
				Reset(path,minSimCount,threshold,resultDetail,resultDetail2,result,results);
				
				secondGUI.setVisible(true);
				resultFrame.setVisible(true);
				resultFrame.setLocation(100,100);
				resultFrame.setSize(1024,600);
				resultFrame.setResizable(false);
				resultFrame.setContentPane(secondGUI);
			}
		});
		GUI.add(ResultFinal);
		
		
		GUI.setVisible(true);
		
		frame.setSize(800,600);
		frame.setLocation(250,100);
		frame.setContentPane(GUI);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	//File Directories List Operation
	public static void dosyaPathDondur(File fileDirectory, ArrayList<String> dosyaList) {
        if (fileDirectory.exists() && fileDirectory.isDirectory()) { 
            for (File chDir : fileDirectory.listFiles()) { 
                if (chDir.isDirectory()) { 
                    dosyaPathDondur(chDir, dosyaList); 
                } else {
                	if(chDir.getAbsolutePath().endsWith(".java")) {
                    dosyaList.add(chDir.getAbsolutePath()); }
                }
            }
        }
        
    }
	

	public static void ResetAll(PathClass path,JTextField minSimCount, JTextField threshold,JTextField FFile,JTextField SFile,JTextArea File1,JTextArea File2,JTextArea result,PlagResult resultDetail,ArrayList<String> dosyaList,ArrayList<String> dosyaList2,PlagResult resultDetail2,JTextArea results) {
		dosyaList.clear();
		dosyaList2.clear();
		path.firstString.clear();
		path.secondString.clear();
		minSimCount.setText("1");
		threshold.setText(threshold.getText());
		path.firstMName.clear();
		path.secondMName.clear();
		resultDetail.tiles.clear();
		resultDetail.suspectedPlagiarism = false;
		resultDetail.id1 = 0;
		resultDetail.id2 = 0;
		resultDetail.tiles.clear();
		resultDetail.similarity = 0;
		result.setText("\n        Plagiarism\n        Project 1 -> Project 2\n        "+ resultDetail.suspectedPlagiarism + "\n" + "        Similarity : %" + resultDetail.similarity*100);
		result.setBackground(Color.GREEN);
		resultDetail2.tiles.clear();
		resultDetail2.suspectedPlagiarism = false;
		resultDetail2.id1 = 0;
		resultDetail2.id2 = 0;
		resultDetail2.tiles.clear();
		resultDetail2.similarity = 0;
		results.setText("\n        Plagiarism\n        Project 2 -> Project 1\n        "+ resultDetail.suspectedPlagiarism + "\n" + "        Similarity : %" + resultDetail.similarity*100);
		results.setBackground(Color.GREEN);
		File1.setText("");
		File2.setText("");
		FFile.setText("");
		SFile.setText("");
	}
	public static void Reset(PathClass path,JTextField minSimCount,JTextField threshold,PlagResult resultDetail,PlagResult resultDetail2,JTextArea result,JTextArea results) {
		
		path.firstString.clear();
		path.secondString.clear();
		minSimCount.setText("1");
		threshold.setText(threshold.getText());
		path.firstMName.clear();
		path.secondMName.clear();
		resultDetail.tiles.clear();
		resultDetail.suspectedPlagiarism = false;
		resultDetail.id1 = 0;
		resultDetail.id2 = 0;
		resultDetail.tiles.clear();
		resultDetail.similarity = 0;
		resultDetail2.tiles.clear();
		resultDetail2.suspectedPlagiarism = false;
		resultDetail2.id1 = 0;
		resultDetail2.id2 = 0;
		resultDetail2.tiles.clear();
		resultDetail2.similarity = 0;
		results.setText("\n        Plagiarism\n        Project 2 -> Project 1\n        "+ resultDetail.suspectedPlagiarism + "\n" + "        Similarity : %" + resultDetail.similarity*100);
		results.setBackground(Color.GREEN);
		result.setText("\n        Plagiarism\n        Project 1 -> Project 2\n        "+ resultDetail.suspectedPlagiarism + "\n" + "        Similarity : %" + resultDetail.similarity*100);
		result.setBackground(Color.GREEN);
	}
}
