import javax.swing.*;


public class GameMain {

	public static void main(String[] args) { 
		

		// 프레임 설정
		JFrame frame = new JFrame("Who is Dongle's Lover?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		// 게임 화면 패널 띄우기
		FindLover findLover = new FindLover();
		
		// 프레임에 게임 화면 패널 붙이기
		frame.getContentPane().add(findLover);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // 컴퓨터 화면 중앙에 뜨도록 설정 
		
	}
}    