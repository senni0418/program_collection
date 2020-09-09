## @file CompoundT.py
#  @author Senni Tan
#  @brief Module that implements a type of CompoundT
#  @date Feb 8, 2020

from MoleculeT import *
from ChemEntity import *
from Equality import *
from ElmSet import *
from MolecSet import *


## @brief An abstract data type that represents a compound
class CompoundT(ChemEntity, Equality):

    ## @brief CompoundT constructor
    #  @details Construct a compound with a given set of molecules
    #  @param M the given set of molecules
    def __init__(self, M):
        self.molec_set = M

    ## @brief The getter method of the class CompoundT
    #  @return The set of molecules contained in the compound
    def get_molec_set(self):
        return self.molec_set

    ## @brief Determine the number of the given element contained in
    #  the compound
    ## @param e the element that needs to be checked
    ## @return The number of the given element contained in the compound
    def num_atoms(self, e):
        num = 0
        for molecule in self.molec_set.to_seq():
            num += molecule.num_atoms(e)
        return num

    ## @brief Determine the elements contained in the compound
    #  @return The set of elements contained in the compound
    def constit_elems(self):
        s = ElmSet([])
        for m in self.get_molec_set().to_seq():
            s.add(m.get_elm())
        return s

    ## @brief Determine if the given compound is equal to this one
    #  @param D the given compound
    #  @return True if two compound contain the same set of molecules,
    #  false otherwise
    def equals(self, D):
        this = self.molec_set
        that = D.get_molec_set()
        num = 0
        if not len(this.s) == len(that.s):
            return False
        for i in range(len(this.s)):
            for j in range(len(this.s)):
                if this.s[i].equals(that.s[j]):
                    num += 1
        if num == len(this.s):
            return True
        return False
				


'''
O = MoleculeT(1, ElementT.O)
H2 = MoleculeT(2, ElementT.H)
a = MoleculeT(4, ElementT.H)
H2O = CompoundT(MolecSet([H2, O]))
print(H2O.get_molec_set())
print(H2O.num_atoms(ElementT.H))
print(H2O.num_atoms(ElementT.O))
print(H2O.num_atoms(ElementT.N))
e = H2O.constit_elems()
e = e.to_seq()
print(e)
o = MoleculeT(1, ElementT.O)
h2 = MoleculeT(2, ElementT.H)
h2o = CompoundT(MolecSet([h2, o]))
print(h2o.get_molec_set())
print(h2o.equals(h2o))
print(h2o.equals(H2O)) #false
'''
