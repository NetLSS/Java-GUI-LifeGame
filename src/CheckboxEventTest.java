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

        q1 = new Label("1. ���� �� ActionEvent�� actionPerformed�ż��尡 ȣ��Ǵ� ����? (��� ������.)");
        q1cb1 = new Checkbox("Button ������ ��");
        q1cb2 = new Checkbox("TextField���� EnterŰ�� ������ ��");
        q1cb3 = new Checkbox("MenuItem�� Ŭ������ ��");
        q1cb4 = new Checkbox("List���� ����Ŭ������ item�� �������� ��");

        q2 = new Label("2. Frame�� �⺻ LayoutManager��? (�ϳ��� ������.)");
        group = new CheckboxGroup();
        q2cb1 = new Checkbox("FlowLayout", group, false);
        q2cb2 = new Checkbox("GridLayout", group, false);
        q2cb3 = new Checkbox("BorderLayout", group, false);
        q2cb4 = new Checkbox("CardLayout", group, false);

        score = new Label("* ��� :");
        end = new Button("�� ��ư�� �����ø� ����� �� �� �ֽ��ϴ�.");
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float totalScore = 0;
                if(q1cb1.getState()) totalScore += 12.5;
                if(q1cb2.getState()) totalScore += 12.5;
                if(q1cb3.getState()) totalScore += 12.5;
                if(q1cb4.getState()) totalScore += 12.5;
                if(q2cb3.getState()) totalScore += 50;
                score.setText("* ��� : ����� ������ "+totalScore+"�� �Դϴ�.");
            }
        });

        Toolkit tk = Toolkit.getDefaultToolkit(); // ��Ŷ�� ����Ͽ�
        Dimension screenSize = tk.getScreenSize(); // ��ũ�� ����� �����´�.
        setLocation(screenSize.width/2-500/2,screenSize.height/2-300/2); // ȭ�� ���߾ӿ� ǥ���Ѵ�.

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
    public void paint(Graphics g) { // �������̵� ��
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("back_quiz.png");// �̹����� �����´�.
        g.drawImage(img,0,0,500,300,this);
    }
//    public static void main(String[] args){
//        new CheckboxEventTest("����");
//    }
}
