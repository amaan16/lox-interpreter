package com.craftinginterpreters.lox;

import java.util.List;
import java.util.Map;

class LoxClass extends LoxInstance implements LoxCallable {
  final String name;
  private final Map<String, LoxFunction> instanceMethods;
  private final Map<String, LoxFunction> staticMethods;

  LoxClass(String name, Map<String, LoxFunction> instanceMethods,
      Map<String, LoxFunction> staticMethods) {
    super(null);
    this.name = name;
    this.instanceMethods = instanceMethods;
    this.staticMethods = staticMethods;
    this.klass = this;
  }

  LoxFunction findMethod(String name) {
    if (instanceMethods.containsKey(name)) {
      return instanceMethods.get(name);
    }
    return null;
  }

  // Lookup for static methods (used when the class object is accessed as a
  // value).
  LoxFunction findStaticMethod(String name) {
    if (staticMethods.containsKey(name)) {
      return staticMethods.get(name);
    }
    return null;
  }

  @Override
  public Object get(Interpreter interpreter, Token name) {
    if (staticMethods.containsKey(name.lexeme)) {
      return staticMethods.get(name.lexeme);
    }
    if (fields.containsKey(name.lexeme)) {
      return fields.get(name.lexeme);
    }
    throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public Object call(Interpreter interpreter,
      List<Object> arguments) {
    LoxInstance instance = new LoxInstance(this);
    LoxFunction initializer = findMethod("init");
    if (initializer != null) {
      initializer.bind(instance).call(interpreter, arguments);
    }

    return instance;
  }

  @Override
  public int arity() {
    LoxFunction initializer = findMethod("init");
    if (initializer == null)
      return 0;
    return initializer.arity();
  }
}
