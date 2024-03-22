![logo-text.png](assets/logo-text.png)

                                        ![VHDL GENERATOR LOGO](assets/logo.png)

# Badges

---

# Overview

---

**VHDL_GENERATOR** is a java swing application that can generate **VHDL Code** it can generate the VHDL file given information of the VHDL interface(entity,port,signals, ...)  and  a **Testbench template file** also it can generate **ready to use VHDL Component** like(Mux,Demux,Adder,...). 

# Demos

---

- **VHDL Code generator**


https://github.com/Var7600/VHDL-GENERATOR/assets/77991946/c287d853-e0ed-462e-9077-085351e61097


- **VHDL Component Generator**
  


https://github.com/Var7600/VHDL-GENERATOR/assets/77991946/249969ff-c6e0-447e-b78d-c0eb2d5daf6a


# Requirements

---

- **Java jdk(>=18)**

- **Maven**

# Maven Plugins

---

- `javadoc-plugin` : for generating the javadoc

- ` JUnit 5` : for unit testing

- `flatlaf`: for the UI themes(Looks And Feels)

- `checkstyle` : to enforce coding rules

- `formatter` : for auto formatting code

- `assembly` : for generating the Jar file with  all dependencies
  
  # Building&Running
  
  ---
  
  - you need to clone the repository first
    
    ```bash
    git clone https://github.com/Var7600/VHDL-GENERATOR
    ```
  
  - go to the VHDL-GENERATOR directory run
    
    1. Building 
    
    ```bash
        mvn install
    ```
    
    2. Running
    
    ```bash
       java -jar target\VHDL_GENERATOR-0.0-SNAPSHOT-jar-with-dependencies.jar
    ```

# Documentation

---

- **Javadoc** : you can find the javadoc documentation of the project in `Apidocs\Javadoc`
- **UML** : there's UML representation of the project in pdf file(you got to zoom in to see clearly)

# TO DO(Upcoming)

---

- [ ] add a option to directly generate testbench file by copying VHDL code to the editor or by choosing a VHDL file

- [ ] add more Component in Library(**Multiplier, Divider, Shifter, Floating Point Adder, T Flip-Flop, ROM, ALU** . . . )
  
  # Contributions
  
  ---
  
  **If you want to contribute to a project and make it better, your help is very welcome. you can report bug reports/issues, feature requests and the noblest of all contributions: a good, clean pull request.**
  
  ### adding component Library
  
  1. first you can create folder in `HDL-library\ (ex: Multiplier)`
  
  2. write your VHDL  code there
  
  3. also a **Testbench!**
  
  4. then you can add a package in `vhdlgenerator.component.Multiplier`
  
  5. and follow same construction in others `component class`
  
  ### How to make a clean pull request
  
  - Create a personal fork of the project on Github.
  - Clone the fork on your local machine. Your remote repo on Github is called `origin`.
  - Add the original repository as a remote called `upstream`.
  - Create a new branch to work on! from `main`.
  - Implement/fix your feature, comment your code.
  - Follow the code style of the project, including indentation(some plugins(checkstyle,foramtter) by maven take care of that so you can write like you want and fix after)
  - If the project has tests run them!
  - Write or adapt tests as needed.
  - Add or change the documentation as needed.
  - Push your branch to your fork on Github, the remote `origin`.
  - From your fork open a pull request in the correct branch.
  - Once the pull request is approved and merged you can pull the changes from `upstream` to your local repo and delete your extra branch(es).
  
  # License
  
  > “Commons Clause” License Condition v1.0
  > 
  > this is a free software; you can redistribute it and/or modify it.
  > 
  > Without limiting other than the License not include or grant  you right to Sell the Software.
  > 
  > For purposes of the foregoing, “Sell” means practicing any or all of the rights granted to you under the License to provide to third parties, 
  > for a fee or other consideration (including without limitation fees for hosting or consulting/ support services related to the Software), 
  > a product or service whose value derives, entirely or substantially, from the functionality of the Software. 
  > Any license notice or attribution required by the License must also include this Commons Cause License Condition notice.
