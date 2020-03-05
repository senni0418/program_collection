## @file Equality.py
#  @author Senni Tan
#  @brief Module that implements an interface of a function equals
#  @date Feb 6, 2020

from abc import ABC, abstractmethod


## @brief An interface that contains the syntax of a function
class Equality(ABC):

    ## @ brief Interface of function equals
    @abstractmethod
    def equals(self, T):
        pass
