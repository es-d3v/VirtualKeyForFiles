package com.comparator;

import java.io.File;
import java.util.Comparator;

import model.FileModel;

public class customFileComaprator implements Comparator<FileModel>{


	@Override
	public int compare(FileModel f1, FileModel f2) {
		// TODO Auto-generated method stub
		return f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
	}

}
