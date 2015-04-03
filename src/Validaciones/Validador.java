package Validaciones;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ariellugo92
 */
public class Validador {

    public void soloLetras(JTextField a, final JLabel s) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                    s.setVisible(true);
                } else {
                    s.setVisible(false);
                }
            }
        });
    }

    public void soloNumeros(JTextField a, final JLabel s) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                    s.setVisible(true);
                } else {
                    s.setVisible(false);
                }
            }
        });
    }

    public boolean validarTel(JTextField a) {
        String tel = a.getText();
        char[] numeros = tel.toCharArray();

        if (numeros[0] != '2' || numeros.length != 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarCel(JTextField a) {
        String cel = a.getText();
        char[] numeros = cel.toCharArray();

        if (numeros[0] != '8' || numeros.length != 8) {
            return true;
        } else {
            return false;
        }
    }

    public void primerLetra(final JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String texto = a.getText();
                if (texto.length() > 0) {
                    String resultado = Character.toUpperCase(texto.charAt(0)) + texto.substring(1);
                }
            }
        });
    }
}
