#ifndef AST_EVALUATOR_HH
#define AST_EVALUATOR_HH

#include "nodes.hh"
#include "../utils/errors.hh"

namespace ast
{
    class ASTEvaluator : public ConstASTIntVisitor
    {
        int visit(const class IntegerLiteral& literal) {
            return literal.value;
        }

        int visit(const class BinaryOperator& bin_op) {
            switch (bin_op.op) {
            case o_plus:
                return bin_op.get_left().accept(*this) + bin_op.get_right().accept(*this);
            case o_minus:
                return bin_op.get_left().accept(*this) - bin_op.get_right().accept(*this);
            case o_times:
                return bin_op.get_left().accept(*this) * bin_op.get_right().accept(*this);
            case o_divide:
                return bin_op.get_left().accept(*this) / bin_op.get_right().accept(*this);
            case o_eq:
                return bin_op.get_left().accept(*this) == bin_op.get_right().accept(*this);
            case o_neq:
                return bin_op.get_left().accept(*this) != bin_op.get_right().accept(*this);
            case o_lt:
                return bin_op.get_left().accept(*this) < bin_op.get_right().accept(*this);
            case o_gt:
                return bin_op.get_left().accept(*this) > bin_op.get_right().accept(*this);
            case o_le:
                return bin_op.get_left().accept(*this) <= bin_op.get_right().accept(*this);
            case o_ge:
                return bin_op.get_left().accept(*this) >= bin_op.get_right().accept(*this);
            default:
                utils::error("Error: Wrong BinaryOperator!");
            }
        }

        int visit(const class Sequence& seq) {
            int result;
            const auto seq_exprs = seq.get_exprs();

            if (seq_exprs.cbegin() == seq_exprs.cend())
                utils::error("Error: Sequence is empty!");

            for (auto seq_expr = seq_exprs.cbegin(); seq_expr != seq_exprs.cend(); seq_expr++)
                result = (*seq_expr)->accept(*this);

            return result;
        }

        int visit(const class IfThenElse& ite) {
            if (ite.get_condition().accept(*this))
                return ite.get_then_part().accept(*this);
            else
                return ite.get_else_part().accept(*this);
        }

        int visit(const class StringLiteral&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class Identifier&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class VarDecl&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class FunDecl&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class FunCall&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class Assign&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class WhileLoop&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class ForLoop&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class Break&) {
            utils::error("Error: Not implemented!");
        }

        int visit(const class Let&) {
            utils::error("Error: Not implemented!");
        }
    };
}

#endif
