package day0206_HomeWork;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class JavaMemoEvent extends WindowAdapter implements ActionListener {
	private static String oldFile;
	private static File oldFileSave;
	private static String newFile;
	private JavaMemoDesign jmd;

	public JavaMemoEvent(JavaMemoDesign jmd) {
		this.jmd = jmd;

	}

	public JavaMemoDesign getJmd() {
		return this.jmd;
	}

	private void overWrite() {

		File file = oldFileSave;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			String msg = jmd.getArea().getText();
			// 3. 스트림에 파일로 출력할 데이터를 기록
			bw.write(msg);
			// 4. 스트림에 남아있는 데이터를 목적지로 분출
			bw.flush();
			newFile = msg;
			bw.close();

		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch

	}// overWrite

	private boolean save() {

		boolean result;

		// System.out.println("저장버튼 클릭");

		FileDialog fdSave = new FileDialog(jmd, "파일 저장 ", FileDialog.SAVE);
		fdSave.setVisible(true);

		String path = fdSave.getDirectory();
		String fName = fdSave.getFile();

		if (path == null) {
			return false;
		}

		if (path != null) {// 취소를 누르면 경로나 파일명에 null 입력

			jmd.setTitle("저장 " + path + "/" + fName);// 타이틀 이름변경 메서드

		} // end if

		// 1. 파일 생성

		File file = new File(path + fName);

		// 2. 스트림 생성
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			String msg = jmd.getArea().getText();
			// 3. 스트림에 파일로 출력할 데이터를 기록
			bw.write(msg);
			oldFile = msg;
			// 4. 스트림에 남아있는 데이터를 목적지로 분출
			bw.flush();
			bw.close();

		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch
		oldFileSave = file;

		return true;

	}// save

	private int saveCheck() {
		int result = JOptionPane.OK_CANCEL_OPTION;
		switch (JOptionPane.showConfirmDialog(jmd, "저장하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			// YES일경우 저장하기

			if (save()) {

				// J.T.A 초기화
				jmd.area.setText("");
				jmd.setTitle("메모장 - 새글");
				jmd.area.requestFocus();
				result = JOptionPane.OK_OPTION;
			}
			break;
		case JOptionPane.NO_OPTION:
			// NO일경우 저장안함

			// J.T.A 초기화
			jmd.area.setText("");
			jmd.setTitle("메모장 - 새글");
			jmd.area.requestFocus();
			result = JOptionPane.NO_OPTION;
			break;
		case JOptionPane.OK_CANCEL_OPTION:

			result = JOptionPane.OK_CANCEL_OPTION;
			break;

		}

		return result;

	}// saveCheck

	private int OverWriteCheck() {
		int result = JOptionPane.OK_CANCEL_OPTION;
		switch (JOptionPane.showConfirmDialog(jmd, "저장하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			// YES일경우 저장하기
			overWrite();

			// J.T.A 초기화
			jmd.area.setText("");
			jmd.setTitle("메모장 - 새글");
			jmd.area.requestFocus();
			result = JOptionPane.OK_OPTION;
			break;
		case JOptionPane.NO_OPTION:
			// NO일경우 저장안함

			// J.T.A 초기화
			jmd.area.setText("");
			jmd.setTitle("메모장 - 새글");
			jmd.area.requestFocus();
			result = JOptionPane.NO_OPTION;
			break;
		case JOptionPane.OK_CANCEL_OPTION:

			result = JOptionPane.OK_CANCEL_OPTION;
			break;

		}

		return result;

	}// saveCheck

	private boolean checkModified() {

		boolean result;

		if (oldFile.equals(newFile)) {// 불러왔던 데이터와 지금 수정되어있는 텍스트 비교
			// 변화 없음
			result = false;

		} else {// 변화 발생
			result = true;
		}

		// 글이 수정되었을 경우 true 반환
		return result;
	}

	private void openFile() {

		FileDialog openFile = new FileDialog(jmd, "열기", FileDialog.LOAD);
		openFile.setVisible(true);

		String path = openFile.getDirectory();
		String fileName = openFile.getFile();
		String openFileString = path + fileName;

		if (path == null) {

			return;

		} // end if

		File file = new File(openFileString);

		if (!file.exists()) {

			System.out.println(file.getAbsolutePath() + "는 존재하지 않습니다.");
			return;
		}

		StringBuilder sb = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(openFileString)));

			String str = "";
			while ((str = br.readLine()) != null) {

				jmd.getArea().append(str + "\n");
				sb.append(str + "\n");

			}
			br.close();

		} catch (IOException ie) {
			ie.printStackTrace();

		}
		oldFile = sb.toString();
		oldFileSave = file;
		jmd.setTitle(openFileString);
		// System.out.println("oldFile : " + oldFile);

	}// openFile

	@Override
	public void windowClosing(WindowEvent e) {

		jmd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == jmd.jmi1) {
			System.out.println("새글버튼이 클릭되었음");

			newFile = jmd.getArea().getText();

			if (!(jmd.getTitle().equals("메모장") || jmd.getTitle().equals("메모장 - 새글"))) {
				// 열기를 진행했음 -> 타이틀이 "메모장" or "메모장 - 새글"이 아님
				System.out.println("열기를 진행했었음");

				if (jmd.getArea().getText().isEmpty()) {// 메모장에 글이 없음

					System.out.println("메모장이 비어있음");

					if (checkModified()) {
						System.out.println("글이 수정되었음");
						// 저장 여부 확인하기

						OverWriteCheck();

					} else {// 글이 수정되지 않았음 -> 메모장에 글이 없음
						System.out.println("글이 수정되지않았음");

						// J.T.A 초기화
						jmd.area.setText("");
						jmd.setTitle("메모장 - 새글");
						jmd.area.requestFocus();

					} // end if

				} else {// 메모장에 글이 존재한다.

					System.out.println("메모장에 정보가 존재함");

					if (checkModified()) {
						System.out.println("글이 수정되었음");

						OverWriteCheck();

					} else {// 글이 수정되지 않았음 -> 메모장에 글이 없음
						System.out.println("글이 수정되지 않았음");
						// J.T.A 초기화
						jmd.area.setText("");
						jmd.setTitle("메모장 - 새글");
						jmd.area.requestFocus();

					}

				}

			} else {
				// 열기를 진행하지 않았음 -> 타이틀이 "메모장" or "메모장 - 새글"임
				System.out.println("열기를 진행하지않았음");
				if (jmd.getArea().getText().isEmpty()) {// 메모장에 글이 없음
					System.out.println("메모장에 글이없음");

					jmd.setTitle("메모장 - 새글");

				} else {// 메모장에 글이 있음
					System.out.println("메모장에 글이있음");

					saveCheck();

				}

			}

		} // actionPerformed
		if (ae.getSource() == jmd.jmi1_1) {
			newFile = jmd.getArea().getText();

			// System.out.println("열기버튼 클릭");

			// 열기를 했을 경우 불러온 정보와 현 area에 존재하는 자료의 정보가 같은지 다른지 확인
			// 다르다면 저장유무추가
			// 변경되지 않았다면 그대로 열기 호출

			if (!(jmd.getTitle().equals("메모장") || jmd.getTitle().equals("메모장 - 새글"))) {// 열기를 했다면

				if (checkModified()) {
					System.out.println("글이 수정되었음");
					// 저장할건지 질문하고
					// 덮어쓰기 후
					// openFile()실행

					if (OverWriteCheck() != 2) {
						openFile();
					}

				} else {// 글이 수정되지 않았음 -> 메모장에 글이 없음
					System.out.println("글이 수정되지 않았음");
					// J.T.A 초기화
					jmd.area.setText("");
					jmd.area.requestFocus();
					openFile();

				}

			} else {// 열기를 하지 않았다면

				if (jmd.getArea().getText().isEmpty()) {// 메모장에 글이 없음
					System.out.println("메모장에 글이없음");
					openFile();

				} else {// 메모장에 글이 있음
					System.out.println("메모장에 글이있음");

					if (saveCheck() != 2) {
						openFile();
					}

				}

			}

		}
		if (ae.getSource() == jmd.jmi1_2) {
			// System.out.println("저장버튼 클릭");

			if (!(jmd.getTitle().equals("메모장") || jmd.getTitle().equals("메모장 - 새글"))) {// 열기를 했다면

				overWrite();

			} else {// 열기를 하지 않았다면

				save();

			}

		}

		if (ae.getSource() == jmd.jmi1_3) {
			System.out.println("새이름으로 저장버튼 클릭");

			save();

		}

		if (ae.getSource() == jmd.jmi1_4) {
			// System.out.println("종료버튼 클릭");
			jmd.dispose();

		}
		if (ae.getSource() == jmd.jmi2) {
			// System.out.println("글꼴버튼 클릭");
			new MemoFontDesign(jmd);

		}
		if (ae.getSource() == jmd.jmi3) {
			// System.out.println("메모장정보 버튼 클릭");

			new MemoHelpDesign(jmd);

		}

	}

}
