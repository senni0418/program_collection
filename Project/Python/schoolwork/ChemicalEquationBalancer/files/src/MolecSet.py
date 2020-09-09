## @file MolecSet.py
#  @author Senni Tan
#  @brief Module that implements a set of type MoleculeT
#  @date Feb 8, 2020

from MoleculeT import *
from Set import *


## @brief An abstract data type that inherit everything from Set but
#  will be used particularly on type MoleculeT
class MolecSet(Set):

    pass


'''
H2 = MoleculeT(2, ElementT.H)
O2 = MoleculeT(2, ElementT.O)
N2 = MoleculeT(2, ElementT.N)
s1 = MolecSet([H2])
print(s1.s)
print(s1.s[0].get_elm())
print(s1.s[0].get_num())
print(s1.s[0].num_atoms(ElementT.H))
print(s1.s[0].num_atoms(ElementT.O))
s1.add(O2)
print(s1.s)
print(s1.s[1].get_elm())
print(s1.s[1].get_num())
print(s1.s[1].num_atoms(ElementT.H))
print(s1.s[1].num_atoms(ElementT.O))
print(s1.member(O2))
print(s1.member(MoleculeT(2, ElementT.O))) #false
s1.rm(O2)
print(s1.member(O2))
print(s1.size())
s1 = s1.to_seq()
print(s1)
'''
