from diagrams import Diagram, Cluster, Edge
from diagrams.aws.management import Config
from diagrams.aws.migration import ADS
from diagrams.aws.network import APIGateway
from diagrams.azure.database import SQLDatabases, DataLake
from diagrams.azure.web import AppServices, Search
from diagrams.onprem.queue import Kafka

with Diagram("Mikrusek - containers", show=False, filename="./assets/container"):
    apiGateway = APIGateway("Edge service")
    engineService = AppServices("Engine service")
    webBrowser = AppServices("Web client")
    datastore = DataLake("TS Storage")
    commandService = AppServices("Command service")
    kafka = Kafka("Event store")

    queryService = AppServices("Query service")

    searchEngine = Search("Search engine")

    configurationService = Config("Configuration")
    serviceDiscovery = ADS("Service discovery")

    readConfigEdge = Edge(color='yellow')
    serviceDiscoveryEdge = Edge(color='grey')
    standartBiEdge = Edge(color='black', forward=True, reverse=True)
    standartEdge = Edge(color='black', forward=True)

    with Cluster("TS Cluster"):
        processing_cluster = engineService
        processing_cluster - datastore
        queryService - datastore

    apiGateway >> standartBiEdge >> processing_cluster
    apiGateway >> standartEdge >> commandService >> standartBiEdge >> kafka >> standartEdge >> engineService
    commandService >> standartEdge >> engineService

    apiGateway >> standartBiEdge >> queryService
    queryService >> readConfigEdge >> configurationService

    webBrowser >> standartEdge >> apiGateway

    apiGateway >> readConfigEdge >> configurationService
    apiGateway >> serviceDiscoveryEdge >> serviceDiscovery

    engineService >> readConfigEdge >> configurationService
    engineService >> serviceDiscoveryEdge >> serviceDiscovery

    commandService >> readConfigEdge >> configurationService
    commandService >> serviceDiscoveryEdge >> serviceDiscovery

    apiGateway >> standartBiEdge >> searchEngine
    searchEngine >> readConfigEdge >> configurationService
    searchEngine >> serviceDiscoveryEdge >> serviceDiscovery
