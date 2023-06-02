import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class FindLover extends JPanel{
	private JButton btnLove, btnBad, btnBore, btnBack, btnStart;
	private ImageIcon ic1, ic2, ic3, icLove, icBad, 
						icBore, icSay, icEff1, icEff2, icEff3, 
						icWrong, icTitle, icStory, icTuto, icBack,
						icTitle2, icDongle, icStart;
	private JLabel lblScore, lblSay, lblWrong, title, story, tutorial,lblRes, lblTitle, lblDongle;
	private static JLabel lblPeo;
	private int nScore, nRandom; // nRandom이 정답
	
	
	GameListener gameL; // actionListener
	GameMouseListener gameML; // mouseListener
	TimeLabel lblTime; // Timer thread 
	
	
	
	
	public FindLover() {
		// 기본 패널 설정
		setBackground(Color.white);
		setPreferredSize(new Dimension(630, 420));
		setLayout(null);
		
		//-------------------------------------------------------------------------
		// 초기 시작화면 패널 구성
		icTitle = new ImageIcon("title.png"); // 제목 이미지
		icStory = new ImageIcon("story.png"); // 게임 스토리 
		icTuto = new ImageIcon("tutorial.png"); // 게임 방법
		icStart = new ImageIcon("gamestart.png"); // 게임 시작 버튼
		
		
		title = new JLabel(); // 제목
		title.setIcon(icTitle);
		title.setBounds(155,20,320,54);
		title.setHorizontalAlignment(JLabel.CENTER);
		add(title);
		
		Color theme = new Color(251,209,251); // 연핑크
		
		story = new JLabel();
		story.setIcon(icStory); // 게임 스토리 설명 (왼쪽 박스)
		story.setBounds(13,110,288,214);
		story.setHorizontalAlignment(JLabel.CENTER);
		story.setBorder(BorderFactory.createLineBorder(theme, 1)); // 테두리 설정
		add(story);
		
		tutorial = new JLabel(); // 게임 방법 설명 (오른쪽 박스)
		tutorial.setIcon(icTuto);
		tutorial.setBounds(329,110,288,214);
		tutorial.setHorizontalAlignment(JLabel.CENTER);
		tutorial.setBorder(BorderFactory.createLineBorder(theme, 1)); // 테두리 설정
		add(tutorial);
		
		
		// 게임 시작 버튼
		btnStart = new JButton(icStart);
		btnStart.setBounds(167, 340, 289, 53);
		
		// 버튼 누르면 게임 화면으로 넘어감.
		btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                setGame(); // 화면 재구성
                lblTime.start(); // thread 시작
            }
        });
		
		add(btnStart);
		
		
		
		
		//-------------------------------------------------------------------------
		// 두번째 화면(게임 화면) 구성
		
		// 리스너 객체 생성
		gameL=new GameListener();
		gameML = new GameMouseListener();
		
		// 틀렸을 때 나올 label
		icWrong = new ImageIcon("myeongeon.jpg");
		lblWrong = new JLabel();
		lblWrong.setIcon(icWrong);
		lblWrong.setVisible(false);
		lblWrong.setBounds(115,64,400,292);
		add(lblWrong);
		
		
		// 총 점수 0으로 초기화
		nScore=0;
		
		// 점수 텍스트
		lblScore = new JLabel("SCORE :   "+nScore);
		lblScore.setBounds(155,10,320,54);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		lblScore.setVisible(false);
		add(lblScore);
		
		// 다음 나올 소개팅 상대 정하기
		nRandom = (int)(Math.random()*3)+1; // 현재 정답
		
		
		// 소개팅 상대 이미지 보여주는 레이블 3개 차례대로 배치
		ic1 = new ImageIcon("love.png"); // love
		ic2 = new ImageIcon("bad.png"); // bad
		ic3 = new ImageIcon("bore.png"); // bore
		
		lblPeo = new JLabel(); // 소개팅 상대 띄우기 -> 랜덤값에 따라 이미지 다르게 설정 
		if (nRandom==1) {lblPeo.setIcon(ic1);}
		else if (nRandom==2) {lblPeo.setIcon(ic2);}
		else {lblPeo.setIcon(ic3);}
		lblPeo.setBounds(265,130,95,95);
		lblPeo.setVisible(false);
		add(lblPeo);
		
		
		// 인삿말 사진
		icSay = new ImageIcon("insa.png");
		lblSay = new JLabel();
		lblSay.setIcon(icSay);
		lblSay.setBounds(373,102,220,129);
		lblSay.setVisible(false);
		add(lblSay);
		
		
		// 남은 시간 텍스트
		lblTime = new TimeLabel(this);
		lblTime.setBounds(160, 360, 320, 54);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		lblTime.setForeground(Color.red);
		lblTime.setVisible(false);
		add(lblTime);
		
		// 3개 버튼 이미지 아이콘 정의
		icLove = new ImageIcon("btnlove.png"); // love
		icBad = new ImageIcon("btnbad.png"); // bad
		icBore = new ImageIcon("btnbore.png"); // bore
		
		// 버튼 클릭시 효과 아이콘
		icEff1 = new ImageIcon("effLove.png");
		icEff2 = new ImageIcon("effBad.png");
		icEff3 = new ImageIcon("effBore.png");

		
		// love 버튼
		btnLove = new JButton();
		btnLove.setIcon(icLove);
		btnLove.setBounds(71, 260, 103, 89);
		btnLove.addActionListener(gameL);
		btnLove.addMouseListener(gameML);
		btnLove.setVisible(false);
		add(btnLove);
		
		// bad 버튼
		btnBad = new JButton();
		btnBad.setIcon(icBad);
		btnBad.setBounds(263, 260, 103, 89);
		btnBad.addActionListener(gameL);
		btnBad.addMouseListener(gameML);
		btnBad.setVisible(false);
		add(btnBad);
		
		// bore 버튼
		btnBore = new JButton();
		btnBore.setIcon(icBore);
		btnBore.setBounds(455, 260, 103, 89);
		btnBore.addActionListener(gameL);
		btnBore.addMouseListener(gameML);
		btnBore.setVisible(false);
		add(btnBore);
		
		// 게임 뒤로가기 버튼 (초기 화면으로 돌아감.)
		icBack = new ImageIcon("finish.png");
		btnBack = new JButton(icBack);
		btnBack.setBounds(13,13,50,50);
		btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	initThread(); // thread 다시 추가하기 
            	setStart(); // 화면 재구성
            }
        });
		btnBack.setVisible(false);
        add(btnBack);
		
        //-----------------------------------------------------------------
        // 세 번째 화면(결과 보여주는 화면) 구성 
        
        
        // 결과 표시하기 1. 점수 텍스트
        lblRes = new JLabel(""+nScore); 
		lblRes.setBounds(170, 120, 270, 150);
		lblRes.setFont(new Font("Consolas", Font.PLAIN, 84));
		lblRes.setHorizontalAlignment(SwingConstants.CENTER);
		lblRes.setVisible(false);
		add(lblRes);
		
		// 결과 표시하기 2. 제목
		icTitle2 = new ImageIcon("result_t.png");
		lblTitle = new JLabel();
		lblTitle.setBounds(0, 0, 640, 86);
		lblTitle.setIcon(icTitle2);
		lblTitle.setVisible(false);
		add(lblTitle);
		
		// 결과 표시하기 3. 동글이 그림
		icDongle = new ImageIcon("result_d.png");
		lblDongle = new JLabel();
		lblDongle.setBounds(0, 244, 640, 176);
		lblDongle.setIcon(icDongle);
		lblDongle.setVisible(false);
		add(lblDongle);
		
	} // 생성자
	
	// 게임 초기화 함수
	public void initThread() {
		// thread 다시 생성해주기.
		lblTime.stop();
		lblTime.setVisible(false);
		
		lblTime = new TimeLabel(this);
		lblTime.setBounds(160, 360, 320, 54);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		lblTime.setForeground(Color.red);
		add(lblTime);
		
		nScore=0; // 점수 초기화.
		lblScore.setText("SCORE :   "+nScore); // 다시 텍스트 정리하기..
		
		// 소개팅 상대도 reset
		nRandom = (int)(Math.random()*3)+1; // 현재 정답
		if (nRandom==1) {lblPeo.setIcon(ic1);} // 그에 따른 아이콘 재설정
		else if (nRandom==2) {lblPeo.setIcon(ic2);}
		else {lblPeo.setIcon(ic3);}
		
	}
	
	public int getScore() {return nScore;} // 점수 가져오기 함수
	// 틀릴 때 나오는 label 제어
	public void setWrong(boolean tf) {lblWrong.setVisible(tf);}
	
	// 틀릴 때 버튼 제어
	public void setLove(boolean tf) {btnLove.setVisible(tf);}
	public void setBad(boolean tf) {btnBad.setVisible(tf);}
	public void setBore(boolean tf) {btnBore.setVisible(tf);}
	
	public static void setPeo(boolean tf) {lblPeo.setVisible(tf);}
	
	// 게임화면에서 버튼3개중에 아무거나 누름 => 40ms 동안 소개팅 상대방 사라지게 하기
	private static void hideLabel() {
        setPeo(false);

        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setPeo(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
	
	// 초기 시작 화면 보여주기 (두번째 화면 객체들 다 set visible:false)
	public void setStart() {
		
		// 필요없는 건 가리기
		lblWrong.setVisible(false); // 틀렸을 때 나오는 이미지
		lblScore.setVisible(false); // 점수 텍스트
		lblSay.setVisible(false); // 말풍선 이미지
		lblTime.setVisible(false); // 시간 텍스트
		lblPeo.setVisible(false); // 소개팅 상대방 이미지
		
		btnLove.setVisible(false); // 게임 조작 버튼 3가지
		btnBad.setVisible(false);
		btnBore.setVisible(false);
		btnBack.setVisible(false); // 초기화면으로 나가는 버튼 (게임종료)
		
		// 필요한 것들 보여주기
		title.setVisible(true); // 제목
		story.setVisible(true); // 게임 스토리
		tutorial.setVisible(true); // 게임 방법
		btnStart.setVisible(true); // 게임 시작버튼
		
	}
	
	// 두번째 화면 보여주기 (첫번째 화면 객체들 다 set visible:false)
	public void setGame() {
		
		// 필요없는 건 가리기
		title.setVisible(false);
		story.setVisible(false);
		tutorial.setVisible(false);
		lblWrong.setVisible(false);
		btnStart.setVisible(false);
		
		// 필요한 것들 보여주기
		lblScore.setVisible(true);
		lblSay.setVisible(true);
		lblPeo.setVisible(true);
		lblTime.setVisible(true);
		btnLove.setVisible(true);
		btnBad.setVisible(true);
		btnBore.setVisible(true);
		btnBack.setVisible(true);
		
	}
	
	// 결과 보여주는 화면
	public void showResult() {
		//initThread(); // thread 다시 추가하기
		
		// 모든 객체 setVisible : false
		lblWrong.setVisible(false); // 틀렸을 때 나오는 이미지
		lblScore.setVisible(false); // 점수 텍스트
		lblSay.setVisible(false); // 말풍선 이미지
		lblTime.setVisible(false); // 시간 텍스트
		lblPeo.setVisible(false); // 소개팅 상대방 이미지
		btnLove.setVisible(false); // 게임 조작 버튼 3가지
		btnBad.setVisible(false);
		btnBore.setVisible(false);
		btnBack.setVisible(false); // 초기화면으로 나가는 버튼 (게임종료)
		title.setVisible(false); // 제목
		story.setVisible(false); // 게임 스토리
		tutorial.setVisible(false); // 게임 방법
		btnStart.setVisible(false); // 게임 시작버튼
		
		// 결과에 띄울 것만 true로 
		lblTitle.setVisible(true); // 결과 제목
		lblDongle.setVisible(true); // 동글이 사진
		lblRes.setVisible(true); // 점수 보여주기
		
		// 결과 표시하기 1. 점수
		int score = getScore(); // 현재 점수 가져오기
		lblRes.setText(""+score); // 다시 텍스트 정리하기..
		
		
	}
	
	private class GameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			
			// 버튼 클릭시 사람 라벨 잠깐 깜빡이기 (전환 효과)
			hideLabel();
			
			// 정답을 맞췄다면 => 점수+1
			if ((nRandom==1 && obj==btnLove)||
					(nRandom==2 && obj==btnBad)||
					(nRandom==3 && obj==btnBore)) {
				// score+=1 
				nScore+=1;
				lblScore.setText("SCORE :   "+nScore);
			}
			else { // 틀렸다면
				// thread interrupt -> 시간 5초 뻇기
				lblTime.interrupt();
			}
			
			// 다음사람 새로뽑기
			nRandom = (int)(Math.random()*3)+1; // 현재 정답
			if (nRandom==1) {lblPeo.setIcon(ic1);} // 그에따른 아이콘 재설정
			else if (nRandom==2) {lblPeo.setIcon(ic2);}
			else {lblPeo.setIcon(ic3);}
			
			
			
		}
		
	}
	
	private class GameMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// 버튼 영역 안으로 들어왔을 때 효과주기
			if ((JButton) e.getSource()==btnLove) {btnLove.setIcon(icEff1);}
			else if ((JButton) e.getSource()==btnBad) {btnBad.setIcon(icEff2);}
			else if ((JButton) e.getSource()==btnBore) {btnBore.setIcon(icEff3);}
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// 다시 마우스 바깥으로 간다면 효과 원래대로
			if ((JButton) e.getSource()==btnLove) {btnLove.setIcon(icLove);}
			else if ((JButton) e.getSource()==btnBad) {btnBad.setIcon(icBad);}
			else if ((JButton) e.getSource()==btnBore) {btnBore.setIcon(icBore);}
			
		}
		
	}

}
