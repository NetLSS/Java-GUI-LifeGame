//
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;  // �ʿ��� ��Ű������ import
import java.net.MalformedURLException;

//import java.awt.event.*;
public class Bingo extends Frame{ // Frame�� ��� �޾� ���
    Button[] buttons=new Button[25]; // ���� ��ư 25���� ������ֱ����� �Ҵ� (��ư�迭)
    boolean[] buttons_state=new boolean[25]; // ��ư Ŭ�� ������ üũ�ϱ����� boolean�迭 ����
    Color orignalColor,clickedColor; // ������, Ŭ������ �� �� ����
    int countClicked; // �� ���� Ŭ���ߴ��� count �Ͽ� 5���� ���� ����ǵ�����.
    public Bingo(String title){
        super(title);
        setLayout(new GridLayout(5,5)); // �׸��巹�̾ƿ� ��,�� ���� 5���� 5x5��� ����
        addWindowListener(new WindowAdapter() { // �ݱ��ư Ŭ���� �ش� â�� �ݵ��� ����
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();// �ݱ��ư Ŭ���� �ش� â�� �ݵ��� ����
            }
        });
        for(int i=0;i<buttons.length;++i){ // ��ư 25���� ���Ͽ� ��������
            buttons[i]=new Button(String.valueOf(i+1)); // ���� 1~25���� ����
            buttons[i].addActionListener(new ButtonHandler()); // �׼Ǹ����� �߰�
            add(buttons[i]); // ���� Ŭ������ ��ư �߰�
        }
        countClicked=0; // Ŭ�� ī��Ʈ 0���� �ʱ�ȭ ���
        orignalColor=buttons[0].getBackground(); // ���� �� �����ͼ� ����
        clickedColor=new Color(105, 255, 131); // Ŭ�� �� ���� (�ʷϰ迭)

        setBounds(500,250,500,500); // ������ġ�� ���μ��� ũ�� ����
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��Ŷ�� ����Ͽ�
        Dimension screenSize = tk.getScreenSize(); // ��ũ�� ����� �����´�.
        setLocation(screenSize.width/2-500/2,screenSize.height/2-500/2); // ȭ�� ���߾ӿ� ǥ���Ѵ�.


        setVisible(true); // â ����ȭ
    }
    public Bingo(){ // �⺻ ������ ��밡���ϰ� ����
        this("Bingo Game!");
    }
    class ButtonHandler implements ActionListener{ // ��ư �ڵ鷯 ���� (ActionListener�� ������)
        @Override
        public void actionPerformed(ActionEvent e) {
            Button source = (Button)e.getSource(); // � ��ư �������� �ҽ���ü�� ������
            int index=(Integer.parseInt(source.getLabel()))-1; // �ش� ��ư�� ���¹迭������ �ε����� ����Ͽ� ����
            if(buttons_state[index]==false){ // �ȴ��� ������
                buttons_state[index]=true; // �������·� �����ϰ�
                source.setBackground(clickedColor); // ������ Ŭ�������� ����
                countClicked++; // ī��Ʈ ����
            }else{ // ����������
                buttons_state[index]=false; // �ȴ������·� �����ϰ�
                source.setBackground(orignalColor); // ���������� ����
                countClicked--; // ī��Ʈ ����
            }
            if(countClicked==5&&((buttons_state[6*0] // ��ư�� 5�� ���������� ���ÿ�
                    &&buttons_state[6*1]&&buttons_state[6*2] // ���������� �����ʾƷ�
                    &&buttons_state[6*3]&&buttons_state[6*4])||// �Ǵ�
                    (buttons_state[4*1]&&buttons_state[4*2] // ������ ������ ���ʾƷ��� ����
                    &&buttons_state[4*3]&&buttons_state[4*4]
                    &&buttons_state[4*5]))){
                    dispose(); // â�� �Ⱥ��̰� �Ѵ�.
            }
        }
    }




//    public static void main(String[] args){
//        new Bingo(); // ���� �Լ����� �׽�Ʈ�� �� �ֵ��� ��.
//    }
}
