package com.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bo.impl.FileBoImpl;
import com.exception.customFileException;

import model.FileModel;

public class AppStart {

	static String rootDir = "C:\\Phase1TestFolder";
	static Scanner sc = new Scanner(System.in);
	static FileBoImpl fileDB ;
	static FileModel currentDirectory;
	public static void menuScreen() {
		System.out.println("\n========================================================\n");
		System.out.println("\nMain Menu\n---------\nPlease enter one of the option below to proceed\n");
		System.out.println("1. View all files");
		System.out.println("\n2. File Operations");
		System.out.println("\n3. EXIT ");
		
	}
	
	public static void operationSubMenu() {
		System.out.println("\n========================================================\n");
		System.out.println("\nOperations Sub-Menu\n-------------------\nChoose the operation you wish to perform\n");
		System.out.println("\n1. Create a File/Directory");
		System.out.println("\n2. Delete a File/Directory");
		System.out.println("\n3. Search for a File/Directory");
		System.out.println("\n4. Go back\n");
	}
	
	public static void operationSubMenuControl() {
		
		char ch = '\0';
		do {
			operationSubMenu();
			ch = sc.next().charAt(0);
			switch (ch) {
			
				case '1':{
					String fileName;
					char fd='\0';
					while (true){
						System.out.println("\nCreate file or Directory?(F/D)");
						fd = sc.next().charAt(0);
						if(fd == 'F' || fd == 'D' )
							break;
						else
							System.out.println("\nEnter F or D as response \n");
					}
						
					System.out.println("\nEnter file name\n");
					sc.nextLine();
					fileName = sc.nextLine();
					
						if(fd == 'F') {
							try {
								fileDB.createFile(new FileModel(currentDirectory+"\\"+fileName), false);
							} catch (customFileException e1) {
								// TODO Auto-generated catch block
								System.out.println(e1.getMessage());
								break;
							}
						
						}
						else
							try {
								fileDB.createFile(new FileModel(currentDirectory+"\\"+fileName), true);
							} catch (customFileException e) {
								// TODO Auto-generated catch block
								System.out.println(e.getMessage());
								break;
							}
						System.out.println("\nCreation Successful\n");
					break;
				}
			case '2' :{
				String fileName;
				System.out.println("\nEnter file to delete\n");
				sc.nextLine();
				fileName = sc.nextLine();
				
				try {
					fileDB.deleteFile(new FileModel(currentDirectory.getPath()+"\\"+fileName));
				} catch (customFileException e) {
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("\nFile Deletion Successful\n");
				
				break;
			}
			
			case '3':{
				String fileName;
				System.out.println("\nEnter file name to be searched\n");
				sc.nextLine();
				fileName = sc.nextLine();
				System.out.println(fileDB.getFile(fileName));
			}
			
			case '4':{
				break;
			}
			default:
				System.out.println("\nEnter a valid Option\n");
				break;
			}
			
		} while (ch!='4');
		return;
	}
	
	
	
	public static void main(String[] args) throws IOException {
			
		
		currentDirectory = new FileModel(rootDir);
		currentDirectory.mkdir();
		
		fileDB = new FileBoImpl(currentDirectory);
		
		
		System.out.println("\n\n========================================================\n");
		System.out.println("\t\tFile Management System");
		System.out.println("\n========================================================\n");
		System.out.println("\t\tDevnath ES ");
	
		menuScreen();
		char ch = '\0';
		do {
			
			ch = sc.next().charAt(0);
			switch (ch) {
			case '1':
				System.out.println(fileDB.getAllFiles(currentDirectory));
				menuScreen();
				break;
			case '2':
				operationSubMenuControl();
				menuScreen();
				break;
			case '3':
				System.out.println("Thank You for using our Software");
				return;
			default:
				System.out.println("\nPlease Enter a valid option\n");
				break;
			}
			
		} while (ch!= '3');
		
		
		sc.close();
	}

}
