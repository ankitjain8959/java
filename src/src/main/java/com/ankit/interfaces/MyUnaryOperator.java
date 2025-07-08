package com.ankit.interfaces;

@FunctionalInterface
public interface MyUnaryOperator<String> {
  String apply(String s);

}

/*
UnaryOperator:
UnaryOperator is specialization of the Function interface where the input and output types are the same.
UnaryOperator is useful when we want to perform an operation on a single value and return a value of the same type.

BinaryOperator:
Similarly, BinaryOperator is a specialization of the BiFunction interface where both the inputs and the output types are the same.
BinaryOperator is useful when we want to perform an operation on two values of the same type and return a value of the same type.
*/
