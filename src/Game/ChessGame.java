package Game;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGame {
    public static LinkedList<Piece> ps=new LinkedList<>();
    public static Piece selectedPiece=null; //comment for trying push for git

    public static void main(String[] args) throws IOException {

        BufferedImage all=ImageIO.read(new File("/Users/rajpc/Downloads/Chess_Game/chess.png"));
        Image imgs[]=new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        Piece b_rook=new Piece(0, 0, false, "rook", ps);
        Piece b_knight=new Piece(1, 0, false, "knight", ps);
        Piece b_bishop=new Piece(2, 0, false, "bishop", ps);
        Piece b_queen=new Piece(3, 0, false, "queen", ps);
        Piece b_king=new Piece(4, 0, false, "king", ps);
        Piece b_bishop2=new Piece(5, 0, false, "bishop", ps);
        Piece b_knight2=new Piece(6, 0, false, "knight", ps);
        Piece b_rook2=new Piece(7, 0, false, "rook", ps);
        Piece b_pawn1=new Piece(1, 1, false, "pawn", ps);
        Piece b_pawn2=new Piece(2, 1, false, "pawn", ps);
        Piece b_pawn3=new Piece(3, 1, false, "pawn", ps);
        Piece b_pawn4=new Piece(4, 1, false, "pawn", ps);
        Piece b_pawn5=new Piece(5, 1, false, "pawn", ps);
        Piece b_pawn6=new Piece(6, 1, false, "pawn", ps);
        Piece b_pawn7=new Piece(7, 1, false, "pawn", ps);
        Piece b_pawn8=new Piece(0, 1, false, "pawn", ps);

        Piece w_rook=new Piece(0, 7, true, "rook", ps);
        Piece w_knight=new Piece(1, 7, true, "knight", ps);
        Piece w_bishop=new Piece(2, 7, true, "bishop", ps);
        Piece w_queen=new Piece(3, 7, true, "queen", ps);
        Piece w_king=new Piece(4, 7, true, "king", ps);
        Piece w_bishop2=new Piece(5, 7, true, "bishop", ps);
        Piece w_knight2=new Piece(6, 7, true, "knight", ps);
        Piece w_rook2=new Piece(7, 7, true, "rook", ps);
        Piece w_pawn1=new Piece(1, 6, true, "pawn", ps);
        Piece w_pawn2=new Piece(2, 6, true, "pawn", ps);
        Piece w_pawn3=new Piece(3, 6, true, "pawn", ps);
        Piece w_pawn4=new Piece(4, 6, true, "pawn", ps);
        Piece w_pawn5=new Piece(5, 6, true, "pawn", ps);
        Piece w_pawn6=new Piece(6, 6, true, "pawn", ps);
        Piece w_pawn7=new Piece(7, 6, true, "pawn", ps);
        Piece w_pawn8=new Piece(0, 6, true, "pawn", ps);

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel pn=new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean white=true;
                for(int y= 0;y<8;y++){
                    for(int x= 0;x<8;x++){
                        if(white){
                            g.setColor(new Color(235,200, 20));
                        }else{
                            g.setColor(new Color(100, 100, 55));

                        }
                        g.fillRect(x*64, y*64, 64, 64);
                        white=!white;
                    }
                    white=!white;
                }
                for(Piece p: ps){
                    int ind=0;
                    if(p.name.equalsIgnoreCase("king")){
                        ind=0;
                    }
                    if(p.name.equalsIgnoreCase("queen")){
                        ind=1;
                    }
                    if(p.name.equalsIgnoreCase("bishop")){
                        ind=2;
                    }
                    if(p.name.equalsIgnoreCase("knight")){
                        ind=3;
                    }
                    if(p.name.equalsIgnoreCase("rook")){
                        ind=4;
                    }
                    if(p.name.equalsIgnoreCase("pawn")){
                        ind=5;
                    }
                    if(!p.isWhite){
                        ind+=6;
                    }
                    g.drawImage(imgs[ind], p.xp*64, p.yp*64, this);
                }
            }

        };
        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece!=null){
                    selectedPiece.x=e.getX()-32;
                    selectedPiece.y=e.getY()-32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // System.out.println((getPiece(e.getX(), e.getY()).isWhite?"white ":"balck ")+getPiece(e.getX(), e.getY()).name);
                selectedPiece=getPiece(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX()/64, e.getY()/64);
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }

    public static Piece getPiece(int x,int y){
        int xp=x/64;
        int yp=y/64;
        for(Piece p: ps){
            if(p.xp==xp&&p.yp==yp){
                return p;
            }
        }
        return null;
    }
}