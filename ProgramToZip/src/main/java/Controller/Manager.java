/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author AN515-57
 */
public class Manager {
     public int checkInputIntLimit(int min, int max) {
         Scanner sc = new Scanner(System.in);
         while(true) {
             try {
                 int result = Integer.parseInt(sc.nextLine());
                 if(result < min || result > max ) {
                     throw new NumberFormatException();
                 }
                 return result;
             } catch (NumberFormatException e) {
                 System.err.println("Re-input");
             }
         }
                 
     }
     
     public String checkString() {
         Scanner sc = new Scanner(System.in);
         while(true) {
             try {
                 String str = sc.nextLine().trim();
                 if(str.length() == 0) {
                     throw new NumberFormatException();
                 }
                 return str;
             } catch (NumberFormatException e) {
                 System.err.println("Not empty!!");
             }
         }
     }
     
     
     public void zipFile() throws IOException {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter Source Folder: ");
         String pathSrc = checkString();
         System.out.print("Enter Destination Folder: ");
         String pathCompress = checkString();
         System.out.print("Enter Name: ");
         String fileZipName = sc.nextLine();
         
         boolean check = compressTo(pathSrc, fileZipName, pathCompress);
         if(check == true) {
             System.out.println("Successfully");
         } else {
             System.out.println("Fail");
         }
     }
     
     public boolean compressTo(String pathSrc, String fileZipName, String pathCompress) throws FileNotFoundException, IOException {
         String sourceFile = "E:\\Zip\\textzip.txt";
         String nameFos = pathCompress + "\\" + fileZipName + ".zip";
         FileOutputStream fos = new FileOutputStream("E:\\Zip\\textzip.zip");
         ZipOutputStream zipOut = new ZipOutputStream(fos);
         File fileToZip = new File("E:\\Zip\\textzip.txt");
         FileInputStream fis = new FileInputStream(fileToZip);
         ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
         zipOut.putNextEntry(zipEntry);
         final byte[] bytes = new byte[1024];
         int length;
         while((length = fis.read(bytes)) >= 0) {
             zipOut.write(bytes, 0, length);
         }
         zipOut.close();
         fis.close();
         fos.close();
         return true;
         
     }
     
     
     public void unzipFile() throws IOException {
         System.out.print("Enter Source file: ");
         String pathZipFile = checkString();
         System.out.print("Enter Destination Folder: ");
         String pathExtract = checkString();
         boolean check = extractTo(pathZipFile, pathExtract);
         if(check == true) {
             System.out.println("Successfully");
         } else {
             System.out.println("Fall");
         }
         
     }
     
     public boolean extractTo(String pathZipFile, String pathExtract) throws FileNotFoundException, IOException {
         String fileZip = "E:\\Zip\\textzip.zip";
         byte[] buffer = new byte[1024];
         ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
         ZipEntry zipEntry = zis.getNextEntry();
         while(zipEntry != null) {
             String fileName = zipEntry.getName();
             File newFile = new File("E:\\Zip\\" + "textzip.zip");
             FileOutputStream fos = new FileOutputStream(newFile);
             int len;
             while((len = zis.read(buffer)) > 0) {
                 fos.write(buffer, 0, len);
             }
             fos.close();
             zipEntry = zis.getNextEntry();
         }
         zis.closeEntry();
         zis.close();
         return true;
     }
     
     public void printMain() throws IOException {
         while(true) {
             System.out.println("1. Compression");
             System.out.println("2. Extraction");
             System.out.println("3. Exit");
             System.out.print("Enter your choice: ");
             int choice = checkInputIntLimit(1, 3);
             switch (choice) {
                 
                 case 1:
                     zipFile();
                     break;
                 case 2:
                     unzipFile();
                     break;
                 case 3:
                     return;
                     
             }
         }
     }
     
}
