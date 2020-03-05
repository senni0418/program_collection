## @file ChemEntity.py
#  @author Senni Tan
#  @brief Module that implement the interface of two functions
#  @date Feb 6,2020

from ChemTypes import *
from ElmSet import *
from abc import ABC, abstractmethod


## @brief An interface that has two functions: num_atoms and constit_elms
class ChemEntity(ABC):

    ## @brief Interface of function num_atoms
    @abstractmethod
    def num_atoms(self, element):
        pass

    ## @brief Interface of function constit_elms
    @abstractmethod
    def constit_elems(self):
        pass
