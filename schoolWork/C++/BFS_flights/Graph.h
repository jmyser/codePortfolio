/* Graph.h
 * Graph class implements the Graph ADT
 * using the adjacency list data structure.
 * 
 * USAGE:
 * Graph takes template parameters to determine the type of objects
 * used to label vertices and edges. For example,
 *    Graph<int, string> is a graph type where
 *    vertices are labeled by integers, and edges are labeled by strings.
 * The graphs for Project 5 should use the type Graph<string, string>.
 * The Graph class also provides types for its component objects:
 * 		Graph<vertexType, edgeType>::Vertex
 * 		Graph<vertexType, edgeType>::VertexList
 * 		Graph<vertexType, edgeType>::VertexItor
 * 		Graph<vertexType, edgeType>::Edge
 * 		Graph<vertexType, edgeType>::EdgeList
 * 		Graph<vertexType, edgeType>::EdgeItor
 * Here's an example showing how to create a graph object,
 * and add two vertices and one edge:
 * 		typedef Graph<string, string> Graph;
 * 		Graph g = Graph();
 * 		Graph::Vertex v = g.insertVertex("Columbus");
 *		Graph::Vertex w = g.insertVertex("Mount Vernon");
 *      Graph::Edge e = g.insertDirectedEdge(v, w, "AA321");
 *
 * Author: Bob Kasper
 * based on the Graph ADT from Data Structures and Algorithms in C++
 * by Goodrich, Tamassia and Mount.
 */
#ifndef GRAPH_H
#define GRAPH_H

#include <list>
#include <map>
#include <string>
using namespace std;

/* DecorableObject
 * stores an Object with a collection of attributes.
 * Each attribute is represented by (Key, Value) pair.
 * (vertices and edges will be subclasses to support marking algorithms)
 */
template <typename Key, typename Value, typename Object>
class DecorableObject {
private:
  Object label;				// stored object
  map<Key, Value> alist;	// list of attributes
protected:
  typedef typename map<Key, Value>::const_iterator AttIterator;
public:
  DecorableObject(const Object o) 	// constructor for object
	: label(o) { }					// with empty attribute list
  ~DecorableObject() { }			// destructor: could delete attribute map
  bool has(const Key& k) const 		// tests whether object has attribute k
    { AttIterator a = alist.find(k);
	  return (a != alist.end());
	}
  Value get(const Key& k) const 	// returns the value of attribute k
    { AttIterator a = alist.find(k);
	  return (a->second);
	}
  void set(const Key& k, const Value& x)	// sets attribute k to x
    { alist[k] = x; }
  void destroy(const Key& k)	// removes attribute k and its associated value
    { alist.erase(k); }
  // implement access functions for a Position
  Object element() const 	 // returns the label
    { return label; }
  void setElement(const Object e) // sets the label
    { label = e; }
};

template <typename vertexElement, typename edgeElement>
class Graph {
public:
	class Vertex;			// allow forward references
	class Edge;
	class edgeObj;
	// lists and iterators
	typedef list<Vertex> VertexList;
	typedef typename list<Vertex>::iterator VertexItor;
	typedef list<Edge> EdgeList;
	typedef typename list<Edge>::iterator EdgeItor;

	class vertexObj : public DecorableObject<string, string, vertexElement> {
	private:
		int inEdgeCount;	// number of incoming directed edges
		int outEdgeCount;	// number of outgoing directed edges
		// reference to the position of the vertex in vertexMap
  		typename map<vertexElement, vertexObj*>::iterator position;
		// lists of incoming and outgoing edges
		list<Edge> inEdges;
		list<Edge> outEdges;
	public:
		vertexObj(vertexElement e) // constructor with label
			: DecorableObject<string, string, vertexElement>(e),
			  inEdgeCount(0), outEdgeCount(0),
			  inEdges(), outEdges() { }
  		vertexObj() // default constructor required by map
			: DecorableObject<string, string, vertexElement>(vertexElement()) { }
		// TO DO: define destructor to delete incidence lists and decorations
		// ~vertexObj();
		friend class Graph<vertexElement, edgeElement>;
	}; // end vertexObj class

	// public Vertex encapsulates pointers to vertexObj to avoid updating
	// copies of vertices after assignment operations
	class Vertex {
	private:
		vertexObj *vPtr;
	public:
		Vertex(vertexElement e) // constructor with label
			{ vPtr = new vertexObj(e); }
		Vertex(vertexObj *p) 	// constructor with vertexObj pointer
			{ vPtr = p; }
		Vertex() 		// default constructor
			{ vPtr = NULL; }
		// provide decorableObject operations
  		bool has(const string& k) const 	// tests whether object has attribute k
    			{ return vPtr->has(k); }
		string get(const string& k) const 	// returns the value of attribute k
    			{ return vPtr->get(k); }
		void set(const string& k, const string& x)	// sets attribute k to x
			{ vPtr->set(k, x); }
  		void destroy(const string& k)	// removes attribute k and its associated value
    			{ vPtr->destroy(k); }
  		vertexElement element() const 	 // returns the label
    			{ return vPtr->element(); }		
		friend class Graph<vertexElement, edgeElement>;
	}; // end Vertex class

	class edgeObj : public DecorableObject<string, string, edgeElement> {
	private:
  		// endpoints
		vertexObj *origin;
		vertexObj *destination;
		// reference to the position of the edgeObj in edgeList
  		typename list<edgeObj*>::iterator position;
	public:
  		edgeObj(edgeElement e) // constructor with label
			: DecorableObject<string, string, edgeElement>(e) { }
		// TO DO: define destructor to delete decorations
		// ~edgeObj();
		friend class Graph<vertexElement, edgeElement>;
	}; // end edgeObj class

	// public Edge encapsulates pointers to edgeObj to avoid updating
	// copies of edges after assignment operations
	class Edge {
	private:
		edgeObj *ePtr;
	public:
		Edge(edgeElement e) // constructor with label
			{ ePtr = new edgeObj(e); }
		Edge(edgeObj *p) 	// constructor with edgeObj pointer
			{ ePtr = p; }
		Edge() 		// default constructor
			{ ePtr = NULL; }
		// provide decorableObject operations
  		bool has(const string& k) const 	// tests whether object has attribute k
    			{ return ePtr->has(k); }
		string get(const string& k) const 	// returns the value of attribute k
    			{ return ePtr->get(k); }
		void set(const string& k, const string& x)	// sets attribute k to x
			{ ePtr->set(k, x); }
  		void destroy(const string& k)	// removes attribute k and its associated value
    			{ ePtr->destroy(k); }
  		edgeElement element() const 	 // returns the label
    			{ return ePtr->element(); }		
		friend class Graph<vertexElement, edgeElement>;
	}; // end Edge class

	// Graph member functions
	Graph()  // constructor for empty graph
		: vertexMap(), edgeList() { }
	// retrieve graph information
	int numVertices() const
		{ return vertexMap.size(); }
	int numEdges() const
		{ return edgeList.size(); }
	bool isEmpty()
		{ return (numVertices() == 0); }
	VertexList vertices() 	// return a list of all vertices
		{ // construct list by using map iterator
		  VertexList vlist;
		  typename map<vertexElement, vertexObj*>::iterator mapI;
		  mapI = vertexMap.begin();
		  while (mapI != vertexMap.end()) {
			  vlist.push_back(Vertex(mapI->second));
			  mapI++;
		  }
		  return vlist;
		}

	// findVertex: lookup vertex by label (function not included in text)
	// return a new vertex object refering to the matching object,
	// or a vertex with EMPTY attribute if no vertex exists with label
	Vertex findVertex(const vertexElement& e) {
		// use map lookup operator
		if (vertexMap.find(e) == vertexMap.end()) {
			// no such vertex exists
			Vertex v("EMPTY");
			v.set("EMPTY", "TRUE");
			return v;
		}
		return Vertex(vertexMap[e]);
	}

	// Accessor methods for directed graphs
	int inDegree(const Vertex& v)
		{ return v.vPtr->inEdgeCount; }
	int outDegree(const Vertex& v)
		{ return v.vPtr->outEdgeCount; }
	EdgeList inIncidentEdges(const Vertex& v)
		{ return v.vPtr->inEdges; }
	EdgeList outIncidentEdges(const Vertex& v)
		{ return v.vPtr->outEdges; }
 	// opposite returns endpoint of e distinct from v
	Vertex opposite(const Vertex& v, const Edge& e)
		{ if (e.ePtr->origin == v.vPtr)
			return destination(e);
		  else
			return origin(e);
		}
	bool areAdjacent(const Vertex& v, const Vertex& w);

	// Methods for directed edges
	Vertex destination(const Edge& e)
		{ return Vertex(e.ePtr->destination); }
	Vertex origin(const Edge& e)
		{ return Vertex(e.ePtr->origin); }

	// Update methods
	// find or create a vertex with label e
	Vertex insertVertex(const vertexElement e)
		{ vertexObj *vo;
		  if (vertexMap.find(e) == vertexMap.end()) {
			// create new vertexObj only if no existing vertex with label e
			vo = new vertexObj(e);
			vertexMap[e] = vo;
			vo->position = vertexMap.find(e);
			}
		  else
			vo = vertexMap[e];
		  return Vertex(vo);
		}
	// insert and return a new directed edge with label
	// from vertex v to vertex w
	Edge insertDirectedEdge(Vertex& v, Vertex& w, const edgeElement label)
		{	edgeObj *e = new edgeObj(label);
			edgeList.push_front(e);
			e->position = edgeList.begin();
			e->origin = v.vPtr;
			e->destination = w.vPtr;
			// add edge to incidence lists of endpoints
			Edge edge(e);
			v.vPtr->outEdges.push_front(edge);
			(v.vPtr->outEdgeCount)++;
			w.vPtr->inEdges.push_front(edge);
			(w.vPtr->inEdgeCount)++;
			return edge;
		}
	// remove vertex v and all its incident edges
	void removeVertex(Vertex& v);
	void removeEdge(Edge& e)
		{ // Remove e from edgeList
		  edgeList.remove(e.ePtr);
		  delete e.ePtr;
		}

private:
	// graph is represented by adjacency list
	// uses list of pointers to avoid copying vertex and edge objects
	// when adding to lists
	map<vertexElement, vertexObj*> vertexMap;
	list<edgeObj*> edgeList;
}; // end Graph class


template <typename vertexElement, typename edgeElement>
void Graph<vertexElement, edgeElement>::removeVertex(Vertex& v) {
	// Remove incident edges
	EdgeList eList = inIncidentEdges(v);
	EdgeItor eItor, ePosition;
	eItor = eList.begin();
	while (eItor != eList.end()) {
		ePosition = eItor;
		eItor++;
		removeEdge(*ePosition);
		}
	eList = outIncidentEdges(v);
	eItor = eList.begin();
	while (eItor != eList.end()) {
		ePosition = eItor;
		eItor++;
		removeEdge(*ePosition);
		}
	// Remove v from vertexMap
	// erase by key to avoid errors when erasing by position
	vertexMap.erase(v.element());
	delete v.vPtr;
}

// Vertex == operator
typedef Graph<string, string> FGraph;

bool operator==(const FGraph::Vertex& v1, const FGraph::Vertex& v2) {
	return v1.element() == v2.element();
}

#endif // GRAPH_H