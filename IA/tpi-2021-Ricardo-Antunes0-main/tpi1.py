#
#Ricardo Antunes, 98275
#

from types import SimpleNamespace
from tree_search import *
from cidades import *

class MyNode(SearchNode):
    def __init__(self,state,parent, depth, cost, heuristic):
        super().__init__(state,parent)
        self.cost = cost
        self.heuristic = heuristic
        self.depth = depth
        self.eval =  cost + heuristic
        self.children=[]

class MyTree(SearchTree):

    def __init__(self,problem, strategy='breadth',seed=0): 
        super().__init__(problem,strategy,seed)
        root = MyNode(problem.initial, None, 0, 0, heuristic = problem.domain.heuristic(problem.initial, problem.goal))
        self.all_nodes = [root]
        self.used_shortcuts = []
        
    def astar_add_to_open(self,lnewnodes):
        self.open_nodes.extend(lnewnodes)
        self.open_nodes.sort(key = lambda x: self.all_nodes[x].cost + self.all_nodes[x].heuristic)


    def propagate_eval_upwards(self,node):
        eval_children=[]
        min = 10000
        count = 0
        
        if node.children == []:
            return self.propagate_eval_upwards(self.all_nodes[node.parent])

        for i in node.children:
            eval_children.append(round(self.all_nodes[i].eval))
            child = eval_children[count]
            if child != None:
                if min > child:
                    min = child
                    count +=1
        node.eval = min
            
        if node.parent == None:
            return None
        return self.propagate_eval_upwards(self.all_nodes[node.parent])


    def search2(self,atmostonce=False):
        while self.open_nodes != []:
            nodeID = self.open_nodes.pop(0)
            node = self.all_nodes[nodeID]
            if self.problem.goal_test(node.state):
                self.solution = node
                self.terminals = len(self.open_nodes)+1
                return self.get_path(node)
            lnewnodes = []
            self.non_terminals += 1
            for a in self.problem.domain.actions(node.state):
                newstate = self.problem.domain.result(node.state,a)
                if newstate not in self.get_path(node):
                    newnode = MyNode(newstate,nodeID, node.depth+1, node.cost+self.problem.domain.cost(node.state,a), self.problem.domain.heuristic(newstate,self.problem.goal))
                    node.children.append(len(self.all_nodes))
                    self.all_nodes.append(newnode)
                    self.propagate_eval_upwards(newnode)
                    lnewnodes.append(len(self.all_nodes)-1)
            self.add_to_open(lnewnodes)
        return None


    def repeated_random_depth(self,numattempts=3,atmostonce=False):
        custo = 10000

        for i in range(0, numattempts):
            Tree = MyTree(self.problem, 'rand_depth', i)
            Tree.search2()

            if Tree.solution.cost < custo:
                self.solution_tree = Tree
                custo = Tree.solution.cost
        return self.solution_tree.get_path(self.solution_tree.solution)


    def make_shortcuts(self):
        tree = self.get_path(self.solution)
        j = 0

        while j < (len(tree)-1):
            for a1,b1 in self.problem.domain.actions(tree[j]):
                if (j+2 >= len(tree)):
                    break
                if b1 == tree[j+2]:
                    self.used_shortcuts += [(a1,b1)]
                    tree.pop(j+1)
                    break
            j += 1
        return tree
        

class MyCities(Cidades):

    def maximum_tree_size(self,depth):   # assuming there is no loop prevention

        neighbors = []
        cities = [] 
        average_num = 0

        for (c1,c2,dist) in self.connections:
            if not ((c1) in cities):
                cities.append(c1) 
            if not ((c2) in  cities):
                cities.append(c2)

        for cidade in cities:
            num_neighbors = 0
            for (c1,c2,dist) in self.connections:
                if cidade == c1 or cidade == c2:
                    num_neighbors +=1 
            neighbors.append(num_neighbors)

        average_num = sum(neighbors)/len(cities)

        result = ((average_num**(depth+1)) - 1)/(average_num - 1)
        return result

        