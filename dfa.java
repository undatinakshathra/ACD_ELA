import java.io.BufferedReader;
import java.io.InputStreamReader;

public class dfa {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
        } catch (Exception e) {
            // If screen clearing is not supported, continue normally
        }
    }

    static void drawDFA() {
        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                     DFA VISUALIZATION");
        System.out.println("==============================================================");
        System.out.println();

        System.out.println("                         1");
        System.out.println("                    +---------+");
        System.out.println("                    |         |");
        System.out.println("                    |         |");
        System.out.println("                    +---------+");
        System.out.println("                         ^");
        System.out.println("                         |");
        System.out.println("                         |");
        System.out.println("                         |");
        System.out.println("                    +---------+");
        System.out.println("                 0  |         |  1");
        System.out.println("              +---->|    q1   |------+");
        System.out.println("              |     |         |      |");
        System.out.println("              |     +---------+      |");
        System.out.println("              |          |           |");
        System.out.println("              |          | 1         |");
        System.out.println("              |          v           |");
        System.out.println("              |     +===========+    |");
        System.out.println("              +-----|    q2      |<--+");
        System.out.println("                    |   FINAL    |");
        System.out.println("                    +===========+");
        System.out.println();

        System.out.println("                    +---------+");
        System.out.println("              START |    q0   |");
        System.out.println("                    +---------+");
        System.out.println("                         |");
        System.out.println("                         | 0");
        System.out.println("                         v");
        System.out.println("                        q1");

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("START STATE : q0");
        System.out.println("FINAL STATE : q2");
        System.out.println();
        System.out.println("Transitions:");
        System.out.println("q0 --0--> q1");
        System.out.println("q0 --1--> q0");
        System.out.println("q1 --0--> q1");
        System.out.println("q1 --1--> q2");
        System.out.println("q2 --0--> q2");
        System.out.println("q2 --1--> q2");
        System.out.println("==============================================================");
    }

    static String getNextState(String state, char input) {

        if (state.equals("q0")) {
            if (input == '0')
                return "q1";
            else
                return "q0";
        }

        if (state.equals("q1")) {
            if (input == '0')
                return "q1";
            else
                return "q2";
        }

        if (state.equals("q2")) {
            return "q2";
        }

        return "q0";
    }

    public static void main(String[] args) throws Exception {

        String input;
        String state = "q0";

        clearScreen();

        drawDFA();

        System.out.print("\nEnter a binary string: ");
        input = br.readLine();

        // Check whether input contains only 0 and 1
        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) != '0' && input.charAt(i) != '1') {
                System.out.println("\nInvalid input!");
                System.out.println("\nOnly 0 and 1 are allowed.");
                return;
            }
        }

        System.out.println("\nStarting DFA execution...");
        System.out.println("Press ENTER to continue.");

        br.readLine();

        // DFA execution
        for (int i = 0; i < input.length(); i++) {

            String currentState = state;
            String nextState = getNextState(state, input.charAt(i));

            clearScreen();

            drawDFA();

            System.out.println("\n\n==============================================================");
            System.out.println("                    DFA EXECUTION");
            System.out.println("==============================================================");

            System.out.println("\nInput string : " + input);
            System.out.println("Reading      : " + input.charAt(i));
            System.out.println("Current state: " + currentState);

            System.out.println("Transition   : " + currentState
                    + " --" + input.charAt(i) + "--> "
                    + nextState);

            System.out.println("\nPress ENTER for next transition...");

            state = nextState;

            br.readLine();
        }

        clearScreen();

        drawDFA();

        System.out.println("\n\n==============================================================");
        System.out.println("                       RESULT");
        System.out.println("==============================================================");

        System.out.println("\nInput String : " + input);
        System.out.println("Final State  : " + state);

        if (state.equals("q2")) {

            System.out.println("\n\nRESULT : ACCEPTED");
            System.out.println("The DFA reached the final state q2.");

        } else {

            System.out.println("\n\nRESULT : REJECTED");
            System.out.println("The DFA did not reach the final state q2.");
        }

        System.out.println("\n\n==============================================================");
    }
}