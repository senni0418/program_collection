## @file MoleculeT.py
#  @author Senni Tan
#  @brief Module that implements a type of MoleculeT
#  @date Feb 8, 2020

from ElmSet import *
from ChemTypes import *
from ChemEntity import *
from Equality import *


## @brief An abstract data type that represents a molecule
class MoleculeT(ChemEntity, Equality):

    ## @brief MoleculeT constructor
    #  @details Construct a molecule with a given element and the
    #  number of that element
    #  @param n the number of the given element
    #  @param e the given element
    def __init__(self, n, e):
        self.num = n
        self.elm = e

    ## @brief The getter method of class MoleculeT
    #  @return The number of elements contained in the molecule
    def get_num(self):
        return self.num

    ## @brief The getter method of class MoleculeT
    #  @return The element contained in the molecule
    def get_elm(self):
        return self.elm

    ## @brief Determine the number of a given element in the molecule
    #  @param e The given element that needs to be checked
    #  @return The number of that given element contained in the
    #  molecule; if that element is not in this molecule, return 0
    def num_atoms(self, e):
        if e == self.elm:
            return self.num
        return 0

    ## @brief Determine the element contained in the molecule
    #  @return A set of elements contained in the molecule
    def constit_elems(self):
        return ElmSet([self.elm])

    ## @brief Determine if two molecules are equal
    #  @return True if two molecules contain the exact same elements
    #  and with the exact same numbers, false otherwise
    def equals(self, m):
        return (self.elm == m.get_elm()) & (self.num == m.get_num())


'''test
H2 = MoleculeT(2, ElementT.H)
print(H2.get_num())
print(H2.get_elm())
print(H2.num_atoms(ElementT.H))
print(H2.num_atoms(ElementT.O))
print(H2.constit_elems().s)
other = MoleculeT(2, ElementT.H)
O2 = MoleculeT(2, ElementT.O)
print(H2.equals(other))
print(H2.equals(O2))
'''
