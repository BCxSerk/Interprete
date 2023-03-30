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
                         case OR: case AND: case XOR:
                             resultado+=lexer.lexema + " :Es la compuerta logica " +tokens+"\n";
                             break;
                         case Suma: case Resta: case Division: case Multiplicacion:
                         case Igual: case Mayor: case Menor: case Modular: case MayorIgual:
                         case MenorIgual: case ExactamenteIgual: case Negacion: case Diferente:
                         case Decremento: case Incremento:
                             resultado+= lexer.lexema +" :Es la operación " +tokens+"\n";
                             break;
                         case Signos:
                             resultado+= lexer.lexema +": Es un signo\n";
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
