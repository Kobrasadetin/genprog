/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.Operations;

/**
 *
 * @author mkarjanm
 */
public class BasicOperation implements OpNode {

    public enum Opcode {
        noop(0),
        mul(1),
        add(2),
        sub(3),
        div(4),
        push(5),
        pop(6),
        comp(7),
        jzer(8),
        jneg(9),
        jpos(10),
        jump(11),
        set(12),;

        private final int operation;

        Opcode(int operation) {
            this.operation = operation;
        }
    }

    public enum JumpCondition {
        none, pos, neg, zero;
    }

    // koska Javassa ei ole delegaatteja, tämä rakenne vaikutti järkevimmältä
    private static interface ArgumentOperation {

        public boolean call(Stack stack, Registry registers, int r1, int r2);
    }

    private static class Noop implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            return true;
        }
    }

    private static class Multiply implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double result = reg.get(r1) * reg.get(r2);
            reg.set(r1, result);
            return true;
        }
    }

    private static class Add implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double result = reg.get(r1) + reg.get(r2);
            reg.set(r1, result);
            return true;
        }
    }

    private static class Divide implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double result = reg.get(r1) / reg.get(r2);
            reg.set(r1, result);
            return true;
        }
    }

    private static class Substract implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double result = reg.get(r1) - reg.get(r2);
            reg.set(r1, result);
            return true;
        }
    }

    private static class Push implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            return stack.push(reg.get(r1));
        }
    }

    private static class Pop implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            return stack.pop(r1, reg);
        }
    }

    private static class Compare implements ArgumentOperation {

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            double v1 = reg.get(r1);
            double v2 = reg.get(r2);
            if (v1 > v2) {
                reg.set(r1, 1.0);
                return true;
            }
            if (v1 < v2) {
                reg.set(r1, -1.0);
                return true;
            }
            reg.set(r1, 0.0);
            return true;
        }
    }

    private static class Jump implements ArgumentOperation {
        //jump to arg2 if arg1 satisfies condition

        private final JumpCondition condition;

        public Jump(JumpCondition condition) {
            this.condition = condition;
        }

        @Override
        public boolean call(Stack stack, Registry reg, int r1, int r2) {
            boolean jumpcond = true;
            if (condition != JumpCondition.none) {
                double v1 = reg.get(r1);
                if (condition == JumpCondition.pos && v1 < 0) {
                    jumpcond = false;
                }
                if (condition == JumpCondition.neg && v1 > 0) {
                    jumpcond = false;
                }
                if (condition == JumpCondition.zero && v1 != 0) {
                    jumpcond = false;
                }
            }
            if (jumpcond == true) {
                reg.setPC(r2);
            }
            return true;
        }
    }

    private final Opcode operation;
    private final int register1;
    private final int register2;
    private final ArgumentOperation argOp;

    BasicOperation(Opcode operation, int register1, int register2) {
        this.operation = operation;
        this.register1 = register1;
        this.register2 = register2;
        switch (operation) {
            case mul:
                argOp = new Multiply();
                break;
            case div:
                argOp = new Divide();
                break;
            case add:
                argOp = new Add();
                break;
            case sub:
                argOp = new Substract();
                break;
            case jump:
                argOp = new Jump(JumpCondition.none);
                break;
            case jpos:
                argOp = new Jump(JumpCondition.pos);
                break;
            case jneg:
                argOp = new Jump(JumpCondition.neg);
                break;
            case jzer:
                argOp = new Jump(JumpCondition.zero);
                break;
            case comp:
                argOp = new Compare();
                break;
            default:
                argOp = new Noop();
        }
    }

    @Override
    public boolean Call(Stack stack, Registry registers) {
        return argOp.call(stack, registers, register1, register2);
    }

}
