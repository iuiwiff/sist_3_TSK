package day0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UseTryWithResources {

	public UseTryWithResources() {

		// 키보드의 입력값을 줄 단위로 읽기


		
		
				
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
				System.out.println("아무키나 누르고 엔터");
				String readData=br.readLine();
				
				System.out.println("입력 값 : " + readData);
				
				
				
			}catch(IOException ie) {
				ie.printStackTrace();
			}//end catch
			
			
			//try ~ with ~ resources는 static인 stream(in , out, err)을 사용하지 않는다.
			
			//////////////////////////////////////////////////////////////////////////////
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
				System.out.println("아무키나 누르고 엔터2");
				String readData=br.readLine();
				
				System.out.println("입력 값 : " + readData);
				
				
				
			}catch(IOException ie) {
				ie.printStackTrace();
			}//end catch
			
			
			
			

	}//UseTryWithResources

	public static void main(String[] agrs) {
		new UseTryWithResources();
	}// main

}// class