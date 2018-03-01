class basic_multipllier:
    def __init__(self):
        pass;

    def toString(self):
        return "Basic Multiplier"

    def multiply(self,a,b):
        dim = a.getDim()
        result = IntMatrix(dim)

        for i in range(0,dim):
            for j in range (0,dim):
                sum = 0
                for k in (0,dim):
                    sum += a.get(i,k) * b.get(k,j)
                result.set(i,j,sum)
        return result
