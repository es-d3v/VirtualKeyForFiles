package com.bo;

import java.io.File;

import com.exception.customFileException;

import model.FileModel;

public interface FileBO {
	
	String getFile(String file);
	void deleteFile(FileModel file) throws customFileException;
	String getAllFiles(FileModel file);
	void createFile(FileModel file, boolean isDirectory) throws customFileException;
	
}
