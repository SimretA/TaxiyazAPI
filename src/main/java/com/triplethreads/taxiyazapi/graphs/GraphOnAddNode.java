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

import java.util.ArrayList;
import java.util.HashMap;
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
        searchForCommon(node);
    }

    private void searchForCommon(Node node) throws CloneNotSupportedException {

        List<RoutingNodes> routingNodesList = new ArrayList<>();
        List<Route> routes = new ArrayList<>();
        addRoute(node, routingNodesList, routes);

    }

    // Please dont try to understand the following code .. only God knows
    private void addRoute(Node node, List<RoutingNodes> routingNodesList, List<Route> routes) throws CloneNotSupportedException {

        List<AvailableNode> availableNodes = node.getAvailableNodes();

        HashMap<AvailableNode, List<AvailableNode>> availableNodeHashMapLv1 = new HashMap<>();
        HashMap<AvailableNode, List<AvailableNode>> availableNodeHashMapLv2 = new HashMap<>();
        HashMap<AvailableNode, List<AvailableNode>> availableNodeHashMapLv3 = new HashMap<>();

        for (AvailableNode av_1 : availableNodes) {
            availableNodeHashMapLv1.put(av_1,
                    nodeRepository.findById(av_1.getDestination().getId()).get().getAvailableNodes());
        }

        for (AvailableNode av_2 : availableNodes) {
            for (AvailableNode av_3 : availableNodeHashMapLv1.get(av_2)) {
                availableNodeHashMapLv2.put(av_3,
                        nodeRepository.findById(av_3.getDestination().getId()).get().getAvailableNodes());
            }
        }
        log.info("avs " + availableNodes.toString());
        log.info("ansm " + availableNodeHashMapLv1.toString());
        log.info("ansm2 " + availableNodeHashMapLv2.toString());
        for (AvailableNode av_2 : availableNodeHashMapLv2.keySet()) {
            for (AvailableNode av_3 : availableNodeHashMapLv2.get(av_2))
                availableNodeHashMapLv3.put(av_3,
                        nodeRepository.findById(av_3.getDestination().getId()).get().getAvailableNodes());
        }

        RoutingNodes rootNode = new RoutingNodes();
        rootNode.setNode(node);

        for (AvailableNode availableNode : availableNodes) {

            RoutingNodes firstNode = new RoutingNodes();
            extractedCreate(firstNode, rootNode, availableNode, routingNodesList);

            for (AvailableNode availableNode1 : availableNodeHashMapLv1.get(availableNode)) {

                RoutingNodes secondNode = new RoutingNodes();
                extractedCreate(secondNode, firstNode, availableNode1, routingNodesList);

                for (AvailableNode availableNode2 : availableNodeHashMapLv2.get(availableNode1)) {

                    RoutingNodes thirdNode = new RoutingNodes();
                    extractedCreate(thirdNode, secondNode, availableNode2, routingNodesList);

                    for (AvailableNode availableNode3 : availableNodeHashMapLv3.get(availableNode2)) {

                        RoutingNodes fourthNode = new RoutingNodes();

                        extractedCreate(fourthNode, thirdNode, availableNode3, routingNodesList);

                    }
                }
            }
        }
        executeRouteAdd(node, routingNodesList, routes);
    }

    private void extractedCreate(RoutingNodes routingNode,
                                 RoutingNodes prevNodes,
                                 AvailableNode availableNode,
                                 List<RoutingNodes> routingNodesList) throws CloneNotSupportedException {

        routingNode.setNode(availableNode.getDestination());
        routingNode.setPrice(availableNode.getPrice());
        prevNodes.setNextNode(routingNode);
        routingNodesList.add((RoutingNodes) prevNodes.clone());
    }

    private void executeRouteAdd(Node node, List<RoutingNodes> routingNodesList, List<Route> routes) {


        for (RoutingNodes routingNode : routingNodesList) {
            Node dest = null;
            double price = 0.0;
            int hops = 0;
            RoutingNodes routingN = routingNode;
            log.info("route-info "+ routingN);
            while (routingN != null) {
                dest = routingN.getNode();
                price += routingN.getPrice();
                hops += 1;
                log.info("price " + dest.getName() + " " + price);
                routingN = routingN.getNextNode();
            }
            log.info("dhume");
            // creating routes
            Route route = new Route();
            route.setTitle(node.getName() + " " + dest.getName());
            route.setStart(node);
            route.setDestination(dest);
            route.setPrice(price);
            route.setHops(hops);
            route.setRating(0.0);
            route.setRoutingNodes(routingNode);

            Iterable<Route> routeIterable = routeRepository.findRoutesByTitle(route.getTitle());
            if (routeIterable.iterator().hasNext()) {
                routeIterable.forEach(

                        _route -> {

                            if (_route.getPrice() != route.getPrice() &&
                                    _route.getHops() != route.getHops() &&
                                    (!_route.getRoutingNodes().equals(route.getRoutingNodes()))) {

                                routes.add(route);
                            }
                        }
                );
            } else {
                routes.add(route);
            }

        }
        routingNodesRepository.saveAll(routingNodesList);
        routeRepository.saveAll(routes);
    }

}