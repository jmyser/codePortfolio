 //
 //  GraphSearch.cpp
 //  Lab5
 //
 //  Reads flight data into a directed graph structure,
 //  whose vertices represent airports, and
 //  whose edges represent available flights.
 //
 //  Uses Graph.h by Bob Kasper
 //  Created by Jeremy Myser on 3/15/16.
 //  MVNU Computer Science
 //

#include <iostream>
#include <fstream>
#include <string>
#include <list>
using namespace std;

#include "Graph.h"
typedef Graph<string, string> FGraph;

/* resetGraph
 * clear vertex and edge attributes in preparation for a
 * new graph traversal
 */
void resetGraph(FGraph& g) {
	FGraph::VertexList vList = g.vertices();
	for (FGraph::VertexItor vIter = vList.begin(); vIter != vList.end(); vIter++) {
		vIter->destroy("VISITED");
		FGraph::EdgeList eList = g.outIncidentEdges(*vIter); // for all outbound edges from *vIter
		for (FGraph::EdgeItor eIter = eList.begin(); eIter != eList.end(); eIter++)
			eIter->destroy("VISITED");
	}
}

/* bfs
 * breadth-first search of a graph to find a path from start to finish
 * returns a list of edges representing a shortest path,
 * or an empty list if no path can be found.
 */
list<FGraph::Edge> bfs(FGraph& g, FGraph::Vertex& start, FGraph::Vertex& finish) {
	resetGraph(g);					// clear marks on all vertices and edges
	list<FGraph::Edge> resultPath;
    
    FGraph::VertexList current;
    current.push_back(start);
    start.set("VISITED", "true");
    int i = 0;
    bool pathFound = false;

    while (!current.empty() && !pathFound) {
        FGraph::VertexList next;
        for (FGraph::VertexItor vIter = current.begin(); vIter != current.end() && !pathFound; vIter++) {
            //cout << "Level " << i << ": from vertex " << vIter->element() << endl;
            FGraph::EdgeList eList = g.outIncidentEdges(*vIter);
            for (FGraph::EdgeItor eIter = eList.begin(); eIter != eList.end() && !pathFound; eIter++) {
                if (!eIter->has("VISITED")) {
                    FGraph::Vertex w = g.destination(*eIter);
                    if (w == finish) {
                        pathFound = true;
                    }
                    if (!w.has("VISITED")) {
                        eIter->set("VISITED", "discovery");
                        w.set("VISITED", "true");
                        next.push_back(w);
                        //cout << "Level " << i << ": edge " << eIter->element() << " (discovery " << w.element() << ")" << endl;
                    }
                    else {
                        eIter->set("VISITED", "cross");
                        //cout << "Level " << i << ": edge " << eIter->element() << " (cross " << w.element()  << ")" << endl;
                    }
                }
            }
        }
        current = next;
        i++;
    }
    if (pathFound) {
        FGraph::Vertex v;
        v = finish;
        while (!(v == start)){
            FGraph::EdgeList resultList = g.inIncidentEdges(v);
            for (FGraph::EdgeItor edgeIter = resultList.begin(); edgeIter != resultList.end(); edgeIter++) {
                string n = edgeIter->get("VISITED");
                if (n == "discovery") {
                    resultPath.push_front(*edgeIter);
                    v = g.origin(*edgeIter);
                }
            }
        }
    }
	return resultPath;
}

/* main function */

int main() {
	string fileName, line;              // input values
	string flight, origin, destination; // for each flight

	FGraph fg = FGraph();	// construct empty flight graph
	FGraph::Vertex v, w;
	FGraph::Edge e;

	// read graph input from file
	cout << "\nEnter flight information filename: " << endl;
	cin >> fileName;
	getline(cin, line); // discard remainder of line
	ifstream infile(fileName.c_str()); // open input file
	while(infile >> flight) { // false when infile reaches end of file
    	if ((infile >> origin) && (infile >> destination)) {
            v = fg.insertVertex(origin);
            w = fg.insertVertex(destination);
            e = fg.insertDirectedEdge(v, w, flight);
        }
	}
	infile.close();
	cout << "Flight Graph contains " << fg.numVertices() << " vertices and "
		 << fg.numEdges() << " edges." << endl;

	// read start and end points, and find a route
	cout << "\nEnter DEPARTURE airport code: " << endl;
	cin >> origin;
	v = fg.findVertex(origin);
	if (v.has("EMPTY")) {
		cout << "Airport could not be found: " << origin << endl;
		return 1;
	}
	cout << "\nEnter DESTINATION airport code: " << endl;
	cin >> destination;
	w = fg.findVertex(destination);
	if (w.has("EMPTY")) {
		cout << "Airport could not be found: " << destination << endl;
		return 1;
	}

	// find route from v to w
	list<FGraph::Edge> path = bfs(fg, v, w);
	if (path.size() == 0)
		cout << "\nSorry, no route could be found." << endl;
	else { // print route represented by path
		cout << "\nShortest route has " << path.size() << " flights:" << endl;
		FGraph::EdgeItor edgeIt = path.begin();
		while (edgeIt != path.end()) {
			// print flight info from edge
			cout << edgeIt->element()
				 << " from " << fg.origin(*edgeIt).element()
				 << " to " << fg.destination(*edgeIt).element() << endl;
			++edgeIt;	// advance to next edge
			}
		}
	return 0;
}
