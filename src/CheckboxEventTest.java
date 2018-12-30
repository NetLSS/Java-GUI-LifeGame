import java.awt.*;
import java.awt.event.*;
public class CheckboxEventTest extends Frame{
    Label q1,q2,score;
    Checkbox q1cb1,q1cb2,q1cb3,q1cb4,
            q2cb1,q2cb2,q2cb3,q2cb4;
    CheckboxGroup group;
    Button end;

    CheckboxEventTest(String title){
        super(title);
        setSize(500,300);
        setLayout(new GridLayout(13,1));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        q1 = new Label("1. 다음 중 ActionEvent의 actionPerformed매서드가 호출되는 경우는? (모두 고르세요.)");
        q1cb1 = new Checkbox("Button 눌렀을 때");
        q1cb2 = new Checkbox("TextField에서 Enter키를 눌렀을 때");
        q1cb3 = new Checkbox("MenuItem을 클릭했을 때");
        q1cb4 = new Checkbox("List에서 더블클릭으로 item을 선택했을 때");

        q2 = new Label("2. Frame의 기본 LayoutManager는? (하나만 고르세요.)");
        group = new CheckboxGroup();
        q2cb1 = new Checkbox("FlowLayout", group, false);
        q2cb2 = new Checkbox("GridLayout", group, false);
        q2cb3 = new Checkbox("BorderLayout", group, false);
        q2cb4 = new Checkbox("CardLayout", group, false);

        score = new Label("* 결과 :");
        end = new Button("이 버튼을 누르시면 결과를 알 수 있습니다.");
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float totalScore = 0;
                if(q1cb1.getState()) totalScore += 12.5;
                if(q1cb2.getState()) totalScore += 12.5;
                if(q1cb3.getState()) totalScore += 12.5;
                if(q1cb4.getState()) totalScore += 12.5;
                if(q2cb3.getState()) totalScore += 50;
                score.setText("* 결과 : 당신의 점수는 "+totalScore+"점 입니다.");
            }
        });

        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-500/2,screenSize.height/2-300/2); // 화면 정중앙에 표시한다.

        add(q1);
        add(q1cb1);
        add(q1cb2);
        add(q1cb3);
        add(q1cb4);
        add(new Label(""));
        add(q2);
        add(q2cb1);
        add(q2cb2);
        add(q2cb3);
        add(q2cb4);
        add(end);
        add(score);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) { // 오버라이딩 함
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("back_quiz.png");// 이미지를 가져온다.
        g.drawImage(img,0,0,500,300,this);
    }
//    public static void main(String[] args){
//        new CheckboxEventTest("퀴즈");
//    }
}
