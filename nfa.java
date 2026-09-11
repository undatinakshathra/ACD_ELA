import java.util.Scanner;

public class nfa {

    static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
        } catch (Exception e) {
            // Continue if screen clearing is not supported
        }
    }

    static void drawNFA() {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                     NFA VISUALIZATION");
        System.out.println("==============================================================");
        System.out.println();

        System.out.println("                 0                    1");
        System.out.println("        +----------------+    +----------------+");
        System.out.println("        |                |    |                |");
        System.out.println("        v                |    v                |");
        System.out.println("   +---------+           |  +=========+        |");
        System.out.println("   |         |           |  ||        ||       |");
        System.out.println("   |   q0    |-----------+->||   q1   ||-------+");
        System.out.println("   |         |     0        ||        ||   0,1 |");
        System.out.println("   +---------+              +=========+        |");
        System.out.println("        |                         |             |");
        System.out.println("        |                         | 1           |");
        System.out.println("        | 1                       v             |");
        System.out.println("        |                   +=========+        |");
        System.out.println("        +------------------>|   q2    |<-------+");
        System.out.println("                            |  FINAL  |");
        System.out.println("                            +=========+");
        System.out.println();

        System.out.println("                    ^");
        System.out.println("                    |");
        System.out.println("                  START");

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("START STATE : q0");
        System.out.println("FINAL STATE : q2");
        System.out.println("TYPE        : NON-DETERMINISTIC FINITE AUTOMATON");
        System.out.println("==============================================================");

        System.out.println();
        System.out.println("Transitions:");
        System.out.println("q0 --0--> q0");
        System.out.println("q0 --0--> q1");
        System.out.println("q0 --1--> q2");
        System.out.println("q1 --1--> q2");
        System.out.println("q2 --0--> q2");
        System.out.println("q2 --1--> q2");

        System.out.println();
        System.out.println("==============================================================");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input;

        clearScreen();
        drawNFA();

        System.out.print("\nEnter a binary string: ");
        input = sc.next();

        // Check whether input contains only 0 and 1
        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) != '0' && input.charAt(i) != '1') {

                System.out.println("\nInvalid input!");
                System.out.println("\nOnly 0 and 1 are allowed.");

                sc.close();
                return;
            }
        }

        System.out.println("\nNFA transition table:\n");

        System.out.println("State       0             1");
        System.out.println("--------------------------------");
        System.out.println("q0          {q0,q1}       {q2}");
        System.out.println("q1          {}             {q2}");
        System.out.println("q2          {q2}           {q2}");

        System.out.println("\n\nInput String : " + input);

        System.out.println(
                "\nNFA can have MULTIPLE possible states after reading a symbol."
        );

        System.out.println(
                "\nTherefore, unlike a DFA, one state can have multiple transitions"
        );

        System.out.println(
                "\nfor the same input symbol."
        );

        System.out.println("\n\n==============================================================");
        System.out.println("                   NFA VISUALIZATION END");
        System.out.println("==============================================================");

        sc.close();
    }
}