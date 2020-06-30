package model;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import com.exception.customFileException;

public class FileModel {
	String name;
	String path;
	
	public FileModel(String address) {
		File file = new File(address);
		name = file.getName();
		path = file.getPath();
	}
	
	@Override
	public String toString() {
		return path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean mkdir() {
		return new File(getPath()).mkdir();
	}
	
	public boolean createNewFile() throws IOException {
		return new File(this.getPath()).createNewFile();
	}
	
	public List<FileModel> listFiles() {
		File[] list = new File(getPath()).listFiles();
		List<FileModel> flist = new ArrayList<>(); 
		for(File f : list)
			flist.add(new FileModel(f.getPath()));
		return flist;
	}
	
	public boolean exists() {
		return new File(getPath()).exists();
	}
	
	public boolean delete() {
		return new File(getPath()).delete();
	}
	
}
