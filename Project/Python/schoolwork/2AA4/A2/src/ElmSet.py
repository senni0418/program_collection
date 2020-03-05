## @file ElemSet.py
#  @author Senni Tan
#  @brief A module that implements a set of type ElementT
#  @date Feb 8, 2020

from ChemTypes import *
from Set import *


## @brief An abstract data type that inherit everything from Set but
#  will be used particularly on type ElementT
class ElmSet(Set):

    pass


'''test
a = ElmSet([ElementT.H, ElementT.He, ElementT.Li, ElementT.Be, ElementT.B])
b = ElmSet([ElementT.C, ElementT.N, ElementT.O, ElementT.F, ElementT.Ne])
c = ElmSet([ElementT.H, ElementT.He, ElementT.Li, ElementT.Be, ElementT.B])
d = ElmSet([ElementT.C, ElementT.N, ElementT.O, ElementT.F, ElementT.Ne])
print(a.s)
print(b.s)
print(a.equals(c.s))
a.rm(ElementT.B)
print(a.equals(c.s))
print(a.s)
a.add(ElementT.B)
print(a.equals(c.s))
print(a.s)
print(b.member(ElementT.Ne))
print(b.member(ElementT.V))
print(a.size())
a.add(1)
print(a.size())
print(a.s)
print(a.to_seq())
'''
