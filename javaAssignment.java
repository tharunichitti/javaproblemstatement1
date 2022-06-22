package javaAssignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class pdfReader {
	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\TGODUGU\\Documents\\resume");
File files[] = f.listFiles();
		
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("|                     Total number of files  in ::" + files.length+"                                  |");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("|                     Number Of Keyword Want To Enter                                |");
		System.out.println("--------------------------------------------------------------------------------------");
		Scanner sc = new Scanner(System.in);
		int numOfKeys = sc.nextInt();
		 sc.nextLine();
		Set<String> set = new HashSet<String>();
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("|                     Please Enter The Keyword                                       |");
		System.out.println("--------------------------------------------------------------------------------------");
		for (int i = 0; i < numOfKeys; i++) {
			String strting = sc.nextLine();
			set.add(strting);
		}
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("|                              SHOWING RESULTS                                    |");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("|                 FILE NAME                |              PERCENTAGE %            |");
		System.out.println("----------------------------------------------------------------------------------");
		for (File file : files) {
			int count=0;
			if (file.getName().contains("docx")) {				
				FileInputStream input = new FileInputStream(file);
				XWPFDocument document = new XWPFDocument(input);
				XWPFWordExtractor ex = new XWPFWordExtractor(document);
				String str = ex.getText().toLowerCase();
                for(String word:set) { 
				if (str.contains(word)) {
					count++;
				}}
                if(count>0) {
                	float percentage=(float) percentage(count,numOfKeys);
                	System.out.printf("%-44s"+"|"+"%.2f",file.getName(),percentage);
                	System.out.println("");
                }

			} else if (file.getName().contains("pdf")) {
				
				PDDocument document = PDDocument.load(file);
				PDFTextStripper test = new PDFTextStripper();
				String str = test.getText(document).toLowerCase();
			    for(String word:set) { 
					if (str.contains(word)) {
						count++;
					}}
			    if(count>0) {
                	float percentage=(float) percentage(count,numOfKeys);
                	System.out.printf("%-44s"+"|"+"%.2f",file.getName(),percentage);
                	System.out.println("");
                	
                }
				document.close();

			}

		}

	}

	private static double percentage(double count,double num) {
		double per;
		per=(count/num)*100;
		return per;


		
			}
	}
