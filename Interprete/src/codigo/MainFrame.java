package codigo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFrame extends JFrame{
    private JTextField txtEntrada;
    private JButton BtnAnalizar;
    private JTextArea txtResultado;
    private JPanel Panel;
    private JButton Salir;

    public MainFrame(){
        super("Analizador Lexico");
        setContentPane(Panel);
        BtnAnalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File archivo= new File("archivo.txt");
                PrintWriter escribir;

             try{
                 escribir= new PrintWriter(archivo);
                 escribir.print(txtEntrada.getText());
                 escribir.close();
             }catch (FileNotFoundException ex){
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 Reader lector= new BufferedReader(new FileReader("archivo.txt"));
                 Lexer lexer= new Lexer(lector);
                 String resultado="";
                 while (true){
                     Tokens tokens= lexer.yylex();
                     if (tokens==null){
                         resultado+= "FIN";
                         txtResultado.setText(resultado);
                         return;
                     }
                     switch (tokens){
                         case ERROR:
                             resultado +="Simbolo no definido\n";
                             break;
                         case Identificador: case Numero: case Reservadas:
                             resultado+=lexer.lexema + " :Es un "+ tokens+ "\n";
                             break;
                         default:
                             resultado+="Token: "+tokens+"\n";
                             break;
                     }
                 }
             }catch (FileNotFoundException ex){
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE,null, ex);
             } catch (IOException ex) {
                 throw new RuntimeException(ex);
             }


            }
        });
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }
}
