### Solution for the Redmart's 'Skiing in Singapore' challenge
*http://geeks.redmart.com/2015/01/07/skiing-in-singapore-a-coding-diversion/*

Modified DFS algorithm where nodes are visited at least 2 times. One for the deepest node search and other for setting the node deepest path.

For performance improvements, the deepest node path is cached on each node in order to avoid revisiting it.

