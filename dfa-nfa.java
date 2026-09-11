import java.util.*;

public class dfa-nfa {

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

    static String setToString(Set<Integer> states) {

        String result = "{";

        int count = 0;

        for (int state : states) {

            if (count > 0)
                result += ",";

            result += "q" + state;

            count++;
        }

        result += "}";

        return result;
    }

    public static void main(String[] args) throws Exception {

        /*
            NFA:

            States: q0, q1, q2
            Alphabet: 0, 1
            Start: q0
            Final: q2

            Transitions:

            q0 --0--> q0
            q0 --0--> q1
            q0 --1--> q2

            q1 --1--> q2

            q2 --0--> q2
            q2 --1--> q2
        */

        // NFA transition table
        Map<Integer, Map<Character, Set<Integer>>> nfa = new HashMap<>();

        // q0 --0--> {q0,q1}
        nfa.computeIfAbsent(0, k -> new HashMap<>())
                .computeIfAbsent('0', k -> new TreeSet<>())
                .add(0);

        nfa.get(0).get('0').add(1);

        // q0 --1--> {q2}
        nfa.get(0)
                .computeIfAbsent('1', k -> new TreeSet<>())
                .add(2);

        // q1 --1--> {q2}
        nfa.computeIfAbsent(1, k -> new HashMap<>())
                .computeIfAbsent('1', k -> new TreeSet<>())
                .add(2);

        // q2 --0--> {q2}
        nfa.computeIfAbsent(2, k -> new HashMap<>())
                .computeIfAbsent('0', k -> new TreeSet<>())
                .add(2);

        // q2 --1--> {q2}
        nfa.get(2)
                .computeIfAbsent('1', k -> new TreeSet<>())
                .add(2);


        // Start state = {q0}
        Set<Integer> start = new TreeSet<>();
        start.add(0);

        // Queue for pending DFA states
        Queue<Set<Integer>> pending = new LinkedList<>();

        // List of DFA states
        List<Set<Integer>> dfaStates = new ArrayList<>();

        // Mapping DFA state set -> DFA state number
        Map<Set<Integer>, Integer> stateNumber = new HashMap<>();

        pending.add(start);
        stateNumber.put(start, 0);
        dfaStates.add(start);


        System.out.println();
        System.out.println("==============================================================");
        System.out.println("              NFA TO DFA CONVERSION");
        System.out.println("                 SUBSET CONSTRUCTION");
        System.out.println("==============================================================");
        System.out.println();

        System.out.println("NFA STATES : q0, q1, q2");
        System.out.println("ALPHABET   : {0, 1}");
        System.out.println("START      : q0");
        System.out.println("FINAL      : q2");
        System.out.println();

        System.out.println("NFA TRANSITIONS");
        System.out.println("--------------------------------------------------------------");
        System.out.println("q0 --0--> {q0,q1}");
        System.out.println("q0 --1--> {q2}");
        System.out.println("q1 --0--> {}");
        System.out.println("q1 --1--> {q2}");
        System.out.println("q2 --0--> {q2}");
        System.out.println("q2 --1--> {q2}");
        System.out.println("--------------------------------------------------------------");

        System.out.println("\n\nPress ENTER to begin conversion...");
        System.in.read();
        System.in.read();


        // Subset construction
        while (!pending.isEmpty()) {

            Set<Integer> current = pending.poll();

            int currentNumber = stateNumber.get(current);

            clearScreen();

            System.out.println();
            System.out.println("==============================================================");
            System.out.println("              NFA TO DFA CONVERSION");
            System.out.println("==============================================================");
            System.out.println();

            System.out.println("Currently processing DFA state:");
            System.out.println();

            System.out.print("D" + currentNumber + " = "
                    + setToString(current));

            boolean currentFinal = false;

            if (current.contains(2))
                currentFinal = true;

            if (currentFinal)
                System.out.print("   <-- FINAL STATE");

            System.out.println("\n");

            System.out.println("Finding transitions...\n");


            // Process symbols 0 and 1
            for (int symbolIndex = 0; symbolIndex < 2; symbolIndex++) {

                char symbol = (char) ('0' + symbolIndex);

                Set<Integer> destination = new TreeSet<>();


                // Find destination states
                for (int nfaState : current) {

                    Map<Character, Set<Integer>> transitions =
                            nfa.get(nfaState);

                    if (transitions != null) {

                        Set<Integer> states =
                                transitions.get(symbol);

                        if (states != null) {
                            destination.addAll(states);
                        }
                    }
                }


                System.out.print("D" + currentNumber
                        + " --" + symbol + "--> ");


                if (destination.isEmpty()) {

                    System.out.println("{}");

                } else {

                    System.out.print(setToString(destination));


                    // Check whether this is a new DFA state
                    if (!stateNumber.containsKey(destination)) {

                        int newNumber = dfaStates.size();

                        stateNumber.put(
                                new TreeSet<>(destination),
                                newNumber
                        );

                        dfaStates.add(
                                new TreeSet<>(destination)
                        );

                        pending.add(
                                new TreeSet<>(destination)
                        );

                        System.out.print(
                                "   [NEW DFA STATE D" + newNumber + "]"
                        );
                    }

                    System.out.println();
                }
            }

            System.out.println("\nPress ENTER for next DFA state...");
            System.in.read();
            System.in.read();
        }


        // Final DFA
        clearScreen();

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                DFA CONVERSION COMPLETE");
        System.out.println("==============================================================");
        System.out.println();

        System.out.println("DFA STATES GENERATED:\n");


        for (int i = 0; i < dfaStates.size(); i++) {

            Set<Integer> state = dfaStates.get(i);

            System.out.print("D" + i + " = "
                    + setToString(state));

            if (state.contains(2))
                System.out.print("   <-- FINAL");

            if (i == 0)
                System.out.print("   <-- START");

            System.out.println();
        }


        System.out.println("\n\n");
        System.out.println("==============================================================");
        System.out.println("                  DFA TRANSITION TABLE");
        System.out.println("==============================================================");
        System.out.println();

        System.out.println("State       0              1");
        System.out.println("--------------------------------------------------------------");


        for (int i = 0; i < dfaStates.size(); i++) {

            Set<Integer> current = dfaStates.get(i);

            Set<Integer> destination0 = new TreeSet<>();
            Set<Integer> destination1 = new TreeSet<>();


            // Find transition for 0 and 1
            for (int nfaState : current) {

                Map<Character, Set<Integer>> transitions =
                        nfa.get(nfaState);

                if (transitions != null) {

                    Set<Integer> states0 =
                            transitions.get('0');

                    Set<Integer> states1 =
                            transitions.get('1');

                    if (states0 != null)
                        destination0.addAll(states0);

                    if (states1 != null)
                        destination1.addAll(states1);
                }
            }


            System.out.print("D" + i + "          ");


            if (destination0.isEmpty())
                System.out.print("{}");
            else
                System.out.print("D"
                        + stateNumber.get(destination0));


            System.out.print("             ");


            if (destination1.isEmpty())
                System.out.print("{}");
            else
                System.out.print("D"
                        + stateNumber.get(destination1));


            System.out.println();
        }


        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                    FINAL DFA");
        System.out.println("==============================================================");
        System.out.println();

        System.out.println("                 0");
        System.out.println("          +---------------+");
        System.out.println("          |               |");
        System.out.println("          v               |");
        System.out.println("       +-------+          |");
        System.out.println("       |       |          |");
        System.out.println(" START |  D0   |----------+");
        System.out.println("       | {q0}  |     1");
        System.out.println("       +-------+");
        System.out.println("           |");
        System.out.println("           | 0");
        System.out.println("           v");
        System.out.println("       +-------+");
        System.out.println("       |       |");
        System.out.println("       |  D1   |----1---->");
        System.out.println("       |{q0,q1}|");
        System.out.println("       +-------+");
        System.out.println("           |");
        System.out.println("           | 1");
        System.out.println("           v");
        System.out.println("       +=========+");
        System.out.println("       ||        ||");
        System.out.println("       ||   D2   ||");
        System.out.println("       ||  {q2}  ||");
        System.out.println("       || FINAL  ||");
        System.out.println("       +==========+");

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("              NFA -> DFA SUCCESSFULLY COMPLETED");
        System.out.println("==============================================================");
    }
}