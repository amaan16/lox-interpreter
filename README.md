# Lox Interpreter

Small Java implementation of the Lox language from _Crafting Interpreters_.

## Run

From the project root:

```bash
cd /Users/amaan/Desktop/src
mkdir -p out
javac -d out $(find main/java -name '*.java')
```

Start the REPL:

```bash
java -cp out com.craftinginterpreters.lox.Lox
```

Run a script:

```bash
java -cp out com.craftinginterpreters.lox.Lox program.lox
```

`program.lox` can live in the project root or any other location if you pass the correct path.

```bash
java -cp out com.craftinginterpreters.lox.Lox path/to/program.lox
```
