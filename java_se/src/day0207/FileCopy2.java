package day0207;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 
 * 
 * 
 * 
 */

public class FileCopy2 extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel jlOutPut;

	public FileCopy2() {
		super("N-I/O를 사용한 파일 복사");

		JButton jbtnCopy = new JButton("복사할 파일 선택");
		jlOutPut = new JLabel("복사할 파일을 선택해주세요.");
		JPanel jpCenter = new JPanel();
		jpCenter.add(jbtnCopy);

		jlOutPut.setBorder(new TitledBorder("출력"));

		add("Center", jpCenter);
		add("South", jlOutPut);

		jbtnCopy.addActionListener(this);

		setBounds(100, 100, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// FileCopy

	public void fileCopy() throws FileNotFoundException, IOException {
		// 원본파일명 얻기
		FileDialog fdOpen = new FileDialog(this, "복사할 파일 선택", FileDialog.LOAD);

		fdOpen.setVisible(true);

		File fileOrigin = new File(fdOpen.getDirectory() + fdOpen.getFile());

		if (fdOpen.getDirectory() == null) {
			return;
		}

		if (!fileOrigin.exists()) {

			JOptionPane.showMessageDialog(this, "파일이 존재하지 않습니다.");
			return;
		} // end if

		// 복사할 파일명을 생성 : 원본파일명_bak.확장자 => a.txt => a_bak.txt

		StringBuilder tempFileName = new StringBuilder(fileOrigin.getAbsolutePath());
		tempFileName.insert(tempFileName.lastIndexOf("."), "_bak");

		File fileCopy = new File(tempFileName.toString());

//		//N-I/O를 사용한 복사
//		Path originPath = Path.of(fileOrigin.getAbsolutePath());//Paths.get("경로")
//		Path copyPath = Path.of(fileCopy.getAbsolutePath());//Paths.get("경로")
//		
//		try {
//			Files.copy(originPath, copyPath,StandardCopyOption.REPLACE_EXISTING);
//			jlOutPut.setText(fileCopy.getName() + "파일명으로 복사가 되었습니다.");
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(this, "파일 복사 도중 문제 발생");
//			e.printStackTrace();
//		}
		//16bit 스트림 연경
		
		// 읽기 - 단순 txt , HTML, XML, Json , java ..... , 그 외에는 전부 불가능 ex) 사진, 동영상 등등...
		BufferedReader br = null;
		// 쓰기
		BufferedWriter bw = null;

		try {
			
			
			br = new BufferedReader(new FileReader(fileOrigin));

			bw = new BufferedWriter(new FileWriter(fileCopy));
			String temp = "";
			// 줄단위를 읽어들여 ( \n 전까지 읽어들인다.)
			while ((temp = br.readLine()) != null) {
				bw.write(temp+"\n");//
			} // end While
			bw.flush();// 스트림에 존재하는 내용을 목적지로 분출

			jlOutPut.setText(fileCopy.getName() + "파일명으로 복사가 되었습니다.");

		} finally {
			// 연결을 끊는다.
			if (br != null) {
				br.close();
			}
			if (bw != null) {
				bw.close();
			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {


		try {
			fileCopy();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// actionPerformed

	public static void main(String[] args) {
		new FileCopy2();
	}// main
}// class