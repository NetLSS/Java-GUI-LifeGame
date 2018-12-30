import java.awt.*;
import java.awt.event.*;

public class Coffee extends Frame{
    Button coffee,coke;
    Label label,label2;

    Coffee(String title){
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();  // 메모리에서 해제 (현재 프레임만 닫힘)
            }
        });

        setLayout(null);
        setBounds(200,200,400,500);
        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-400/2,screenSize.height/2-500/2); // 화면 정중앙에 표시한다.

        Font f1 = new Font("Serif",Font.BOLD,15);
        Font f2 = new Font("SansSerif",Font.ITALIC,15);
        Font f3 = new Font("Dialog",Font.PLAIN,15);
        Font f4 = new Font("Monospaced",Font.BOLD,15);

        label = new Label("음료를 선택해주세요.");
        label.setBounds(150,50,200,50);
        label.setForeground(new Color(61, 72, 255));
        label.setFont(f2);
        label2 = new Label("배경을 클릭하면 취소할 수 있습니다.^^");
        label2.setFont(f1);
        label2.setBounds(100,100,300,50);
        label2.setForeground(Color.RED);
        coffee = new Button("커피");
        coffee.setFont(f3);
        coffee.setBounds(50,50,50,50);
        coffee.addActionListener(new buttonHandler());
        coffee.setBackground(new Color(255, 150, 35));
        coke = new Button("콜라");
        coke.setBounds(50,150,50,50);
        coke.setBackground(new Color(166, 55, 54));
        coke.addActionListener(new buttonHandler());
        coke.setFont(f4);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setText("음료를 선택해주세요.");
                setBackground(Color.WHITE);
                label.setBackground(Color.WHITE);
                label2.setBackground(Color.WHITE);
            }
        });

        add(coffee);
        add(coke);
        add(label);
        add(label2);
        setVisible(true);
    }

//    public static void main(String[] args){
//       new Coffee("음료");
//    }

    public void paint(Graphics g) {
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("back_coffee.png");// 이미지를 가져온다.
        g.drawImage(img,0,0,400,500,this);
    }

    class buttonHandler implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText(e.getActionCommand()+"가 선택되었습니다.");
            if(e.getActionCommand()=="커피"){
                setBackground(Color.ORANGE);
                label.setBackground(Color.ORANGE);
                label2.setBackground(Color.ORANGE);
            }else{
                setBackground(Color.GREEN);
                label.setBackground(Color.GREEN);
                label2.setBackground(Color.GREEN);
            }
        }
    }
}
