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

public class javaAssignment {

public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub



String filepath = "C:\\Users\\TGODUGU\\Documents\\resume";



File ip = new File(filepath);



File[] fp = ip.listFiles();



Set<String> set = new HashSet<String>();



Scanner input = new Scanner(System.in);
System.out.println("Enter the number of Keywords required to compare :");
int numOfKey = input.nextInt();
// System.out.println(numOfKey);
input.nextLine();
System.out.println("Enter the keywords :");
for (int i = 0; i < numOfKey; i++) {
String keyword = input.nextLine();
set.add(keyword);
}
System.out.println("List of keywords are : ");
System.out.println(set);
System.out.println(" ");
System.out.println("percentages of files containing Keywords are : ");



System.out.println(" ");



for (File file : fp) {



if (file.getName().contains("docx")) {
FileInputStream one = new FileInputStream(file);
XWPFDocument doc = new XWPFDocument(one);
XWPFWordExtractor ex = new XWPFWordExtractor(doc);
String resume = ex.getText();
ex.close();
String Resume = resume.toLowerCase();
int count = 0;
for (String ele : set) {
if (Resume.contains(ele)) {



count++;
}
}
System.out.println(file.getName());
percentage(count, numOfKey);



} else {
PDDocument document = PDDocument.load(file);
PDFTextStripper test = new PDFTextStripper();
String resume = test.getText(document);
String Resume = resume.toLowerCase();
int count = 0;
for (String ele : set) {
if (Resume.contains(ele)) {

count++;
}
}
System.out.println(file.getName());
percentage(count, numOfKey);
document.close();
}
System.out.println("*********************************************************");



}



}



public static void percentage(int count, int num) {
if (count == 0) {
System.out.println("0%");
} else {



System.out.println("matched keywords count is " + count);
// System.out.println(file.getName());
double diff = num - count;
double per = (diff / num) * 100;
double matched = 100 - per;
System.out.println(String.format("%.2f", matched));



}



}
}