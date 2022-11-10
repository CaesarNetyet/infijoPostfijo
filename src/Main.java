import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa tu expresion infija");
        String infija = scanner.nextLine();
        String postfijo = infijoaPostfijo(infija);
        System.out.println("Expresion postfija: " + postfijo);
    }


    public static String infijoaPostfijo(String infijo) {
        Stack<Character> pila = new Stack<>();
        String postfijo = "";
        for (int i = 0; i < infijo.length(); i++) {
            char c = infijo.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                postfijo += c;
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    postfijo += pila.pop();
                }
                pila.pop();
            } else {
                while (!pila.isEmpty() && prioridad(c) <= prioridad(pila.peek())) {
                    postfijo += pila.pop();
                }
                pila.push(c);
            }
        }
        while (!pila.isEmpty()) {
            postfijo += pila.pop();
        }
        return  postfijo;
    }

    private static int prioridad(char c) {
        if (c == '^') {
            return 3;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return -1;
        }
    }
}
