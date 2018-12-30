import java.awt.*;
import java.awt.event.*;
public class CheckboxEventTest2 extends Frame{
    CheckboxGroup group;
    Checkbox cb1,cb2,cb3;

    CheckboxEventTest2(String title){
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setBackground(Color.WHITE);
                cb1.setBackground(Color.WHITE);
                cb2.setBackground(Color.WHITE);
                cb3.setBackground(Color.WHITE);
                cb1.setState(false);
                cb2.setState(false);
                cb3.setState(false);
            }
        });
        group = new CheckboxGroup();
        cb1 = new Checkbox("red", group,true);
        cb2 = new Checkbox("green", group,false);
        cb3 = new Checkbox("blue", group,false);

        cb1.addItemListener(new EventHandler());
        cb2.addItemListener(new EventHandler());
        cb3.addItemListener(new EventHandler());

        setLayout(new FlowLayout());
        add(cb1);
        add(cb2);
        add(cb3);
        setBackground(Color.red);

        setSize(300,200);
        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-300/2,screenSize.height/2-200/2); // 화면 정중앙에 표시한다.
//        setLocation(500,300);
        setVisible(true);
    }

    //내부 클래스 쓰는 이유는 내부 멤버에 접근하기 위함이다.
    private class EventHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Checkbox cb = (Checkbox)e.getSource();  // 이벤트를 발생시킨 소스(버튼) 가져오기
            String color = cb.getLabel();
            if(color.equals("red")){
                Color c = Color.red;
                setBackground(c);
                cb1.setBackground(c);
                cb2.setBackground(c);
                cb3.setBackground(c);
            }else if(color.equals("green")){
                Color c = new Color(80, 255, 80);
                setBackground(c);
                cb1.setBackground(c);
                cb2.setBackground(c);
                cb3.setBackground(c);
            }else{
                Color c = new Color(80, 80, 255);
                setBackground(c);
                cb1.setBackground(c);
                cb2.setBackground(c);
                cb3.setBackground(c);
            }
        }
    }
//    public static void main(String[] args){
//        new CheckboxEventTest2("색 바꾸기");
//    }
}
