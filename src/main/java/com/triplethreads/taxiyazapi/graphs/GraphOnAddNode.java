package com.triplethreads.taxiyazapi.graphs;

import com.triplethreads.taxiyazapi.model.AvailableNode;
import com.triplethreads.taxiyazapi.model.Node;
import com.triplethreads.taxiyazapi.model.Route;
import com.triplethreads.taxiyazapi.model.RoutingNodes;
import com.triplethreads.taxiyazapi.repository.AvailableNodesRepository;
import com.triplethreads.taxiyazapi.repository.NodeRepository;
import com.triplethreads.taxiyazapi.repository.RouteRepository;
import com.triplethreads.taxiyazapi.repository.RoutingNodesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GraphOnAddNode {

    /**
     * this class is called when new node connection is added, it has bad time complexity so should run on
     * background thread
     */

    private final RouteRepository routeRepository;
    private final AvailableNodesRepository availableNodesRepository;
    private final RoutingNodesRepository routingNodesRepository;
    private final NodeRepository nodeRepository;

    @Autowired
    public GraphOnAddNode(RouteRepository routeRepository,
                          AvailableNodesRepository availableNodesRepository,
                          RoutingNodesRepository routingNodesRepository,
                          NodeRepository nodeRepository) {

        this.routeRepository = routeRepository;
        this.availableNodesRepository = availableNodesRepository;
        this.routingNodesRepository = routingNodesRepository;
        this.nodeRepository = nodeRepository;
    }

    public void onAddNode(Node node) throws CloneNotSupportedException {

        if (node.getAvailableNodes() == null) {
            return;
        }
        addRoute(node);
    }

    // Please dont try to understand the following code .. only God knows
    private void addRoute(Node node) throws CloneNotSupportedException {

        List<AvailableNode> availableNodes = node.getAvailableNodes();

        RoutingNodes rootNode = new RoutingNodes();
        rootNode.setNode(node);

        for (AvailableNode av_1 : availableNodes) {
            RoutingNodes firstNode = new RoutingNodes();
            extractedCreate(rootNode, firstNode, rootNode, av_1);

            for (AvailableNode av_2 : nodeRepository.findById(av_1.getDestination().getId()).get().getAvailableNodes()) {

                RoutingNodes secondNode = new RoutingNodes();
                extractedCreate(rootNode, secondNode, firstNode, av_2);

                for (AvailableNode av_3 : nodeRepository.findById(av_2.getDestination().getId()).get().getAvailableNodes()) {
                    RoutingNodes thirdNode = new RoutingNodes();
                    extractedCreate(rootNode, thirdNode, secondNode, av_3);
                }
            }
        }
    }

    private void extractedCreate(
            RoutingNodes rootNode,
            RoutingNodes currentNode,
            RoutingNodes prevNodes,
            AvailableNode availableNode
    ) throws CloneNotSupportedException {

        currentNode.setNode(availableNode.getDestination());
        prevNodes.setPrice(availableNode.getPrice());
        prevNodes.setNextNode(currentNode);

        executeRouteAdd(rootNode);
    }

    private void executeRouteAdd(RoutingNodes rootNode) throws CloneNotSupportedException {
        Node node = rootNode.getNode();

        Node dest = null;
        double price = 0.0;
        int hops = 0;
        RoutingNodes routingN = (RoutingNodes) rootNode.clone();
        while (routingN != null) {
            dest = routingN.getNode();
            price += routingN.getPrice();
            hops += 1;
            System.out.print(routingN.getNode().getName() + " > ");
            routingN = routingN.getNextNode();
        }

        hops--;

        // creating routes
        Route route = new Route();
        route.setTitle(node.getName() + " " + dest.getName());
        route.setStart(node);
        route.setDestination(dest);
        route.setPrice(price);
        route.setHops(hops);
        route.setRating(0.0);
        route.setRoutingNodes(rootNode);

        Iterable<Route> routeIterable = routeRepository.findRoutesByTitle(route.getTitle());
        if (routeIterable.iterator().hasNext()) {
            routeIterable.forEach(

                    _route -> {

                        if (_route.getPrice() != route.getPrice() &&
                                _route.getHops() != route.getHops() &&
                                (!_route.getRoutingNodes().equals(route.getRoutingNodes()))) {

                            routeRepository.save(route);
                        }
                    }
            );
        } else {
            routeRepository.save(route);
        }

        routingNodesRepository.save(rootNode);
    }

}