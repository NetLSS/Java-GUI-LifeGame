//
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;  // 필요한 패키지들을 import
import java.net.MalformedURLException;

//import java.awt.event.*;
public class Bingo extends Frame{ // Frame을 상속 받아 사용
    Button[] buttons=new Button[25]; // 빙고 버튼 25개를 만들어주기위해 할당 (버튼배열)
    boolean[] buttons_state=new boolean[25]; // 버튼 클릭 유무를 체크하기위해 boolean배열 생성
    Color orignalColor,clickedColor; // 원본색, 클릭했을 때 색 변수
    int countClicked; // 몇 개를 클릭했는지 count 하여 5개일 때만 종료되도록함.
    public Bingo(String title){
        super(title);
        setLayout(new GridLayout(5,5)); // 그리드레이아웃 행,열 각각 5개로 5x5행렬 생성
        addWindowListener(new WindowAdapter() { // 닫기버튼 클릭시 해당 창만 닫도록 설정
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();// 닫기버튼 클릭시 해당 창만 닫도록 설정
            }
        });
        for(int i=0;i<buttons.length;++i){ // 버튼 25개에 대하여 세팅진행
            buttons[i]=new Button(String.valueOf(i+1)); // 라벨을 1~25까지 지정
            buttons[i].addActionListener(new ButtonHandler()); // 액션리스너 추가
            add(buttons[i]); // 현재 클래스에 버튼 추가
        }
        countClicked=0; // 클릭 카운트 0으로 초기화 명시
        orignalColor=buttons[0].getBackground(); // 원본 색 가져와서 저장
        clickedColor=new Color(105, 255, 131); // 클릭 색 지정 (초록계열)

        setBounds(500,250,500,500); // 시작위치및 가로세로 크기 지정
        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-500/2,screenSize.height/2-500/2); // 화면 정중앙에 표시한다.


        setVisible(true); // 창 가시화
    }
    public Bingo(){ // 기본 생성자 사용가능하게 지정
        this("Bingo Game!");
    }
    class ButtonHandler implements ActionListener{ // 버튼 핸들러 생성 (ActionListener를 구현함)
        @Override
        public void actionPerformed(ActionEvent e) {
            Button source = (Button)e.getSource(); // 어떤 버튼 눌렀는지 소스객체를 가져옴
            int index=(Integer.parseInt(source.getLabel()))-1; // 해당 버튼의 상태배열에서의 인덱스를 계산하여 저장
            if(buttons_state[index]==false){ // 안눌려 있을때
                buttons_state[index]=true; // 누른상태로 변경하고
                source.setBackground(clickedColor); // 색상을 클릭색으로 변경
                countClicked++; // 카운트 증가
            }else{ // 눌려있을때
                buttons_state[index]=false; // 안누른상태로 변경하고
                source.setBackground(orignalColor); // 원본색으로 지정
                countClicked--; // 카운트 감소
            }
            if(countClicked==5&&((buttons_state[6*0] // 버튼이 5개 눌려있음과 동시에
                    &&buttons_state[6*1]&&buttons_state[6*2] // 왼쪽위에서 오른쪽아래
                    &&buttons_state[6*3]&&buttons_state[6*4])||// 또는
                    (buttons_state[4*1]&&buttons_state[4*2] // 오른쪽 위에서 왼쪽아래일 때만
                    &&buttons_state[4*3]&&buttons_state[4*4]
                    &&buttons_state[4*5]))){
                    dispose(); // 창을 안보이게 한다.
            }
        }
    }




//    public static void main(String[] args){
//        new Bingo(); // 메인 함수에서 테스트할 수 있도록 함.
//    }
}
