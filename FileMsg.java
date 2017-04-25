package com.app.model;

public class FileMsg {
	
	
	private long fileID;
	
	private String fileName;
	
	private long size;

	public long getFileID() {
		return fileID;
	}

	public void setFileID(long fileID) {
		this.fileID = fileID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public FileMsg(long fileID, String fileName, long size) {
		super();
		this.fileID = fileID;
		this.fileName = fileName;
		this.size = size;
	}

	@Override
	public String toString() {
		return "FileMsg [fileID=" + fileID + ", fileName=" + fileName
				+ ", size=" + size + "]";
	}
	
	
	

}
