import java.awt.Color;


import javax.swing.*;

@SuppressWarnings("serial")
public class TimeLabel extends JLabel implements Runnable{
	
	Thread myThread;
	private int nTime; // 남은 시간
	FindLover game; // 게임 score 얻어올려고..
	
	
	
	
	public TimeLabel(FindLover n) {
		
		myThread = new Thread(this);
		nTime=30; // 30초 제한
		game = n; // GamePanel 객체 얻어오기.
	} //생성자
	
	public void start() { // 스레드 시작하게
		if (myThread != null)
			myThread.start();
	}
	@SuppressWarnings("deprecation")
	public void stop() { // 스레드 중단시키기..
		myThread.stop();
	}
	
	public void interrupt() { // interrupt exception 발생
		myThread.interrupt();
	}
	
	

	@Override
	public void run() { // 스레드 실행
		
		nTime = 30; // 30초 제한
		setForeground(Color.black);
		
        while (nTime >= 0) { // 30초동안 매 초마다 반복되는 코드
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setText("Time :   "+nTime); // 남은 시간 띄우기
                }
            });
            if (nTime<=5) {
            	//5초 이하면 time Label의 색 변경..
            	setForeground(Color.red);
            }
            
            try {
                Thread.sleep(1000); // 1초 대기
                game.setWrong(false); // 틀렸을 때 나오는 명언 이미지 치우기
                game.setLove(true); // 선택 버튼 3가지 다시 띄우기
            	game.setBad(true);
            	game.setBore(true);
            } catch (InterruptedException e) { // 중간에 틀렸을 경우.
            	nTime-=5; // 시간 5초 감소
            	// 틀렸을 때 이벤트를 줘보고 싶다. -> 명언을 잠시 띄우자.
            	game.setWrong(true);
            	game.setLove(false); // 그리고 선택 버튼 3가지 잠시 없애기
            	game.setBad(false);
            	game.setBore(false);
            	
            }

            nTime--; // 1초 감소
        }
        game.showResult(); // 게임 종료 및 결과 화면 띄우기
        
	}

}
