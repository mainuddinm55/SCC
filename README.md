## Srongly Conneted Component
The strongly connected components (SCC) of a directed
graph are its maximal strongly connected subgraphs.

## Algorithm is
SCC(G)
1. call DFS(G) to compute finishing times f [u] for all u
2. compute GT
3. call DFS(GT), but in the main loop, consider vertices in order of
decreasing f [u] (as computed in first DFS)
4. output the vertices in each tree of the depth-first forest formed in second
DFS as a separate SCC

### Sample Input
```
9
0 1
1 2
2 3
3 0
2 4
4 5
5 6
6 7
6 4
```

### Sample output
```
SCC are:  1, 2, 3, 0,
SCC are:  5, 6, 4,
SCC are:  7,
```