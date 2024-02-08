package day0206_HomeWork;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 1.새글 메모장의 내용이 없다면 타이틀 바에 "메모장-새글"로 설정하여 보여준다.
 * 
 * 
 * - 열기를 하지 않은 상태에서 내용이 있다면 "저장여부를 묻고"파일다이얼로그를 사용하여 해당 파일에 저장한 후 J.T.A를 초기화
 * 
 * 
 * - 열기를 한 상태에서는 기존의 내용과 현재 내용이 변경되어있는지 판단하여 변경되지 않았다면 J.T.A를 초기화 한다. 변경된 내용이
 * 있다면. 저장 여부를 묻고, 열기한 파일에 저장한 후 J.T.A를 초기화 한다.
 * 
 * 2.열기 - J.T.A에 내용이 없는 상태에서는 열기를 누르면 파일다이얼로그를 보여주고 열기를 수행한다 - 열기를 하지 않은 상태에서
 * J.T.A에 내용이 있다면 저장 여부를 물어보고 저장 후 열기를 수행 - 열기를 수행한 상태에서 다시 열기를 하면 이전에 열기한 내용과
 * 현재의 내용이 변경되었는지 물어보고 변경되었다면 저장여부를 묻고 저장한 후 열기를 수행한다.
 * 
 * 3.저장 파일을 한번이라도 열지 않았다면 무조건 "다른이름"으로 저장을 수행. 파일을 열었었다면 기존 파일에 저장을 수행 (덮어쓰기)
 * 
 * 4.새이름으로 저장 - 파일다이얼로그를 열어서 저장한다.
 * 
 * 5. 프로그램이 종료되면 현재 글꼴상태를 저장한 후 다음 번 실행할 때 해당 글꼴이 적용되어 J.T.A에 보여준다.
 */
@SuppressWarnings("serial")
public class JavaMemoDesign extends JFrame {

	JMenuBar jmb;

	JMenu jm1;

	JMenu jm2;
	JMenu jm3;

	JMenuItem jmi1;
	JMenuItem jmi1_1;
	JMenuItem jmi1_2;
	JMenuItem jmi1_3;
	JMenuItem jmi1_4;

	JMenuItem jmi2;

	JMenuItem jmi3;

	JTextArea area;
	JScrollPane sp;

	public JavaMemoDesign() {
		super("메모장");
//    1. MenuBar 생성
		jmb = new JMenuBar();

//     2. Menu 생성
		jm1 = new JMenu("파일");

		jm2 = new JMenu("서식");
		jm3 = new JMenu("도움말");

//     3. MenuItem 생성

		jmi1 = new JMenuItem("새글");
		jmi1_1 = new JMenuItem("열기");
		jmi1_2 = new JMenuItem("저장");
		jmi1_3 = new JMenuItem("새이름으로 저장");
		jmi1_4 = new JMenuItem("종료");

		jmi2 = new JMenuItem("글꼴");

		jmi3 = new JMenuItem("메모장 정보");

		area = new JTextArea();
		sp = new JScrollPane(area); // 스크롤판 추가
		add(sp); // 화면에 추가

//     4. Menu에 MenuItem 배치
//      메뉴객체명.add(메뉴아이템객체);

		jm1.add(jmi1);
		jm1.addSeparator();
		jm1.add(jmi1_1);

		jm1.add(jmi1_2);
		jm1.add(jmi1_3);

		jm1.addSeparator();

		jm1.add(jmi1_4);
		jm2.add(jmi2);
		jm3.add(jmi3);

//     5. MenuItem을 가진 Menu를 MenuBar 붙임
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);

//     6. JMenuBar를 JFrame에 설정
		setJMenuBar(jmb);

		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JavaMemoEvent jme = new JavaMemoEvent(this);
		addWindowListener(jme);
		jmi1.addActionListener(jme);
		jmi1_1.addActionListener(jme);
		jmi1_2.addActionListener(jme);
		jmi1_3.addActionListener(jme);
		jmi1_4.addActionListener(jme);
		jmi2.addActionListener(jme);
		jmi3.addActionListener(jme);

	}

	public JMenuBar getJmb() {
		return jmb;
	}

	public JMenu getJm1() {
		return jm1;
	}

	public JMenu getJm2() {
		return jm2;
	}

	public JMenu getJm3() {
		return jm3;
	}

	public JMenuItem getJmi1() {
		return jmi1;
	}

	public JMenuItem getJmi1_1() {
		return jmi1_1;
	}

	public JMenuItem getJmi1_2() {
		return jmi1_2;
	}

	public JMenuItem getJmi1_3() {
		return jmi1_2;
	}

	public JMenuItem getJmi1_4() {
		return jmi1_4;
	}

	public JMenuItem getJmi2() {
		return jmi2;
	}

	public JMenuItem getJmi3() {
		return jmi3;
	}

	public JTextArea getArea() {
		return area;
	}

	public JScrollPane getSp() {
		return sp;
	}

}// class