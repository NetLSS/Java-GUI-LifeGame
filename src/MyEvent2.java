import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyEvent2 extends Frame {
    final int WIDTH=500;
    final int HEIGHT=400;
    int fee=0;
    public MyEvent2(String title){
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();  // 메모리에서 해제 (현재 프레임만 닫힘)
            }
        });
        setSize(WIDTH,HEIGHT);
        setLayout(null);

        // 화면 정중앙에 표시 하는 코드
        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-WIDTH/2,screenSize.height/2-HEIGHT/2); // 화면 정중앙에 표시한다.

        Label coffee_select = new Label("커피를 선택하세요.");
        coffee_select.setLocation(20,40);
        coffee_select.setSize(100,20);
        coffee_select.setBackground(new Color(255, 124, 114));

        CheckboxGroup coffee = new CheckboxGroup();
        Checkbox coffee_c1 = new Checkbox("아메리카노(1500원)", coffee, false);
        Checkbox coffee_c2 = new Checkbox("카페라떼(2000원)", coffee, false);
        Checkbox coffee_c3 = new Checkbox("카푸치노(2500원)", coffee, false);

        coffee_c1.setBounds(45,163,150,20);
        coffee_c2.setBounds(208,163,130,20);
        coffee_c3.setBounds(351,163,150,20);

        Label size_select = new Label("사이즈를 선택하세요.");
        size_select.setBounds(20,190,150,20);
        size_select.setBackground(new Color(116, 255, 248));

        CheckboxGroup size = new CheckboxGroup();
        Checkbox small_size = new Checkbox("작은(+100원)", size, false);
        Checkbox mid_size = new Checkbox("중간(+200원)", size, false);
        Checkbox large_size = new Checkbox("큰(+300원)", size, false);
        //45,255
        small_size.setBounds(45,215,100,20);
        mid_size.setBounds(45,235,100,20);
        large_size.setBounds(45,255,100,20);

        //구매 내역 공간 20,286
        Label score = new Label("구매 해주세요");
        score.setBackground(new Color(253, 255, 153));
        score.setBounds(20,286,400,100);

        //구매하기 버튼 241,218
        Button buy_button = new Button("구매하기");
        buy_button.setBounds(241,218,200,50);
        buy_button.setBackground(new Color(71, 255, 139));
        buy_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fee=0;
                score.setText("");
                String setThis="구매하신 커피는 ";
                if(coffee_c1.getState()){
                    fee+=1500;
                    setThis+="[아메리카노] ";
                }
                else if(coffee_c2.getState()){
                    fee+=2000;
                    setThis+="[카페라떼] ";
                }
                else if(coffee_c3.getState()){
                    fee+=2500;
                    setThis+="[카푸치노] ";
                }
                else{
                    score.setText("커피를 선택하세요.");
                    return;
                }

                if(small_size.getState()){
                    fee+=100;
                    setThis+="[작은] 사이즈 ";
                }
                else if(mid_size.getState()){
                    fee+=200;
                    setThis+="[중간] 사이즈 ";
                }
                else if(large_size.getState()){
                    fee+=300;
                    setThis+="[큰] 사이즈 ";
                }
                else{
                    score.setText("사이즈를 선택하세요.");
                    return;
                }
                setThis+="입니다. 총가격은 ["+fee+"] 입니다.";
                score.setText(setThis);
            }
        });



        add(coffee_c1);
        add(coffee_c2);
        add(coffee_c3);
        add(small_size);
        add(mid_size);
        add(large_size);
        add(coffee_select);
        add(size_select);
        add(buy_button);
        add(score);
        setVisible(true);
    }

    public MyEvent2(){
        this("커피 자판기");
    }

    @Override
    public void paint(Graphics g) { // 오버라이딩 함
        Image img;

        img = Toolkit.getDefaultToolkit().getImage("back_shop.png");// 이미지를 가져온다.
        g.drawImage(img,0,0,500,400,this);
        img = Toolkit.getDefaultToolkit().getImage("americano.png");// 이미지를 가져온다.
        g.drawImage(img,28,75,50,80,this);
        img = Toolkit.getDefaultToolkit().getImage("latte.png");// 이미지를 가져온다.
        g.drawImage(img,186,75,60,80,this);
        img = Toolkit.getDefaultToolkit().getImage("capucino.png");// 이미지를 가져온다.
        g.drawImage(img,350,75,60,80,this);
    }

//    public static void main(String[] args){
//        new MyEvent2();
//    }
}
