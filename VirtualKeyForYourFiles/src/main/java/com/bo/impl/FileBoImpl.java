package com.bo.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.exception.customFileException;

import model.FileModel;

import com.bo.FileBO;
import com.comparator.customFileComaprator;

public class FileBoImpl implements FileBO {

	public static List<FileModel> fileList; 
	
	public FileBoImpl(FileModel file) {
		fileList = new ArrayList<FileModel>();
		fileList = file.listFiles();
	}
	@Override
	public void createFile(FileModel file,boolean isDirectory) throws customFileException{
		
		if(isDirectory) {
			if(!file.mkdir()){
				if(file.exists())
					throw new customFileException("Directory already exists");
				else
					throw new customFileException("Directory could not be created. Please try again");
			}
		}
		else {
				
			
				if(file.exists())
					throw new customFileException("File already exists"); 
				else {
					try {
						file.createNewFile();
					} catch (IOException e) {
						throw new customFileException("File not created. Please try again\n"+file.getPath());
					}
				}
			
		}
		fileList.add(file);
		
}

	@Override
	public String getFile(String fileName) {
		
		for(FileModel f : fileList) {
			if(f.getName().compareTo(fileName) == 0)
				return "File Found\n"+fileName;
		}
		return "File Not Found";
	}

	

	@Override
	public void deleteFile(FileModel file) throws customFileException {
	
		if(!file.exists())
			throw new customFileException("File or Directory doesn't exist");
		if(!file.delete())
			throw new customFileException("Error deleting the file. Please try again");
		for(FileModel fmodel : fileList)
			if(fmodel.getName().compareTo(file.getName()) == 0) {
				{
					fileList.remove(fmodel);
					break;
				}
			}
	}

	@Override
	public String getAllFiles(FileModel file) {
		
		fileList = file.listFiles();
		Collections.sort(fileList,new customFileComaprator());
		String s="";
		for(FileModel f : fileList)
			s+=f.getName()+"\n";
		if(s.isEmpty())
			return "Directory is Empty";
		return s;
		
	}
	

}
