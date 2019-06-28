package Plagiarism;

import java.util.ArrayList;

public class PathClass {
	//Navigate the File 
	String path,path2;
	ArrayList<String> firstString = new ArrayList<String>();
	ArrayList<String> secondString = new ArrayList<String>();
	ArrayList<String> firstMName = new ArrayList<String>();
	ArrayList<String> secondMName = new ArrayList<String>();
	ArrayList<Float> similarityAll = new ArrayList<Float>();
	ArrayList<Float> similarityAll2 = new ArrayList<Float>();
	public PathClass() {
		
	}
					//
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path=path;
	}
					//
	public String getPath2() {
		return path2;
	}
	public void setPath2(String path2) {
		this.path2=path2;
	}
					//
	public ArrayList<String> getFirstString(){
		return firstString;
	}
	
	public void setFirstString(ArrayList<String> firstString) {
		this.firstString = firstString;
	}
					//
	public ArrayList<String> getSecondString(){
		return secondString;
	}
	
	public void setSecondString(ArrayList<String> secondString) {
		this.secondString = secondString;
	}
					//
	public ArrayList<String> getFirstMethodName(){
		return firstMName;
	}
	
	public void setFirstMethodName(ArrayList<String> firstMName) {
		this.firstMName = firstMName;
	}
					//
	public ArrayList<String> getSecondMethodName(){
		return secondMName;
	}
	
	public void setSecondMethodName(ArrayList<String> secondMName) {
		this.secondMName = firstMName;
	}
	public void setSimilarityForAll(ArrayList<Float> similarityAll) {
		this.similarityAll = similarityAll;
	}
	public ArrayList<Float> getSimilarityForAll() {
		return similarityAll;
	}
					//
	public void setSimilarityForAll2(ArrayList<Float> similarityAll2) {
		this.similarityAll2 = similarityAll2;
	}
	public ArrayList<Float> getSimilarityForAll2() {
		return similarityAll2;
	}
	//
}
