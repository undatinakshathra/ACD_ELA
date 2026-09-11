[# ACD_ELA
# 🎥 WATCH CODE EXPLANATION VIDEO

> 🚀 **Click below to watch the complete explanation of DFA, NFA & NFA → DFA Conversion:**
>
> 👉 **[▶️ CODE EXPLANATION VIDEO](https://drive.google.com/file/d/10SBpxJ9x16sg1ts1F1w_07hBVhF5ORHF/view?usp=sharing)**

---

# 📘 Finite Automata Implementation (DFA, NFA & NFA → DFA)

## 📌 Overview

This repository contains C++ implementations of three core concepts from **Theory of Computation**:

- Deterministic Finite Automaton (**DFA**)  
- Non-Deterministic Finite Automaton (**NFA**)  
- Conversion of **NFA to DFA** using the **Subset Construction Algorithm**  

Each program is designed to clearly demonstrate how automata process input strings with **step-by-step console-based visualization**.

---

## 📂 Files Included

- `dfa.java` → DFA implementation with detailed execution and final result  
- `nfa.java` → NFA visualization with multiple transitions representation  
- `dfa-nfa.java` → Conversion of NFA to DFA using sets, maps, and queue (subset construction)  

---

## 🚀 How to Run

### Step 1: Compile

```bash
g++ dfa.cpp -o dfa
g++ nfa.cpp -o nfa
g++ nfa-dfa.cpp -o convert
```

### Step 2: Execute

```bash
./dfa
./nfa
./convert
```

---

## 🧠 Concepts Covered

### 🔹 DFA (Deterministic Finite Automaton)
- Exactly one transition per input symbol  
- Follows a single execution path  
- Accepts a string if it reaches the final state  

---

### 🔹 NFA (Non-Deterministic Finite Automaton)
- Multiple transitions possible for the same input  
- Can explore multiple paths simultaneously  
- Accepts if any path reaches the final state  

---

### 🔹 NFA → DFA Conversion
- Uses **Subset Construction Algorithm**  
- Each DFA state represents a **set of NFA states**  
- Eliminates non-determinism for practical implementation  

---

## 🎯 Learning Outcome

- Clear understanding of DFA vs NFA  
- Visualization of automata execution step-by-step  
- Practical implementation of subset construction  
- Strong foundation in Theory of Computation concepts  

---

## 👨‍💻 Author

**Nakshathra**  
B.Tech Data Science Student  

---

## ⭐ Note

This project is useful for:

- Lab assignments  
- Viva explanations  
- Exam preparation  
- Conceptual clarity in automata theory  ]
