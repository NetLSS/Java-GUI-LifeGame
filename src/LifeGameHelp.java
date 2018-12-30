import java.awt.*;
import java.awt.event.*;
public class LifeGameHelp extends Frame {
    public void paint(Graphics g) {
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("LifeGameHelp.png");// 이미지를 가져온다.
        g.drawImage(img,0,0,1050,600,this);
    }
    public LifeGameHelp(){
        super("라이프게임 도움말");
        // 화면 정중앙에 표시 하는 코드
        Toolkit tk = Toolkit.getDefaultToolkit(); // 툴킷을 사용하여
        Dimension screenSize = tk.getScreenSize(); // 스크린 사이즈를 가져온다.
        setLocation(screenSize.width/2-1050/2,screenSize.height/2-600/2); // 화면 정중앙에 표시한다.
        setSize(1050,600);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
//
//    public static void main(String[] args){
//        new LifeGameHelp();
//    }
}
