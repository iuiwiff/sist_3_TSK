package day0206;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

/**
 * 
 * //C:/dev/temp/java_read.txt를 읽어와서<br>
 * //C:/dev/temp/java_write4.txt 파일을 생성한 후 <br>
 * //C:/dev/temp/java_read.txt 읽어들여 java_write4.txt 쓰는 <br>
 * //코드를 작성해보세요.<br>
 * //단, C:/dev/temp/java_write4.txt 파일이 존재하지 않으면 파일을 생성하고,<br>
 * //존재 한다면 덮어쓸것 인지 물어본 후 파일에 덮어 쓴다.<br>
 * 
 * 
 * 
 */
public class TestStream {

	public TestStream() throws IOException {
		BufferedWriter bw = null;
		BufferedReader br = null;
		String path = JOptionPane.showInputDialog("읽어올 파일의 경로를 입력해주세요.");

		if (path == null) {

			return;

		} // end if
		File readFile = new File(path);

		if (!readFile.exists()) {
			JOptionPane.showMessageDialog(null, "파일명이나 경로를 확인해주세요.");
			return;

		} // end if

		File newFile = new File("C:/dev/temp/java_write4.txt");
		if (newFile.exists()) {

			int option = JOptionPane.showConfirmDialog(null, "덮어쓰시겠습니까?");
			switch (option) {

			case JOptionPane.OK_OPTION:

				try {
					bw = new BufferedWriter(new FileWriter(newFile));
					br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile)));

					String str = "";

					while ((str = br.readLine()) != null) {

						bw.write(str + "\n");

					} // end while

				} finally {
					if (br != null) {

						br.close();
					}

				}

				bw.flush();
				bw.close();
				break;

			}

		}else {
			
			try {
				bw = new BufferedWriter(new FileWriter(newFile));
				br = new BufferedReader(new InputStreamReader(new FileInputStream(readFile)));

				String str = "";

				while ((str = br.readLine()) != null) {

					bw.write(str + "\n");

				} // end while

			} finally {
				if (br != null) {

					br.close();
				}

			}

			bw.flush();
			bw.close();
			
			
		}
	

	}// TestStream

	public static void main(String[] args) {

		try {
			new TestStream();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}// main

}// class
