package day0207;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class UseFiles {

	public void allLines(){
		
		//1. 읽어들일 파일의 경로 설정
		Path path = Paths.get("C:/dev/temp/java_read.txt");
		//2. 파일의 내용을 읽기
		System.out.println(path);
		try {
			List<String> lines = Files.readAllLines(path);
			
//			for(String readLine : lines) {
//				System.out.println(readLine);
//			}
			
			
			Iterator<String> ita = lines.iterator();
			while(ita.hasNext()) {
				System.out.println(ita.next());
				
			}
			
			
			
		} catch(NoSuchFileException nsfe){
			nsfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}//allLines
	
	public static void main(String[] args) {

		new UseFiles().allLines();
	}//main

}//class