package day0131_HomeWork;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

public class MemoFontEvent extends WindowAdapter implements ActionListener, MouseListener {

	private MemoFontDesign mfd;


	private String font = "고딕체";
	private int style = Font.PLAIN;
	private int size = 12;

	public MemoFontEvent(MemoFontDesign mfd) {
		this.mfd = mfd;

		this.mfd.getJlPreview().setFont(new Font(this.font, this.style, this.size));

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent me) {

		if (me.getSource() == mfd.getJlFont()) {

			int index = mfd.getJlFont().getSelectedIndex();
			if (index != -1) {

				String temp = mfd.getDlmFont().getElementAt(index);

				mfd.getJtfFont().setText(temp);
				this.font = temp;

				Font setFont = new Font(this.font, this.style, this.size);
				mfd.getJlPreview().setFont(setFont);

			}

			// System.out.println("폰트 클릭되었음");

		}
		if (me.getSource() == mfd.getJlStyle()) {

			int index = mfd.getJlStyle().getSelectedIndex();
			if (index != -1) {

				String temp = mfd.getDlmStyle().getElementAt(index);

				switch (temp) {
				case "일반":
					this.style = Font.PLAIN;
					break;
				case "굵게":
					this.style = Font.BOLD;
					break;
				case "기울임꼴":
					this.style = Font.ITALIC;
					break;
				case "굵은 기울임꼴":
					this.style = Font.BOLD | Font.ITALIC;
					break;

				}

				mfd.getJtfStyle().setText(temp);

				mfd.getJlPreview().setFont(new Font(this.font, this.style, this.size));

			}

			// System.out.println("스타일 클릭되었음");

		}
		if (me.getSource() == mfd.getJlSize()) {

			int index = mfd.getJlSize().getSelectedIndex();
			if (index != -1) {

				String temp = mfd.getDlmSize().getElementAt(index);

				mfd.getJtfSize().setText(temp);

				this.size = Integer.parseInt(temp);

				mfd.getJlPreview().setFont(new Font(this.font, this.style, this.size));

			}

			// System.out.println("사이즈 클릭되었음");

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == mfd.getJbtnCheck()) {
			// System.out.println("확인버튼 클릭");

			mfd.getJmd().getArea().setFont(new Font(this.font, this.style, this.size));
			mfd.dispose();

		}
		if (ae.getSource() == mfd.getJbCancel()) {
			// System.out.println("취소버튼 클릭");
			mfd.dispose();

		}

	}

}