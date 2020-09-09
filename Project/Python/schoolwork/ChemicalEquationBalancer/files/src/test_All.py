## @file test_All.py
#  @author Senni Tan
#  @brief Tests implementation of python files Set.py, MoleculeT.py,
#  CompoundT.py and ReactionT.py
#  @date Feb 8, 2020

from ChemTypes import *
from ChemEntity import *
from Equality import *
from Set import *
from ElmSet import *
from MolecSet import *
from MoleculeT import *
from CompoundT import *
from ReactionT import *
from pytest import *


## @brief Tests methods from Set.py
class TestSet:

    ## @brief Methods to test the add method of the Set class
    def test_add(self):
        nums = Set([1, 2, 3, 4])
        nums.add(5)
        assert nums.equals(Set([1, 2, 3, 4, 5]))

    ## @brief Methods to test the rm method of the Set class
    def test_rm(self):
        s1 = Set([1, 2, 3, 4])
        expect = Set([1, 2, 3])
        s1.rm(4)
        assert s1.equals(expect)

    ## @brief Methods to test the member method of the Set class
    def test_member(self):
        s = Set([1, 2, 3, 4])
        assert s.member(1)

    ## @brief Methods to test the size method of the Set class
    def test_size(self):
        s = Set([1, 2, 3, 4])
        size1 = s.size()
        assert size1 == 4

    ## @brief Methods to test the equals method of the Set class
    def test_equals(self):
        s1 = Set([1, 2, 3, 4])
        s2 = Set([1, 2, 3, 4])
        assert s1.equals(s2)

    ## @brief Methods to test the to_seq method of the Set class
    def test_to_seq(self):
        s1 = Set([1, 2, 3, 4])
        s1 = s1.to_seq()
        assert s1 == [1, 2, 3, 4]


## @brief Tests methods from MoleculeT.py
class TestMoleculeT:

    ## @brief Methods to test the get_num method of the MoleculeT class
    def test_get_num(self):
        h2 = MoleculeT(2, ElementT)
        num = h2.get_num()
        assert num == 2

    ## @brief Methods to test the get_elm method of the MoleculeT class
    def test_get_elm(self):
        h2 = MoleculeT(2, ElementT.H)
        elm = h2.get_elm()
        assert elm == ElementT.H

    ## @brief Methods to test the num_atoms method of the MoleculeT class
    def test_num_atoms(self):
        h2 = MoleculeT(2, ElementT.H)
        n1 = h2.num_atoms(ElementT.H)
        n2 = h2.num_atoms(ElementT.O)
        assert n1 == 2
        assert n2 == 0

    ## @brief Methods to test the constit_elems method of the MoleculeT class
    def test_constit_elems(self):
        h2 = MoleculeT(2, ElementT.H)
        s = h2.constit_elems()
        assert s.s[0] == ElementT.H

    ## @brief Methods to test the equals method of the MoleculeT class
    def test_equals(self):
        h2 = MoleculeT(2, ElementT.H)
        h2_2 = MoleculeT(2, ElementT.H)
        assert h2.equals(h2)
        assert h2.equals(h2_2)


## @brief Tests methods from CompoundT.py
class TestCompoundT:

    ## @brief Methods to test the get_num method of the CompoundT class
    def test_get_molec_set(self):
        oxy = MoleculeT(1, ElementT.O)
        h2 = MoleculeT(2, ElementT.H)
        h2o = CompoundT(MolecSet([h2, oxy]))
        s = h2o.get_molec_set().to_seq()
        assert s[0].get_elm() == ElementT.H
        assert s[0].get_num() == 2
        assert s[1].get_elm() == ElementT.O
        assert s[1].get_num() == 1

    ## @brief Methods to test the num_atoms method of the CompoundT class
    def test_num_atoms(self):
        oxy = MoleculeT(1, ElementT.O)
        h2 = MoleculeT(2, ElementT.H)
        h2o = CompoundT(MolecSet([h2, oxy]))
        h_num = h2o.num_atoms(ElementT.H)
        o_num = h2o.num_atoms(ElementT.O)
        assert h_num == 2
        assert o_num == 1

    ## @brief Methods to test the constit_elms method of the CompoundT class
    def test_constit_elems(self):
        O = MoleculeT(1, ElementT.O)
        H2 = MoleculeT(2, ElementT.H)
        H2O = CompoundT(MolecSet([H2, O]))
        s = H2O.constit_elems()
        assert s.s == [ElementT.H, ElementT.O]

    ## @brief Methods to test the equals method of the CompoundT class
    def test_equals(self):
        O = MoleculeT(1, ElementT.O)
        H2 = MoleculeT(2, ElementT.H)
        H2O = CompoundT(MolecSet([H2, O]))
        o = MoleculeT(1, ElementT.O)
        h2 = MoleculeT(2, ElementT.H)
        h2o = CompoundT(MolecSet([h2, o]))
        assert H2O.equals(H2O)
        assert not H2O.equals(h2o)


## @brief Tests methods from ReactionT.py
class TestReactionT:

    ## @brief Methods to test the get_lhs method of the ReactionT class
    def test_get_lhs(self):
        H2 = MoleculeT(2, ElementT.H)
        O2 = MoleculeT(2, ElementT.O)
        O = MoleculeT(1, ElementT.O)
        H2O = CompoundT(MolecSet([H2, O]))
        H2 = CompoundT(MolecSet([H2]))
        O2 = CompoundT(MolecSet([O2]))
        L = [H2, O2]
        R = [H2O]
        reaction = ReactionT(L, R)
        l = reaction.get_lhs()
        l = l[0].get_molec_set().s
        l = l[0]
        assert l.get_elm() == ElementT.H
        assert l.get_num() == 2

    ## @brief Methods to test the get_rhs mehtod of the ReactionT class
    def test_get_rhs(self):
        H2 = MoleculeT(2, ElementT.H)
        O2 = MoleculeT(2, ElementT.O)
        O = MoleculeT(1, ElementT.O)
        H2O = CompoundT(MolecSet([H2, O]))
        H2 = CompoundT(MolecSet([H2]))
        O2 = CompoundT(MolecSet([O2]))
        L = [H2, O2]
        R = [H2O]
        reaction = ReactionT(L, R)
        r = reaction.get_rhs()
        r = r[0].get_molec_set().s
        r1 = r[0]
        r2 = r[1]
        assert r1.get_elm() == ElementT.H
        assert r1.get_num() == 2
        assert r2.get_elm() == ElementT.O
        assert r2.get_num() == 1

    ## @brief Methods to test the get_lhs_coeff method of the
    #  ReactionT class
    def test_lhs_coeff(self):
        H2 = MoleculeT(2, ElementT.H)
        O2 = MoleculeT(2, ElementT.O)
        O = MoleculeT(1, ElementT.O)
        H2O = CompoundT(MolecSet([H2, O]))
        H2 = CompoundT(MolecSet([H2]))
        O2 = CompoundT(MolecSet([O2]))
        L = [H2, O2]
        R = [H2O]
        reaction = ReactionT(L, R)
        cL = reaction.get_lhs_coeff()
        assert cL[0] == 2
        assert cL[1] == 1

    ## @brief Methods to test the get_rhs_coeff method of the ReactionT class
    def test_rhs_coeff(self):
        H2 = MoleculeT(2, ElementT.H)
        O2 = MoleculeT(2, ElementT.O)
        O = MoleculeT(1, ElementT.O)
        H2O = CompoundT(MolecSet([H2, O]))
        H2 = CompoundT(MolecSet([H2]))
        O2 = CompoundT(MolecSet([O2]))
        L = [H2, O2]
        R = [H2O]
        reaction = ReactionT(L, R)
        cR = reaction.get_rhs_coeff()
        assert cR[0] == 2
